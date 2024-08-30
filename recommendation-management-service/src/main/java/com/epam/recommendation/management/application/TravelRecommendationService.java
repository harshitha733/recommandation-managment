package com.epam.recommendation.management.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelRecommendationService {
	public static void main(String[] args) {
		SpringApplication.run(TravelRecommendationService.class, args);
	}
}