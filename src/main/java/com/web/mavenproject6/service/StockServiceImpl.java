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
    public Stock getItemInfo(Stock id) 
    {
        TypedQuery query = em.createQuery("select s from stock s where s.id = ?1", Stock.class);
        query.setParameter(1,id);
        return ((Stock) query.getSingleResult());
    }

    @Override
    public StockRepository getRepository() 
    {
        return stockRepository;
    }

    @Override
    public BasketLog findBasketLogByStock(Stock id) 
    {
        TypedQuery query = em.createQuery("select s from stock s where s.id = ?1", Stock.class);
        query.setParameter(1,id);
        return ((BasketLog) query.getSingleResult());
    }

    @Override
    public Stock findStockLogByBasketLog(BasketLog id) 
    {
        TypedQuery query = em.createQuery("select s from stock s where s.id = ?1", Stock.class);
        query.setParameter(1,id);
        return ((Stock) query.getSingleResult());
    }

    @Override
    public List<Stock> getAllItemInfo(Stock id) 
    {
        TypedQuery query = em.createQuery("select s from stock s where s.id = ?1", Stock.class);
        query.setParameter(1, id);
        return (List<Stock>) query.getResultList();
    }
    
}
