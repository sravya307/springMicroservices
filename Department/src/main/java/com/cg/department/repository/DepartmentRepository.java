package com.cg.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.department.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Department findByDepartmentCode(String departmentCode);
}
