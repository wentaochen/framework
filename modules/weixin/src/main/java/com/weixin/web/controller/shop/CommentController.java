package com.weixin.web.controller.shop;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.Comment;
import com.weixin.domain.model.shop.Member;
import com.weixin.domain.service.shop.CommentService;
import com.weixin.infra.Config;

@Controller
@RequestMapping
public class CommentController {

	@Inject
	private CommentService commentService;

	@RequestMapping(value = { "comments" })
	public String list(Model model) throws IOException {
		return Config.VIEWS_SHOP + "commentList";
	}
	
	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpSession session, Comment comment, Model model) throws IOException {
		Member member = (Member) session.getAttribute(Config.SESSION_USER);
		comment.setMember(member);
		commentService.save(comment);
		session.setAttribute(Config.SESSION_USER, member);
		return "ok";
	}

}
