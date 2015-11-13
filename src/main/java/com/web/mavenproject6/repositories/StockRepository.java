/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.repositories;

import com.web.mavenproject6.entities.BasketLog;
import com.web.mavenproject6.entities.Roles;
import com.web.mavenproject6.entities.Stock;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Татьяна Юрченко
 */
public interface StockRepository extends CrudRepository<Stock, Long>
{

}
