package com.team1.incidentticketsystem.services;

import com.team1.incidentticketsystem.models.UserSeverity;

import org.springframework.stereotype.Component;

import com.team1.incidentticketsystem.models.Employee;
import com.team1.incidentticketsystem.models.Ticket2;
import com.team1.incidentticketsystem.models.Ticket;

@Component
public class SeverityService
{
    /** callable compute severity. ideally can take configuration information to choose the severity
     *  calculation function */
    public Integer computeSeverity(Ticket ticket,Employee creator)
    {
        return this.employeeLevelScaledSeverity(ticket,creator);
        // return this.nonScaledSeverity(ticket,creator);
    }

    /** compute a severity based on employee level and the given user severity */
    Integer employeeLevelScaledSeverity(Ticket ticket,Employee creator)
    {
        return (
            this.userSeverityToInt(ticket.userSeverity)
            *this.employeeLevelToMultiplier(creator.jobLevel)
        );
    }

    /** no scaling applied to severity */
    Integer nonScaledSeverity(Ticket ticket,Employee creator)
    {
        return this.userSeverityToInt(ticket.userSeverity);
    }

    /** convert employee level into a multipler */
    Integer employeeLevelToMultiplier(Integer employeeLevel)
    {
        // level 1-3 have same multiplier
        if (employeeLevel<=3)
        {
            return 1;
        }

        // level 4
        else if (employeeLevel==4)
        {
            return 3;
        }

        // level 5
        else if (employeeLevel>=5)
        {
            return 10;
        }

        System.out.println("somehow unknown employee level: "+employeeLevel);
        return 0;
    }

    /** convert user severity to int */
    Integer userSeverityToInt(UserSeverity userSeverity)
    {
        switch (userSeverity)
        {
            case LOW:
            return 1;

            case NORMAL:
            return 2;

            case HIGH:
            return 3;

            case CRITICAL:
            return 4;
        }

        System.out.println("unknown severity: "+userSeverity.toString());
        return 0;
    }
}
