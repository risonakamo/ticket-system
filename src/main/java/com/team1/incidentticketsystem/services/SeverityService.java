package com.team1.incidentticketsystem.services;

import com.team1.incidentticketsystem.models.UserSeverity;
import com.team1.incidentticketsystem.models.Ticket2;

public class SeverityService
{
    /** compute a severity based on employee level and the given user severity */
    Integer employeeLevelScaledSeverity(Ticket2 ticket)
    {
        return this.userSeverityToInt(ticket.userSeverity);
    }

    /** no scaling applied to severity */
    Integer nonScaledSeverity(Ticket2 ticket)
    {
        return 1;
    }

    /** convert employee level into a multipler */
    Integer employeeLevelToMultiplier(Integer employeeLevel)
    {
        // level 1-3 have same multiplier
        if (employeeLevel<=1)
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
