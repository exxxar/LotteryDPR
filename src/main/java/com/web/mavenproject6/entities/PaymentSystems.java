/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Татьяна Юрченко
 */
@Entity
@Table(name = "paymentsystems")
public class PaymentSystems implements Serializable
{
      @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    

   
    private long userId;
    
    private Long walletnumber;
    private Long typeofwallet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Users getUser() {
//        return user;
//    }
//
//    public void setUser(Users user) {
//        this.user = user;
//    }

    public Long getWalletnumber() {
        return walletnumber;
    }

    public void setWalletnumber(Long walletnumber) {
        this.walletnumber = walletnumber;
    }

    public Long getTypeofwallet() {
        return typeofwallet;
    }

    public void setTypeofwallet(Long typeofwallet) {
        this.typeofwallet = typeofwallet;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

   
    
}
