package com.team1.incidentticketsystem.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.userdetails.InetOrgPerson;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserAuthService {
    // public String UserAuthService(Authentication auth) {

    //     InetOrgPerson thing=(InetOrgPerson) auth.getPrincipal();
    //     System.out.println("uid "+thing.getUid());

    //     if (auth.getPrincipal() instanceof LdapUserDetails) {
    //         LdapUserDetails principal = (LdapUserDetails) auth.getPrincipal();
    //         System.out.println("user is " + principal.getUsername());
    //         System.out.println("user is " + principal.getAuthorities());
    //         return "Home page";
    //     } else {
    //         return "redirect:/login";
    //     }
    // }

    public void parseAuth(Authentication auth)
    {
        InetOrgPerson details=(InetOrgPerson) auth.getPrincipal();
        System.out.println("user: "+details.getUsername());
        System.out.println("user auths: "+details.getAuthorities());
        System.out.println("user id: "+details.getUid());
    }
}
