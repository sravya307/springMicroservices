package com.cg.mentorservice.repository;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cg.mentorservice.entity.Mentor;

@DataJpaTest
public class MentorRepositoryTest {

	@Autowired
	MentorRepository mentorRepo;
	
	
	@Test
	public void givenMentorObject_whenSave_thenSavedMentorObject() {
		
		Mentor mentor = Mentor.builder()
				.mentorName("sravya")
				.mentorEmail("sravya@gmail.com")
				.technologyCode("KDHS")
				.Status("ACTIVE")
				.build();
		
		Mentor savedMentor = mentorRepo.save(mentor);
		
		Assertions.assertThat(savedMentor).isNotNull();
		Assertions.assertThat(savedMentor.getId()).isGreaterThan(0);
		
	}
}
