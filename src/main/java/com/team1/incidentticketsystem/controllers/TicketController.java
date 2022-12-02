package com.team1.incidentticketsystem.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpsRedirectSpec;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.HashMap;

import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.security.UserAuthService;
import com.team1.incidentticketsystem.services.TicketService;

@RestController
@RequestMapping("tickets")
public class TicketController
{
    @Autowired
    TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(
        @RequestBody Ticket createTicketBody,
        @RequestHeader("employee-id") String employeeId
    )
    {
        UUID uid=UUID.fromString(employeeId);
        createTicketBody.creatorId=uid;
        Optional<UUID> madeTicket=this.ticketService.createTicket(createTicketBody);

        if (!madeTicket.isPresent())
        {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("failed to create ticket");
        }

        return ResponseEntity.ok("created ticket: "+madeTicket.get());
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<?> updateTicket(@PathVariable String id)
    // {

    // }

    // @GetMapping("/tickets/{id}")
    // public ResponseEntity<?> getTicket(@PathVariable String id)
    // {

    // }

    // @GetMapping("/")
    // public ResponseEntity<?> getAllTickets()
    // {

    // }

    // @GetMapping("/get-owned")
    // public ResponseEntity<?> getOwnedTickets()
    // {

    // }

    // @GetMapping("/get-owned/{id}")
    // public ResponseEntity<?> getOwnedTicketsWithId(@PathVariable String id)
    // {

    // }
}
