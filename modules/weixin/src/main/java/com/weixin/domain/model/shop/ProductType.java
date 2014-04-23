package com.weixin.domain.model.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product_type")
public class ProductType extends IdEntity {

	private static final long serialVersionUID = -1799082886073352287L;

	private String name;

	private Integer sort;

	@OneToMany(mappedBy="type")
	private List<Product> products = new ArrayList<Product>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
