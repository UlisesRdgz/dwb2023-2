package com.invoice.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoCustomer;
import com.invoice.api.dto.DtoProduct;
import com.invoice.api.entity.Cart;
import com.invoice.api.repository.RepoCart;
import com.invoice.configuration.client.CustomerClient;
import com.invoice.configuration.client.ProductClient;
import com.invoice.exception.ApiException;

@Service
public class SvcCartImp implements SvcCart {

	@Autowired
	RepoCart repo;
	
	@Autowired
	CustomerClient customerCl;

	@Autowired
	ProductClient productCl;
	
	@Override
	public List<Cart> getCart(String rfc) {
		return repo.findByRfcAndStatus(rfc,1);
	}

	@Override
	public ApiResponse addToCart(Cart cart) {
		if(!validateCustomer(cart.getRfc()))
			throw new ApiException(HttpStatus.BAD_REQUEST, "customer does not exist");
			
		/*
		 * Requerimiento 3
		 * Validar que el GTIN exista. Si existe, asignar el stock del producto a la variable product_stock 
		 */
		if(!validateGtin(cart.getGtin()))
			throw new ApiException(HttpStatus.BAD_REQUEST, "product does not exist");

		DtoProduct product = productCl.getProduct(cart.getGtin()).getBody();
		Integer stock = product.getStock(); // cambiar el valor de cero por el stock del producto recuperado de la API Product 
		
		if(cart.getQuantity() > stock) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "invalid quantity");
		}
		
		/*
		 * Requerimiento 4
		 * Validar si el producto ya había sido agregado al carrito para solo actualizar su cantidad
		 */
		List<Cart> carrito = repo.findByRfcAndStatus(cart.getRfc(), 1);
		for (Cart productCart : carrito) {
			if(productCart.getGtin().equals(cart.getGtin())) {
				int newQuantity = productCart.getQuantity() + cart.getQuantity();
				if(newQuantity > stock)
					throw new ApiException(HttpStatus.BAD_REQUEST, "invalid quantity");
				
				productCart.setQuantity(newQuantity);
				repo.save(productCart);
				return new ApiResponse("quantity updated");
			}
		}
		
		cart.setStatus(1);
		repo.save(cart);
		return new ApiResponse("item added");
	}

	@Override
	public ApiResponse removeFromCart(Integer cart_id) {
		if (repo.removeFromCart(cart_id) > 0)
			return new ApiResponse("item removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "item cannot be removed");
	}

	@Override
	public ApiResponse clearCart(String rfc) {
		if (repo.clearCart(rfc) > 0)
			return new ApiResponse("cart removed");
		else
			throw new ApiException(HttpStatus.BAD_REQUEST, "cart cannot be removed");
	}
	
	private boolean validateCustomer(String rfc) {
		try {
			ResponseEntity<DtoCustomer> response = customerCl.getCustomer(rfc);
			if(response.getStatusCode() == HttpStatus.OK)
				return true;
			else
				return false;
		}catch(Exception e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "unable to retrieve customer information");
		}
	}

	private boolean validateGtin(String gtin) {
		try {
			ResponseEntity<DtoProduct> response = productCl.getProduct(gtin);
			if(response.getStatusCode() == HttpStatus.OK)
				return true;
			else
				return false;
		}catch(Exception e) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "unable to retrieve customer information");
		}
	}

}
