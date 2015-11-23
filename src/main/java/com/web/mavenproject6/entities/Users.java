/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author Татьяна Юрченко
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String fio;   
    private String adress;
    private String telephone;
    private String email;
    private String password;
    private Long numberoftikets;
    private Long discount;
    private long regdate;
    private long lastonline;
    private boolean verifiedAccount;
    
    @Column(unique = true)
    private String login;


    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    private UsersTypes userType;

    private boolean accountExpired;
    private boolean accountLocked;
    private boolean enabled;

  
    
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
    private Roles role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
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

    public long getRegdate() {
        return regdate;
    }

    public void setRegdate(long regdate) {
        this.regdate = regdate;
    }

    public long getLastonline() {
        return lastonline;
    }

    public void setLastonline(long lastonline) {
        this.lastonline = lastonline;
    }

    public boolean isVerifiedAccount() {
        return verifiedAccount;
    }

    public void setVerifiedAccount(boolean verifiedAccount) {
        this.verifiedAccount = verifiedAccount;
    }

    public UsersTypes getUserType() {
        return userType;
    }

    public void setUserType(UsersTypes userType) {
        this.userType = userType;
    }

   
  

 
}
