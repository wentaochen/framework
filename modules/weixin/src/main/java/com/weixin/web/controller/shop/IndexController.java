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
public class IndexController {

	@Inject
	private MemberService memberService;

	@RequestMapping(value = { "", "/" ,"index"})
	public String index(
			@RequestParam(value = "openid", required = false) String openid,
			HttpSession session, Model model) throws IOException {
		
		// 解决微信跳转出现两个不同的session,是由于配置微信采用代理的方式
		if (openid != null) {
			Member member = memberService.findByOpenid(openid);
			session.setAttribute(Config.SESSION_USER, member);
		}
		return Config.VIEWS_SHOP + "index";
	}

}
