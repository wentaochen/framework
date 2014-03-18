/*
 * @(#)StaticizeFilter.java 2009-11-19
 * 
 * Copy Right@ 精伦电子股份有限公司
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
 * 页面静态化过滤器
 * 
 * @author wangzh
 * 
 * @version 1.0
 * 
 * 修改版本: 1.0
 * 修改日期: 2009-11-19
 * 修改人 :  wangzh
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
@SuppressWarnings("unchecked")
public class StaticizeFilter implements Filter {

	/**
	 * 页面静态化配置文件
	 */
	protected String pageMappingFile = "/WEB-INF/page-mapping.xml";

	/**
	 * 静态页面存放位置
	 */
	protected String pagesLocation;

	/**
	 * 静态页面存放位置前缀
	 */
	protected String prefix = "/p";

	/**
	 * 默认主机名
	 */
	protected String defaultHost;

	/**
	 * <pre>
	 * http服务器加速回调响应头
	 * nginx:X-Accel-Redirect
	 * apache/lighttpd:X-Sendfile
	 * </pre>
	 */
	protected String xSendFileKey;

	/**
	 * 页面静态化映射
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
					throw new ServletException("静态化页面存放位置没有配置");
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
			// 不需要静态化的页面
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
				// 文件已存在,加共享锁,防止读取脏数据,同步块要尽可能的小，提高并发性
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
					// 关闭共享锁文件流
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
				// 清除不合法静态文件
				if (!file.isFile() || file.delete()) {
					file.getParentFile().mkdirs();
					file.createNewFile();
					// 文件不存在加排他锁,防止重复写入,同步块要尽可能的小，提高并发性
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
					// 关闭排他锁文件流
					try {
						out.close();
					}
					catch (Exception ex) {
					}
				}
			}
		}

		if (fileLocked) {
			// 静态页面正在写入,跳过静态化
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
				// 静态页面合法,直接跳转过去
				StringBuilder sb = new StringBuilder();
				sb.append(prefix);
				sb.append(filePath);

				if ("web".equals(xSendFileKey)) {
					// 直接使用web容器返回
					RequestDispatcher dispatcher = request
							.getRequestDispatcher(sb.toString());

					dispatcher.forward(request, response);
				}
				else {
					// 使用http服务器进行加速
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
			// 静态页面不存在或者已过期
			StaticizeResponse res = null;

			try {
				res = new StaticizeResponse(response, out, file);
				if (expired != Long.MAX_VALUE) {
					expired = expired < 0 ? 0 : expired / 1000;
					response.setHeader("cache-control", "max-age=" + expired);
				}
				// md5还没生成先写入空值的md5值,下次请求返回正确的md5值
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
	 * 查找文件的etag值
	 * 
	 * @param etagFile
	 *            存放etag的文件
	 * @return etag值
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
	 * 保存etag值
	 * 
	 * @param etagFile
	 *            存放etag的文件
	 * @param etag
	 *            etag值
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
	 * 找出静态页面存放位置
	 * 
	 * @param request
	 *            http请求
	 * @param p
	 *            封装静态页面对象
	 * @return 静态页面地址
	 */
	protected String findStaticPage(HttpServletRequest request, Page p) {
		String filePath = null;

		if (p.paramNames == null || p.paramNames.isEmpty()) {
			// 页面存放地址不存在参数
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
		 * 静态页面存放位置
		 */
		public String path;

		/**
		 * cron表达式
		 */
		public CronExpression cronExpression;

		/**
		 * 页面更新时间间隔,默认为永久有效
		 */
		public long refreshInterval;

		/**
		 * 描述
		 */
		public String description;

		/**
		 * path中出现的参数名
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
