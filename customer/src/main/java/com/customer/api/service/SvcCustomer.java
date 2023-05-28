package com.customer.api.service;

import java.util.List;

import com.customer.api.dto.ApiResponse;
import com.customer.api.dto.DtoCustomerList;
import com.customer.api.entity.Customer;
import com.customer.api.entity.Region;

public interface SvcCustomer {

	public List<DtoCustomerList> getCustomers();
	public Customer getCustomer(String rfc);
	public ApiResponse createCustomer(Customer in);
	public ApiResponse updateCustomer(Customer in, Integer id);
	public ApiResponse deleteCustomer(Integer id);
	
	public ApiResponse updateCustomerRegion(Region region, Integer id);

}
