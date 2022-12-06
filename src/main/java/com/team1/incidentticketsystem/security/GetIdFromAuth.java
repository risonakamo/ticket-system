package com.team1.incidentticketsystem.security;

import java.util.UUID;

import org.springframework.security.core.Authentication;

import com.team1.incidentticketsystem.models.EmployeeUserDetails;

public class GetIdFromAuth {
	
	public static UUID getIdFromAuth(Authentication auth) {
		Object principal = auth.getPrincipal();

		if (principal instanceof EmployeeUserDetails) {
			EmployeeUserDetails userDetails = (EmployeeUserDetails) principal;
			return userDetails.getId();
		}
		
		// change this to throw an appropriate exception
		return null;
	}

}
