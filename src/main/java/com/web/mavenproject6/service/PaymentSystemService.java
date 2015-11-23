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

/**
 *
 * @author Татьяна Юрченко
 */
public interface PaymentSystemService 
{
    PaymentRepository getRepository();
}
