package com.weixin.web.controller.shop;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.shop.MemberService;
import com.weixin.infra.Config;

/**
 * 会员登录控制类
 * 
 * @author chenwentao
 * 
 */
@Controller
@RequestMapping
public class LoginController {

	@Inject
	private MemberService memberService;

	/**
	 * 跳转到登陆页面
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm(Model model) throws IOException {
		return Config.VIEWS_SHOP + "login";
	}

	/**
	 * 登陆操作
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public String login(Member member, HttpSession session, Model model)
			throws IOException {
		Member currentMember = memberService.login(member);
		if (currentMember == null) {
			// return Config.REDIRECT_INDEX + "login";
			return "fail";
		}
		session.setAttribute(Config.SESSION_USER, currentMember);

		// return Config.REDIRECT_INDEX;
		return "ok";
	}

	/**
	 * 登出操作
	 */
	@RequestMapping(value = "loginout")
	public String login(HttpServletRequest request,
			HttpServletResponse response, SessionStatus sessionStatus)
			throws IOException {
		sessionStatus.setComplete();
		request.getSession().invalidate();

		// Cookie[] cookies = request.getCookies();
		// // cookies不为空，则清除
		// if (cookies != null) {
		// for (Cookie oldCookie : cookies) {
		// if (CheckCookieFilter.USER_ID.equals(oldCookie.getName())) {
		// Cookie cookie = new Cookie(CheckCookieFilter.USER_ID, null);
		// cookie.setMaxAge(0);
		// cookie.setPath("/");
		// cookie.setDomain(".wuhan360.net");
		// response.addCookie(cookie);
		// continue;
		// }
		//
		// if (CheckCookieFilter.USER_MOBILE.equals(oldCookie.getName())) {
		// Cookie cookie = new Cookie(CheckCookieFilter.USER_MOBILE,
		// null);
		// cookie.setMaxAge(0);
		// cookie.setPath("/");
		// cookie.setDomain(".wuhan360.net");
		// response.addCookie(cookie);
		// continue;
		// }
		//
		// if (CheckCookieFilter.USER_PASSWORD.equals(oldCookie.getName())) {
		// Cookie cookie = new Cookie(CheckCookieFilter.USER_PASSWORD,
		// null);
		// cookie.setMaxAge(0);
		// cookie.setPath("/");
		// cookie.setDomain(".wuhan360.net");
		// response.addCookie(cookie);
		// continue;
		// }
		// }
		//
		// }
		return Config.REDIRECT_INDEX;
	}

}
