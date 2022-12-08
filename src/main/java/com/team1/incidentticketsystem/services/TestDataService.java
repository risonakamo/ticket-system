package com.team1.incidentticketsystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.team1.incidentticketsystem.models.Employee;
import com.team1.incidentticketsystem.repositories.EmployeeRepository;
import java.util.List;

@Component
public class TestDataService
{
    @Autowired
    EmployeeRepository employeeRepository;

    // create admin employee. does nothing if already exists
    public void addAdmin()
    {
        Optional<List<Employee>> foundadmin=this.employeeRepository.findByEmail(
            "admin@admin.com"
        );

        if (foundadmin.isPresent() && foundadmin.get().size()>0)
        {
            return;
        }

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

        Employee admin=new Employee(
            "admin",
            "person",
            "admin",
            5,
            "admin@admin.com",
            "online",
            true,
            "admin",
            encoder.encode("password"),
            "ROLE_ADMIN"
        );

        this.employeeRepository.save(admin);
    }

    //create random employees
    public void createEmployees(Integer amount)
    {
        Faker faker=new Faker();

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

        for (int i=0;i<amount;i++)
        {
            Employee employee=new Employee(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.job().title(),
                faker.random().nextInt(1,5),
                faker.internet().emailAddress(),
                faker.address().fullAddress(),
                false,
                faker.name().username(),
                encoder.encode("password"),
                "ROLE_USER"
            );

            this.employeeRepository.save(employee);
        }
    }
}
