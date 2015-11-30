/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.controller;

import com.web.mavenproject6.entities.PaymentSystems;
import com.web.mavenproject6.entities.Users;
import com.web.mavenproject6.forms.UserForm;
import com.web.mavenproject6.service.PaymentSystemService;
import com.web.mavenproject6.service.UserService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Value("${robokassa.merchant.password2}")
    private String password2;
    @Value("${robokassa.lang}")
    private String lang;
    @Value("${robokassa.encoding}")
    private String encoding;
    @Value("${robokassa.outsumcurrency}")
    private String outSumCurrency;
    @Value("${robokassa.url}")
    private String url;
    int shp_item = 1;// тип товара, всегда 1 - биллет  

    @Autowired
    private PaymentSystemService paymentService;

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @RequestMapping(value = {"/pay"})
    public Object requestURL(
            @RequestParam(value = "money", required = true) String money,
            @RequestParam(value = "paymentType", required = true) String paymentType) throws Exception {

        int inv_id = 0;// номер заказа       
        String inv_desc = "Техническая документация по ROBOKASSA";// описание заказа
        String email = "test@test.com";// Адрес электронной почты покупателя
        String expirationDate = "2015-07-30T12:00";// Срок действия счёта

        String in_curr =  "BANKOCEAN2R";     

        double _money = Double.parseDouble("0" + money);
        String md5String = md5SignatureValue(_money, inv_id, password1, ":Shp_item=" + shp_item);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        Users u = userService.getRepository().findUsersByLogin(userDetail.getUsername());
        PaymentSystems ps = new PaymentSystems();
        ps.setCash(0);
        ps.setExpiration((new Date()).getTime() + 10000000);
        ps.setKey(md5String);
        ps.setTypeofwallet(Long.parseLong(paymentType));
        ps.setUserId(u.getId());
        paymentService.getRepository().save(ps);

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
            @RequestParam(value = "SignatureValue", required = true) String signatureValue,
            @RequestParam(value = "Culture", required = false) String culture) throws Exception {

        double _money = Double.parseDouble(outSum);
        long _id = Long.parseLong(invId);

        String md5String = md5SignatureValue(_money, _id, password2, ":Shp_item=" + shp_item);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Users u = userService.getRepository().findUsersByLogin(userDetail.getUsername());

        PaymentSystems ps = (PaymentSystems) paymentService.getRepository().findPaymentSystemsByUserId(u.getId());
        if (md5String.equals(ps.getKey())) {
            u.setSummaryCash(u.getSummaryCash()+_money);
            userService.getRepository().save(u);
        }
        HttpGet method = new HttpGet(url.concat("?OK").concat(invId));
        HttpClient client = new DefaultHttpClient();
        client.execute(method);

    }

    @RequestMapping(value = {"/pay_success_url"}, method = RequestMethod.POST)
    public void successURL(@RequestParam(value = "OutSum", required = true) String outSum,
            @RequestParam(value = "InvId", required = true) String invId,
            @RequestParam(value = "SignatureValue", required = true) String signatureValue,
            @RequestParam(value = "Culture", required = false) String culture) throws Exception {

        double _money = Double.parseDouble(outSum);
        long _id = Long.parseLong(invId);

        String md5String = md5SignatureValue(_money, _id, password2, ":Shp_item=" + shp_item);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Users u = userService.getRepository().findUsersByLogin(userDetail.getUsername());

        PaymentSystems ps = (PaymentSystems) paymentService.getRepository().findPaymentSystemsByUserId(u.getId());
        if (md5String.equals(ps.getKey())) {
            u.setSummaryCash(u.getSummaryCash()+_money);
            userService.getRepository().save(u);
        }
        HttpGet method = new HttpGet(url.concat("?OK").concat(invId));
        HttpClient client = new DefaultHttpClient();
        client.execute(method);

    }

    @RequestMapping(value = {"/pay_failur_url"}, method = RequestMethod.POST)
    public void failurURL(@RequestParam(value = "OutSum", required = true) String outSum,
            @RequestParam(value = "InvId", required = true) String invId,
            @RequestParam(value = "Culture", required = true) String culture) throws Exception {

    }

    public String md5SignatureValue(double cash, long inv_id, String pass, String userParam) {
        return DigestUtils.md5Hex(login + ":" + cash + ":" + inv_id + ":" + password1 + userParam);
    }
}
