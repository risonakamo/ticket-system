package com.team1.incidentticketsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.HashMap;

import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;

@RestController
@RequestMapping("tickets")
public class TicketController
{
    // todo: replace with database
    public Map<Integer,Ticket2> tickets=new HashMap<Integer,Ticket2>();

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(@RequestBody Ticket createTicketBody)
    {

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable String id)
    {

    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<?> getTicket(@PathVariable String id)
    {

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllTickets()
    {

    }

    @GetMapping("/get-owned")
    public ResponseEntity<?> getOwnedTickets()
    {

    }

    @GetMapping("/get-owned/{id}")
    public ResponseEntity<?> getOwnedTicketsWithId(@PathVariable String id)
    {

    }
}
