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
@Table(name = "base_user_member")
public class UserMember extends IdEntity {

	private static final long serialVersionUID = 2581242444675590714L;

	/**
	 * 等级
	 */
	private String level;

	/**
	 * 积分
	 */
	private Long credit;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getCredit() {
		return credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}
}
