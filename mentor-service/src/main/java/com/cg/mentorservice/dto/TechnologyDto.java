package com.cg.mentorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDto {

	private int id;
	private String technologyCode;
	private String technologyName;
	private int hoursTaken;
}
