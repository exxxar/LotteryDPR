///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.web.mavenproject6.entities;
//
//
//import java.io.Serializable;
//import java.sql.Date;
//import java.sql.Time;
//import javax.persistence.*;
//import org.hibernate.annotations.GenericGenerator;
///**
// *
// * @author Smitg
// */
//@Entity
//@Table(name = "users_in_queues")
//public class UsersInQueues implements Serializable 
//{
//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name= "increment", strategy= "increment")
//    @Column(name = "id", nullable = false)
//    private Long id;
//    
//     @OneToOne(fetch=FetchType.LAZY)
//     private Queues queue;
//     
//     @OneToOne(fetch=FetchType.LAZY)
//     private Users user;
//     
//     private boolean needToRemind;
//     
//     private Date reminderDate;
//     
//     private Time reminderTime;
//     
//     private String phoneToRemind;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Queues getQueue() {
//        return queue;
//    }
//
//    public void setQueue(Queues queue) {
//        this.queue = queue;
//    }
//
//    public Users getUser() {
//        return user;
//    }
//
//    public void setUser(Users user) {
//        this.user = user;
//    }
//
//    public boolean isNeedToRemind() {
//        return needToRemind;
//    }
//
//    public void setNeedToRemind(boolean needToRemind) {
//        this.needToRemind = needToRemind;
//    }
//
//    public Date getReminderDate() {
//        return reminderDate;
//    }
//
//    public void setReminderDate(Date reminderDate) {
//        this.reminderDate = reminderDate;
//    }
//
//    public Time getReminderTime() {
//        return reminderTime;
//    }
//
//    public void setReminderTime(Time reminderTime) {
//        this.reminderTime = reminderTime;
//    }
//
//    public String getPhoneToRemind() {
//        return phoneToRemind;
//    }
//
//    public void setPhoneToRemind(String phoneToRemind) {
//        this.phoneToRemind = phoneToRemind;
//    }
//      
//     
//}
