package com.team1.incidentticketsystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.Arrays;
import java.util.HashMap;

import com.team1.incidentticketsystem.models.EmployeeUserDetails;
import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.security.GetIdFromAuth;
import com.team1.incidentticketsystem.services.TicketService;

@RestController
@RequestMapping("tickets")
public class TicketController {
	private final TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createTicket(@RequestBody Ticket createTicketBody, Authentication auth) {
		// get the user's id from the authentication
		createTicketBody.creatorId = GetIdFromAuth.getIdFromAuth(auth);
		Optional<UUID> ticketIdOpt = ticketService.createTicket(createTicketBody);

		if (ticketIdOpt.isPresent()) {
			UUID ticketId = ticketIdOpt.get();
			// TODO Add the appropriate return message
			return ResponseEntity.ok("" + ticketId);
		} else {
			// TODO add the appropriate error message "Ticket not created"
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	private UUID getIdFromAuth(Authentication auth) {
//		Object principal = auth.getPrincipal();
//
//		if (principal instanceof EmployeeUserDetails) {
//			EmployeeUserDetails userDetails = (EmployeeUserDetails) principal;
//			return userDetails.getId();
//		}
//		
//		// change this to throw an appropriate exception
//		return null;
//	}

//     @PutMapping("/update/{id}")
//     public ResponseEntity<?> updateTicket(@PathVariable String id)
//     {
//
//     }
//
//     @GetMapping("/tickets/{id}")
//     public ResponseEntity<?> getTicket(@PathVariable String id)
//     {

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
