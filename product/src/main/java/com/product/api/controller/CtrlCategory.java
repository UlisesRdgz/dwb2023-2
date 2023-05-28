package com.product.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dto.ApiResponse;
import com.product.api.entity.Category;
import com.product.api.service.SvcCategory;
import com.product.exception.ApiException;

@RestController
@RequestMapping("/category")
public class CtrlCategory {

    @Autowired
    SvcCategory svc;
    
    @GetMapping
    public ResponseEntity<List<Category>> listCategories() throws Exception{ 
        return new ResponseEntity<>(svc.listCategories(), HttpStatus.OK);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<Category> readCategory(@PathVariable("category_id") int category_id) {
        return new ResponseEntity<>(svc.readCategory(category_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) 
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        
        return new ResponseEntity<>(svc.createCategory(category), HttpStatus.OK);
    }

    @PutMapping("/{category_id}")
    public ResponseEntity<ApiResponse> uptdateCategory(@PathVariable("category_id") int category_id, @Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) 
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
            
        return new ResponseEntity<>(svc.updateCategory(category_id, category), HttpStatus.OK);
    }

    @DeleteMapping("/{category_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("category_id") int category_id) {
        return new ResponseEntity<>(svc.deleteCategory(category_id), HttpStatus.OK);
    }
}
