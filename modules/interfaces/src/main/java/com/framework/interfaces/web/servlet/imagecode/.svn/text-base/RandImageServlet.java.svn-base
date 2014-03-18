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
 * 随机生成验证码图片servlet请求
 * 
 * 
 * @version 1.0.0
 * 
 * 修改版本: 1.0.0
 * 修改日期: 2010-8-24
 * 修改说明: 重构代码
 * 复审人 ：
 * </pre>
 */
public class RandImageServlet extends HttpServlet {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -5196057680344093979L;

	/**
	 * 日志对象
	 */
	private static final Log log = LogFactory.getLog(RandImageServlet.class);

	/**
	 * 随机图像生成器
	 */
	private RandomGraphic randomGraphic;

	/**
	 * 放入session中的key
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
		// 设置输出内容为图像，格式为jpeg
		res.setContentType("image/jpeg");

		try {
			HttpSession session = req.getSession();
			// 将内容输出到响应客户端对象的输出流中，生成的图片中包含4个字符
			String v = randomGraphic.drawNumber(RandomGraphic.GRAPHIC_PNG, res
					.getOutputStream());
			// 将字符串的值保留在session中，便于和用户手工输入的验证码比较，比较部分不是本文讨论重点，故略
			session.setAttribute(sessionKey, v);
		}
		catch (Exception ex) {
			log.warn("生成验证码失败", ex);
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
