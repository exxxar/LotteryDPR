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
@Table(name = "basketlog")
public class BasketLog implements Serializable 
{
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne
    private Users user;
    
    private String winnings;
    private Date adddate;
    private Date enddate;
    
    
    @OneToOne
    private Stock item;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getWinnings() {
        return winnings;
    }

    public void setWinnings(String winnings) {
        this.winnings = winnings;
    }

    public Stock getItem() {
        return item;
    }

    public void setItem(Stock item) {
        this.item = item;
    }

    public Date getAdddate() {
        return adddate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
    
    
}
