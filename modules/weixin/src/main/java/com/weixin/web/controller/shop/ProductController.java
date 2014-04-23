package com.weixin.web.controller.shop;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weixin.domain.model.shop.Product;
import com.weixin.domain.service.shop.ProductService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/product/")
public class ProductController {

	@Inject
	private ProductService productService;

	/**
	 * 列表出所有的产品
	 */
	@RequestMapping(value = "/list")
	public String product(HttpSession session,Model model) throws IOException {
		System.out.println(session.getId());
		List<Product> products = productService.find("from Product");
		model.addAttribute("items", products);
		return Config.VIEWS_SHOP + "productList";
	}
}
