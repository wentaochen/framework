package com.weixin.domain.service.shop;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.ProductType;
import com.weixin.domain.service.common.AbstractService;

@Service("productrTypeService")
@Transactional
public class ProductTypeService extends AbstractService<ProductType> {

	/**
	 * 根据sort从低到高进行排序
	 * 
	 * @return
	 */
	public List<ProductType> findAll() {
		return this.find("from ProductType order by sort asc");
	}

}
