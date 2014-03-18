package com.framework.interfaces.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.framework.infrastructure.utils.ServletUtils;

/**
 * ���ؾ�̬����չʾ�����ص�Servlet.
 * 
 * ��ʾ�ļ���Ч��ȡ,�ͻ��˻�����Ƽ�Gzipѹ������.
 * ��ʹ��org.springside.examples.showcase.cache���µ�Ehcache�򱾵�Map���澲̬���ݻ�����Ϣ(δ��ʾ).
 *  
 * ��ʾ���ʵ�ַΪ��
 * static-content?contentPath=static/img/logo.jpg
 * static-content?contentPath=static/img/logo.jpg&download=true
 * 
 * @author calvin
 */
public class StaticContentServlet extends HttpServlet {

	private static final long serialVersionUID = -1855617048198368534L;

	/** ��Ҫ��Gzipѹ����Mime����. */
	private static final String[] GZIP_MIME_TYPES = { "text/html", "text/xml", "text/plain", "text/css",
			"text/javascript", "application/xml", "application/xhtml+xml", "application/x-javascript" };

	/** ��Ҫ��Gzipѹ������С�ļ���С. */
	private static final int GZIP_MINI_LENGTH = 512;

	private MimetypesFileTypeMap mimetypesFileTypeMap;

	private ApplicationContext applicationContext;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ȡ�ò���
		String contentPath = request.getParameter("contentPath");
		if (StringUtils.isBlank(contentPath)) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "contentPath parameter is required.");
			return;
		}

		//��ȡ�������ݵĻ�����Ϣ.
		ContentInfo contentInfo = getContentInfo(contentPath);

		//����Etag��ModifiedSince Header�жϿͻ��˵Ļ����ļ��Ƿ���Ч, ������Ч�����÷�����Ϊ304,ֱ�ӷ���.
		if (!ServletUtils.checkIfModifiedSince(request, response, contentInfo.lastModified)
				|| !ServletUtils.checkIfNoneMatchEtag(request, response, contentInfo.etag)) {
			return;
		}

		//����Etag/����ʱ��
		ServletUtils.setExpiresHeader(response, ServletUtils.ONE_YEAR_SECONDS);
		ServletUtils.setLastModifiedHeader(response, contentInfo.lastModified);
		ServletUtils.setEtag(response, contentInfo.etag);

		//����MIME����
		response.setContentType(contentInfo.mimeType);

		//���õ��������ļ����󴰿ڵ�Header
		if (request.getParameter("download") != null) {
			ServletUtils.setFileDownloadHeader(response, contentInfo.fileName);
		}

		//����OutputStream
		OutputStream output;
		if (checkAccetptGzip(request) && contentInfo.needGzip) {
			//ʹ��ѹ�������outputstream, ʹ��http1.1 trunked���벻����content-length.
			output = buildGzipOutputStream(response);
		} else {
			//ʹ����ͨoutputstream, ����content-length.
			response.setContentLength(contentInfo.length);
			output = response.getOutputStream();
		}

		//��Ч��ȡ�ļ����ݲ����,Ȼ��ر�input file
		org.apache.commons.io.FileUtils.copyFile(contentInfo.file, output);
		output.flush();
	}

	/**
	 * ���������ͻ����Ƿ�֧��gzip����.
	 */
	private static boolean checkAccetptGzip(HttpServletRequest request) {
		//Http1.1 header
		String acceptEncoding = request.getHeader("Accept-Encoding");

		return StringUtils.contains(acceptEncoding, "gzip");
	}

	/**
	 * ����Gzip Header������GZIPOutputStream.
	 */
	private OutputStream buildGzipOutputStream(HttpServletResponse response) throws IOException {
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Vary", "Accept-Encoding");
		return new GZIPOutputStream(response.getOutputStream());
	}

	/**
	 * ��ʼ��.
	 */
	@Override
	public void init() throws ServletException {
		//����applicationContext�Ա����ã�����ʾ.
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

		//��ʼ��mimeTypes, Ĭ��ȱ��css�Ķ���,���֮.
		mimetypesFileTypeMap = new MimetypesFileTypeMap();
		mimetypesFileTypeMap.addMimeTypes("text/css css");
	}

	/**
	 * ����Content������Ϣ.
	 */
	private ContentInfo getContentInfo(String contentPath) {
		ContentInfo contentInfo = new ContentInfo();

		String realFilePath = getServletContext().getRealPath(contentPath);
		File file = new File(realFilePath);

		contentInfo.file = file;
		contentInfo.contentPath = contentPath;
		contentInfo.fileName = file.getName();
		contentInfo.length = (int) file.length();

		contentInfo.lastModified = file.lastModified();
		contentInfo.etag = "W/\"" + contentInfo.lastModified + "\"";

		contentInfo.mimeType = mimetypesFileTypeMap.getContentType(contentInfo.fileName);

		if (contentInfo.length >= GZIP_MINI_LENGTH && ArrayUtils.contains(GZIP_MIME_TYPES, contentInfo.mimeType)) {
			contentInfo.needGzip = true;
		} else {
			contentInfo.needGzip = false;
		}

		return contentInfo;
	}

	/**
	 * ����Content�Ļ�����Ϣ.
	 */
	static class ContentInfo {
		protected String contentPath;
		protected File file;
		protected String fileName;
		protected int length;
		protected String mimeType;
		protected long lastModified;
		protected String etag;
		protected boolean needGzip;
	}
}
