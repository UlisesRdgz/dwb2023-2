package com.invoice.api.dto;

/*
 * Requerimiento 3
 * Agregar atributos de clase para la validación del producto
 */
public class DtoProduct {

    private String gtin;

    private Integer stock;

	private Double price;

    public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

    public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
