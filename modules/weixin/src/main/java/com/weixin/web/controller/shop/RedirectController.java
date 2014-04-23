package com.weixin.web.controller.shop;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.shop.MemberService;
import com.weixin.infra.Config;

@Controller
@RequestMapping
public class RedirectController {

	@Inject
	private MemberService memberService;

	/**
	 * 直接根据openid登录存在安全隐患，可能会导致系统出错
	 */
	@RequestMapping(value = { "redirect" })
	public String redirect(
			@RequestParam(value = "forward", required = false) String forward,
			@RequestParam(value = "openid", required = false) String openid,
			HttpSession session, Model model) throws IOException {
		if (forward == null || openid == null) {
			return Config.REDIRECT_INDEX;
		}
		
		session.setAttribute(Config.OPENID, openid);
		System.out.println(session.getId());

		Member member = memberService.findByOpenid(openid);
		session.setAttribute(Config.SESSION_USER, member);
		return Config.REDIRECT_INDEX + forward;
	}
}
