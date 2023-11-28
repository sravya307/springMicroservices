package com.cg.department.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepDto {

	private Long id;
	private String departmentName;
	private String departmentDescription;
	private String departmentCode;
}
