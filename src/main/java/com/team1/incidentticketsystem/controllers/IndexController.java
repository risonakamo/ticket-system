package com.team1.incidentticketsystem.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.models.TicketType;
import com.team1.incidentticketsystem.models.UserSeverity;
import com.team1.incidentticketsystem.repositories.TicketRepository;

import java.util.List;

@RestController
@RequestMapping("/")
public class IndexController
{
    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/")
    public ResponseEntity<String> index()
    {
        List<String> impactedSystems=new ArrayList<>();
        impactedSystems.add("system1");
        impactedSystems.add("system2");

        Ticket ticket=new Ticket(
            "test",
            1,
            TicketType.INCIDENT_REPORT,
            impactedSystems,
            "something something",
            "",
            UserSeverity.NORMAL
        );

        Ticket2 ticket2=new Ticket2(ticket);

        this.ticketRepository.save(ticket2);

        return ResponseEntity.ok("hello");
    }
}
