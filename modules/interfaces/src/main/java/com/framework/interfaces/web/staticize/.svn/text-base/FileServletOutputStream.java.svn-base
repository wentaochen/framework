/*
 * @(#)FileServletOutputStream.java May 2, 2009
 * 
 * Copy Right@ 精伦电子股份有限公司
 */

package com.framework.interfaces.web.staticize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletOutputStream;

import org.apache.commons.codec.binary.Hex;

/**
 * <pre>
 * FileOutputStream/ServletOutputStream流,实现边写servlet流边写文件
 * 
 * @author wangzh
 * 
 * @version 1.0
 * 
 * 修改版本: 1.0
 * 修改日期: May 2, 2009
 * 修改人 :  wangzh
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class FileServletOutputStream extends ServletOutputStream {

	/**
	 * 原始ServletOutputStream
	 */
	protected ServletOutputStream servletStream;

	/**
	 * 文件流
	 */
	protected FileOutputStream fileStream = null;

	/**
	 * 使用消息摘要生成etag值
	 */
	protected MessageDigest digest;

	/**
	 * 使用md5值作为etag
	 */
	protected String md5 = null;

	public FileServletOutputStream(ServletOutputStream servletStream,
			FileOutputStream fileStream) {
		this.servletStream = servletStream;
		this.fileStream = fileStream;
		try {
			this.digest = MessageDigest.getInstance("md5");
		}
		catch (Exception ex) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		try {
			servletStream.write(b, off, len);
		}
		catch (Exception ex) {
		}

		try {
			fileStream.write(b, off, len);
		}
		catch (Exception ex) {
		}
		try {
			digest.update(b, off, len);
		}
		catch (Exception ex) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(byte[])
	 */
	@Override
	public void write(byte[] b) throws IOException {
		try {
			servletStream.write(b);
		}
		catch (Exception ex) {
		}

		try {
			fileStream.write(b);
		}
		catch (Exception ex) {
		}
		try {
			digest.update(b);
		}
		catch (Exception ex) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int b) {
		try {
			servletStream.write(b);
		}
		catch (Exception ex) {
		}

		try {
			fileStream.write(b);
		}
		catch (Exception ex) {
		}
		try {
			digest.update(new byte[] { (byte) b });
		}
		catch (Exception ex) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#close()
	 */
	@Override
	public void close() {
		try {
			servletStream.close();
		}
		catch (Exception ex) {
		}

		try {
			fileStream.close();
		}
		catch (Exception ex) {
		}
		if (md5 == null && digest != null) {
			md5 = new String(Hex.encodeHex(digest.digest()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#flush()
	 */
	@Override
	public void flush() {
		try {
			servletStream.flush();
		}
		catch (Exception ex) {
		}
		try {
			fileStream.flush();
		}
		catch (Exception ex) {
		}
		if (md5 == null && digest != null) {
			md5 = new String(Hex.encodeHex(digest.digest()));
		}
	}

	/**
	 * 获取文件流的md5值
	 * 
	 * @return md5值
	 */
	public String getMd5() {
		return md5;
	}
}
