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
@Entity//аннотация, показывающая менеджеру работы с бд, чтчо данный класс является таблицей,а поля класса - полями таблицы
@Table(name = "users")//задает имя таблицы в базе данных
public class Users implements Serializable { //создание класса таблицы с уникальным идентификатором

    @Id//указывает менеджеру, что данное поле является полем идентификации элемента в таблице
    @GeneratedValue(strategy = GenerationType.AUTO) //тип индекса - автоинкримент
    private Long id;//тип в таблицы - число
    
    private String fio;   //строковый тип 0..255 символов, без ограничений, фио пользователя
    private String adress;  //строковый тип 0..255 символов, без ограничений, адресс пользователя
    private String telephone;  //строковый тип 0..255 символов, без ограничений, телефонный номер пользователя
    private String email;  //строковый тип 0..255 символов, без ограничений, адресс электронной почты пользователя
    private String password;  //строковый тип 0..255 символов, без ограничений, пароль пользователя
    private int age; 
    private Long numberoftikets; //целочисленный тип, количество билетов
    private Long discount;//целочисленный тип, процент скидки пользователя
    private long regdate;//целочисленный тип, представление даты и времени в виде числа, дата регистрации
    private long lastonline;//целочисленный тип, представление даты и времени в виде числа, дата последнего входа в систему
    private boolean verifiedAccount;//логический тип, подтвержденный аккаунт
    private double summaryCash;//вещественный тип, отображение суммы на счету пользователя
    
    @Column(unique = true) //уникальное поле
    private String login; //строковый тип 0..255 символов, является уникальным поле, отвечающим за вход пользователя


    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}) //аннотация, связывающая каскадно таблицу тип пользвоателя с пользователем связью 1 к 1, ключевое поле - user
    private UsersTypes userType;// таблица типов пользователя, поле необходимо для связывания

    private boolean accountExpired; //логический тип, аккаунт действителен
    private boolean accountLocked;//логический тип, аккунт не блокирован
    private boolean enabled;//логический тип, аккаунт активне

  
    
    @OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}) //аннотация, связывающая каскадкно таблицу пользователей и таблиу ролей пользователя 
    private Roles role;// таблица ролей пользователя, поле необходимо для связывания

    //ниже прежставлены набор функций установки и получения данных из выше перечисленных полей таблицы
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

    public double getSummaryCash() {
        return summaryCash;
    }

    public void setSummaryCash(double summaryCash) {
        this.summaryCash = summaryCash;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

   
  

 
}
