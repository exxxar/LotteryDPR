/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Aleks
 */
@Controller
@PropertySource("resources/application.properties")
public class VKController {

    @Value("${vkapi.connection.url}")
    private String connectonUrl;
    @Value("${vkapi.api.url}")
    private String apiUrl;
    @Value("${vkapi.connection.url.accessToken}")
    private String accessTokenUrl;
    @Value("${vkapi.id}")
    private String clientId;
    @Value("${vkapi.secret}")
    private String apiSecret;
    @Value("${vkapi.redirect}")
    private String redirectUrl;

    private String accessToken;

    private class Param {

        private String name;
        private String value;

        public Param() {
            this.name = "";
            this.value = "";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
    @Autowired
    UserDetailsService myUserDetailsService;
    private static List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>(1) {
        {
            add(new GrantedAuthorityImpl("ROLE_USER"));
        }
    };

    @RequestMapping(value = "/public/vk_connection_url")
    public void vkConnectionUrl(HttpServletResponse response) throws IOException {
        response.sendRedirect(connectonUrl);
    }

    @RequestMapping(value = "/public/vk_callback", method = RequestMethod.GET)
    public Object vkCallback(@RequestParam("code") String code, HttpServletRequest r, HttpServletResponse response, ModelMap model) throws IOException, JSONException {

        HttpClient client = new DefaultHttpClient();
        HttpGet method = new HttpGet(accessTokenUrl
                .concat("client_id=")
                .concat(clientId)
                .concat("&client_secret=")
                .concat(apiSecret)
                .concat("&code=")
                .concat(code)
                .concat("&redirect_uri=")
                .concat(redirectUrl)
        );

        HttpResponse resp = client.execute(method);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
        JSONObject json = new JSONObject(reader.readLine());
        accessToken = json.get("access_token").toString();
        reader.close();
        //
        UserDetails userDetails = new User("SYSTEMADMIN", "SPASSWORD", AUTHORITIES);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, "SPASSWORD", AUTHORITIES);
        SecurityContextHolder.getContext().setAuthentication(auth);
        //ModelAndView m =  new ModelAndView("jsp/index");
        //m.addObject("user",new UserForm());
        RedirectView re = new RedirectView("/", true);
        return re;
    }

    private JSONObject execute(String apiMethod, Param... params) throws IOException, JSONException {
        if (accessToken != null || StringUtils.isEmpty(accessToken)) {
            return new JSONObject();
        }

        String paramString = "?access_token".concat(accessToken);
        if (params != null) {
            for (Param p : params) {
                paramString = paramString
                        .concat("&")
                        .concat(p.getName())
                        .concat("=")
                        .concat(p.getValue());
            }
        }

        HttpClient client = new DefaultHttpClient();
        HttpGet method = new HttpGet(apiUrl
                .concat(apiMethod)
                .concat(paramString)
        );

        HttpResponse resp = client.execute(method);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
        JSONObject json = new JSONObject(reader.readLine());
        reader.close();
        return json;
    }

}
