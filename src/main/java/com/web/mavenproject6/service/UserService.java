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

/**
 *
 * @author Татьяна Юрченко
 */
public interface UserService 
{
    boolean isUserExistByEmail(String email);
    boolean isUserExistByLogin(String login);
    
    void save(Users u);

    void remove(Users u);

    void remove(long id);

    void update(Users u);
    
    Users getinfo(Users id);
    
    Long getNumberOfWallets(Users id);
    
    List<String> getWallets(Users login);
    
    List<PaymentSystems> getWalletNumber(Users id);
    
    String getUserRole(Users id);
    
    String getUserRoleByLogin(Users login);
    
    UserRepository getRepository();
}
