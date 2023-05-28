package com.customer.api.service;

import java.util.List;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;

public interface SvcRegion {
	List<Region> getRegions() throws Exception;
	Region getRegion(Integer region_id);
	ApiResponse createRegion(Region region);
	ApiResponse updateRegion(Integer region_id, Region region);
	ApiResponse deleteRegion(Integer region_id);
}
