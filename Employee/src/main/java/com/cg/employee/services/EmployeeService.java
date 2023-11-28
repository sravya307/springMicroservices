package com.cg.employee.services;

import com.cg.employee.dto.APIResponseDto;
import com.cg.employee.dto.EmployeeDto;

public interface EmployeeService {

	public EmployeeDto addEmployee(EmployeeDto Edto);
	
	public APIResponseDto getEmployeeById(Long id);
}
