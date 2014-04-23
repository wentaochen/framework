package com.weixin.domain.service.shop;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.OrderItem;
import com.weixin.domain.service.common.AbstractService;

@Service("ordersItemService")
@Transactional
public class OrderItemService extends AbstractService<OrderItem> {

}
