package com.weixin.web.controller.shop;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.shop.MemberService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Inject
	private MemberService memberService;

	@RequestMapping(value = { "", "/" })
	public String index(HttpSession session, Model model) throws IOException {

		return Config.VIEWS_SHOP + "member";
	}

	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session, Member member, Model model)
			throws IOException {
		memberService.update(member);
		session.setAttribute(Config.SESSION_USER, member);
		return "ok";
	}
}
