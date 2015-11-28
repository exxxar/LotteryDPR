/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.controller;

import com.web.mavenproject6.entities.Roles;
import com.web.mavenproject6.entities.Users;
import com.web.mavenproject6.entities.UsersTypes;
import com.web.mavenproject6.forms.UserForm;
import com.web.mavenproject6.other.UserSessionComponent;
import com.web.mavenproject6.service.MailSenderService;
import com.web.mavenproject6.service.PaymentSystemService;
import com.web.mavenproject6.service.RolesService;
import com.web.mavenproject6.service.UserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Aleks
 */
@Controller
public class UserController {

    @Qualifier("rootLogger")
    @Autowired
    private org.apache.log4j.Logger log;

    @Autowired
    Environment env;

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @Autowired
    @Qualifier("RolesServiceImpl")
    RolesService rolesService;

    @Autowired
    @Qualifier("PaymentSystemServiceImpl")
    PaymentSystemService paymentSystemService;

//    @Autowired
//    AccountsVerifiedCodes securityCodeRepository;
    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    UserDetailsService myUserDetailsService;

    @Autowired
    private UserSessionComponent userSessionComponent;

    private static List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>(1) {
        {
            add(new GrantedAuthorityImpl("ROLE_USER"));
        }
    };

    public void setMyUserDetailsService(UserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @RequestMapping(value = "/public/signup_confirm", method = RequestMethod.POST)
    public Object createUser(Model model,
            @ModelAttribute("user") UserForm form,
            @RequestParam(value = "recaptcha_challenge_field", required = false) String challangeField,
            @RequestParam(value = "recaptcha_response_field", required = false) String responseField,
            ServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {

        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey(env.getProperty("key.private"));
        String remoteAdress = servletRequest.getRemoteAddr();
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAdress, challangeField, responseField);
        if (!reCaptchaResponse.isValid()) {
            servletRequest.setAttribute("user", new UserForm());
            RedirectView re = new RedirectView("/login#signup", true);
            re.addStaticAttribute("user", form);
            return re;
        }

        String login = userService.getRepository().findUsersByLogin(form.getUsername()).getLogin();
        if (form.getUsername().length() < 6
                || login != null
                || !form.getPassword().equals(form.getConfirmPassword())) {
            ModelAndView m = new ModelAndView("registr");
            m.addObject("user", form);
            return m;
        }
        Users user = new Users();

        user.setLogin(form.getUsername());
        user.setEmail(form.getEmail());
        user.setEnabled(false);
        user.setPassword(form.getPassword());

        user.setDiscount(0l);
        user.setLastonline(new Date().getTime());
        user.setFio(form.getFname() + " " + form.getSname() + " " + form.getTname());
        user.setNumberoftikets(0l);
        user.setTelephone("");
        user.setAge(18);
        user.setRegdate(new Date().getTime());
        user.setVerifiedAccount(true);

        Roles role = new Roles();
        role.setUser(user);
        role.setRole(2);
        user.setRole(role);

        UsersTypes ut = new UsersTypes();
        ut.setTypeName("ПОКУПАТЕЛЬ");
        ut.setUser(user);
        user.setUserType(ut);
        user.setSummaryCash(1000.0);
        userService.getRepository().save(user);

        mailSenderService.sendGreatingMail(user.getEmail(), "new user has entered to your site");

        RedirectView re = new RedirectView("/login#signup", true);
        return re;
    }

//    @RequestMapping(value = "/public/activation", method = RequestMethod.GET)
//    @Transactional
//    public String activation(@RequestParam String mail, @RequestParam String code, Model model) {
//        log.debug("Enter: activation");
//        //if (userService.findUserBySecurityCode(mail, code) != null) {
//        Users user = userService.getRepository().findUsersByEmail(mail);
//        user.setVerifiedAccount(true);
//        // securityCodeRepository.delete(user.getSecurityCode());
//        //user.setSecurityCode(null);
//        userService.save(user);
//        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getEmail());
//        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), AUTHORITIES);
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        userSessionComponent.setCurrentUser(user);
//        log.debug("Exit: activation");
//        //model.addAttribute("propId", user.getPerson().getAccessNumber());
//        return "thy/personal/profile";
//        //}
//        //log.debug("Exit: activation");
//        // return "thy/error/error";
//
//    }
    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model,
            ServletRequest servletRequest, HttpServletResponse servletResponse) {

        model.addAttribute("user", new UserForm());

        if (logout != null) {
            return "jsp/index";
        }
        return "jsp/index";

    }

    @ResponseBody
    @RequestMapping(value = "/public/remeberMyPassword",method = RequestMethod.POST)
    public String remeberPassword(
            @RequestParam(value = "mail", required = true) String mail, Model model,
            ServletRequest servletRequest, HttpServletResponse servletResponse) {

        Users user = null;

        try {
            user = userService.getRepository().findUsersByEmail(mail);
            mailSenderService.sendGreatingMail(user.getEmail(), "Ваш логин:" + user.getLogin() + "Ваш пароль:" + user.getPassword());
        } catch (Exception e) {
            mailSenderService.sendGreatingMail(mail, "Извените, но данные, запрошенные вами, не найдены.");
        }

        return "ok";
    }

}
