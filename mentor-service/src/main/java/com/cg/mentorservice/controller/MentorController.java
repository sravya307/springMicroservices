package com.cg.mentorservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mentorservice.dto.APIResponseDto;
import com.cg.mentorservice.dto.MentorDto;
import com.cg.mentorservice.services.MentorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;


@Tag(
		name = "CRUD REST APIs for Mentor Microservice",
		description = "CRUD REST APIs")
@RestController
@RequestMapping("mentor")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MentorController {

	private static final Logger Logger = LoggerFactory.getLogger(MentorController.class);
	
	private MentorService mentorService;
	
	@Operation(
			summary = "Create Mentor REST API",
			description = "Adding a Mentor details into the database using this REST API")
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED")	
	@PostMapping("add")
	@ResponseStatus(HttpStatus.CREATED)
	public MentorDto addMentor(@RequestBody MentorDto mentorDto) {
		Logger.info("post request");
		return mentorService.addMentor(mentorDto);
	}
	
	
	@Operation(
			summary = "GET Mentor REST API",
			description = "Fetching a Mentor detail by id from the database")
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS")
	@GetMapping("get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public APIResponseDto getMentorById(@PathVariable("id") int mId) {
		return mentorService.getMentorById(mId);
	}
	
	
	@Operation(
			summary = "GET Mentor REST API",
			description = "Fetching all Mentor details from the database")
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS")
	@GetMapping("getall")
	@ResponseStatus(HttpStatus.OK)
	public List<MentorDto> getAllMentors(){
		return mentorService.getAll();
	}
	

	@Operation(
			summary = "DELETE Mentor REST API",
			description = "Deleting Mentor details by id from the database")
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS")
	@DeleteMapping("delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteMentorById(@PathVariable int id) {
		mentorService.deleteMentorById(id);
		return "Deleted mentor by Id:"+id;
	}
	
	
	@Operation(
			summary = "UPDATE Mentor REST API",
			description = "Updating Mentor details by id in the database")
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS")
	@PutMapping("update/{id}")
	@ResponseStatus(HttpStatus.OK)
	public MentorDto deleteMentorById(@PathVariable int id, @RequestBody MentorDto mentorDto) {
		mentorDto.setId(id);
		return mentorService.updateById(mentorDto);
		
	}
}
