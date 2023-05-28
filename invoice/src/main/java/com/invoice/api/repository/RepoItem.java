package com.invoice.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.invoice.api.entity.Item;

@Repository
public interface RepoItem extends JpaRepository<Item, Integer>{

	@Query(value ="SELECT * FROM item WHERE invoice_id = :invoice_id AND status = 1", nativeQuery = true)
	List<Item> getInvoiceItems(@Param("invoice_id") Integer invoice_id);
}
