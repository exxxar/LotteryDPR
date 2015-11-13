///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.web.mavenproject6.entities;
//
//import java.io.Serializable;
//import javax.persistence.*;
//import org.hibernate.annotations.GenericGenerator;
//import java.util.List;
//
//
///**
// *
// * @author Smitg
// */
//@Entity
//@Table(name = "queues_types")
//public class QueuesTypes implements Serializable 
//{
//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name= "increment", strategy= "increment")
//    @Column(name = "id", nullable = false)
//    private Long id;
//    
//    private String typeName;
//
//    @OneToMany(mappedBy = "queueType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private List<Queues> queues;
//
//    public List<Queues> getQueues() {
//        return queues;
//    }
//
//    public void setQueues(List<Queues> queues) {
//        this.queues = queues;
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
//    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//    
//    
//}
