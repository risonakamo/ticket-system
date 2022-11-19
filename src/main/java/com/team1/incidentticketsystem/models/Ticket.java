package com.team1.incidentticketsystem.models;

import java.util.List;

/** partial ticket that user is able to fill out */
public class Ticket
{
    // indentification info
    public String title;
    public Integer creatorId;
    public TicketType type;

    // description information
    public List<String> impactedSystems;
    public String description;
    public String comments;

    // severity information
    public UserSeverity userSeverity;

    public Ticket()
    {

    }

    /** create a ticket with all required fields */
    public Ticket(
        String title,
        Integer creatorId,
        TicketType type,
        List<String> impactedSystems,
        String description,
        String comments,
        UserSeverity severity
    )
    {
        this.title=title;
        this.creatorId=creatorId;
        this.type=type;
        this.impactedSystems=impactedSystems;
        this.description=description;
        this.comments=comments;
        this.userSeverity=severity;
    }
}
