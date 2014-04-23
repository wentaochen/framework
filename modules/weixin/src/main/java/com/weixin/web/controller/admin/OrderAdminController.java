package com.weixin.web.controller.admin;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.Order;
import com.weixin.domain.service.admin.OrderAdminService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/admin/order/")
public class OrderAdminController {

	@Inject
	private OrderAdminService orderAdminService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Order> pList = orderAdminService.findAll();
		model.addAttribute("orders", pList);
		return Config.VIEWS_ADMIN + "orderList";
	}

	@RequestMapping(value = "confirm/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String confirm(@PathVariable("id") Long id, Model model) {
		orderAdminService.confirm(id);
		return "Ok";
	}

	@RequestMapping(value = "complete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String complete(@PathVariable("id") Long id, Model model) {
		orderAdminService.complete(id);
		return "Ok";
	}

	@RequestMapping(value = "cancel/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String cacnel(@PathVariable("id") Long id, Model model) {
		orderAdminService.cancel(id);
		return "Ok";
	}

}
