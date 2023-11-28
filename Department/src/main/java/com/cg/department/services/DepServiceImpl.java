package com.cg.department.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cg.department.dto.DepDto;
import com.cg.department.exceptions.DepartmentNotFoundException;
import com.cg.department.model.Department;
import com.cg.department.repository.DepartmentRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class DepServiceImpl implements DepService {

	private DepartmentRepository deprepo;
	
	private ModelMapper modelMapper;
	
	@Override
	public DepDto saveDepartment(DepDto depDto) {
		// Department Dto to Department JPA Entity
		Department dept = modelMapper.map(depDto, Department.class);
		Department savedDept = deprepo.save(dept);
		DepDto deptDto = modelMapper.map(savedDept, DepDto.class); 
		
		return deptDto;
	}

	

	@Override
	public List<DepDto> getAll() {
		List<Department> depAll = deprepo.findAll();
		
		return depAll.stream().map((dep)->(modelMapper.map(dep, DepDto.class)))
				.collect(Collectors.toList());
	}

	@Override
	public DepDto updateDepartment(DepDto depDto) {
		Department dep = modelMapper.map(depDto, Department.class);
		Optional<Department> depDb = deprepo.findById(dep.getId());
		if(depDb.isPresent()) {
			Department depUpdate = depDb.get();
			depUpdate.setDepartmentCode(dep.getDepartmentCode());
			depUpdate.setDepartmentDescription(dep.getDepartmentDescription());
			depUpdate.setDepartmentName(dep.getDepartmentName());
			deprepo.save(depUpdate);
			return modelMapper.map(depUpdate, DepDto.class);
		}else {
			return null;
		}
		
		
	}



	@Override
	public DepDto getDepartmentByCode(String code) {
		Department dep = deprepo.findByDepartmentCode(code);
		
		DepDto dDto = modelMapper.map(dep, DepDto.class);
		return dDto;
	}

}
