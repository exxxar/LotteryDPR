/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.service;

import com.mysql.fabric.xmlrpc.base.Data;
import com.web.mavenproject6.entities.BasketLog;
import com.web.mavenproject6.entities.Users;
import com.web.mavenproject6.repositories.BasketRepository;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Татьяна Юрченко
 */
public interface BasketLogService 
{
    long getAddDate(BasketLog id);
    
    long getEndDate(BasketLog id);
    
    void setDate(Date d);
    
    BasketRepository getRepository();
    
    List<BasketLog> findBasketLogByUsers(Users user);

    Users findUsersByBasketLog(BasketLog basketlogid);
    
    
}
