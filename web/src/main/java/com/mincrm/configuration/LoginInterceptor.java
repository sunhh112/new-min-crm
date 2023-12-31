package com.mincrm.configuration;

/**
 * Created by sunhh on 2018/11/16.
 */


import com.mincrm.crm.request.CrmAccountResponse;
import com.mincrm.entity.pojo.MinAccountVo;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        CrmAccountResponse m =SessionUtils.getCrmAccount();
       if(m==null){
            return reLogin(request,response);
        }
        return true;
    }

    private boolean reLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath()+"/min/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
