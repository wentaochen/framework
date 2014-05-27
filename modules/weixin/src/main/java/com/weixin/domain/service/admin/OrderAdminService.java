package com.weixin.domain.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Order;
import com.weixin.domain.model.shop.OrderState;
import com.weixin.domain.service.common.AbstractService;

@Service("orderAdminService")
@Transactional
public class OrderAdminService extends AbstractService<Order> {

	public List<Order> findAll() {
		return this.find("from Order order by id desc");
	}

	public void confirm(Long id) {
		Order order = this.get(id);
		order.setOrderState(OrderState.confirmed);
		this.update(order);
	}

	public void cancel(Long id) {
		Order order = this.get(id);
		order.setOrderState(OrderState.cancelled);
		this.update(order);
	}

	public void complete(Long id) {
		Order order = this.get(id);
		order.setOrderState(OrderState.completed);
		this.update(order);
	}

}
