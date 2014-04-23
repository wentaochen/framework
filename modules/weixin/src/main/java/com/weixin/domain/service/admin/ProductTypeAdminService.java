package com.weixin.domain.service.admin;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Product;
import com.weixin.domain.model.shop.ProductType;
import com.weixin.domain.service.common.AbstractService;

@Service("productTypeAdminService")
@Transactional
public class ProductTypeAdminService extends AbstractService<ProductType> {

	@Inject
	private ProductAdminService productAdminService;

	public List<ProductType> findAll() {
		return this.find("from ProductType");
	}

	@Override
	public boolean delete(Serializable id) {
		try {
			ProductType type = get(id);
			clearProductType(type);
			delete(type);
			return true;
		} catch (Exception e) {
			logger.error("delete 错误", e);
			return false;
		}
	}

	/**
	 * 清空Product的关联关系
	 * 
	 * @param type
	 */
	private void clearProductType(ProductType type) {
		List<Product> productList = productAdminService.find(
				"from Product where type=?", type);
		for (Product product : productList) {
			product.setType(null);
			productAdminService.update(product);
		}
	}

}
