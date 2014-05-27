package com.weixin.domain.model.shop;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product extends IdEntity {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -2538526157050401619L;

	private String name;

	private Double price;

	private String unit;

	private String imageUrl;

	/**
	 * 打折价格
	 */
	private Integer discountPrice;

	/**
	 * 销售数量
	 */
	private Integer salesVolume;

	/**
	 * 好评数量
	 */
	private Integer goodReputation;

	@ManyToOne(optional = true)
	@JoinColumn(name = "type_id")
	private ProductType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	/**
	 * @return the discountPrice
	 */
	public Integer getDiscountPrice() {
		return discountPrice;
	}

	/**
	 * @param discountPrice
	 *            the discountPrice to set
	 */
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}

	/**
	 * @return the salesVolume
	 */
	public Integer getSalesVolume() {
		return salesVolume;
	}

	/**
	 * @param salesVolume
	 *            the salesVolume to set
	 */
	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	/**
	 * @return the goodReputation
	 */
	public Integer getGoodReputation() {
		return goodReputation;
	}

	/**
	 * @param goodReputation
	 *            the goodReputation to set
	 */
	public void setGoodReputation(Integer goodReputation) {
		this.goodReputation = goodReputation;
	}
}
