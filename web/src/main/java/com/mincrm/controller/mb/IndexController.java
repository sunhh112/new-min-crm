package com.mincrm.controller.mb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunhh on 2019/10/23.
 */
@RequestMapping(value="/mb")
@Controller
public class IndexController {
    @RequestMapping(value="/user", method= RequestMethod.GET)
    @ResponseBody
    public String user(){
        //String dd = user.abc();
        return String.valueOf(System.currentTimeMillis()+"/");
    }
    @RequestMapping("login")
    public String login(HttpServletRequest request){
        request.setAttribute("apptab", 4);
        return "login";
    }
    @RequestMapping("index")
    public String babyindex(HttpServletRequest request){
        request.setAttribute("apptab", 4);

        return "mb/index";
    }

    @RequestMapping("load")//加载页面
    public String load(HttpServletRequest request){
        String p =  request.getParameter("menupage");
        request.setAttribute("menupage",p);
        String shopId = request.getParameter("shopId")+"";
        String customerTel = request.getParameter("customerTel")+"";
        String customerName = request.getParameter("customerName")+"";
        request.setAttribute("shopId1",shopId);
        request.setAttribute("customerTel1",customerTel);
        request.setAttribute("customerName1",customerName);
        return p;
    }




}
