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
@Table(name = "item")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("item_id")
	@Column(name = "item_id")
	private Integer item_id;
	
	@JsonProperty("invoice_id")
	@Column(name = "invoice_id")
	@NotNull(message="invoice id required")
	private Integer id_invoice;
	
	@JsonProperty("gtin")
	@Column(name = "gtin")
	@NotNull(message="gtin code required")
	private String gtin;
	
	@JsonProperty("quantity")
	@Column(name = "quantity")
	@NotNull(message="quantity required")
	@Min(value=1, message="quantity must be greater than 0")
	private Integer quantity;
	
	@JsonProperty("unit_price")
	@Column(name = "unit_price")
	@NotNull(message="unit price required")
	private Double unit_price;
	
	@JsonProperty("subtotal")
	@Column(name = "subtotal")
	@NotNull(message="subtotal required")
	private Double subtotal;
	
	@JsonProperty("taxes")
	@Column(name = "taxes")
	@NotNull(message="taxes required")
	private Double taxes;
	
	@JsonProperty("total")
	@Column(name = "total")
	@NotNull(message="total required")
	private Double total;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getId_invoice() {
		return id_invoice;
	}

	public void setId_invoice(Integer id_invoice) {
		this.id_invoice = id_invoice;
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

	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
