package com.weixin.domain.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.weixin.domain.model.shop.IdEntity;

/**
 * 
 * @author chenwentao
 * 
 */
@Entity
@Table(name = "base_account_log")
public class AccountLog extends IdEntity {

	private static final long serialVersionUID = 3008649038118840195L;

	private Long accountId;

	private Long shopId;

	private Double money;

	/**
	 * 0 充值 1 赠送
	 */
	private String type;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operation_time")
	private Date operationTime = new Date();

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

}
