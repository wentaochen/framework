//package com.weixin.web;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//@RequestMapping
//public class DzdpController {
//
//	@RequestMapping(value = "search")
//	public void authwxGet(@RequestParam("la") String la,
//			@RequestParam("lo") String lo,@RequestParam("type") String type,Model model,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		response.setContentType("text/html;charset=UTF-8");
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setCharacterEncoding("UTF-8");
//		
//		String echostr = DemoApiTool.search(la, lo,type);
//		//echostr=echostr.replace("\"", "'");
//		response.getOutputStream().write(echostr.getBytes("UTF-8"));
//	}
//}
