package com.cg.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.employee.dto.APIResponseDto;
import com.cg.employee.dto.EmployeeDto;
import com.cg.employee.services.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
	
	private EmployeeService eserv;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeDto addEmployee(@RequestBody EmployeeDto empDto) {
		return eserv.addEmployee(empDto);
	}
	
	
	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public APIResponseDto getEmployeeById(@PathVariable Long id) {
		return eserv.getEmployeeById(id);
	}
	

}
