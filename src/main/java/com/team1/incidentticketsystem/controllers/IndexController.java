package com.team1.incidentticketsystem.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.github.javafaker.Faker;
import com.team1.incidentticketsystem.models.Employee;
import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.models.TicketType;
import com.team1.incidentticketsystem.models.UserSeverity;
import com.team1.incidentticketsystem.repositories.EmployeeRepository;
import com.team1.incidentticketsystem.repositories.TicketRepository;
import com.team1.incidentticketsystem.services.TicketService;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class IndexController
{
    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<String> index(@RequestAttribute("huh") String thing)
    {
        System.out.println("got arg "+thing);

        System.out.println(
            this.ticketService.getOwnedTickets(
                UUID.fromString("9c3c86ec-ec8a-4640-a7c3-6ceea40e37cd")
            )
        );

        System.out.println(
            this.ticketService.getOwnedTickets(
                UUID.fromString("f0cc15a4-1075-4c89-a293-6928ea924fcf")
            )
        );

        return ResponseEntity.ok("hello");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@RequestHeader("testthing") String testparam)
    {
        System.out.println("got param: "+testparam);
        return ResponseEntity.ok("hello2");
    }
}
