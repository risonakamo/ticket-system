package com.team1.incidentticketsystem.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team1.incidentticketsystem.models.Employee;
import com.team1.incidentticketsystem.models.OwnedTickets;
import com.team1.incidentticketsystem.models.Ticket;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.repositories.EmployeeRepository;
import com.team1.incidentticketsystem.repositories.TicketRepository;

@Component
public class TicketService
{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    SeverityService severityService;

    /** create and upload a ticket to database. fails if the ticket does not have a corresponding
     *  employee. returns the UUID of the created ticket */
    public Optional<UUID> createTicket(Ticket ticket)
    {
        Optional<Employee> gotEmployee=this.employeeRepository.findById(ticket.creatorId);

        ticket.fillInFields();

        if (!gotEmployee.isPresent())
        {
            System.out.println(
                "failed to create ticket - could not find employee id: "
                +ticket.creatorId
            );

            return Optional.empty();
        }

        this.severityService.computeSeverity(ticket,gotEmployee.get());

        Ticket2 newTicket=new Ticket2(
            ticket,
            this.severityService.computeSeverity(ticket,gotEmployee.get())
        );

        newTicket.fillInFields();

        System.out.println("creating ticket");
        this.ticketRepository.save(newTicket);

        return Optional.of(newTicket.id);
    }

    /** given a ticket, find it and update it in the database. provide the relevant permissions the
     *  update should be executed with */
    public void updateTicket(Ticket2 ticket,boolean admin,boolean assignedEmployee)
    throws Exception
    {
        if (ticket.id==null)
        {
            System.out.println("did not provide ticket id");
            throw new Exception("did not provide ticket id");
        }

        Optional<Ticket2> foundTicket=this.ticketRepository.findById(ticket.id);

        if (!foundTicket.isPresent())
        {
            System.out.print("could not find ticket with id: "+ticket.id);
            throw new Exception("could not find ticket");
        }

        foundTicket.get().update(ticket,admin,assignedEmployee);

        this.ticketRepository.save(foundTicket.get());
    }

    /** get a ticket by ticket id */
    public Optional<Ticket2> getTicket(UUID ticketId)
    {
        Optional<Ticket2> foundticket=this.ticketRepository.findById(ticketId);

        if (!foundticket.isPresent())
        {
            System.out.println("could not find ticket: "+ticketId);
            return Optional.empty();
        }

        return foundticket;
    }

    /** return all tickets */
    public List<Ticket2> getAllTickets()
    {
        return this.ticketRepository.findAll();
    }

    /** get tickets "owned" by target employee */
    public OwnedTickets getOwnedTickets(UUID employeeId)
    {
        List<Ticket2> createdTickets=this.ticketRepository.findByCreatorId(employeeId);
        List<Ticket2> assignedTickets=this.ticketRepository.findAssignedTickets(employeeId);

        return new OwnedTickets(createdTickets,assignedTickets);
    }

    /** return if specified user id can access ticket id. always true if admin, otherwise only
     *  true if the user either created or is assigned to the target ticket */
    public Boolean checkTicketAccess(UUID userId,UUID ticketId)
    {
        // try to find employee to check if admin
        Optional<Employee> foundemployee=this.employeeRepository.findById(userId);

        // if does not exist, reject
        if (!foundemployee.isPresent())
        {
            System.out.print("rejected ticket access due to employee not existing");
            return false;
        }

        // always return true if admin
        if (foundemployee.get().isAdmin)
        {
            return true;
        }

        // otherwise, find if the ticket was created or was assigned to the user
        List<Ticket2> foundtickets=this.ticketRepository.checkTicketAccess(
            userId,
            ticketId
        );

        // user is able to access the ticket as long as this is not empty
        return !foundtickets.isEmpty();
    }
}
