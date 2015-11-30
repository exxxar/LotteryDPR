/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.controller;

import com.web.mavenproject6.entities.Stock;
import com.web.mavenproject6.forms.StockForm;
import com.web.mavenproject6.forms.UserForm;
import com.web.mavenproject6.service.StockService;
import com.web.mavenproject6.service.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Aleks
 */
@Controller
public class MainController {

    @Autowired
    UserDetailsService myUserDetailsService;

    @Autowired
    StockService stockService;

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @RequestMapping(value = {"/"})
    public String login(Model model, @RequestParam(required = false) String message) {
        UserForm uu = new UserForm();
        model.addAttribute("user", uu);
        return "jsp/index";
    }

    @RequestMapping(value = {"/signin"})
    public String signin(Model model, @RequestParam(required = false) String message) {
        return "jsp/login";
    }

    @RequestMapping(value = {"/signup"})
    public String signup(Model model, @RequestParam(required = false) String message) {
        UserForm uu = new UserForm();
        model.addAttribute("user", uu);
        return "jsp/registr";
    }

    @RequestMapping(value = "/error/{index}", method = RequestMethod.GET)
    public ModelAndView exError(@PathVariable(value = "index") String index) {

        int _index = Integer.parseInt(index);
        ModelAndView m = new ModelAndView("jsp/error");
        m.addObject("errorNum", _index);
        return m;
    }

    @RequestMapping(value = {"/admin/stock"}, method = RequestMethod.GET)
    public String admin(Model model) {
        model.addAttribute("stockList", stockService.getRepository().findAll());
        model.addAttribute("stock", new StockForm());
      //  model.addAttribute("userForm", new UserForm());
      //  model.addAttribute("userList", userService.getRepository().findAll());
        return "jsp/admin";
    }

    @RequestMapping(value = {"/admin/stock"}, method = RequestMethod.POST)
    public String stock(Model model,
            @ModelAttribute("stock") StockForm form) {

        Stock st = new Stock();
        st.setCategory(form.getCategory());
        st.setPrizes(form.getPrizes());
        st.setComingdate(new Date().getTime());
        st.setRented(false);
        st.setSold(false);
        st.setImage(new byte[1]);
        stockService.getRepository().save(st);

        model.addAttribute("stockList", stockService.getRepository().findAll());
        model.addAttribute("stock", new StockForm());
      //  model.addAttribute("userForm", new UserForm());
      //  model.addAttribute("userList", userService.getRepository().findAll());
        return "jsp/admin";
    }

    @RequestMapping(value = {"/admin/user"}, method = RequestMethod.POST)
    public String user(Model model,
            @ModelAttribute("userForm") UserForm form) {

        model.addAttribute("stockList", stockService.getRepository().findAll());
        model.addAttribute("stock", new StockForm());
       // model.addAttribute("userForm", new UserForm());
       // model.addAttribute("userList", userService.getRepository().findAll());
        return "jsp/admin";
    }

}
