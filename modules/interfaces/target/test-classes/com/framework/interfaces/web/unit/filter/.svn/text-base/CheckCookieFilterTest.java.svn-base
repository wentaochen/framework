package com.framework.interfaces.web.unit.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;

import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.framework.interfaces.web.filter.CheckCookieFilter;

public class CheckCookieFilterTest {

	/**
	 * 测试有cooike的情况
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void test() throws ServletException, IOException {
		MockFilterChain chain = new MockFilterChain();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		// 生成cookie
		Cookie cName = new Cookie("username", "chenwentao");
		Cookie cPassword = new Cookie("password", "password");
		response.addCookie(cName);
		response.addCookie(cPassword);

		CheckCookieFilter checkCookieFilter = new CheckCookieFilter();
		checkCookieFilter.doFilter(request, response, chain);
	}

	/**
	 * 测试无cooike的情况
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Test
	public void test_no_cookie() throws ServletException, IOException {
		MockFilterChain chain = new MockFilterChain();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		CheckCookieFilter checkCookieFilter = new CheckCookieFilter();
		checkCookieFilter.doFilter(request, response, chain);
	}

}
