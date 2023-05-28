package com.product.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.api.dto.DtoProductList;

@Repository
public interface RepoProductList extends JpaRepository<DtoProductList, Integer>{

    @Query(value = "SELECT * FROM product WHERE category_id = :category_id AND status = 1", nativeQuery = true)
    List<DtoProductList> findByCategoryId(@Param("category_id") Integer category_id);
    
}
