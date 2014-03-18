package com.framework.interfaces.web.servlet.imagecode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <pre>
 * ���������֤��ͼƬservlet����
 * 
 * 
 * @version 1.0.0
 * 
 * �޸İ汾: 1.0.0
 * �޸�����: 2010-8-24
 * �޸�˵��: �ع�����
 * ������ ��
 * </pre>
 */
public class RandImageServlet extends HttpServlet {

	/**
	 * ���л��汾��
	 */
	private static final long serialVersionUID = -5196057680344093979L;

	/**
	 * ��־����
	 */
	private static final Log log = LogFactory.getLog(RandImageServlet.class);

	/**
	 * ���ͼ��������
	 */
	private RandomGraphic randomGraphic;

	/**
	 * ����session�е�key
	 */
	private String sessionKey;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// �����������Ϊͼ�񣬸�ʽΪjpeg
		res.setContentType("image/jpeg");

		try {
			HttpSession session = req.getSession();
			// �������������Ӧ�ͻ��˶����������У����ɵ�ͼƬ�а���4���ַ�
			String v = randomGraphic.drawNumber(RandomGraphic.GRAPHIC_PNG, res
					.getOutputStream());
			// ���ַ�����ֵ������session�У����ں��û��ֹ��������֤��Ƚϣ��Ƚϲ��ֲ��Ǳ��������ص㣬����
			session.setAttribute(sessionKey, v);
		}
		catch (Exception ex) {
			log.warn("������֤��ʧ��", ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		String charCountStr = getInitParameter("charCount");
		String lineCountStr = getInitParameter("lineCount");
		String wordHeightStr = getInitParameter("wordHeight");
		String wordWidthStr = getInitParameter("wordWidth");
		String fontSizeStr = getInitParameter("fontSize");
		String fontName = getInitParameter("fontName");
		sessionKey = getInitParameter("sessionKey");

		int charCount = 4;
		int lineCount = 0;
		int wordHeight = 10;
		int wordWidth = 15;
		int fontSize = 22;

		if (StringUtils.isNotBlank(charCountStr)) {
			charCount = Integer.parseInt(charCountStr.trim());
		}
		if (StringUtils.isNotBlank(lineCountStr)) {
			lineCount = Integer.parseInt(lineCountStr.trim());
		}
		if (StringUtils.isNotBlank(wordHeightStr)) {
			wordHeight = Integer.parseInt(wordHeightStr.trim());
		}
		if (StringUtils.isNotBlank(wordWidthStr)) {
			wordWidth = Integer.parseInt(wordWidthStr.trim());
		}
		if (StringUtils.isNotBlank(fontSizeStr)) {
			fontSize = Integer.parseInt(fontSizeStr.trim());
		}
		if (StringUtils.isBlank(fontName)) {
			fontName = "Verdana";
		}
		if (StringUtils.isBlank(sessionKey)) {
			sessionKey = "rv";
		}
		randomGraphic = RandomGraphic.createInstance(charCount, lineCount,
				wordHeight, wordWidth, fontSize);
	}

}
