package com.mincrm.controller.mb;

import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.entity.model.MbCustomer;
import com.mincrm.entity.pojo.MinAccountVo;
import com.mincrm.entity.pojo.QueryCustomer;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.entity.pojo.ShopVo;
import com.mincrm.mb.MbCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sunhh on 2019/10/23.
 */
@RequestMapping(value="/mb/customer")
@Controller
public class Customer2Controller {

    @Autowired
    MbCustomerService mbCustomerService;
/*
    @RequestMapping("load")//加载页面
    public String load(HttpServletRequest request){
        String p =  request.getParameter("menupage");
        request.setAttribute("menupage",p);
        return p;
    }
*/
    @RequestMapping(value="/customerlist")
    @ResponseBody
    public ResponseBean customerlist(@RequestBody QueryCustomer queryCustomer){
        queryCustomer.setCurrentshopid(SessionUtils.getCurrentShopVo().getShopId());
        ReturnPage returnPage = mbCustomerService.customerlist(queryCustomer);
        return ResponseBean.buildSuccess(returnPage);
    }

    @RequestMapping(value="/customerSave")
    @ResponseBody
    public ResponseBean customerSave(@RequestBody MbCustomer mbCustomer){
        mbCustomer.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        mbCustomer.setCreateUid(SessionUtils.getUid());
        int i = mbCustomerService.customerSave(mbCustomer);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }
    @RequestMapping(value="/customerRemove")
    @ResponseBody
    public ResponseBean customerRemove(@RequestBody MbCustomer mbCustomer){
        mbCustomer.setUpdateUid(SessionUtils.getUid());
        mbCustomer.setUpdateDate(new Date());
        mbCustomer.setActiveFlag((byte)2);
        int i = mbCustomerService.customerRemove(mbCustomer);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }


    @RequestMapping(value="/customerEdit")
    @ResponseBody
    public ResponseBean customerEdit(@RequestBody MbCustomer mbCustomer){
        MbCustomer mbCustomer1 = mbCustomerService.customerEdit(mbCustomer);
        return ResponseBean.buildSuccess(mbCustomer1);
    }

    /*
    @RequestMapping(value="/customerUpdate")
    @ResponseBody
    public ResponseBean customerUpdate(@RequestBody MbCustomer mbCustomer){
        mbCustomer.setUpdateUid(SessionUtils.getUid());
        mbCustomer.setUpdateDate(new Date());
        int i = mbCustomerService.customerUpdate(mbCustomer);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }
*/

    @RequestMapping(value="/firstpage")
    @ResponseBody
    public ResponseBean firstpage(){
        List<ShopVo> shopVos = SessionUtils.getMinAccountVo().getShopVoList();
        List<Integer> listshops =  shopVos.stream().map(a->a.getShopId()).collect(Collectors.toList());
        List<Map<String, Object>> mapList = mbCustomerService.firstpage(listshops);
        if(mapList!=null&&mapList.size()>0) {
            int i = (int)Math.ceil(mapList.size()/2.0);
            List<List<Map<String, Object>>> lists = new ArrayList<>();
            for(int ii=1;ii<=i;ii++){
                List<Map<String, Object>> list2 = null;
                if(ii*2<mapList.size())
                    list2 = mapList.subList((ii-1)*2,ii*2);
                else
                    list2 = mapList.subList((ii-1)*2,mapList.size());
                lists.add(list2);
            }
            return ResponseBean.buildSuccess(lists);
        }
        else
            return ResponseBean.buildFailure();
    }




}
