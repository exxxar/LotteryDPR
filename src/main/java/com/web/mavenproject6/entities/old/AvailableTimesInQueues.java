///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.web.mavenproject6.entities;
//
//import java.io.Serializable;
//import java.sql.Time;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// *
// * @author Smitg
// */
//@Entity
//@Table(name = "available_times_in_queues")
//public class AvailableTimesInQueues implements Serializable 
//{
//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name= "increment", strategy= "increment")
//    @Column(name = "id", nullable = false)
//    private Long id;
//    
//    @ManyToOne
//    private Queues queue;
//    
//    private Time time;
//    
//    private boolean busy; 
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
//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }
//
//    public boolean isBusy() {
//        return busy;
//    }
//
//    public void setBusy(boolean busy) {
//        this.busy = busy;
//    }
//    
//    
//}
