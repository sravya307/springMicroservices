package com.cg.mentorservice.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.cg.mentorservice.dto.APIResponseDto;
import com.cg.mentorservice.dto.MentorDto;
import com.cg.mentorservice.dto.TechnologyDto;
import com.cg.mentorservice.entity.Mentor;
import com.cg.mentorservice.repository.MentorRepository;
import com.cg.mentorservice.services.MentorService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class MentorServiceImpl implements MentorService {
	
	public static final Logger Logger = LoggerFactory.getLogger(MentorServiceImpl.class);
	
	private ModelMapper  modelMapper;
	
	private MentorRepository mentorRepo;
	
	private WebClient webClient;
	
	
	@Override
	public MentorDto addMentor(MentorDto mentorDto) {
		Logger.info("add Mentor method Executed");
		Mentor mentor = modelMapper.map(mentorDto, Mentor.class);
		//Logger.info("id -> ", mentor.getId());
		Mentor savedMentor = mentorRepo.save(mentor);
		MentorDto mentorDto1 = modelMapper.map(savedMentor, MentorDto.class);
		return mentorDto1;
	}


	@Override
	public List<MentorDto> getAll() {
		List<Mentor> mentors = mentorRepo.findAll();
		List<MentorDto> mentorsDto = mentors.stream().map((mentor) ->modelMapper.map(mentor, MentorDto.class))
										.collect(Collectors.toList());
		
		return mentorsDto;
	}

	@Override
	public APIResponseDto getMentorById(int id) {
		Mentor mentor = mentorRepo.findById(id).get();
		
		TechnologyDto techDto = webClient.get()
				.uri("http://localhost:8080/technology/get/"+mentor.getTechnologyCode())
				.retrieve()
				.bodyToMono(TechnologyDto.class)
				.block();
		
		MentorDto mentorDto = modelMapper.map(mentor, MentorDto.class);
	
		APIResponseDto apiDto = new APIResponseDto();
		apiDto.setMentor(mentorDto);
		apiDto.setTech(techDto);
		
		return apiDto;
	}


	@Override
	public void deleteMentorById(int mentorId) {
		Optional<Mentor> mentorDetails = mentorRepo.findById(mentorId);
		if(mentorDetails.isPresent()) {
			mentorRepo.deleteById(mentorId);
		}
		
	}


	@Override
	public MentorDto updateById(MentorDto mentorDto) {
		Mentor mentor = modelMapper.map(mentorDto, Mentor.class);
		Optional<Mentor> mentorDb = mentorRepo.findById(mentor.getId());
		
		if(mentorDb.isPresent()) {
			Mentor mentorUpdate = mentorDb.get();
			
			mentorUpdate.setMentorName(mentor.getMentorName());
			mentorUpdate.setMentorEmail(mentor.getMentorEmail());
			mentorUpdate.setTechnologyCode(mentor.getTechnologyCode());
			mentorUpdate.setStatus(mentor.getStatus());
			
			mentorRepo.save(mentorUpdate);
			
			return modelMapper.map(mentorUpdate, MentorDto.class);
		}
		else {
			return null;
		}
	}
	
}
