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
@Table(name = "base_credit_log")
public class CreditLog extends IdEntity {

	private static final long serialVersionUID = -6052051665870760763L;

	private Long userId;

	private Long credit;

	/**
	 * 0 充值积分 1 消费积分
	 */
	private String type;

	/**
	 * 等级
	 */
	private Long shopId;

	/**
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "operation_time")
	private Date operationTime = new Date();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCredit() {
		return credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

}
