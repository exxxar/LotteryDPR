///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.web.mavenproject6.entities;
//
//import java.io.Serializable;
//import java.util.List;
//import javax.persistence.*;
//import org.hibernate.annotations.GenericGenerator;
///**
// *
// * @author Smitg
// */
//@Entity
//@Table(name = "queues")
//public class Queues implements Serializable 
//{
//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name= "increment", strategy= "increment")
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @ManyToOne
//    private Users author;
//    
//    @ManyToOne
//    private QueuesTypes queueType;
//    
//    @OneToMany(mappedBy = "queue", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<AvailableTimesInQueues> availableTimes;
//    
//    private boolean active;
//    
//    public List<AvailableTimesInQueues> getAvailableTimes() {
//        return availableTimes;
//    }
//
//    public void setAvailableTimes(List<AvailableTimesInQueues> availableTimes) {
//        this.availableTimes = availableTimes;
//    }  
//    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Users getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Users author) {
//        this.author = author;
//    }
//
//    public QueuesTypes getQueueType() {
//        return queueType;
//    }
//
//    public void setQueueType(QueuesTypes queueType) {
//        this.queueType = queueType;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//}
