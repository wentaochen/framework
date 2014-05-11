package com.weixin.web.controller.shop;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.shop.MemberService;
import com.weixin.infra.Config;
import com.weixin.infra.Exceptions;

/**
 * 完成从微信跳转到注册页面，特别是binding方法，后面openid就是跟的微信id
 * 
 * @author chenwentao
 * 
 */
@Controller
@RequestMapping
public class BindingController {

	private Logger logger = Logger.getLogger(BindingController.class
			.getSimpleName());

	/**
	 *  
	 */
	private static final String REDIRECT_INDEX_OPENID = "redirect:/?openid=";

	@Inject
	private MemberService memberService;

	/**
	 * 跳转到注册页面
	 * 
	 * @param openid
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/binding/{openid}", produces = "text/html;charset=UTF-8")
	public String bindingForm(@PathVariable("openid") String openid, Model model)
			throws IOException {
		// 如果已经注册采用重定向的方式登陆到首页
		if (memberService.findByOpenid(openid) != null) {
			return REDIRECT_INDEX_OPENID + openid;
		}

		model.addAttribute("openid", openid);

		return Config.VIEWS_SHOP + "register";
	}

	/**
	 * 创建会员信息
	 * 
	 * @param session
	 * @param member
	 * @param model
	 * @return
	 * @throws IOException
	 */
	//produces = "text/html;charset=UTF-8",
	@RequestMapping(value = "/binding",method = RequestMethod.POST)
	@ResponseBody
	public String createMember(HttpSession session, Member member, Model model)
			throws IOException {

		try {
			memberService.save(member);
			session.setAttribute(Config.SESSION_USER, member);
			return "ok";
		} catch (Exception e) {
			logger.error(Exceptions.getStackTraceAsString(e));
			return "fail";
		}
	}
}
