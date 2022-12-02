package com.team1.incidentticketsystem.models;

import java.util.List;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.array.ListArrayType;

import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.ArrayList;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.apache.commons.lang3.builder.ToStringBuilder;

/** full ticket with all fields */
@Entity
@TypeDef(
    name="list",
    typeClass=ListArrayType.class
)
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
    @Type(type="list")
    @Column(columnDefinition="uuid[]")
    public List<UUID> assignedEmployees;

    public Ticket2()
    {
        super();
        Date nowtime=new Date();
        this.creationDate=nowtime;
        this.lastUpdateTime=nowtime;
        this.stepsTaken="";
        this.severity=0;
        this.status="created";
        this.opened=true;
    }

    /** construct ticket2 from ticket1 */
    public Ticket2(Ticket ticket,Integer severity)
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
        this.severity=severity;
        this.status="created";
        this.opened=true;

        this.assignedEmployees=new ArrayList<UUID>();
    }

    /** update this ticket with another. only certain fields can be changed, unless provide
     *  admin setting, or assigned employees setting, which allows more fields to be changed. */
    public void update(Ticket2 ticket,Boolean admin,Boolean assignedEmployee)
    {
        if (ticket.title!=null)
        {
            this.title=ticket.title;
        }

        if (ticket.type!=null)
        {
            this.type=ticket.type;
        }

        if (ticket.impactedSystems!=null)
        {
            this.impactedSystems=ticket.impactedSystems;
        }

        if (ticket.description!=null)
        {
            this.description=ticket.description;
        }

        if (ticket.comments!=null)
        {
            this.comments=ticket.comments;
        }

        if (ticket.userSeverity!=null)
        {
            this.userSeverity=ticket.userSeverity;
        }

        // fields that assigned employees or admins can change
        if (admin || assignedEmployee)
        {
            if (ticket.status!=null)
            {
                this.status=ticket.status;
            }

            if (ticket.opened!=null)
            {
                this.opened=ticket.opened;
            }

            if (ticket.lastUpdateTime!=null)
            {
                this.lastUpdateTime=ticket.lastUpdateTime;
            }

            if (ticket.stepsTaken!=null)
            {
                this.stepsTaken=ticket.stepsTaken;
            }

            if (ticket.severity!=null)
            {
                this.severity=ticket.severity;
            }
        }

        // fields that only admin can change
        if (admin)
        {
            if (ticket.creatorId!=null)
            {
                this.creatorId=ticket.creatorId;
            }

            if (ticket.assignedEmployees!=null)
            {
                this.assignedEmployees=ticket.assignedEmployees;
            }

            if (ticket.creationDate!=null)
            {
                this.creationDate=ticket.creationDate;
            }
        }
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }
}
