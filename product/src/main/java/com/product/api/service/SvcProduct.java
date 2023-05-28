package com.product.api.service;

import java.util.List;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.entity.Product;
import com.product.api.dto.DtoProductList;;

public interface SvcProduct {

	public List<DtoProductList> listProducts(Integer category_id);
	public Product getProduct(String gtin);
	public ApiResponse createProduct(Product in);
	public ApiResponse updateProduct(Product in, Integer id);
	public ApiResponse updateProductStock(String gtin, Integer stock);
	public ApiResponse deleteProduct(Integer id);

	public ApiResponse updateProductCategory(Category category, String gtin);
}
