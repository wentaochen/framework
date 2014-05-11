package com.weixin.domain.service.shop;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixin.domain.model.shop.Cart;
import com.weixin.domain.model.shop.Member;
import com.weixin.domain.model.shop.OrderItem;
import com.weixin.domain.model.shop.OrderState;
import com.weixin.domain.model.shop.Order;
import com.weixin.domain.service.common.AbstractService;

@Service("orderService")
@Transactional
public class OrderService extends AbstractService<Order> {
	
	@Inject
	private OrderItemService orderItemService;

	public List<Order> findAll(Member member) {
		return this.find("from Order o where o.member=?", member);
	}

	public void save(Member member, Cart cart) {
		Order order = new Order();
		order.setOrderState(OrderState.unconfirmed);
		order.setMember(member);
		order.setTotalPrice(cart.getTotalCount());
		for (Map.Entry<Long, OrderItem> entry : cart.getProducts().entrySet()) {
			order.getOrderItem().add(entry.getValue());
			orderItemService.save(entry.getValue());
		}
		
		this.merge(order);
	}

	public List<Order> findHistory(Member member) { 
		return this.find("from Order o where o.member=? order by o.createTime desc", member);
	}
}
