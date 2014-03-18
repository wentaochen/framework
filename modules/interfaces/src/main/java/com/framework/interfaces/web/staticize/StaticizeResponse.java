/*
 * @(#)StaticizeResponse.java 2009-11-19
 * 
 * Copy Right@ 精伦电子股份有限公司
 */

package com.framework.interfaces.web.staticize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * <pre>
 * 页面静态化响应
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
public class StaticizeResponse extends HttpServletResponseWrapper {

	/**
	 * servlet流
	 */
	protected ServletOutputStream servletStream = null;

	/**
	 * PrintWriter
	 */
	protected PrintWriter writer = null;

	/**
	 * 文件流
	 */
	protected FileOutputStream fileStream = null;

	/**
	 * 静态化文件
	 */
	protected File file;

	public StaticizeResponse(HttpServletResponse response) {
		super(response);
	}

	public StaticizeResponse(HttpServletResponse response,
			FileOutputStream fileStream, File file) {
		super(response);
		this.fileStream = fileStream;
		this.file = file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponseWrapper#sendError(int,
	 *      java.lang.String)
	 */
	@Override
	public void sendError(int sc, String msg) throws IOException {
		super.sendError(sc, msg);
		setHeader("nodata", "true");
		setHeader("pragma", "no-cache");
		setHeader("cache-control", "no-cache");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServletResponseWrapper#sendError(int)
	 */
	@Override
	public void sendError(int sc) throws IOException {
		super.sendError(sc);
		setHeader("nodata", "true");
		setHeader("pragma", "no-cache");
		setHeader("cache-control", "no-cache");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponseWrapper#getOutputStream()
	 */
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (servletStream != null) {
			return servletStream;
		}

		if (fileStream != null && !this.containsHeader("nodata")) {
			servletStream = new FileServletOutputStream(
					super.getOutputStream(), fileStream);
		}
		else {
			try {
				fileStream.close();
			}
			catch (Exception ex) {
			}
			try {
				file.delete();
			}
			catch (Exception ex) {
			}
			servletStream = super.getOutputStream();
		}

		return servletStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponseWrapper#getWriter()
	 */
	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer != null) {
			return writer;
		}

		String charsetName = getCharacterEncoding();

		if (charsetName == null) {
			charsetName = "utf-8";
		}

		writer = new PrintWriter(new OutputStreamWriter(getOutputStream(),
				charsetName));

		return writer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletResponseWrapper#flushBuffer()
	 */
	@Override
	public void flushBuffer() throws IOException {
		if (writer != null) {
			writer.flush();
		}
		else if (servletStream != null) {
			servletStream.flush();
		}
	}

	/**
	 * 关闭流
	 */
	public void close() throws IOException {
		if (writer != null) {
			writer.flush();
			writer.close();
		}
		else if (servletStream != null) {
			servletStream.flush();
			servletStream.close();
		}
		else if (fileStream != null) {
			try {
				fileStream.close();
			}
			catch (Exception ex) {
			}
			try {
				file.delete();
			}
			catch (Exception ex) {
			}
		}
	}

	/**
	 * 获取文件流的md5值
	 * 
	 * @return md5值
	 */
	public String getMd5() {
		if (servletStream instanceof FileServletOutputStream) {
			FileServletOutputStream stream = (FileServletOutputStream) servletStream;

			return stream.getMd5();
		}

		return null;
	}
}
