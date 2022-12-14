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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;

import java.lang.StackWalker.Option;
import java.util.HashMap;
import java.util.List;

import com.team1.incidentticketsystem.models.Employee;
import com.team1.incidentticketsystem.models.EmployeeUserDetails;
import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.repositories.EmployeeRepository;
import com.team1.incidentticketsystem.repositories.TicketRepository;
import com.team1.incidentticketsystem.security.GetIdFromAuth;
import com.team1.incidentticketsystem.services.TicketService;

@RestController
@RequestMapping("tickets")
public class TicketController
{
    @Autowired
    TicketService ticketService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TicketRepository ticketRepository;

    @GetMapping("/test")
    public String test(Authentication auth)
    {
        return "tickettea";
    }

    /** request to create a ticket. employee uid must be provided via auth or somehow.
     *  ensures the ticket is filled out with the creator id. calls ticket service to
     *  create the ticket and returns the id of the ticket when successful */
    @PostMapping("/create")
    public ResponseEntity<?> createTicket(
        @RequestBody Ticket createTicketBody,
        Authentication auth
    )
    {
        UUID uid=GetIdFromAuth.getIdFromAuth(auth);

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
     *  belongs to the target employee or if the employee has admin permissions
     *
     *  behaviour:
     *  - if the employee requesting an update does not exist, fails.
     *  - if the employee requesting an update is an admin, performs the ticket update with admin
     *    privledges
     *  - if employee is not an admin, the employee must either be the creator of the target ticket
     *    or have been assigned to the ticket. if neither of these are true, fails
     *  - once ensure the update requester is allowed to update the ticket, performs the ticket
     *    update with assigned employee privledges or regular update based on assignment status */
    @PutMapping("/update")
    public ResponseEntity<?> updateTicket(
        @RequestBody Ticket2 updateRequest,
        Authentication auth
    )
    throws Exception
    {
        UUID employeeId=GetIdFromAuth.getIdFromAuth(auth);

        // fail if no target ticket id specified
        if (updateRequest.id==null)
        {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("did not provide target ticket id to update");
        }

        // try to find the requesting employee. fail if does not exist, because need this information
        // to determine what level of update to use
        Optional<Employee> foundemployee=this.employeeRepository.findById(employeeId);

        if (!foundemployee.isPresent())
        {
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("failed to find employee id");
        }

        // if employee is admin, perform the update
        if (foundemployee.get().isAdmin)
        {
            this.ticketService.updateTicket(updateRequest,true,true);
            return ResponseEntity.ok("updated ticket as admin");
        }

        // otherwise, the user must have created or is assigned to the ticket to be able
        // ot update it. check for that
        List<Ticket2> foundtickets=this.ticketRepository.checkTicketAccess(
            employeeId,
            updateRequest.id
        );

        if (foundtickets.isEmpty())
        {
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("you are not allowed to access ticket "+updateRequest.id);
        }

        Ticket2 foundticket=foundtickets.get(0);

        // determine if the ticket is assigned to the user requesting an update
        Boolean assigned=foundticket.assignedEmployees.contains(employeeId);

        // perform the update with the found information
        this.ticketService.updateTicket(updateRequest,false,assigned);

        if (assigned)
        {
            return ResponseEntity.ok("updated ticket as assigned employee");
        }

        else
        {
            return ResponseEntity.ok("updated ticket as ticket creator");
        }
    }

    /** try to get a single ticket. fail if could not find the ticket. also, the user requesting the ticket
     *  must be allowed to access the ticket */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTicket(
        @PathVariable UUID id,
        Authentication auth
    )
    {
        UUID employeeId=GetIdFromAuth.getIdFromAuth(auth);

        if (!this.ticketService.checkTicketAccess(employeeId,id))
        {
            return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("not allowed to access specified ticket");
        }

        Optional<Ticket2> gotticket=this.ticketService.getTicket(id);

        if (!gotticket.isPresent())
        {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("could not find ticket");
        }

        return ResponseEntity.ok(gotticket.get());
    }

    /** get all tickets */
    @GetMapping("/")
    public ResponseEntity<?> getAllTickets(Authentication auth)
    {
        UUID employeeId=GetIdFromAuth.getIdFromAuth(auth);

        Optional<ResponseEntity<String>> adminCheckResult=this.adminCheck(employeeId);

        if (adminCheckResult.isPresent())
        {
            return adminCheckResult.get();
        }

        return ResponseEntity.ok(this.ticketService.getAllTickets());
    }

    /** get all tickets that were created by the person calling this route */
	@GetMapping("/own-created")
	public ResponseEntity<?> getOwnCreated(Authentication auth)
	{
        UUID employeeId=GetIdFromAuth.getIdFromAuth(auth);

        return ResponseEntity.ok(this.ticketRepository.findByCreatorId(employeeId));
	}

    /** get all tickets that were assigned to the person calling this route */
	@GetMapping("/own-assigned")
	public ResponseEntity<?> getOwnAssigned(Authentication auth)
	{
        UUID employeeId=GetIdFromAuth.getIdFromAuth(auth);

        return ResponseEntity.ok(this.ticketRepository.findAssignedTickets(employeeId));
	}

    /** get all tickets created by target id. only usable by admin */
	@GetMapping("/created/{id}")
	public ResponseEntity<?> getCreatedById(
        @PathVariable UUID id,
        Authentication auth
    )
	{
        UUID employeeId=GetIdFromAuth.getIdFromAuth(auth);

        Optional<ResponseEntity<String>> adminCheckResult=this.adminCheck(employeeId);

        if (adminCheckResult.isPresent())
        {
            return adminCheckResult.get();
        }

        return ResponseEntity.ok(this.ticketRepository.findByCreatorId(id));
	}

	@GetMapping("/assigned/{id}")
	public ResponseEntity<?> getAssignedById(
        @PathVariable UUID id,
        Authentication auth
    )
	{
        UUID employeeId=GetIdFromAuth.getIdFromAuth(auth);

        Optional<ResponseEntity<String>> adminCheckResult=this.adminCheck(employeeId);

        if (adminCheckResult.isPresent())
        {
            return adminCheckResult.get();
        }

        return ResponseEntity.ok(this.ticketRepository.findAssignedTickets(id));
	}

    /** check if employee is admin. returns empty if all clear. if error occured, returns an http status */
    private Optional<ResponseEntity<String>> adminCheck(UUID employeeId)
    {
        Optional<Employee> foundemployee=this.employeeRepository.findById(employeeId);

        if (!foundemployee.isPresent())
        {
            return Optional.of(ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("failed to find employee id"));
        }

        if (!foundemployee.get().isAdmin)
        {
            return Optional.of(ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("can only be accessed by admin"));
        }

        return Optional.empty();
    }
}
