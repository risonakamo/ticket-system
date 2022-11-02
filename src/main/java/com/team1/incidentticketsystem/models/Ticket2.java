package com.team1.incidentticketsystem.models;

import java.util.List;
import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;

/** full ticket with all fields */
public class Ticket2 extends Ticket
{
    public UUID id;

    // dates
    public Date creationDate;
    public Date lastUpdateTime;

    // description information
    public String stepsTaken;

    // severity information
    public Integer severity;

    // status information
    public String status;
    public Boolean opened;

    // other
    public List<Integer> assignedEmployees;

    /** construct ticket2 from ticket1 */
    Ticket2(Ticket ticket)
    {
        // copy all ticket values
        this.title=ticket.title;
        this.creatorId=ticket.creatorId;
        this.type=ticket.type;
        this.impactedSystems=ticket.impactedSystems;
        this.description=ticket.description;
        this.comments=ticket.description;
        this.userSeverity=ticket.userSeverity;

        // use defaults for the remaining values
        this.id=UUID.randomUUID();

        Date nowtime=new Date();
        this.creationDate=nowtime;
        this.lastUpdateTime=nowtime;

        this.stepsTaken="";
        // todo: compute auto severity
        this.severity=0;
        this.status="created";
        this.opened=true;

        this.assignedEmployees=new ArrayList<Integer>();
    }
}
