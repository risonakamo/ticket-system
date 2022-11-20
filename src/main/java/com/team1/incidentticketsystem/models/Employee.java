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
    public String password;

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
        String password
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
        this.password=password;
    }
}
