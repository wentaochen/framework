package com.weixin.web.controller.weixin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.service.common.ExceptionService;

@Controller
@RequestMapping
public class ExceptionControllerTest {

	@Inject
	private ExceptionService exceptionService;

	/**
	 * 产品加入购物车
	 * 
	 * @param id
	 *            产品主键
	 */
	@RequestMapping(value = "/test")
	@ResponseBody
	public String testException(HttpSession session, Model model)
			throws IOException {
		exceptionService.doSome("123");

		int a = 1;
		int b = 1;
		if (a == b) {
			throw new RuntimeException("java Exceitp 捕获测试");
		}
		return "ok";
	}

	@RequestMapping(value = "/test1")
	@ResponseBody
	public String testException1(HttpSession session, Model model)
			throws IOException {

		int a = 1;
		int b = 1;
		if (a == b) {
			throw new RuntimeException("java Exceitp 捕获测试");
		}
		return "ok";
	}
}
