package com.team1.incidentticketsystem.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.team1.incidentticketsystem.models.Employee;
import com.team1.incidentticketsystem.models.EmployeeUserDetails;
import com.team1.incidentticketsystem.repositories.EmployeeRepository;

@Service
public class EmployeeUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	private final EmployeeRepository repo;
	
	
	public EmployeeUserDetailsService(EmployeeRepository repo) {
		this.repo = repo;
	}



	@Override
	public EmployeeUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Employee> employee  = repo.findByUsername(username);
		
		if(employee.isPresent()) {
			return new EmployeeUserDetails(employee.get());
		}
		return null;
	}
}
