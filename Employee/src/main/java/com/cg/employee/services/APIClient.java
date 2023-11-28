package com.cg.employee.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.employee.dto.DepDto;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

	@GetMapping("/department/get/{departmentCode}")
	public DepDto getDepartmentByCode(@PathVariable String departmentCode); 
		
	
}
