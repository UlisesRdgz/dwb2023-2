package com.customer.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;
import com.customer.api.repository.RepoRegion;
import com.customer.exception.ApiException;

@Service
public class SvcRegionImp implements SvcRegion {

	@Autowired
	RepoRegion repo;
	
	@Override
	public List<Region> getRegions() {
		return repo.findByStatus(1);
	}

	@Override
	public Region getRegion(Integer region_id) {
		Region region = repo.findByRegionId(region_id);
		if(region == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "region does not exist");
		else
			return region;
	}

	@Override
	public ApiResponse createRegion(Region region) {
		Region regionSaved = (Region) repo.findByRegion(region.getRegion());
		if(regionSaved != null) { 
			if(regionSaved.getStatus() == 0) {
				repo.activateRegion(regionSaved.getRegion_id());
				return new ApiResponse("region has been activated");
			}
			else
				throw new ApiException(HttpStatus.BAD_REQUEST,"region alredy exists");
		}
		repo.createRegion(region.getRegion());
		return new ApiResponse("region created");
	}

	@Override
	public ApiResponse updateRegion(Integer region_id, Region region) {
		Region regionSaved = (Region) repo.findByRegionId(region_id);
		if(regionSaved == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "region does not exist");
		else {
			if(regionSaved.getStatus() == 0)
				throw new ApiException(HttpStatus.BAD_REQUEST, "region is not active");
			else {
				regionSaved = (Region) repo.findByRegion(region.getRegion());
				if(regionSaved != null)
					throw new ApiException(HttpStatus.BAD_REQUEST, "region alredy exists");
				repo.updateRegion(region_id, region.getRegion());
				return new ApiResponse("region updated");
			}
		}
	}

	@Override
	public ApiResponse deleteRegion(Integer region_id) {
		Region regionSaved = (Region) repo.findByRegionId(region_id);
		if(regionSaved == null)
			throw new ApiException(HttpStatus.NOT_FOUND, "region does not exist");
		else {
			repo.deleteById(region_id);
			return new ApiResponse("region removed");
		}
	}

}
