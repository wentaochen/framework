package com.weixin.domain.model.shop;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem extends IdEntity {

	@OneToOne
	private Product product;

	private Integer count;

	/**
	 * 产品订单对应的选项
	 */
	private OrderItem() {
	}

	public OrderItem(Product product, Integer count) {
		this.product = product;
		this.count = count;
	}

	/**
	 * 添加一个产品
	 * 
	 * @param product
	 * @return
	 */
	public void add(Product product) {
		if (product == null) {
			throw new RuntimeException("添加产品失败");
		}
		// 自增加1
		count = count + 1;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
