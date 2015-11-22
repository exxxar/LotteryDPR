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
    
    private long userId;
    
    private String winnings;
    private long adddate;
    private long enddate;
    
    @OneToOne(mappedBy = "basketLog",cascade = CascadeType.ALL)
    private Stock stock;
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public String getWinnings() {
        return winnings;
    }

    public void setWinnings(String winnings) {
        this.winnings = winnings;
    }

    public long getAdddate() {
        return adddate;
    }

    public void setAdddate(long adddate) {
        this.adddate = adddate;
    }

    public long getEnddate() {
        return enddate;
    }

    public void setEnddate(long enddate) {
        this.enddate = enddate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

 

 

    
    
}
