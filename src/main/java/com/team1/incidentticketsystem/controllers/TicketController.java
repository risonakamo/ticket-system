package com.team1.incidentticketsystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
