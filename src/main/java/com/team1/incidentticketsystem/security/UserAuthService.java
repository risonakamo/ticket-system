package com.team1.incidentticketsystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

public class UserAuthService {
    public String UserAuthService(Authentication auth) {
        if (auth.getPrincipal() instanceof LdapUserDetails) {
            LdapUserDetails principal = (LdapUserDetails) auth.getPrincipal();
            System.out.println("user is " + principal.getUsername());
            System.out.println("user is " + principal.getAuthorities());
            return "Home page";
        } else {
            return "redirect:/login";
        }
    }
}
