package com.weixin.web.controller.shop;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.Cart;
import com.weixin.domain.model.shop.Product;
import com.weixin.domain.service.shop.ProductService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/cart/")
public class CartController {

	@Inject
	private ProductService productService;

	/**
	 * 产品加入购物车
	 * 
	 * @param id
	 *            产品主键
	 */
	@RequestMapping(value = "/add/{id}")
	@ResponseBody
	public String addCart(@PathVariable("id") Long id, HttpSession session,
			Model model) throws IOException {

		Cart cart = (Cart) session.getAttribute(Config.SESSION_CART);
		if (cart == null) {
			cart = new Cart();
			session.setAttribute(Config.SESSION_CART, cart);
		}

		Product product = productService.get(id);

		if (product == null) {
			throw new RuntimeException("没有找到相对应的产品");
		}

		cart.addProduct(product);

		return "ok";
	}

	@RequestMapping(value = "/list")
	public String carList(HttpSession session, Model model) throws IOException {
		return Config.VIEWS_SHOP + "cartList";
	}

	@RequestMapping(value = "/clear")
	public String clear(HttpSession session, Model model) throws IOException {
		Cart cart = (Cart) session.getAttribute(Config.SESSION_CART);
		if (cart != null) {
			cart.clear();
		}
		return Config.REDIRECT_INDEX + "cart/list";
	}

}
