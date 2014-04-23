package com.weixin.domain.model.base;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.weixin.domain.model.shop.IdEntity;

@Entity
@Table(name = "base_shop")
public class Shop extends IdEntity {

	private static final long serialVersionUID = 7636868839862059912L;

	@ManyToOne
	private Shop father;

	private String name;

	private String mobile;

	private String password;

	private String email;

	private String address;

	private String detail;

	private String weixinId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Shop getFather() {
		return father;
	}

	public void setFather(Shop father) {
		this.father = father;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}
}
