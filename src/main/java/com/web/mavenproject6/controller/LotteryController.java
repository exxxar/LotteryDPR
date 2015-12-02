package com.web.mavenproject6.controller;

import com.web.mavenproject6.entities.BasketLog;
import com.web.mavenproject6.entities.Users;
import com.web.mavenproject6.service.BasketLogService;
import com.web.mavenproject6.service.StockService;
import com.web.mavenproject6.service.UserService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
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
 * @author
 */
@Controller
public class LotteryController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    StockService stockService;

    @Autowired
    BasketLogService bascketLog;

    @Autowired
    @Qualifier("UserServiceImpl")
    UserService userService;

    @Autowired
    Environment env;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/lottery"})
    public String getLotteryMainPage() {
        return "jsp/playLottery";
    }

    @RequestMapping(value = {"/userroom"})
    public String getLotteryUserRoom() {
        return "jsp/userRoom";
    }

    @RequestMapping(value = {"/tiket"})
    public String getLotteryTiket() {
        return "jsp/tiket";
    }
    
    
    @RequestMapping(value = {"/getPrize"})
    public String getPrize() {
        return "jsp/prize";
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
                obj.put("balance", "230Р 50КОП.");//
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

            Users u = userService.getRepository().findUsersByLogin(userDetail.getUsername());
            int count = bascketLog.getRepository().findBasketLogByUserId(u.getId()).size();
            JSONObject obj = new JSONObject();

            obj.put("id", u.getId());
            obj.put("balance", u.getSummaryCash());
            obj.put("discont", u.getDiscount() + "%");
            obj.put("ticketCount", count);
            obj.put("allTicketCount", u.getNumberoftikets());//
            obj.put("fio", u.getFio());//
            obj.put("age", u.getAge());//
            obj.put("tel", u.getTelephone());
            obj.put("email", u.getEmail());

            return obj.toString();
        }
        return "not ok";
    }

    @ResponseBody
    @RequestMapping(value = "/getUserPhoto", method = RequestMethod.GET)
    public byte[] getUserPhoto() throws IOException {
        BufferedImage img;
        InputStream in;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();

            in = servletContext.getResourceAsStream(env.getProperty("photo.avatar.default"));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            img = ImageIO.read(in);
            ImageIO.write(img, env.getProperty("photo.type"), baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        }

        in = servletContext.getResourceAsStream(env.getProperty("photo.avatar.default"));
        img = ImageIO.read(in);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, env.getProperty("photo.type"), baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;

    }

    @ResponseBody
    @RequestMapping(value = "/getTickets", method = RequestMethod.POST)
    public String getUserTickets(
            @RequestParam(required = true) String ticketPacket,
            Locale locale
    ) throws JSONException {
        int packetTicket = Integer.parseInt(ticketPacket);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            Users u = userService.getRepository().findUsersByLogin(userDetail.getUsername());
            JSONObject o = new JSONObject();
            o.put("ticketsCount", bascketLog.getRepository().count());
            o.put("displayedTickets", env.getProperty("ticket.displayMax"));
            JSONArray ja = new JSONArray();
            for (int i = packetTicket;
                    i < (packetTicket * Integer.parseInt(env.getProperty("ticket.displayMax"))
                    + Integer.parseInt(env.getProperty("ticket.displayMax")));
                    i++) {

                BasketLog b = bascketLog.getRepository().findBasketLogByUserId(u.getId()).get(i);

                JSONObject obj = new JSONObject();
                obj.put("id", b.getId());
                obj.put("name", messageSource.getMessage("ticket.name", null, locale));
                obj.put("title", messageSource.getMessage("ticket.title", null, locale));
                obj.put("price", env.getProperty("ticket.price") + messageSource.getMessage("ticket.currency", null, locale));//            
                obj.put("userId", b.getUserId());
                obj.put("winnings", b.getWinnings());
                obj.put("addDate", b.getAdddate());
                obj.put("endDate", b.getEnddate());
                ja.add(obj);
            }
            o.put("tickets", ja);
            return o.toString();

        }
        return "not ok";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllTickets", method = RequestMethod.GET)
    public String getUserAllTickets(Locale locale) throws JSONException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Users u = userService.getRepository().findUsersByLogin(userDetail.getUsername());

        JSONObject o = new JSONObject();
        o.put("ticketsCount", bascketLog.getRepository().count());
        JSONArray ja = new JSONArray();
        for (BasketLog b : bascketLog.getRepository().findBasketLogByUserId(u.getId())) {

            JSONObject obj = new JSONObject();
            obj.put("id", b.getId());
            obj.put("name", messageSource.getMessage("ticket.name", null, locale));
            obj.put("title", messageSource.getMessage("ticket.title", null, locale));
            obj.put("price", env.getProperty("ticket.price") + messageSource.getMessage("ticket.currency", null, locale));//            
            obj.put("userId", b.getUserId());
            obj.put("winnings", b.getWinnings());
            obj.put("addDate", b.getAdddate());
            obj.put("endDate", b.getEnddate());
            ja.add(obj);
        }
        o.put("tickets", ja);
        return o.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/byeTicket", method = RequestMethod.POST)
    public String byeOneLotteryTicket(Locale locale) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();
        Users u = userService.getRepository().findUsersByLogin(userDetail.getUsername());
        
        if ((u.getSummaryCash()-Integer.parseInt(env.getProperty("ticket.price")))>0)
        {
            u.setSummaryCash(u.getSummaryCash()-Integer.parseInt(env.getProperty("ticket.price")));
            BasketLog bl = new BasketLog();
            bl.setWinnings(messageSource.getMessage("ticket.winnings", null, locale));
            bl.setUserId(u.getId());
            bl.setAdddate(new Date().getTime());
            bl.setEnddate(new Date().getTime()+Long.parseLong(env.getProperty("ticket.expire")));
            
            long maxPizes = stockService.getRepository().count();
            long randomItem = (int)(Math.random() * maxPizes);
            
            bl.setStockId(stockService.getRepository().findOne(randomItem).getId());
           
            bascketLog.getRepository().save(bl);
            userService.getRepository().save(u);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTicket", method = RequestMethod.POST)
    public String deleteLotteryTicket(@RequestParam(value = "ticketId", required = true) String ticketId) {
        bascketLog.getRepository().delete(bascketLog.getRepository().findOne(Long.parseLong(ticketId)));
        return bascketLog.getRepository().exists(Long.parseLong(ticketId)) ? "1" : "0";
    }

    @ResponseBody
    @RequestMapping(value = "/openTicket", method = RequestMethod.POST)
    public String openLotteryTicket(@RequestParam(value = "ticketId", required = true) String ticketId) {

        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/sendToFriend", method = RequestMethod.POST)
    public String sendTicketToFriend(
            @RequestParam(value = "ticketId", required = true) String ticketId,
            @RequestParam(value = "friendId", required = true) String friendId) {

        return "";
    }
    
    

}
