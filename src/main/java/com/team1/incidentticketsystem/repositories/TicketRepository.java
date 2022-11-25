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

    List<Ticket2> findByCreatorId(UUID creatorId);

    @Query(value="""
        select * from ticket2
        where :employeeId = any(ticket2.assigned_employees)
    """,
    nativeQuery=true
    )
    List<Ticket2> findAssignedTickets(UUID employeeId);
}
