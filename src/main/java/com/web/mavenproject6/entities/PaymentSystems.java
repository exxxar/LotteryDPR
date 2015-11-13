/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Татьяна Юрченко
 */
@Entity
@Table(name = "paymentsystems")
public class PaymentSystems implements Serializable
{
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", nullable = false)
    private Long id;
    
   // @ManyToOne
    private Users userid;
    
    private Long walletnumber;
    private Long typeofwallet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

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
    
}
