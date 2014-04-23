package com.weixin.web.controller.admin;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.Product;
import com.weixin.domain.service.admin.ProductAdminService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/admin/")
public class IndexAdminController {

	@Inject
	private ProductAdminService productAdminService;

	/**
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "", "/" })
	public String index(Model model) {
		return Config.VIEWS_ADMIN + "index";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(Product product, Model model) {

		if (productAdminService.save(product)) {
			return "ok";
		} else {
			return "fail";
		}
	}

}
