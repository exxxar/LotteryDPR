/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.repositories;

import com.web.mavenproject6.entities.Users;
import java.lang.annotation.Documented;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Aleks
 */
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findUsersByLogin(String login);

    Users findUsersByEmail(String email);

    Users findUsersById(long id);

    Users findUsersByLoginOrEmail(String login, String email);
}
