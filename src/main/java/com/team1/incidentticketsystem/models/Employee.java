package com.team1.incidentticketsystem.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee
{
    // id info
    @Id
    public UUID id;
    public String firstName;
    public String lastName;

    // job info
    public String jobTitle;
    public Integer jobLevel;

    // additional info
    public String email;
    public String location;

    // auth info
    public Boolean isAdmin;
    public String username;
    public String password;
    public String roles;

    public Employee()
    {

    }

    public Employee(
        String firstName,
        String lastName,
        String jobTitle,
        Integer jobLevel,
        String email,
        String location,
        boolean isAdmin,
        String username,
        String password,
        String roles
    )
    {
        this.id=UUID.randomUUID();
        this.firstName=firstName;
        this.lastName=lastName;

        this.jobTitle=jobTitle;
        this.jobLevel=jobLevel;

        this.email=email;
        this.location=location;

        this.isAdmin=isAdmin;
        this.username=username;
        this.password=password;
        this.roles=roles;
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
