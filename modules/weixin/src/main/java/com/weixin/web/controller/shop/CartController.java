package com.weixin.web.controller.shop;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.Cart;
import com.weixin.domain.model.shop.Product;
import com.weixin.domain.model.shop.ProductType;
import com.weixin.domain.service.shop.ProductService;
import com.weixin.domain.service.shop.ProductTypeService;
import com.weixin.infra.Config;

/**
 * 购物车控制类，目前只使用了carList方法
 * 
 * @author chenwentao
 * 
 */
@Controller
@RequestMapping("/cart/")
public class CartController {

	@Inject
	private ProductService productService;

	@Inject
	private ProductTypeService productTypeService;

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

	/**
	 * 产品从购物车中删除
	 * 
	 * @param id
	 *            产品主键
	 */
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public String deleteCart(@PathVariable("id") Long id, HttpSession session,
			Model model) throws IOException {
		Cart cart = (Cart) session.getAttribute(Config.SESSION_CART);
		if (cart == null) {
			return "ok";
		}

		cart.deleteProduct(id);

		return "ok";
	}

	/**
	 * 产品加入购物车
	 * 
	 * @param id
	 *            产品主键
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addCartItems(@RequestParam("ids") String ids,
			HttpSession session, Model model) throws IOException {

		String[] keyAndValue = ids.split(";");

		for (String tempValue : keyAndValue) {
			String[] value = tempValue.split(":");
			Long pId = Long.valueOf(value[0]);
			Integer count = Integer.valueOf(value[1]);

			Cart cart = (Cart) session.getAttribute(Config.SESSION_CART);
			if (cart == null) {
				cart = new Cart();
				session.setAttribute(Config.SESSION_CART, cart);
			}

			Product product = productService.get(pId);

			if (product == null) {
				throw new RuntimeException("没有找到相对应的产品");
			}

			for (int i = 0; i < count; i++) {
				cart.addProduct(product);
			}

		}

		return "ok";

		// Cart cart = (Cart) session.getAttribute(Config.SESSION_CART);
		// if (cart == null) {
		// cart = new Cart();
		// session.setAttribute(Config.SESSION_CART, cart);
		// }
		//
		// Product product = productService.get(id);
		//
		// if (product == null) {
		// throw new RuntimeException("没有找到相对应的产品");
		// }
		//
		// cart.addProduct(product);
		//
		// return "ok";
	}

	/**
	 * 进入到商品选购页面
	 * 
	 * @param session
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/list")
	public String carList(HttpSession session, Model model) throws IOException {
		List<ProductType> types = productTypeService.findAll();
		model.addAttribute("types", types);

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
