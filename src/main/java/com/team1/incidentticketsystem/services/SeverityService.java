package com.team1.incidentticketsystem.services;

import com.team1.incidentticketsystem.models.UserSeverity;

import java.util.List;

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
        return this.scaledSeverity(ticket,creator);
        // return this.nonScaledSeverity(ticket,creator);
    }

    /** compute severity based on scaling functions */
    Integer scaledSeverity(Ticket ticket,Employee creator)
    {
        return (int) Math.round(
            this.userSeverityToInt(ticket.userSeverity)
            *this.employeeLevelToMultiplier(creator.jobLevel)
            *this.locationSeverityScale(creator.location)
            *this.impactedSystemsScaleMultiple(ticket.impactedSystems)
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

    /** return multiplier based on location */
    double locationSeverityScale(String location)
    {
        switch (location)
        {
            case "HQ":
            case "main server":
            case "root server":
            return 10;

            case "cdn server":
            return .5;

            case "bob's server":
            return -1;
        }

        return 1;
    }

    /** return multiplier for impacted systems */
    double impactedSystemsScale(String system)
    {
        switch (system)
        {
            case "server0":
            return 2;

            case "cdn server":
            return .5;
        }

        return 1;
    }

    /** calculate total multiplier for all systems impacted */
    double impactedSystemsScaleMultiple(List<String> systems)
    {
        double totalMultiplier=0;

        for (String system:systems)
        {
            totalMultiplier+=this.impactedSystemsScale(system);
        }

        return totalMultiplier;
    }
}
