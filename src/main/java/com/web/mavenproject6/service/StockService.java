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

/**
 *
 * @author Татьяна Юрченко
 */
public interface StockService 
{
    Stock getItemInfo(Stock id);
    
    List<Stock> getAllItemInfo(Stock id);
    
    StockRepository getRepository();
    
    BasketLog findBasketLogByStock(Stock id);
    
    Stock findStockLogByBasketLog(BasketLog id);
}
