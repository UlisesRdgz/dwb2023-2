package com.customer.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customer_image")
public class CustomerImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_image_id")
	private Integer customer_image_id;
	
	@NotNull
	@Column(name = "customer_image")
	private String customer_image;
	
	public CustomerImage() {
		this.customer_image = "";
	}

	public Integer getCustomer_image_id() {
		return customer_image_id;
	}

	public void setCustomer_image_id(Integer customer_image_id) {
		this.customer_image_id = customer_image_id;
	}

	public String getCustomer_image() {
		return customer_image;
	}

	public void setCustomer_image(String customer_image) {
		this.customer_image = customer_image;
	}
}
