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
@Table(name = "users")
public class Users implements Serializable 
{
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", nullable = false)
    private Long id;
    
    private String fio;   
    private String adress;
    private String telephone;
    private String email;
    private String password;
    private Long numberoftikets;
    private Long discount;
    private Date regdate;
    private Date lastonline;
    private boolean verifiedAccount;
    
    @Column(unique = true)
    private String login;
    
    @ManyToOne
    private Roles role;
    
    @ManyToOne
    private UsersTypes userType;

     //  @OneToMany(mappedBy = "userid", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private PaymentSystems paymentSystems;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getNumberoftikets() {
        return numberoftikets;
    }

    public void setNumberoftikets(Long numberoftikets) {
        this.numberoftikets = numberoftikets;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public void setLastonline(Date lastonline) {
        this.lastonline = lastonline;
    }

    public void setVerifiedAccount(boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }

    public void setUserType(UsersTypes userType) {
        this.userType = userType;
    }

    public Date getRegdate() {
        return regdate;
    }

    public Date getLastonline() {
        return lastonline;
    }

    public boolean isVerifiedAccount() {
        return verifiedAccount;
    }

    public UsersTypes getUserType() {
        return userType;
    }

    public PaymentSystems getPaymentSystems() {
        return paymentSystems;
    }

    public void setPaymentSystems(PaymentSystems paymentSystems) {
        this.paymentSystems = paymentSystems;
    }

    
}