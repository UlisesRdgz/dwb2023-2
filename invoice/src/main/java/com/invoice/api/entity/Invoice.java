package com.invoice.api.entity;

import java.time.LocalDateTime;

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
@Table(name = "invoice")
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("invoice_id")
	@Column(name = "invoice_id")
	private Integer invoice_id;
	
	@JsonProperty("rfc")
	@Column(name = "rfc")
	@NotNull(message="rfc required")
	private String rfc;
	
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
	
	@Column(name = "created_at")
	@JsonProperty("created_at")
	@NotNull(message="created_at required")
	private LocalDateTime created_at;
	
	@JsonIgnore
	@Column(name = "status")
	@Min(value=0, message="status must be 0 or 1")
	@Max(value=1, message="status must be 0 or 1")
	private Integer status;
	
	public Invoice() {
		
	}

	public Integer getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(Integer invoice_id) {
		this.invoice_id = invoice_id;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getTaxes() {
		return taxes;
	}

	public void setTaxes(Double taxes) {
		this.taxes = taxes;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
