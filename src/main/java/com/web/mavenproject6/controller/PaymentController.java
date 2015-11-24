/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.controller;

import com.web.mavenproject6.forms.UserForm;
import org.apache.commons.codec.digest.DigestUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Aleks
 */
@Controller
public class PaymentController {

    @Value("${robokassa.merchant.login}")
    private String login;
    @Value("${robokassa.merchant.password1}")
    private String password1;
    @Value("${robokassa.lang}")
    private String lang;
    @Value("${robokassa.encoding}")
    private String encoding;
    @Value("${robokassa.outsumcurrency}")
    private String outSumCurrency;
    @Value("${robokassa.url}")
    private String url;

    @RequestMapping(value = {"/pay"})
    public Object requestURL(
            @RequestParam(value = "money", required = true) String money,
            @RequestParam(value = "paymentType", required = true) String paymentType) throws Exception {

        int inv_id = 0;// номер заказа
        int shp_item = 0;// тип товара    
        String inv_desc = "Техническая документация по ROBOKASSA";// описание заказа
        String email = "test@test.com";// Адрес электронной почты покупателя
        String expirationDate = "2015-07-30T12:00";// Срок действия счёта

        String in_curr = "";// предлагаемая валюта платежа
        switch (Integer.parseInt(paymentType)) {
            case 1:
                in_curr = "QIWI";
                break;
            case 2:
                in_curr = "WEBMONEY";
                break;
            case 3:
                in_curr = "YANDEX";
                break;
            case 0:
            default:
                in_curr = "BANKOCEAN2R";
                break;
        }

        int _money = Integer.parseInt("0" + money);
        String md5String = DigestUtils.md5Hex(login + ":" + money + ":" + inv_id + ":" + password1 + ":Shp_item=" + shp_item);
        ModelAndView m = new ModelAndView("jsp/askrequest");
        m.addObject("action", url);
        m.addObject("MrchLogin", login);
        m.addObject("OutSum", _money);
        m.addObject("InvId", inv_id);
        m.addObject("Desc", inv_desc);
        m.addObject("SignatureValue", md5String);
        m.addObject("Shp_item", shp_item);
        m.addObject("IncCurrLabel", in_curr);
        m.addObject("Culture", lang);
        m.addObject("Email", email);
        m.addObject("ExpirationDate", expirationDate);
        m.addObject("OutSumCurrency", outSumCurrency);
        return m;
    }

    @RequestMapping(value = {"/pay_result_url"}, method = RequestMethod.POST)
    public void resultURL(@RequestParam(value = "OutSum", required = true) String outSum,
            @RequestParam(value = "InvId", required = true) String invId,
            @RequestParam(value = "SignatureValue", required = true) String signatureValue) throws Exception {

    }
    
      @RequestMapping(value = {"/pay_success_url"}, method = RequestMethod.POST)
    public void successURL(@RequestParam(value = "OutSum", required = true) String outSum,
            @RequestParam(value = "InvId", required = true) String invId,
            @RequestParam(value = "SignatureValue", required = true) String signatureValue) throws Exception {

    }

    @RequestMapping(value = {"/pay_failur_url"}, method = RequestMethod.POST)
    public void failurURL(@RequestParam(value = "OutSum", required = true) String outSum,
            @RequestParam(value = "InvId", required = true) String invId,
            @RequestParam(value = "Culture", required = true) String culture) throws Exception {

    }
}
