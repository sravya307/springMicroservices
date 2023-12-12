package com.cg.mentorservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
		description = "MentorDto Model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDto {


	private int id;	
	
	@Schema(
			description = "Mentor Name")
	private String mentorName;	
	
	@Schema(
			description = "Mentor Email Id")
	private String mentorEmail;
	
	@Schema(
			description = "Technology Mentor working on")
	private String technologyCode;
	private String Status;
}
