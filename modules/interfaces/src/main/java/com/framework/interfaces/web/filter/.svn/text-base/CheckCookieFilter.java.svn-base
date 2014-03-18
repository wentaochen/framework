package com.framework.interfaces.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * <pre>
 * 注意:CheckCookieFilter在web.xml中的位置,必须在权限filter的前面
 * 
 * 
 * 验证cookie的用户名密码，生成session
 * 
 * @author chenwentao
 *
 * @version 0.9
 *
 * 修改版本: 0.9
 * 修改日期: Apr 18, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
public class CheckCookieFilter extends OncePerRequestFilter {

	public static final String USERNAME = "username";

	public static final String PASSWORD = "password";

	private static final Map<String, String> EMPTY_COOKIE = new HashMap<String, String>();

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute(USERNAME);

		if (StringUtils.isBlank(username)) {
			// 用户未登录网站,检查cookie
			Map<String, String> mapCookie = cookieArrayToMap(request
					.getCookies());
			String cookieName = mapCookie.get(USERNAME);
			String cookiePassword = mapCookie.get(PASSWORD);
			validateCookieUser(cookieName, cookiePassword);
		}

		filterChain.doFilter(request, response);
	}

	protected void validateCookieUser(String username, String password) {

	}

	private Map<String, String> cookieArrayToMap(Cookie[] cookies) {
		if (cookies == null) {
			return EMPTY_COOKIE;
		}

		Map<String, String> mapCookie = new HashMap<String, String>();
		for (Cookie cookie : cookies) {
			mapCookie.put(cookie.getName(), cookie.getValue());
		}
		return mapCookie;
	}
}
