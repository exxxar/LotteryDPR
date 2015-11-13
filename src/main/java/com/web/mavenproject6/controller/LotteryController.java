/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web.mavenproject6.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Татьяна Юрченко
 */
@Controller
public class LotteryController {

    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = {"/lottery"})
    public String getLotteryMainPage() {
        return "jsp/index";
    }

    @RequestMapping(value = {"/userroom"})
    public String getLotteryUserRoom() {
        return "jsp/userRoom";
    }
    
    @RequestMapping(value = {"/tiket"})
    public String getLotteryTiket() {
        return "jsp/tiket";
    }

    @ResponseBody
    @RequestMapping(value = {"/getUserInfo"}, method = RequestMethod.GET)
    public String getUserInfoGET() {
        return "only for post";
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public String getUserInfoPOST() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return "ok " + userDetail.getUsername();
        }
        return "not ok";
    }

    @ResponseBody
    @RequestMapping(value = "/getUserPhoto", method = RequestMethod.GET)
    public BufferedImage getUserPhoto() throws IOException {
        BufferedImage img;
        InputStream in;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            in = servletContext.getResourceAsStream("/resources/img/Send.png");
            img = ImageIO.read(in);
            return img;
        }

        in = servletContext.getResourceAsStream("/resources/img/Gerb.png");
        img = ImageIO.read(in);

        return img;

    }

    @ResponseBody
    @RequestMapping(value = "/getPacketTickets", method = RequestMethod.GET)
    public String getUserPacketTickets(
            @RequestParam(required = false) String ticketPacket
    ) {
        int packetTicket = Integer.parseInt(ticketPacket);
        //json
        return "";
    }
    
    @ResponseBody
    @RequestMapping(value = "/getAllTickets", method = RequestMethod.GET)
    public String getUserAllTickets() {
 
        //json
        return "";
    }
    
    @ResponseBody
    @RequestMapping (value= "/selectAllTikets", method = RequestMethod.GET)
    public String selectAllUserTikets() {
        //выделение всех чекбоксов
        return "";
    }
    
  
}
