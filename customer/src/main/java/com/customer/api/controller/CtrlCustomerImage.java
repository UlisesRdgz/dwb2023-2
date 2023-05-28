package com.customer.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;
import com.customer.api.service.SvcCustomerImage;
import com.customer.exception.ApiException;

@RestController
@RequestMapping("/customer-image")
public class CtrlCustomerImage {

	@Autowired
	SvcCustomerImage svc;
	
	@PutMapping
	public ResponseEntity<ApiResponse> setCustomerImage(@Valid @RequestBody CustomerImage in, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		
		return new ResponseEntity<>(svc.setCustomerImage(in), HttpStatus.OK);
	}
}
