package com.customer.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.api.dto.DtoCustomerList;

@Repository
public interface RepoCustomerList extends JpaRepository<DtoCustomerList, Integer>{

	List<DtoCustomerList> findByStatus(Integer status);

}
