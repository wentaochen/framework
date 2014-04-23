package com.weixin.domain.model.shop;

/**
 * 订单状态类
 * 
 */
public enum OrderState {
	unconfirmed("已下单"), confirmed("已确认"), completed("完成"), cancelled("订单取消");

	private String label;

	private OrderState(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
