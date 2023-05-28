package com.customer.api.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.dto.ApiResponse;
import com.customer.api.entity.Region;
import com.customer.api.service.SvcRegion;
import com.customer.exception.ApiException;

@RestController
@RequestMapping("/region")
public class CtrlRegion {
	
	@Autowired
	SvcRegion svc;

	@GetMapping
	public ResponseEntity<List<Region>> getRegions() throws Exception{
		return new ResponseEntity<>(svc.getRegions(), HttpStatus.OK);
	}
	
	@GetMapping("/{region_id}")
	public ResponseEntity<Region> getRegion(@PathVariable int region_id){
		return new ResponseEntity<>(svc.getRegion(region_id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse> createRegion(@Valid @RequestBody Region region, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>(svc.createRegion(region), HttpStatus.OK);
	}
	
	@PutMapping("/{region_id}")
	public ResponseEntity<ApiResponse> updateRegion(@PathVariable int region_id, @Valid @RequestBody Region region, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		return new ResponseEntity<>(svc.updateRegion(region_id, region), HttpStatus.OK);
	}
	
	@DeleteMapping("/{region_id}")
	public ResponseEntity<ApiResponse> deleteRegion(@PathVariable int region_id){
		return new ResponseEntity<>(svc.deleteRegion(region_id), HttpStatus.OK);
	}
}
