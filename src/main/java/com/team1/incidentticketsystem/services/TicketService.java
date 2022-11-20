package com.team1.incidentticketsystem.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.team1.incidentticketsystem.models.Employee;
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

        System.out.println("creating ticket");
        this.ticketRepository.save(newTicket);

        return Optional.of(newTicket.id);
    }
}
