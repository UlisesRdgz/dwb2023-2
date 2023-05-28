package com.invoice.configuration.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.invoice.api.dto.DtoCustomer;

@FeignClient(name = "customer-service")
public interface CustomerClient {

	@GetMapping("customer/{rfc}")
	public ResponseEntity<DtoCustomer> getCustomer(@PathVariable("rfc") String rfc);
}
