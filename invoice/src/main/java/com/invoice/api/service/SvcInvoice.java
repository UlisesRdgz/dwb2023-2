package com.invoice.api.service;

import java.util.List;

import com.invoice.api.dto.ApiResponse;
import com.invoice.api.entity.Invoice;
import com.invoice.api.entity.Item;

public interface SvcInvoice {

	public List<Invoice> getInvoices(String rfc);
	public List<Item> getInvoiceItems(Integer invoice_id);
	public ApiResponse generateInvoice(String rfc);
}
