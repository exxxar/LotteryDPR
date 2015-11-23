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

/**
 *
 * @author Татьяна Юрченко
 */
public interface RolesService 
{
    RolesRepository getRepository();     
}
