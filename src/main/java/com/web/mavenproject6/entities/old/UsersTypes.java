///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.web.mavenproject6.entities;
//
//import java.io.Serializable;
//
//import javax.persistence.*;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// *
// * @author Smitg
// */
//@Entity
//@Table(name = "users_types")
//public class UsersTypes implements Serializable 
//{
//    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name= "increment", strategy= "increment")
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    private String TypeName;
//    
//    //двунаправленность. Решил что не нужна.
//    
////    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
////    private List<Users> users;
////
////    public List<Users> getUsers() {
////        return users;
////    }
////
////    public void setUsers(List<Users> users) {
////        this.users = users;
// //   }
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
//        return TypeName;
//    }
//
//    public void setTypeName(String TypeName) {
//        this.TypeName = TypeName;
//    }
//}
