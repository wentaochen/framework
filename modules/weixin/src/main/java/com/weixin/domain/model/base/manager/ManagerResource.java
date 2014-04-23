package com.weixin.domain.model.base.manager;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.weixin.domain.model.shop.IdEntity;

@Entity
@Table(name = "manager_resource")
public class ManagerResource extends IdEntity {

	private static final long serialVersionUID = 1192624697536460579L;

	protected String name;

	protected String url;

	@ManyToOne
	protected ManagerResource parent;

	protected String functionPotion;

	protected String root;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ManagerResource getParent() {
		return parent;
	}

	public void setParent(ManagerResource parent) {
		this.parent = parent;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
}
