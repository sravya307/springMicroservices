package com.cg.department.services;

import java.util.List;

import com.cg.department.dto.DepDto;

public interface DepService {

	public DepDto saveDepartment(DepDto depDto);
	
	public DepDto getDepartmentByCode(String code);
	
	public List<DepDto> getAll();
	
	public DepDto updateDepartment(DepDto depDto);
}
