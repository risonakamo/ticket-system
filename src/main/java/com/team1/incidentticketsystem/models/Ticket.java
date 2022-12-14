package com.team1.incidentticketsystem.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

/** partial ticket that user is able to fill out */
@MappedSuperclass
public class Ticket
{
    // indentification info
    public String title;
    // should match employee id
    public UUID creatorId;
    public TicketType type;

    // description information
    @Type(type="list")
    @Column(columnDefinition="text[]")
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
        UUID creatorId,
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

    /** fill in fields of ticket if missing */
    public void fillInFields()
    {
        if (this.title==null)
        {
            this.title="";
        }

        if (this.type==null)
        {
            this.type=TicketType.INCIDENT_REPORT;
        }

        if (this.impactedSystems==null)
        {
            this.impactedSystems=new ArrayList<>();
        }

        if (this.description==null)
        {
            this.description="";
        }

        if (this.comments==null)
        {
            this.comments="";
        }

        if (this.userSeverity==null)
        {
            this.userSeverity=UserSeverity.LOW;
        }
    }
}
