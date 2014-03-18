package com.framework.infrastructure.utils;

/**
 * <pre>
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Apr 22, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public abstract class HtmlUtils {

	/**
	 * html特殊字符转译
	 * 
	 * @param paramStr
	 * @return
	 */
	public static String htmlspecialchars(String paramStr) {
		String resultStr = paramStr;
		resultStr = resultStr.replaceAll("&", "&amp;");
		resultStr = resultStr.replaceAll("<", "&lt;");
		resultStr = resultStr.replaceAll(">", "&gt;");
		resultStr = resultStr.replaceAll("\"", "&quot;");

		return resultStr;
	}
}
