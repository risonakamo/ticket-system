package com.team1.incidentticketsystem.security;

import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.team1.incidentticketsystem.models.EmployeeUserDetails;
import com.team1.incidentticketsystem.services.EmployeeUserDetailsService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final EmployeeUserDetailsService service;
	private final PasswordEncoder encoder;

	public CustomAuthenticationProvider(EmployeeUserDetailsService service, PasswordEncoder encoder) {
		this.service = service;
		this.encoder = encoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		EmployeeUserDetails empDetails = service.loadUserByUsername(username);
		
		if(empDetails != null) {
			if(empDetails.isEnabled()) {
				if(encoder.matches(password, empDetails.getPassword())) {
					return new EmployeeAuthentication(empDetails, true);
				} else {
					throw new BadCredentialsException(username);
				}
			} else {
				throw new DisabledException(username);
			}
		}
		
		throw new UsernameNotFoundException(username);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		boolean b = authentication.equals(UsernamePasswordAuthenticationToken.class);
		return b;
	}

}
