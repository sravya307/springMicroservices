package com.cg.department.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.cg.department.dto.DepDto;
import com.cg.department.exceptions.DepartmentNotFoundException;
import com.cg.department.exceptions.ErrorDetails;
import com.cg.department.services.DepService;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {

	private DepService depserv;
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public DepDto saveDepartment(@RequestBody DepDto depDto) {
		return depserv.saveDepartment(depDto);
	}
	
	
	@GetMapping("/get/{departmentCode}")
	@ResponseStatus(HttpStatus.OK)
	public DepDto getDepartmentByCode(@PathVariable String departmentCode) {
		return depserv.getDepartmentByCode(departmentCode);
	}
	
	@GetMapping("/getall")
	@ResponseStatus(HttpStatus.OK)
	public List<DepDto> getAll() {
		return depserv.getAll();
	}
	
	@PutMapping("/update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public DepDto updateDepartment(@PathVariable Long id,@RequestBody DepDto depdto) {
		depdto.setId(id);
		return depserv.updateDepartment(depdto);
	}
	
	@ExceptionHandler(DepartmentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDetails handleUserNotFoundException(DepartmentNotFoundException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"Department Not Found");
		
		return errorDetails;
	}
}

