package com.weixin.domain.model.base.manager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.weixin.domain.model.base.Shop;
import com.weixin.domain.model.shop.IdEntity;

/**
 * 
 * @author chenwentao
 * 
 */
@Entity
@Table(name = "manager_user")
public class ManagerUser extends IdEntity {

	private static final long serialVersionUID = 260131292548716395L;

	@ManyToOne
	private Shop shop;

	protected String loginName;

	protected String password;// 为简化演示使用明文保存的密码

	protected String nickname;

	protected String email;

	protected String remark;

	protected boolean admin;

	@ManyToMany
	private List<ManangerRole> roles = Lists.newArrayList();// 有序的关联对象集合

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<ManangerRole> getRoles() {
		return roles;
	}

	public void setRoles(List<ManangerRole> roles) {
		this.roles = roles;
	}
}
