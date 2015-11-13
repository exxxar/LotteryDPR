/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.service;

import com.web.mavenproject6.entities.Roles;
import com.web.mavenproject6.entities.Users;
import com.web.mavenproject6.repositories.RolesRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Татьяна Юрченко
 */
public class RolesServiceImpl implements RolesService{
    
    @Autowired //подгрузка репозитория
    private RolesRepository rolesRepository;

    @PersistenceContext
    private EntityManager em; //запросы
    
    @Override
    public RolesRepository getRepository() 
    {
        return rolesRepository;
    }

    @Override
    public Roles findRolesByUsers(Users user) 
    {
        TypedQuery query = em.createQuery("select r from roles r where r.user.id = ?1", Roles.class);
        query.setParameter(1,user.getId());
        return ((Roles) query.getSingleResult());
    }

    @Override
    public List<Users> findUsersByRoles(Roles role) 
    {
        TypedQuery query = em.createQuery("select r from roles r where r.role.id = ?1", Roles.class);
        query.setParameter(1, role.getId());
        return (List<Users>) query.getResultList();
    }

    @Override
    public Roles findRolesByUsersId(Long id) 
    {
        TypedQuery query = em.createQuery("select r from roles r where r.user.id = ?1", Roles.class);
        query.setParameter(1,id);
        return ((Roles) query.getSingleResult());
    }
    
}
