package com.weixin.domain.model.base;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.weixin.domain.model.shop.IdEntity;

/**
 * 
 * @author chenwentao
 * 
 */
@Entity
@Table(name = "base_user")
public class User extends IdEntity {

	private static final long serialVersionUID = 260131292548716395L;

	@ManyToOne
	private Shop shop;

	@OneToOne
	private UserMember member;

	@OneToOne
	private Account account;

	private String openid;

	private String name;

	private String mobile;

	private String password;

	private String email;

	private String sex;

	private Integer age;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

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

	public String getSex() {
		return sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public UserMember getMember() {
		return member;
	}

	public void setMember(UserMember member) {
		this.member = member;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
