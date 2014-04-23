package com.weixin.domain.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Product;
import com.weixin.domain.service.common.AbstractService;

@Service("productAdminService")
@Transactional
public class ProductAdminService extends AbstractService<Product> {

	public List<Product> findAllProduct() {
		return this.find("from Product");
	}
}
