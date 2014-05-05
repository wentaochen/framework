package com.weixin.web.controller.shop;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weixin.infra.Config;

/**
 * 负责跳转联系我们、反馈我们
 * 
 * @author chenwentao
 * 
 */
@Controller
@RequestMapping
public class ContactController {

	private Logger logger = Logger.getLogger(ContactController.class
			.getSimpleName());

	/**
	 * 跳装到联系我们页面
	 * 
	 * @param openid
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/contactus", method = RequestMethod.GET)
	public String toContractus(Model model) {

		return Config.VIEWS_SHOP + "contactus";
	}

	/**
	 * 跳装到联系我们页面
	 * 
	 * @param openid
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/contactfeedback", method = RequestMethod.GET)
	public String toContractFeedback(Model model) {

		return Config.VIEWS_SHOP + "contactFeedback";
	}

}
