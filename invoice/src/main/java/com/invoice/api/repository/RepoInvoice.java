package com.invoice.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.invoice.api.entity.Invoice;

import feign.Param;

@Repository
public interface RepoInvoice extends JpaRepository<Invoice, Integer>{

	List<Invoice> findByRfcAndStatus(String rfc, Integer status);

	@Modifying
	@Transactional
	@Query(value = "UPDATE invoice SET total = :total, taxes = :taxes, subtotal = :subtotal WHERE invoice_id = :invoice_id", nativeQuery = true)
	Integer updateInvoice(@Param("invoice_id") Integer invoice_id, @Param("total") Double total, @Param("taxes") Double taxes, @Param("subtotal") Double subtotal);

}
