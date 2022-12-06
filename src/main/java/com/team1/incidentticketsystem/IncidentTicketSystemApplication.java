package com.team1.incidentticketsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class IncidentTicketSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncidentTicketSystemApplication.class, args);
		System.out.println("running");
	}
	
	// encode password // for now we are suppressing encoding
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}