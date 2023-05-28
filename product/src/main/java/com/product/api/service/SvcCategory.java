package com.product.api.service;

import java.util.List;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;

public interface SvcCategory {
    
    List<Category> listCategories();

    Category readCategory(Integer category_id);

    ApiResponse createCategory(Category category);

    ApiResponse updateCategory(Integer category_id, Category category);

    ApiResponse deleteCategory(Integer category_id);
}
