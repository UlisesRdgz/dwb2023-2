package com.invoice.api.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.entity.Cart;
import com.invoice.api.service.SvcCart;
import com.invoice.exception.ApiException;

@RestController
@RequestMapping("/cart")
public class CtrlCart {

	@Autowired
	SvcCart svcCart;

	@GetMapping("/{rfc}")
	public ResponseEntity<List<Cart>> getCart(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcCart.getCart(rfc), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> addToCart(@Valid @RequestBody Cart in, BindingResult bindingResult){
		if(bindingResult.hasErrors())
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(svcCart.addToCart(in),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> removeFromCart(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svcCart.removeFromCart(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/clear/{rfc}")
	public ResponseEntity<ApiResponse> deleteCart(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcCart.clearCart(rfc), HttpStatus.OK);
	}
}
