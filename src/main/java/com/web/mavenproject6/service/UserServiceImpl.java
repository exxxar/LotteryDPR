/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.service;

import com.web.mavenproject6.entities.PaymentSystems;
import com.web.mavenproject6.entities.Users;
import com.web.mavenproject6.repositories.UserRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Татьяна Юрченко
 */

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService{

    @Autowired //подгрузка репозитория
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em; //запросы
      
//    @Override
//    public boolean isUserExistByEmail(String email) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.email = ?1", Users.class);
//        query.setParameter(1,email);
//        return  query.getSingleResult()!=null;
//    }
//
//    @Override
//    public boolean isUserExistByLogin(String login) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.login = ?1", Users.class);
//        query.setParameter(1,login);
//        return  query.getSingleResult()!=null;
//    }
//
//    @Override
//    public void save(Users u) 
//    {
//        em.persist(u);
//    }
//
//    @Override
//    public void remove(Users u) 
//    {
//        em.remove(u);
//    }
//
//    @Override
//    public void remove(long id) 
//    {
//        em.remove(id);
//    }
//
//    @Override
//    public void update(Users u) 
//    {
//        em.refresh(u);
//    }
//
//    @Override
//    public Users getinfo(Users id) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.id = ?1", Users.class);
//        query.setParameter(1,id.getId());
//        return ((Users) query.getSingleResult());
//    }
//
//    @Override
//    public Long getNumberOfWallets(Users id) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.id = ?1", Users.class);
//        query.setParameter(1,id.getId());
//        return (long)query.getMaxResults();
//    }
//
//    @Override
//    public List<String> getWallets(Users login) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.user.login = ?1", Users.class);
//        query.setParameter(1, login.getLogin());
//        return (List<String>) query.getResultList();
//    }
//
//    @Override
//    public List<PaymentSystems> getWalletNumber(Users id) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.id = ?1", Users.class);
//        query.setParameter(1,id.getId());
//        return (List<PaymentSystems>) query.getResultList();
//    }
//
//    @Override
//    public String getUserRole(Users id) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.id = ?1", Users.class);
//        query.setParameter(1,id.getId());
//        return "1";
//    }
//
//    @Override
//    public String getUserRoleByLogin(Users login) 
//    {
//        TypedQuery query = em.createQuery("select u from users u where u.id = ?1", Users.class);
//        query.setParameter(1,login.getLogin());
//        return "2";
//    }

    @Override
    public UserRepository getRepository() 
    {
        return userRepository;
    }
    
}
