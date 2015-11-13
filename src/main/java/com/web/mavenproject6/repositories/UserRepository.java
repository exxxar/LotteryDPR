/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.repositories;

import com.web.mavenproject6.entities.Users;
import java.lang.annotation.Documented;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Aleks
 */
public interface UserRepository extends CrudRepository<Users, Long>{


    String findPasswordByLogin(String login);

    String findPasswordByEmail(String email);

    String findEmailByLogin(String login);

    String findLoginByEmail(String email);

    //Users - имя таблицы. подрозумевает 1го пользователя
    Users findUsersByLogin(String login);

    Users findUsersByEmail(String email);
    
    Users findUsersById(long id);

    Users findUsersByLoginOrEmail(String login, String email);    
}
