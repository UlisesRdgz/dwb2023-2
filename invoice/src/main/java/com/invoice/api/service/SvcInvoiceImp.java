package com.invoice.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.dto.DtoProduct;
import com.invoice.api.entity.Cart;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.repository.RepoCart;
import com.invoice.api.repository.RepoInvoice;
import com.invoice.api.repository.RepoItem;
import com.invoice.configuration.client.ProductClient;
import com.invoice.exception.ApiException;

@Service
public class SvcInvoiceImp implements SvcInvoice {

	@Autowired
	RepoInvoice repo;
	
	@Autowired
	RepoItem repoItem;

	@Autowired
	RepoCart repoCart;

	@Autowired
	ProductClient productCl;

	@Override
	public List<Invoice> getInvoices(String rfc) {
		return repo.findByRfcAndStatus(rfc, 1);
	}

	@Override
	public List<Item> getInvoiceItems(Integer invoice_id) {
		return repoItem.getInvoiceItems(invoice_id);
	}

	@Override
	public ApiResponse generateInvoice(String rfc) {
		/*
		 * Requerimiento 5
		 * Implementar el método para generar una factura 
		 */
		List<Cart> cartItems = repoCart.findByRfcAndStatus(rfc, 1);

		if(cartItems.isEmpty())
			throw new ApiException(HttpStatus.NOT_FOUND, "cart has no items");

		Invoice invoice = new Invoice();

		Double total = 0.0;
		Double taxes = 0.0;
		Double subtotal = 0.0;

		/* Generamos factura vacia para obtener id */
		invoice.setRfc(rfc);
		invoice.setTotal(total);
		invoice.setTaxes(taxes);
		invoice.setSubtotal(subtotal);
		invoice.setCreated_at(LocalDateTime.now());
		invoice.setStatus(1);

		repo.save(invoice);

		for (Cart cart : cartItems) {	

			/* Generamos los artículos de la factura. */
			Item item = new Item();

			DtoProduct product = productCl.getProduct(cart.getGtin()).getBody();

			item.setId_invoice(invoice.getInvoice_id());
			item.setGtin(cart.getGtin());
			item.setQuantity(cart.getQuantity());
			item.setUnit_price(product.getPrice());
			item.setTotal(item.getUnit_price() * item.getQuantity());
			item.setTaxes(item.getTotal() * 0.16);
			item.setSubtotal(item.getTotal() - item.getTaxes());
			item.setStatus(1);

			repoItem.save(item);

			/* Actualizamos el product stock */
			productCl.updateProductStock(item.getGtin(), cart.getQuantity());

			/* Calculamos los valores de la factura */
			total += item.getTotal();
			taxes += item.getTaxes();
			subtotal += item.getSubtotal();
		}

		/* Generamos la factura */
		invoice.setTotal(total);
		invoice.setTaxes(taxes);
		invoice.setSubtotal(subtotal);

		repo.updateInvoice(invoice.getInvoice_id(), invoice.getTotal(), invoice.getTaxes(), invoice.getSubtotal());

		/* Vaciamos el carrito */
		repoCart.clearCart(rfc);
		
		return new ApiResponse("invoice generated");
	}

}
