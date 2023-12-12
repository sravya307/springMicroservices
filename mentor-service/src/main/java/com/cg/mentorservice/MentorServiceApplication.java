package com.cg.mentorservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Mentor MicroService REST API Documentation",
				description = "Mentor MicroService REST API Documentation"
				))
public class MentorServiceApplication {

	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MentorServiceApplication.class, args);
	}

}
