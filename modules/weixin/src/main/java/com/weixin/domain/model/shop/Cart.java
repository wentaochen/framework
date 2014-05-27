package com.weixin.domain.model.shop;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cart")
public class Cart extends IdEntity {

	private static final long serialVersionUID = -6501629521541178565L;

	@Transient
	private Map<Long, OrderItem> products = new ConcurrentHashMap<Long, OrderItem>();

	@Transient
	private Double totalPrice;

	@Transient
	private Integer totalNumber;

	public void addProduct(Product product) {
		OrderItem cartItem = this.products.get(product.getId());
		if (cartItem == null) {
			cartItem = new OrderItem(product, 0);
		}
		cartItem.add(product);

		products.put(product.getId(), cartItem);
	}

	/**
	 * 从购物车中删除一个产品
	 * 
	 * @param productId
	 */
	public void deleteProduct(Long productId) {
		OrderItem cartItem = this.products.get(productId);
		if (cartItem == null) {
			return;
		}

		int currentCount = cartItem.getCount() - 1;
		if (currentCount < 0) {
			currentCount = 0;
		}

		cartItem.setCount(currentCount);
	}

	public Map<Long, OrderItem> getProducts() {
		return products;
	}

	public void clear() {
		if (products != null) {
			products.clear();
		}
	}

	public Double getTotalPrice() {
		BigDecimal tc = new BigDecimal(0);
		for (Map.Entry<Long, OrderItem> entry : products.entrySet()) {
			OrderItem item = entry.getValue();
			// 获得单价
			BigDecimal unitPrice = new BigDecimal(item.getProduct().getPrice());
			// 总价加上单价×数量
			tc = tc.add(unitPrice.multiply(new BigDecimal(item.getCount())));
		}
		return tc.doubleValue();
	}

	public Integer getTotalNumber() {
		Integer totalNumber = 0;
		for (Map.Entry<Long, OrderItem> entry : products.entrySet()) {
			OrderItem item = entry.getValue();
			totalNumber = totalNumber + item.getCount();
		}

		return totalNumber;
	}
}
