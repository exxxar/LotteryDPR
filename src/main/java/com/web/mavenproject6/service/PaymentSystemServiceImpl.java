/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.service;

import com.web.mavenproject6.entities.PaymentSystems;
import com.web.mavenproject6.entities.Users;
import com.web.mavenproject6.repositories.PaymentRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Татьяна Юрченко
 */
public class PaymentSystemServiceImpl implements PaymentSystemService{

    @Autowired //подгрузка репозитория
    private PaymentRepository paymentRepository;

    @PersistenceContext
    private EntityManager em; //запросы
   
    @Override
    public PaymentRepository getRepository() 
    {        
        return paymentRepository;
    }

    @Override
    public Users findUsersByPaymentSystems(PaymentSystems id) 
    {
        TypedQuery query = em.createQuery("select p from paymentsystems p where p.id = ?1", PaymentSystems.class);
        query.setParameter(1,id);
        return ((PaymentSystems) query.getSingleResult()).getUserid();
    }

    @Override
    public List<PaymentSystems> findPaymentSystemsByUsers(Users user) 
    {
        TypedQuery query = em.createQuery("select p from paymentsystems p where b.user.id = ?1", PaymentSystems.class);
        query.setParameter(1, user.getId());
        return (List<PaymentSystems>) query.getResultList();
    }
    
}
