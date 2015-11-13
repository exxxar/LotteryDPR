///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.web.mavenproject6.entities;
//
//import java.io.Serializable;
//import java.sql.Date;
//import javax.persistence.*;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// *
// * @author Smitg
// */
//@Entity
//@Table(name = "users")
//public class Users implements Serializable 
//{
//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name= "increment", strategy= "increment")
//    @Column(name = "id", nullable = false)
//    private Long id;
//    
//    private String login;
//    
//    private String email;
//    
//    private String password;
//    
//    private String name;
//    
//    private String surname;
//    
//    private String thirdname;
//    
//    private String phoneNumber;
//    
//    private boolean online;
//    
//    private Date registrationDate;
//    
//    private Date lastOnlineDate;
//    
//    private boolean verifiedAccount;
//    
//    @ManyToOne
//   // @JoinColumn(name="UserTypeID")
//    private UsersTypes userType;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getThirdname() {
//        return thirdname;
//    }
//
//    public void setThirdname(String thirdname) {
//        this.thirdname = thirdname;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public boolean isOnline() {
//        return online;
//    }
//
//    public void setOnline(boolean online) {
//        this.online = online;
//    }
//
//    public Date getRegistrationDate() {
//        return registrationDate;
//    }
//
//    public void setRegistrationDate(Date registrationDate) {
//        this.registrationDate = registrationDate;
//    }
//
//    public Date getLastOnlineDate() {
//        return lastOnlineDate;
//    }
//
//    public void setLastOnlineDate(Date lastOnlineDate) {
//        this.lastOnlineDate = lastOnlineDate;
//    }
//
//    public UsersTypes getUserType() {
//        return userType;
//    }
//
//    public void setUserType(UsersTypes userType) {
//        this.userType = userType;
//    }
//
//    public boolean isVerifiedAccount() {
//        return verifiedAccount;
//    }
//
//    public void setVerifiedAccount(boolean verifiedAccount) {
//        this.verifiedAccount = verifiedAccount;
//    }
//    
//    
//    
//}
