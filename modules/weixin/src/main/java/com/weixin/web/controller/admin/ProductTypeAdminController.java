package com.weixin.web.controller.admin;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixin.domain.model.shop.ProductType;
import com.weixin.domain.service.admin.ProductTypeAdminService;
import com.weixin.infra.Config;

@Controller
@RequestMapping("/admin/productype/")
public class ProductTypeAdminController {

	@Inject
	private ProductTypeAdminService service;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<ProductType> types = service.findAll();
		model.addAttribute("types", types);
		return Config.VIEWS_ADMIN + "productType/" + "list";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateToForm(@PathVariable Long id, Model model) {
		ProductType type = service.get(id);
		model.addAttribute("item", type);
		return Config.VIEWS_ADMIN + "productType/" + "form";
	}

	// @RequestMapping(value = "update", method = RequestMethod.POST)
	// public String update(ProductType type, Model model) {
	// service.update(type);
	// return "redirect:/admin/productype/list";
	// }

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String form(Model model) {
		return Config.VIEWS_ADMIN + "productType/" + "form";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String add(ProductType type, Model model) {
		if (service.saveOrUpdate(type)) {
			return "ok";
		} else {
			return "fail";
		}
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		service.delete(id);
		return "redirect:/admin/productype/list";
	}
}
