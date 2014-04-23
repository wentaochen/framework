package com.weixin.domain.service.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Product;
import com.weixin.domain.service.common.AbstractService;

@Service("productrService")
@Transactional
public class ProductService extends AbstractService<Product> {

}
