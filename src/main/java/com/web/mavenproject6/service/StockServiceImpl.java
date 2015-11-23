/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.service;

import com.web.mavenproject6.entities.BasketLog;
import com.web.mavenproject6.entities.Stock;
import com.web.mavenproject6.repositories.StockRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Татьяна Юрченко
 */
public class StockServiceImpl implements StockService{

    @Autowired //подгрузка репозитория
    private StockRepository stockRepository;

    @PersistenceContext
    private EntityManager em; //запросы
    
   
    @Override
    public StockRepository getRepository() 
    {
        return stockRepository;
    }

}
