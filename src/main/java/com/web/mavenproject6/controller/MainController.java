/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.controller;

import com.web.mavenproject6.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

}
