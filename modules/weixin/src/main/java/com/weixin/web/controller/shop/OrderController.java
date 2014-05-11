package com.weixin.web.controller.shop;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weixin.domain.model.shop.Cart;
import com.weixin.domain.model.shop.Member;
import com.weixin.domain.model.shop.Order;
import com.weixin.domain.service.shop.OrderService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/order/")
public class OrderController {

	@Inject
	private OrderService orderService;

	/**
	 * 通过点击首页上的查看历史，进入列表页面
	 */
	@RequestMapping(value = "history", method = RequestMethod.GET)
	public String historyList(HttpSession session, Model model)
			throws IOException {
		Member member = (Member) session.getAttribute(Config.SESSION_USER);
		List<Order> orderList =  orderService.findHistory(member);
		model.addAttribute("orderList", orderList);
		return Config.VIEWS_SHOP + "orderHistoryList";
	}

	/**
	 * qingkong
	 */
	@RequestMapping(value = "clear")
	public String clear(HttpSession session, Model model) throws IOException {

		return Config.VIEWS_SHOP + "orderList";
	}

	/**
	 * 确认订单操作
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "confirm")
	public String confirm(HttpSession session, Model model) throws IOException {
		Member member = (Member) session.getAttribute(Config.SESSION_USER);
		if (member == null) {
			// 跳转到登陆页面
			return Config.VIEWS_SHOP + "login";
		}
		// TODO 这里要加入对购物车为空的错误处理,和产品清单为空的处理
		Cart cart = (Cart) session.getAttribute(Config.SESSION_CART);

		orderService.save(member, cart);

		List<Order> ordersList = orderService.findAll(member);
		model.addAttribute("orders", ordersList);
		// clear cart
		cart.clear();

		return Config.VIEWS_SHOP + "orderList";
	}

	@RequestMapping(value = "list")
	public String list(HttpSession session, Model model) throws IOException {
		Member member = (Member) session.getAttribute(Config.SESSION_USER);
		// if (customer == null) {
		// // 跳转到登陆页面
		// return Config.VIEWS_SHOP + "login";
		// }

		List<Order> ordersList = orderService.findAll(member);
		model.addAttribute("orders", ordersList);

		return Config.VIEWS_SHOP + "orderList";
	}
}
