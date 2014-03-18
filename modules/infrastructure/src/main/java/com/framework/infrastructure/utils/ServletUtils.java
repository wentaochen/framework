package com.framework.infrastructure.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.Validate;

/**
 * Http��Servlet������.
 * 
 * @author calvin
 */
public class ServletUtils {

	// -- Content Type ���� --//
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String TEXT_TYPE = "text/plain";

	//-- Header ���� --//
	public static final String AUTHENTICATION_HEADER = "Authorization";

	//-- ������ֵ���� --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	private ServletUtils() {
	}

	/**
	 * ���ÿͻ��˻������ʱ�� ��Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		//Http 1.0 header, set a fix expires date.
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000);
		//Http 1.1 header, set a time after now.
		response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
	}

	/**
	 * ���ý�ֹ�ͻ��˻����Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		//Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		//Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	/**
	 * ����LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * ����Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * ���������If-Modified-Since Header, �����ļ��Ƿ��ѱ��޸�.
	 * 
	 * ������޸�, checkIfModify����false ,����304 not modify status.
	 * 
	 * @param lastModified
	 *            ���ݵ�����޸�ʱ��.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request, HttpServletResponse response,
			long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * ��������� If-None-Match Header, ����Etag�Ƿ�����Ч.
	 * 
	 * ���Etag��Ч, checkIfNoneMatch����false, ����304 not modify status.
	 * 
	 * @param etag
	 *            ���ݵ�ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request, HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * ������������������ضԻ����Header.
	 * 
	 * @param fileName
	 *            ���غ���ļ���.
	 */
	public static void setFileDownloadHeader(HttpServletResponse response, String fileName) {
		try {
			// �����ļ���֧��
			String encodedfileName = new String(fileName.getBytes(), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * ȡ�ô���ͬǰ׺��Request Parameters.
	 * 
	 * ���صĽ����Parameter����ȥ��ǰ׺.
	 */
	public static Map getParametersStartingWith(ServletRequest request, String prefix) {
		Validate.notNull(request, "Request must not be null");
		Enumeration paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * ��Http Basic��֤�� Header���б���.
	 */
	public static String encodeHttpBasic(String userName, String password) {
		String encode = userName + ":" + password;
		return "Basic " + EncodeUtils.base64Encode(encode.getBytes());
	}

	/**
	 * �ҳ��û����ʵĵ�ַ
	 * 
	 * @param request
	 *            ����
	 * @param response
	 *            ��Ӧ
	 * @throws ServletException
	 */
	@SuppressWarnings("unchecked")
	public static String findLastPage(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		StringBuilder lastPage = new StringBuilder();
		Map<String, Object> params = request.getParameterMap();

		lastPage.append(request.getContextPath());
		lastPage.append(request.getServletPath());

		if (params != null) {
			Entry<String, Object> entry = null;
			String key = null;
			Object value = null;
			Iterator<Entry<String, Object>> it = params.entrySet().iterator();
			String separator = "?";
			String charset = request.getCharacterEncoding();

			if (charset == null) {
				charset = "utf-8";
			}

			try {
				while (it.hasNext()) {
					entry = it.next();
					key = entry.getKey();
					value = entry.getValue();

					if (key == null || value == null) {
						continue;
					}

					if (value.getClass().isArray()) {
						int length = Array.getLength(value);

						if (length <= 0) {
							continue;
						}

						Object v = null;

						for (int i = 0; i < length; i++) {
							v = Array.get(value, i);

							if (v == null) {
								continue;
							}

							lastPage.append(separator);
							lastPage.append(key);
							lastPage.append("=");
							lastPage.append(URLEncoder.encode(v.toString(), charset));
							separator = "&";
						}
					}
				}
			} catch (Exception ex) {
				throw new ServletException(ex);
			}
		}

		return lastPage.toString();
	}
}
