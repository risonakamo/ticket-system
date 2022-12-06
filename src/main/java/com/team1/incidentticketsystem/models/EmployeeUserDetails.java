package com.team1.incidentticketsystem.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
// convert user details from database to instance
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class EmployeeUserDetails implements UserDetails {
	public UUID id;
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities; // list of granted authorities

	public EmployeeUserDetails(Employee employee) {
		this.id = employee.id;
		this.userName = employee.getUsername();
		this.password = employee.getPassword();
		this.active = true;
		if (employee.getRoles() != null) {
			this.authorities = Arrays.stream(employee.getRoles().split(",")).map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		} else {
			this.authorities = new ArrayList<>();
		}

	}

	public EmployeeUserDetails() {
	}

	public UUID getId() {
		return id;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (authorities); // list of granted authorities
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {
		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return active;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
