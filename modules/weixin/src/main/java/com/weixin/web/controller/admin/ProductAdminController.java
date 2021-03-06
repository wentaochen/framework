package com.weixin.web.controller.admin;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.weixin.domain.model.shop.Product;
import com.weixin.domain.model.shop.ProductType;
import com.weixin.domain.service.admin.ProductAdminService;
import com.weixin.domain.service.admin.ProductTypeAdminService;
import com.weixin.infra.Config;
import com.weixin.infra.utils.FileHelpUtils;

@Controller
@RequestMapping("/admin/product/")
public class ProductAdminController {

	@Inject
	private ProductAdminService productAdminService;

	@Inject
	private ProductTypeAdminService typeAdminService;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Product> pList = productAdminService.findAllProduct();
		model.addAttribute("products", pList);
		return Config.VIEWS_ADMIN + "productList";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String productForm(Model model) {
		putAllTypeInRequest(model);
		return Config.VIEWS_ADMIN + "productForm";
	}

	/**
	 * 添加产品
	 * 
	 * @param imageFile
	 * @param product
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addProduct(
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
			Product product, Model model) throws Exception {
		// 上传图片处理
		if (!imageFile.isEmpty()) {
			String imageName = FileHelpUtils.uploadFile(imageFile);
			product.setImageUrl(imageName);
		}

		productAdminService.saveOrUpdate(product);
		return "redirect:/admin/product/list";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable Long id, Model model) {
		Product product = productAdminService.get(id);
		model.addAttribute("model", product);
		putAllTypeInRequest(model);
		return Config.VIEWS_ADMIN + "productForm";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable Long id, Model model) {
		productAdminService.delete(id);
		return "redirect:/admin/product/list";
	}

	/**
	 * 放入type至Request中
	 * 
	 * @param model
	 */
	private void putAllTypeInRequest(Model model) {
		List<ProductType> types = typeAdminService.findAll();
		model.addAttribute("types", types);
	}
}
