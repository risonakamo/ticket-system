package com.team1.incidentticketsystem.repositories;

import java.util.UUID;

import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team1.incidentticketsystem.models.Ticket2;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket2,UUID>
{
    List<Ticket2> findAll();

    /** get all tickets created by or assigned to some user */
    @Query(
        value="""
        select * from ticket2
        where
            creator_id = :employeeId
            or :employeeId = any(ticket2.assigned_employees)
        """,
        nativeQuery=true
    )
    List<Ticket2> findOwnedTickets(UUID employeeId);

    /** check if a user can access a ticket */
    @Query(
        value="""
        select * from ticket2
        where
            (
                creator_id = :employeeId
                or :employeeId = any(ticket2.assigned_employees)
            )
            and id = :ticketId
        """,
        nativeQuery=true
    )
    List<Ticket2> checkTicketAccess(UUID employeeId,UUID ticketId);

    /** find all tickets created by someone */
    List<Ticket2> findByCreatorId(UUID creatorId);

    /** find all tickets assigned to some user */
    @Query(value="""
        select * from ticket2
        where :employeeId = any(ticket2.assigned_employees)
    """,
    nativeQuery=true
    )
    List<Ticket2> findAssignedTickets(UUID employeeId);
}
