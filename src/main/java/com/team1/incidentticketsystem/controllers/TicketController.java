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

import javax.imageio.plugins.tiff.GeoTIFFTagSet;

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

    /** request to create a ticket. employee uid must be provided via auth or somehow.
     *  ensures the ticket is filled out with the creator id. calls ticket service to
     *  create the ticket and returns the id of the ticket when successful */
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

    /** update a ticket. give the requested details to update a target ticket. must have id
     *  of the ticket filled in. employee id must be provided somehow to determine if the ticket
     *  belongs to the target employee or if the employee has admin permissions */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTicket(
        @RequestBody Ticket2 updateRequest,
        @RequestHeader("employee-id") UUID employeeId
    )
    {

    }

    /** try to get a single ticket. fail if could not find the ticket */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicket(@PathVariable String id)
    {
        Optional<Ticket2> gotticket=this.ticketService.getTicket(UUID.fromString(id));

        if (!gotticket.isPresent())
        {
            ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("could not find ticket");
        }

        return ResponseEntity.ok(gotticket.get());
    }

    /** get all tickets */
    @GetMapping("/")
    public ResponseEntity<?> getAllTickets()
    {
        return ResponseEntity.ok(this.ticketService.getAllTickets());
    }

    // @GetMapping("/get-owned")
    // public ResponseEntity<?> getOwnedTickets()
    // {

    // }

    // @GetMapping("/get-owned/{id}")
    // public ResponseEntity<?> getOwnedTicketsWithId(@PathVariable String id)
    // {

    // }
}
