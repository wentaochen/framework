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

/**
 * 会员中心管理
 * 
 * @author chenwentao
 * 
 */
@Controller
@RequestMapping("/member/")
public class MemberController {

	@Inject
	private MemberService memberService;

	@RequestMapping(value = { "", "/" })
	public String index(HttpSession session, Model model) throws IOException {

		return Config.VIEWS_SHOP + "member";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpSession session, Member member, Model model)
			throws IOException {
		Member editMember = memberService.get(member.getId());
		editMember.setName(member.getName());
		editMember.setEmail(member.getEmail());
		editMember.setSex(member.getSex());
		editMember.setAddress(member.getAddress());
		memberService.update(editMember);
		session.setAttribute(Config.SESSION_USER, editMember);
		return "ok";
	}
	
	@RequestMapping(value = "edit")
	public String edit(HttpSession session, Model model)
			throws IOException {
		Member member = (Member) session.getAttribute(Config.SESSION_USER);
		System.out.println("id:" + member.getId());
		model.addAttribute("member", member);
		return Config.VIEWS_SHOP + "memberEdit";
	}
}
