package com.weixin.web.controller.shop;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
