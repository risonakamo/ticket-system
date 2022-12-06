package com.team1.incidentticketsystem.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.team1.incidentticketsystem.models.EmployeeUserDetails;

public class EmployeeAuthentication implements Authentication {

	private static final long serialVersionUID = 1568664102303354687L;
	
	private final EmployeeUserDetails details;
	private final boolean authenticated;

	public EmployeeAuthentication(EmployeeUserDetails details, boolean authenticated) {
		this.details = details;
		this.authenticated = details != null && authenticated;
	}

	@Override
	public String getName() {
		return details.getUsername();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return details.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return details;
	}

	@Override
	public boolean isAuthenticated() {
		return authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		throw new UnsupportedOperationException();
	}

}
