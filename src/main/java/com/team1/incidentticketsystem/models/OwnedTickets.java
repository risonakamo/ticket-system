package com.team1.incidentticketsystem.models;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OwnedTickets
{
    List<Ticket2> createdTickets;
    List<Ticket2> assignedTickets;

    public OwnedTickets(List<Ticket2> createdTickets,List<Ticket2> assignedTickets)
    {
        this.createdTickets=createdTickets;
        this.assignedTickets=assignedTickets;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
