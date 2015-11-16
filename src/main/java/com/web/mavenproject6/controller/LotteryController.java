
package com.web.mavenproject6.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
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
    @RequestMapping(value = {"/getUserPayment"}, method = RequestMethod.POST)
    public String getUserPayments() throws JSONException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            JSONObject o = new JSONObject();
            JSONArray ar = new JSONArray();
            o.put("paymentCount", "10");

            for (int i = 0; i < 10; i++) {
                JSONObject obj = new JSONObject();
                int paymentType = 0;
                switch (i) {
                    case 0:
                    case 1:
                        paymentType = 1;
                        break;
                    case 2:
                        paymentType = 2;
                        break;
                    case 3:
                        paymentType = 3;
                        break;
                    default:
                        paymentType = 1;
                        break;
                }
                obj.put("paymentType", paymentType);
                obj.put("paymentNum", (long) (Math.random() * 100000000));
                obj.put("balance", "230р 50коп.");//
                ar.add(obj);
            }
            o.put("payment", ar);

            return o.toString();
        }
        return "not ok";
    }

    @ResponseBody
    @RequestMapping(value = {"/getUserInfo"}, method = RequestMethod.GET)
    public String getUserInfoGET() {
        return "only for post";
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public String getUserInfoPOST() throws JSONException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            JSONObject obj = new JSONObject();

            obj.put("balance", "2000р");
            obj.put("discont", "20%");
            obj.put("ticketCount", "1000шт");
            obj.put("allTicketCount", "1560шт");//
            obj.put("fio", "Казякин Семко Албрехтович");//
            obj.put("age", "33 ГОДА");//
            obj.put("tel", "8-800-500-0-501");
            obj.put("email", "dodod@GMAIL.COM");

            return obj.toString();
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

            in = servletContext.getResourceAsStream("/resources/img/avatar.jpg");
            img = ImageIO.read(in);
            return img;
        }

        in = servletContext.getResourceAsStream("/resources/img/avatar.jpg");
        img = ImageIO.read(in);

        return img;

    }

    @ResponseBody
    @RequestMapping(value = "/getTickets", method = RequestMethod.POST)
    public String getUserTickets(
            @RequestParam(required = false) String ticketPacket
    ) throws JSONException {
        int packetTicket = Integer.parseInt(ticketPacket);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            
            JSONObject o = new JSONObject();
            JSONArray ar = new JSONArray();
            o.put("ticketsCount", "37");
            o.put("displayedTickets", "10");
            
            for (int i=0;i<10;i++){
            JSONObject obj = new JSONObject();

            obj.put("id", "1");
            obj.put("name", "НАЦИОНАЛЬНАЯ ЛОТЕРЕЯ ДНР");
            obj.put("title", "100% ПОБЕДА");
            obj.put("price", "100р");//            
            obj.put("adddate","01.05.2015");//
            obj.put("enddate", "01.06.2015");// 
            obj.put("isOpend", "false");

            ar.add(obj);
            }
            return o.put("tickets", ar).toString();
        }
        return "not ok";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllTickets", method = RequestMethod.GET)
    public String getUserAllTickets() {

        //json
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/selectAllTikets", method = RequestMethod.GET)
    public String selectAllUserTikets() {
        //выделение всех чекбоксов
        return "";
    }

}
