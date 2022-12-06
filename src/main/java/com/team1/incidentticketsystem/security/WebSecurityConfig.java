package com.team1.incidentticketsystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private final CustomAuthenticationProvider authProvider;
	
	public WebSecurityConfig(CustomAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		builder.authenticationProvider(authProvider);
		
		return builder.build();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.formLogin()
			.and()
			.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/tickets/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().permitAll();
			
		
		return http.build();
	}
}
