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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Service;

/**
 *
 * @author Татьяна Юрченко
 */

@Service
public class BasketLogServiceImpl implements BasketLogService {

    @Autowired //подгрузка репозитория
    private BasketRepository basketRepository;

    @PersistenceContext
    private EntityManager em; //запросы

    @Override
    public BasketRepository getRepository() 
    {
        return basketRepository;
    }
  
}
