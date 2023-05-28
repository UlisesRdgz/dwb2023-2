package com.invoice.api.service;

import java.util.List;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.entity.Cart;

public interface SvcCart {

	public List<Cart> getCart(String rfc);
	public ApiResponse addToCart(Cart cart);
	public ApiResponse removeFromCart(Integer cart_id);
	public ApiResponse clearCart(String rfc);

}
