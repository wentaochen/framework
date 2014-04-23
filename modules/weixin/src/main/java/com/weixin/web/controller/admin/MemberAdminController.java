package com.weixin.web.controller.admin;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.admin.MemberAdminService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/admin/member/")
public class MemberAdminController {

	@Inject
	private MemberAdminService memberAdminService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Member> members = memberAdminService.findAll();
		model.addAttribute("members", members);
		return Config.VIEWS_ADMIN + "memberList";
	}

}
