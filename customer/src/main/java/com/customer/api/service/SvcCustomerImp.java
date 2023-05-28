package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.dto.DtoCustomerList;
import com.customer.api.entity.Customer;
import com.customer.api.entity.CustomerImage;
import com.customer.api.entity.Region;
import com.customer.api.repository.RepoCustomer;
import com.customer.api.repository.RepoCustomerList;
import com.customer.exception.ApiException;

@Service
public class SvcCustomerImp implements SvcCustomer {

	@Autowired
	RepoCustomer repo;

	@Autowired
	RepoCustomerList repoCustomerList;

	@Override
	public List<DtoCustomerList> getCustomers() {
		return repoCustomerList.findByStatus(1);
	}

	@Override
	public Customer getCustomer(String rfc) {
		Customer customer = repo.findByRfcAndStatus(rfc, 1);
		if (customer != null)
			return customer;
		else
			throw new ApiException(HttpStatus.NOT_FOUND, "customer does not exist");
	}

	@Override
	public ApiResponse createCustomer(Customer in) {
		in.setStatus(1);
		in.setCustomerImage(new CustomerImage());
		try {
			repo.save(in);
		}catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer rfc already exist");
			if (e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer mail already exist");
		}
		return new ApiResponse("customer created");
	}

	@Override
	public ApiResponse updateCustomer(Customer in, Integer id) {
		try {
			repo.updateCustomer(id, in.getName(), in.getSurname(), in.getDate_birth(), in.getRfc(), in.getMail(), in.getAddress());
		}catch (DataIntegrityViolationException e) {
			if (e.getLocalizedMessage().contains("rfc"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer rfc already exist");
			if (e.getLocalizedMessage().contains("mail"))
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer mail already exist");
		}
		return new ApiResponse("customer updated");
	}

	@Override
	public ApiResponse deleteCustomer(Integer id) {
		if (repo.deleteCustomer(id) > 0)
			return new ApiResponse("customer removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "customer cannot be deleted");
	}

	@Override
	public ApiResponse updateCustomerRegion(Region region, Integer id) {
		try {
			if (repo.updateCustomerRegion(region.getRegion_id(), id) > 0)
				return new ApiResponse("customer region updated");
			else
				throw new ApiException(HttpStatus.BAD_REQUEST, "customer region cannot be updated");
		} catch (DataIntegrityViolationException e) {
			throw new ApiException(HttpStatus.NOT_FOUND, "region not found");
		}
	}

}
