//package com.team1.incidentticketsystem.models;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//@Entity
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private Long autoUserId;
//
//    @Column (name="FIRST_NAME")
//    private String firstName;
//
//    @Column (name="LAST_Name")
//    private String lastName;
//
//    @Column (name="TITLE")
//    private String title;
//
//    @Column (name="TELEPHONE")
//    private String telephoneNumber;
//
//    @Column (name="EMPLOYEE_NUMBER")
//    private String employeeNumber;
//
//    @Column (name="USERNAME")
//    private String username;
//
//
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//    public String getTelephoneNumber() {
//        return telephoneNumber;
//    }
//
//    public void setTelephoneNumber(String telephoneNumber) {
//        this.telephoneNumber = telephoneNumber;
//    }
//
//    public String getEmployeeNumber() {
//        return employeeNumber;
//    }
//
//    public void setEmployeeNumber(String employeeNumber) {
//        this.employeeNumber = employeeNumber;
//    }
//
//    @Column (name="PASSWORD")
//    private String password;
//
//    @Column (name="mail")
//    private String mail;
//
//    @Transient
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getMail() {
//        return mail;
//    }
//
//    public void setMail(String mail) {
//        this.mail = mail;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
