package com.customer.api.service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.CustomerImage;

public interface SvcCustomerImage {

	ApiResponse setCustomerImage(CustomerImage in);
}
