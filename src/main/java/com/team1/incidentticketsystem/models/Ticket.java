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
}
