/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.repositories;

import com.web.mavenproject6.entities.PaymentSystems;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Татьяна Юрченко
 */
public interface PaymentRepository extends JpaRepository<PaymentSystems, Long>
{
     List<PaymentSystems> findPaymentSystemsByUserId(long userId);
}
