package com.invoice.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;
import com.invoice.api.service.SvcInvoice;

@RestController
@RequestMapping("/invoice")
public class CtrlInvoice {

	@Autowired
	SvcInvoice svcInvoice;
	
	@GetMapping("/{rfc}")
	public ResponseEntity<List<Invoice>> getInvoices(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcInvoice.getInvoices(rfc), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/item")
	public ResponseEntity<List<Item>> getInvoiceItems(@PathVariable("id") Integer id){
		return new ResponseEntity<>(svcInvoice.getInvoiceItems(id), HttpStatus.OK);
	}
	
	@PostMapping("/{rfc}")
	public ResponseEntity<ApiResponse> generateInvoice(@PathVariable("rfc") String rfc){
		return new ResponseEntity<>(svcInvoice.generateInvoice(rfc),HttpStatus.CREATED);
	}
}
