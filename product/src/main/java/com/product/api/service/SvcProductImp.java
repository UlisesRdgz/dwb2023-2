package com.product.api.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.dto.DtoProductList;
import com.product.api.entity.Category;
import com.product.api.entity.Product;
import com.product.api.repository.RepoCategory;
import com.product.api.repository.RepoProduct;
import com.product.api.repository.RepoProductList;
import com.product.exception.ApiException;

@Service
public class SvcProductImp implements SvcProduct {

	@Autowired
	RepoProduct repo;

	@Autowired
	RepoProductList repoProductList;
	
	@Autowired
	RepoCategory repoCategory;

	@Override
	public List<DtoProductList> listProducts(Integer category_id) {
		return repoProductList.findByCategoryId(category_id);
	}

	@Override
	public Product getProduct(String gtin) {
		Product product = repo.findByGtinAndStatus(gtin, 1);
		if (product != null) {
			product.setCategory(repoCategory.findByCategoryId(product.getCategory_id()));
			return product;
		}else
			throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
	}

	@Override
	public ApiResponse createProduct(Product in) {
		Product product = repo.findByGtinAndStatus(in.getGtin(), 0);
		if(product != null) {
			repo.updateStatus(product.getProduct_id());
			return new ApiResponse("product activated");
		}

		in.setStatus(1);
		try {
			repo.save(in);
		} catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("gtin")) 
				throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exists");
			if (e.getLocalizedMessage().contains("product")) 
				throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exists");	
			if (e.contains(SQLIntegrityConstraintViolationException.class))
				throw new ApiException(HttpStatus.BAD_REQUEST, "category not found");
		}
		return new ApiResponse("product created");
	}

	@Override
	public ApiResponse updateProduct(Product in, Integer id) {
		Integer updated = 0;
		try {
			updated = repo.updateProduct(id, in.getGtin(), in.getProduct(), in.getDescription(), in.getPrice(), in.getStock(), in.getCategory_id());
		}catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("gtin"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product gtin already exist");
			if (e.getLocalizedMessage().contains("product"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "product name already exist");
			if (e.contains(SQLIntegrityConstraintViolationException.class))
				throw new ApiException(HttpStatus.NOT_FOUND, "category not found");
		}
		if(updated == 0)
			throw new ApiException(HttpStatus.NOT_FOUND, "product does not exist");
		else
			return new ApiResponse("product updated");
	}

	@Override
	public ApiResponse deleteProduct(Integer id) {
		if (repo.deleteProduct(id) > 0)
			return new ApiResponse("product removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "product cannot be deleted");
	}

	@Override
	public ApiResponse updateProductStock(String gtin, Integer stock) {
		Product product = getProduct(gtin);
		if(stock > product.getStock())
			throw new ApiException(HttpStatus.BAD_REQUEST, "stock to update is invalid");
		
		repo.updateProductStock(gtin, product.getStock() - stock);
		return new ApiResponse("product stock updated");
	}

	@Override
	public ApiResponse updateProductCategory(Category category, String gtin) {
		try {
			if (repo.updateProductCategory(category.getCategory_id(), gtin) > 0) 
				return new ApiResponse("product category update");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "product does not exist");
				
		} catch (DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "category not found");
		}
	}
}
