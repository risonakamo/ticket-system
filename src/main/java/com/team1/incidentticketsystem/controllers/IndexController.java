package com.team1.incidentticketsystem.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.team1.incidentticketsystem.models.Employee;
import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.models.TicketType;
import com.team1.incidentticketsystem.models.UserSeverity;
import com.team1.incidentticketsystem.repositories.EmployeeRepository;
import com.team1.incidentticketsystem.repositories.TicketRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class IndexController
{
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public ResponseEntity<String> index()
    {
        List<String> impactedSystems=new ArrayList<>();
        impactedSystems.add("system1");
        impactedSystems.add("system2");

        Ticket ticket=new Ticket(
            "test",
            UUID.randomUUID(),
            TicketType.INCIDENT_REPORT,
            impactedSystems,
            "something something",
            "",
            UserSeverity.NORMAL
        );

        Ticket2 ticket2=new Ticket2(ticket);

        System.out.println(ticket2);
        this.ticketRepository.save(ticket2);

        Employee employee=new Employee(
            "person",
            "test",
            "admin",
            5,
            "admin@admin.com",
            "online",
            true,
            "admin"
        );

        this.employeeRepository.save(employee);

        return ResponseEntity.ok("hello");
    }
}
