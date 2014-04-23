package com.weixin.domain.model.base;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.weixin.domain.model.shop.IdEntity;

/**
 * 
 * @author chenwentao
 * 
 */
@Entity
@Table(name = "base_account")
public class Account extends IdEntity {

	private static final long serialVersionUID = 2989736395696244304L;

	/**
	 * 金额
	 */
	private Double amount;

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
