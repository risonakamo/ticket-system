package com.team1.incidentticketsystem.models;

import java.util.List;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

import java.util.ArrayList;

/** full ticket with all fields */
@Entity
public class Ticket2 extends Ticket
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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
    @Type(type="list-array")
    @Column(
        columnDefinition="integer[]"
    )
    public List<Integer> assignedEmployees;

    public Ticket2()
    {

    }

    /** construct ticket2 from ticket1 */
    public Ticket2(Ticket ticket)
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
