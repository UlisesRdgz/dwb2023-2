package com.product.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.repository.RepoCategory;
import com.product.exception.ApiException;

@Service
public class ScvCategoryImp implements SvcCategory{

    @Autowired
    RepoCategory repo;

    @Override
    public List<Category> listCategories() {
        return repo.findByStatus(1);
    }

    @Override
    public Category readCategory(Integer category_id) {
        Category categorySaved = repo.findByCategoryId(category_id);

        if (categorySaved == null) 
            throw new ApiException(HttpStatus.BAD_REQUEST, "category does not exist");
        
        return categorySaved;
    }

    @Override
    public ApiResponse createCategory(Category category) {
        Category categorySaved = (Category) repo.findByCategory(category.getCategory(), category.getAcronym());

        if (categorySaved != null) {
            if (categorySaved.getStatus() == 0) {
                repo.activateCategory(categorySaved.getCategory_id());
                return new ApiResponse("category has been activated");
            }
            throw new ApiException(HttpStatus.BAD_REQUEST, "category alredy exists");
        }
        repo.createCategory(category.getCategory(), category.getAcronym());
        return new ApiResponse("category created");
    }

    @Override
    public ApiResponse updateCategory(Integer category_id, Category category) {
        Category categorySaved = (Category) repo.findByCategoryIdGeneral(category_id);

        if (categorySaved != null) {
            if (categorySaved.getStatus() == 0) 
                throw new ApiException(HttpStatus.BAD_REQUEST, "category is not active");

            categorySaved = (Category) repo.findByCategory(category.getCategory(), category.getAcronym());
            if (categorySaved != null) 
                throw new ApiException(HttpStatus.BAD_REQUEST, "category alredy exists");
            
            repo.updateCategory(category_id, category.getCategory(), category.getAcronym());
            return new ApiResponse("category updated");
        }

        throw new ApiException(HttpStatus.BAD_REQUEST, "category does not exist");
    }

    @Override
    public ApiResponse deleteCategory(Integer category_id) {
        Category categorySaved = (Category) repo.findByCategoryId(category_id);

        if (categorySaved != null) {
            repo.deleteById(category_id);
            return new ApiResponse("category removed");
        }
        throw new ApiException(HttpStatus.NOT_FOUND, "category does not exist");
    }
    
}
