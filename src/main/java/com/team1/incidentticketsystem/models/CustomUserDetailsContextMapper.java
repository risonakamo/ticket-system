//package com.team1.incidentticketsystem.models;
//
//import org.springframework.ldap.core.DirContextOperations;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
//
//import java.util.Collection;
//
//public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {
//    @Override
//    public UserDetails CustomUserDetailsContextMapper(DirContextOperations ctx, String username,
//                                          Collection<? extends GrantedAuthority> authorities) {
//        User user = new User();
//        user.setFirstName(ctx.getStringAttribute("givenName"));
//        user.setLastName(ctx.getStringAttribute("sn"));
//        user.setTitle(ctx.getStringAttribute("title"));
//        user.setMail(ctx.getStringAttribute("mail"));
//        user.setUsername(username);
//        user.setAuthorities(authorities);
//        return user;
//
//    }
//}
