package com.customer.api.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.api.entity.Customer;

@Repository
public interface RepoCustomer extends JpaRepository<Customer, Integer>{

	Customer findByRfcAndStatus(String rfc, Integer status);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE customer "
					+ "SET name = :name, "
						+ "surname = :surname, "
						+ "date_birth = :date_birth, "
						+ "rfc = :rfc, "
						+ "mail = :mail, "
						+ "address = :address "
					+ "WHERE customer_id = :customer_id AND status = 1", nativeQuery = true)
	Integer updateCustomer(
			@Param("customer_id") Integer customer_id,
			@Param("name") String name, 
			@Param("surname") String surname, 
			@Param("date_birth") LocalDate date_birth, 
			@Param("rfc") String rfc, 
			@Param("mail") String mail, 
			@Param("address") String address);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE customer SET status = 0 WHERE customer_id = :customer_id AND status = 1", nativeQuery = true)
	Integer deleteCustomer(@Param("customer_id") Integer customer_id);
	
	@Modifying
	@Transactional
	@Query(value ="UPDATE customer SET region_id = :region_id WHERE customer_id = :customer_id AND status = 1", nativeQuery = true)
	Integer updateCustomerRegion(@Param("region_id") Integer id_region, @Param("customer_id") Integer id_customer);
}
