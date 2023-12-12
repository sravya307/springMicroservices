package com.cg.mentorservice.services;

import java.util.List;

import com.cg.mentorservice.dto.APIResponseDto;
import com.cg.mentorservice.dto.MentorDto;
import com.cg.mentorservice.entity.Mentor;

public interface MentorService {

		MentorDto addMentor(MentorDto mentorDto);
		
		APIResponseDto getMentorById(int id);
		
		List<MentorDto> getAll();
		
		void deleteMentorById(int mentorId);
		
		MentorDto updateById(MentorDto mentorDto);
}
