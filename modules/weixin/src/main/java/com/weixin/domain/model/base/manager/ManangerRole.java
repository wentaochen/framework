package com.weixin.domain.model.base.manager;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.weixin.domain.model.shop.IdEntity;

/**
 * <pre>
 * @author chenwentao
 * 
 * @version 0.9
 * 
 * 修改版本: 0.9
 * 修改日期: Feb 8, 2011
 * 修改人 :  chenwentao
 * 修改说明: 初步完成
 * 复审人 ：
 * </pre>
 */
@Entity
@Table(name = "manager_role")
public class ManangerRole extends IdEntity {

	private static final long serialVersionUID = 1723112321266974573L;

	protected String name;

	protected String description;

	@ManyToMany
	private List<ManagerResource> resources = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ManagerResource> getResources() {
		return resources;
	}

	public void setResources(List<ManagerResource> resources) {
		this.resources = resources;
	}
}
