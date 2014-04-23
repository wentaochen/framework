package com.weixin.domain.model.shop;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends IdEntity {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2758780201858075441L;

	private String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
