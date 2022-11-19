package com.team1.incidentticketsystem.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.team1.incidentticketsystem.models.Ticket2;

@Repository
public interface TicketRepository extends CrudRepository<Ticket2,UUID>
{

}
