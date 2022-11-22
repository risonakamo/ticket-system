package com.team1.incidentticketsystem.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TestFilter
extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    )
    throws ServletException,IOException
    {
        System.out.println("filter trigger");
        request.setAttribute("huh","something");

        filterChain.doFilter(request,response);
    }
}
