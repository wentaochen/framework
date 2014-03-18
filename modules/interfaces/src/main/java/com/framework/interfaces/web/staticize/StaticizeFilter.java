/*
 * @(#)StaticizeFilter.java 2009-11-19
 * 
 * Copy Right@ ���׵��ӹɷ����޹�˾
 */

package com.framework.interfaces.web.staticize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.quartz.CronExpression;

/**
 * <pre>
 * ҳ�澲̬��������
 * 
 * @author wangzh
 * 
 * @version 1.0
 * 
 * �޸İ汾: 1.0
 * �޸�����: 2009-11-19
 * �޸��� :  wangzh
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
@SuppressWarnings("unchecked")
public class StaticizeFilter implements Filter {

	/**
	 * ҳ�澲̬�������ļ�
	 */
	protected String pageMappingFile = "/WEB-INF/page-mapping.xml";

	/**
	 * ��̬ҳ����λ��
	 */
	protected String pagesLocation;

	/**
	 * ��̬ҳ����λ��ǰ׺
	 */
	protected String prefix = "/p";

	/**
	 * Ĭ��������
	 */
	protected String defaultHost;

	/**
	 * <pre>
	 * http���������ٻص���Ӧͷ
	 * nginx:X-Accel-Redirect
	 * apache/lighttpd:X-Sendfile
	 * </pre>
	 */
	protected String xSendFileKey;

	/**
	 * ҳ�澲̬��ӳ��
	 */
	protected Map<String, Page> pageMappings = new LinkedHashMap<String, Page>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		FileInputStream in = null;

		try {
			xSendFileKey = filterConfig.getInitParameter("xSendFileKey");
			if (StringUtils.isBlank(xSendFileKey)) {
				xSendFileKey = "X-Accel-Redirect";
			}
			if ("web".equals(xSendFileKey)) {
				pagesLocation = filterConfig.getServletContext().getRealPath(
						"/");
				pagesLocation = pagesLocation.replace(File.separatorChar, '/');
				prefix = filterConfig.getInitParameter("pagesLocation");
				if (StringUtils.isBlank(prefix)) {
					prefix = "/p";
					pagesLocation += "p";
				}
				else {
					if (prefix.startsWith("/")) {
						pagesLocation += prefix.substring(1);
					}
					else {
						pagesLocation += prefix;
						prefix = "/" + prefix;
					}
				}
			}
			else {
				pagesLocation = filterConfig.getInitParameter("pagesLocation");
				if (StringUtils.isBlank(pagesLocation)) {
					throw new ServletException("��̬��ҳ����λ��û������");
				}
				if (pagesLocation.endsWith("/")) {
					pagesLocation = pagesLocation.substring(0, pagesLocation
							.length() - 1);
				}
				int idx = pagesLocation.lastIndexOf('/');
				if (idx != -1) {
					prefix = pagesLocation.substring(idx);
				}
			}

			defaultHost = filterConfig.getInitParameter("defaultHost");
			if (StringUtils.isBlank(defaultHost)) {
				defaultHost = "localhost";
			}

			in = new FileInputStream(filterConfig.getServletContext()
					.getRealPath(pageMappingFile));
			SAXReader reader = new SAXReader();
			reader.setValidation(false);
			Document doc = reader.read(in);
			Element root = doc.getRootElement();

			if (root != null) {
				Element element = null;
				Page page = null;
				int beginIndex = 0;
				int endIndex = 0;
				String paramName = null;
				Iterator<Element> it = root.elementIterator();

				while (it.hasNext()) {
					beginIndex = 0;
					endIndex = 0;
					page = new Page();
					element = it.next();
					page.url = element.elementTextTrim("url");
					page.path = element.elementTextTrim("path");
					page.paramNames = new LinkedList<String>();

					while (true) {
						beginIndex = page.path.indexOf("${", beginIndex);

						if (beginIndex < 0) {
							break;
						}

						endIndex = page.path.indexOf("}", beginIndex);

						if (endIndex < 0) {
							break;
						}

						paramName = page.path.substring(beginIndex + 2,
								endIndex);
						page.paramNames.add(paramName);
						page.path = page.path.replace("${" + paramName + "}",
								"%" + page.paramNames.size() + "$s");
					}

					try {
						page.cronExpression = new CronExpression(element
								.elementTextTrim("cronExpression"));
					}
					catch (Exception ex) {
					}
					try {
						page.refreshInterval = Long.parseLong(element
								.elementTextTrim("refreshInterval")) * 1000;
					}
					catch (Exception ex) {
						page.refreshInterval = Long.MAX_VALUE;
					}

					page.description = element.elementTextTrim("description");
					pageMappings.put(page.url, page);
				}

			}
		}
		catch (Exception ex) {
			throw new ServletException(ex);
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception ex) {
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		if (!(req instanceof HttpServletRequest)
				|| !(resp instanceof HttpServletResponse)) {
			filterChain.doFilter(req, resp);

			return;
		}

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String servletPath = request.getServletPath();
		Page p = pageMappings.get(servletPath);

		if (p == null) {
			// ����Ҫ��̬����ҳ��
			filterChain.doFilter(request, response);
			return;
		}

		String filePath = findStaticPage(request, p);
		File file = new File(pagesLocation, filePath);
		File etagFile = new File(pagesLocation, filePath + ".etag");
		FileOutputStream out = null;
		boolean fileLocked = true;
		String etagServer = findEtag(etagFile);
		String etagClient = request.getHeader("If-None-Match");
		boolean fileValid = file.isFile() && file.length() > 0
				&& etagServer != null;
		long expired = 0;
		if (p.cronExpression != null) {
			expired = p.cronExpression.getNextValidTimeAfter(
					new Date(file.lastModified())).getTime()
					- System.currentTimeMillis();
		}
		else {
			if (p.refreshInterval == Long.MAX_VALUE) {
				expired = Long.MAX_VALUE;
			}
			else {
				expired = System.currentTimeMillis() - file.lastModified();
				expired = expired > 0 ? p.refreshInterval - expired
						: p.refreshInterval;
			}
		}
		fileValid = fileValid && expired > 0;

		if (fileValid) {
			try {
				// �ļ��Ѵ���,�ӹ�����,��ֹ��ȡ������,ͬ����Ҫ�����ܵ�С����߲�����
				out = new FileOutputStream(file, true);
				FileChannel fc = out.getChannel();
				FileLock fileLock = fc.tryLock(0, Long.MAX_VALUE, false);
				fileLocked = (fileLock == null || !fileLock.isValid());
			}
			catch (Exception ex) {
				fileLocked = true;
			}
			finally {
				if (out != null) {
					// �رչ������ļ���
					try {
						out.close();
					}
					catch (Exception ex) {
					}
				}
			}
		}
		else {
			try {
				// ������Ϸ���̬�ļ�
				if (!file.isFile() || file.delete()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
					// �ļ������ڼ�������,��ֹ�ظ�д��,ͬ����Ҫ�����ܵ�С����߲�����
					out = new FileOutputStream(file);
					try {
						FileChannel fc = out.getChannel();
						FileLock fileLock = fc.tryLock();
						fileLocked = (fileLock == null || !fileLock.isValid());
					}
					catch (Exception ex) {
						fileLocked = true;
					}
					// double check lock
					etagServer = findEtag(etagFile);
					fileValid = file.isFile() && file.length() > 0
							&& etagServer != null;
					if (p.cronExpression != null) {
						expired = p.cronExpression.getNextValidTimeAfter(
								new Date(file.lastModified())).getTime()
								- System.currentTimeMillis();
					}
					else {
						if (p.refreshInterval == Long.MAX_VALUE) {
							expired = Long.MAX_VALUE;
						}
						else {
							expired = System.currentTimeMillis()
									- file.lastModified();
							expired = expired > 0 ? p.refreshInterval - expired
									: p.refreshInterval;
						}
					}
					fileValid = fileValid && expired > 0;
				}
				else {
					fileLocked = true;
				}
			}
			catch (Exception ex) {
				fileLocked = true;
			}
			finally {
				if (fileLocked && out != null) {
					// �ر��������ļ���
					try {
						out.close();
					}
					catch (Exception ex) {
					}
				}
			}
		}

		if (fileLocked) {
			// ��̬ҳ������д��,������̬��
			if (expired != Long.MAX_VALUE) {
				expired = expired < 0 ? 0 : expired / 1000;
				response.setHeader("cache-control", "max-age=" + expired);
			}
			filterChain.doFilter(request, response);
			return;
		}

		if (fileValid) {
			response.setHeader("etag", etagServer);
			if (etagServer.equalsIgnoreCase(etagClient)) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			}
			else {
				// ��̬ҳ��Ϸ�,ֱ����ת��ȥ
				StringBuilder sb = new StringBuilder();
				sb.append(prefix);
				sb.append(filePath);

				if ("web".equals(xSendFileKey)) {
					// ֱ��ʹ��web��������
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(sb.toString());

					dispatcher.forward(request, response);
				}
				else {
					// ʹ��http���������м���
					response.setHeader(xSendFileKey, sb.toString());
					if (expired != Long.MAX_VALUE) {
						expired = expired < 0 ? 0 : expired / 1000;
						response.setHeader("cache-control", "max-age="
								+ expired);
					}
				}
			}
		}
		else {
			// ��̬ҳ�治���ڻ����ѹ���
			StaticizeResponse res = null;

			try {
				res = new StaticizeResponse(response, out, file);
				if (expired != Long.MAX_VALUE) {
					expired = expired < 0 ? 0 : expired / 1000;
					response.setHeader("cache-control", "max-age=" + expired);
				}
				// md5��û������д���ֵ��md5ֵ,�´����󷵻���ȷ��md5ֵ
				response.setHeader("etag", "d41d8cd98f00b204e9800998ecf8427e");
				filterChain.doFilter(request, res);
			}
			finally {
				if (res != null) {
					try {
						res.flushBuffer();
						res.close();
					}
					catch (Exception ex) {
					}
					etagServer = res.getMd5();
					saveEtag(etagFile, etagServer);
				}
			}
		}

	}

	/**
	 * �����ļ���etagֵ
	 * 
	 * @param etagFile
	 *            ���etag���ļ�
	 * @return etagֵ
	 */
	protected String findEtag(File etagFile) {
		FileInputStream in = null;

		try {
			in = new FileInputStream(etagFile);
			byte[] bs = new byte[in.available()];
			int len = in.read(bs);

			if (len > 0) {
				return new String(bs, "utf-8");
			}
		}
		catch (Exception ex) {
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception ex) {
				}
			}
		}

		return null;
	}

	/**
	 * ����etagֵ
	 * 
	 * @param etagFile
	 *            ���etag���ļ�
	 * @param etag
	 *            etagֵ
	 */
	protected void saveEtag(File etagFile, String etag) {
		if (StringUtils.isBlank(etag)) {
			return;
		}

		FileOutputStream out = null;

		try {
			out = new FileOutputStream(etagFile);
			out.write(etag.getBytes("utf-8"));
		}
		catch (Exception ex) {
		}
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch (Exception ex) {
				}
			}
		}
	}

	/**
	 * �ҳ���̬ҳ����λ��
	 * 
	 * @param request
	 *            http����
	 * @param p
	 *            ��װ��̬ҳ�����
	 * @return ��̬ҳ���ַ
	 */
	protected String findStaticPage(HttpServletRequest request, Page p) {
		String filePath = null;

		if (p.paramNames == null || p.paramNames.isEmpty()) {
			// ҳ���ŵ�ַ�����ڲ���
			filePath = p.path;
		}
		else {
			int index = -1;
			String value = null;
			List<String> paramValues = new ArrayList<String>();
			HttpSession session = request.getSession();

			for (String n : p.paramNames) {
				value = null;
				index = n.indexOf('.');

				if (index != -1) {
					String paramType = n.substring(0, index);
					String paramName = n.substring(index + 1);

					if ("param".equals(paramType)) {
						String[] vs = request.getParameterValues(paramName);
						if (vs != null) {
							String separator = "";
							StringBuilder sb = new StringBuilder();
							for (String v : vs) {
								sb.append(separator);
								sb.append(v);
								separator = "_";
							}
							value = sb.toString();
						}
					}
					else if ("request".equals(paramType)) {
						Object obj = request.getAttribute(paramName);
						if (obj != null) {
							value = obj.toString();
						}
					}
					else if ("session".equals(paramType)) {
						Object obj = session.getAttribute(paramName);
						if (obj != null) {
							value = obj.toString();
						}
					}
				}
				else {
					String[] vs = request.getParameterValues(n);
					if (vs != null) {
						String separator = "";
						StringBuilder sb = new StringBuilder();
						for (String v : vs) {
							sb.append(separator);
							sb.append(v);
							separator = "_";
						}
						value = sb.toString();
					}
				}

				paramValues.add(StringUtils.defaultString(value));
			}

			filePath = String.format(p.path, paramValues.toArray());
		}

		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		if (StringUtils.isBlank(serverName)) {
			serverName = defaultHost;
		}
		if (serverPort <= 0) {
			serverPort = 80;
		}
		int idx = filePath.lastIndexOf('.');
		if (idx != -1) {
			filePath = new StringBuilder().append(filePath.substring(0, idx))
					.append("_").append(serverName).append("_").append(
							serverPort).append(filePath.substring(idx))
					.toString();
		}
		else {
			filePath = new StringBuilder().append(filePath).append("_").append(
					serverName).append("_").append(serverPort).toString();
		}

		return filePath;
	}

	static public class Page {

		/**
		 * servletPath
		 */
		public String url;

		/**
		 * ��̬ҳ����λ��
		 */
		public String path;

		/**
		 * cron���ʽ
		 */
		public CronExpression cronExpression;

		/**
		 * ҳ�����ʱ����,Ĭ��Ϊ������Ч
		 */
		public long refreshInterval;

		/**
		 * ����
		 */
		public String description;

		/**
		 * path�г��ֵĲ�����
		 */
		public List<String> paramNames;
	}

	public String getPageMappingFile() {
		return pageMappingFile;
	}

	public void setPageMappingFile(String pageMappingFile) {
		this.pageMappingFile = pageMappingFile;
	}

	public String getPagesLocation() {
		return pagesLocation;
	}

	public void setPagesLocation(String pagesLocation) {
		this.pagesLocation = pagesLocation;
	}
}
