/*
 * @(#)FileServletOutputStream.java May 2, 2009
 * 
 * Copy Right@ ���׵��ӹɷ����޹�˾
 */

package com.framework.interfaces.web.staticize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletOutputStream;

import org.apache.commons.codec.binary.Hex;

/**
 * <pre>
 * FileOutputStream/ServletOutputStream��,ʵ�ֱ�дservlet����д�ļ�
 * 
 * @author wangzh
 * 
 * @version 1.0
 * 
 * �޸İ汾: 1.0
 * �޸�����: May 2, 2009
 * �޸��� :  wangzh
 * �޸�˵��: �������
 * ������ ��
 * </pre>
 */
public class FileServletOutputStream extends ServletOutputStream {

	/**
	 * ԭʼServletOutputStream
	 */
	protected ServletOutputStream servletStream;

	/**
	 * �ļ���
	 */
	protected FileOutputStream fileStream = null;

	/**
	 * ʹ����ϢժҪ����etagֵ
	 */
	protected MessageDigest digest;

	/**
	 * ʹ��md5ֵ��Ϊetag
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
	 * ��ȡ�ļ�����md5ֵ
	 * 
	 * @return md5ֵ
	 */
	public String getMd5() {
		return md5;
	}
}
