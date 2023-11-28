package com.cg.employee.services;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.cg.employee.dto.APIResponseDto;
import com.cg.employee.dto.DepDto;
import com.cg.employee.dto.EmployeeDto;
import com.cg.employee.model.Employee;
import com.cg.employee.repository.EmployeeRepository;
//org.springframework.cloud.openfeign.FeignContext;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	ModelMapper modelMapper;
	
	EmployeeRepository erepo;
	
	//APIClient apiClient;
	
	private WebClient webClient; 
	
	//RestTemplate rt;
	@Override
	public EmployeeDto addEmployee(EmployeeDto Edto) {
		// converting dto to jpa entity
		
		Employee emp = modelMapper.map(Edto, Employee.class);
		erepo.save(emp);
		EmployeeDto empDto = modelMapper.map(emp, EmployeeDto.class);
		return empDto;
	}

	//@CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDto getEmployeeById(Long id) {
		
		LOGGER.info("getEmployeeByID method");
		Employee emp = erepo.findById(id).get();
		
		/*ResponseEntity<DepDto> responseEntity = rt.getForEntity("http://localhost:8080/department/get/"+emp.getDepartmentCode(), DepDto.class);
		DepDto dDto = responseEntity.getBody();*/
		
		DepDto dDto = webClient.get()
		.uri("http://localhost:8080/department/get/"+emp.getDepartmentCode())
		.retrieve()
		.bodyToMono(DepDto.class)
		.block();
		
		//DepDto dDto = apiClient.getDepartmentByCode(emp.getDepartmentCode());
		
		 
		
		EmployeeDto edto = modelMapper.map(emp, EmployeeDto.class);
		APIResponseDto apiDto = new APIResponseDto();
		
		apiDto.setEmployee(edto);
		apiDto.setDep(dDto);
		
		
		return apiDto;
	}
	
	public APIResponseDto getDefaultDepartment(Long id, Exception e) {
		LOGGER.info("getDefaultDepartment method");
		Employee emp = erepo.findById(id).get();
		
		DepDto dDto = new DepDto();
		dDto.setDepartmentName("R&D Department");
		dDto.setDepartmentCode("DHSE");
		dDto.setDepartmentDescription("R&D");
		
		EmployeeDto edto = modelMapper.map(emp, EmployeeDto.class);
		APIResponseDto apiDto = new APIResponseDto();
		
		apiDto.setEmployee(edto);
		apiDto.setDep(dDto);
		
		
		return apiDto;
	}

}
