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

/**
 *
 * @author Татьяна Юрченко
 */
public class BasketLogServiceImpl implements BasketLogService {

    @Autowired //подгрузка репозитория
    private BasketRepository basketRepository;

    @PersistenceContext
    private EntityManager em; //запросы

    @Override
    public long getAddDate(BasketLog id) 
    {
        TypedQuery query = em.createQuery("select b from basketlog b where b.id = ?1", BasketLog.class);
        query.setParameter(1, id);
        return ((BasketLog) query.getSingleResult()).getAdddate();
    }

    @Override
    public long getEndDate(BasketLog id) 
    {
        TypedQuery query = em.createQuery("select b from basketlog b where b.id = ?1", BasketLog.class);
        query.setParameter(1, id);
        return ((BasketLog) query.getSingleResult()).getEnddate();
    }

    @Override
    public void setDate(Date d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BasketRepository getRepository() 
    {
        return basketRepository;
    }

    @Override
    public List<BasketLog> findBasketLogByUsers(Users user) 
    {
        TypedQuery query = em.createQuery("select b from basketlog b where b.user.id = ?1", BasketLog.class);
        query.setParameter(1, user.getId());
        return (List<BasketLog>) query.getResultList();
    }

    @Override
    public Users findUsersByBasketLog(BasketLog basketlogid) 
    {
        TypedQuery query = em.createQuery("select b from basketlog b where b.id = ?1", BasketLog.class);
        query.setParameter(1,basketlogid );
        return null;
    }

}
