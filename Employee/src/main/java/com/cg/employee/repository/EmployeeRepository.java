package com.cg.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
