package com.cg.mentorservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mentorservice.entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, Integer> {

}
