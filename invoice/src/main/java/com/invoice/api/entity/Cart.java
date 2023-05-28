package com.invoice.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("cart_id")
	@Column(name = "cart_id")
	private Integer cart_id;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc is required")
	private String rfc;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin is required")
	private String gtin;
	
	@JsonProperty("quantity")
	@Column(name = "quantity")
	@NotNull(message="quantity is required")
	private Integer quantity;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;

	public Integer getCart_id() {
		return cart_id;
	}

	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}