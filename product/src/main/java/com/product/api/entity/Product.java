package com.product.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("product_id")
	@Column(name = "product_id")
	private Integer product_id;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin is required")
	private String gtin;
	
	@JsonProperty("product")
	@Column(name = "product")
	@NotNull(message="product is required")
	private String product;
	
	@JsonProperty("description")
	@Column(name = "description")
	private String description;
	
	@JsonProperty("price")
	@Column(name = "price")
	@NotNull(message="price is required")
	@Min(value=0, message="price must be positive")
	private Double price;
	
	@JsonProperty("stock")
	@Column(name = "stock")
	@NotNull(message="stock is required")
	@Min(value=1, message="stock must be greater than 0")
	private Integer stock;
	
	@JsonProperty("category_id")
	@Column(name = "category_id")
	@NotNull(message="category_id is required")
	private Integer category_id;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	@Transient 
	@JsonProperty("category")
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
