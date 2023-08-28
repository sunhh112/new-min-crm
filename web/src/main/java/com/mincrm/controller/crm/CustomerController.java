package com.mincrm.controller.crm;

import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.BaseController;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.crm.CrmCustomerService;
import com.mincrm.entity.model.MbGoods;
import com.mincrm.entity.model.MinShop;
import com.mincrm.entity.pojo.*;
import com.mincrm.mb.MbGoodsService;
import com.mincrm.mb.MbShopService;
import com.mincrm.min.IMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@RequestMapping(value="/crm/customer")
@Controller
public class CustomerController extends BaseController{

    @Autowired
    CrmCustomerService crmCustomerService;

    @RequestMapping(value="/list")
    @ResponseBody
    public ResponseBean<ReturnPage<CrmCustomerDto>> list(@RequestBody CustomerQueryVo customerQueryVo){
        customerQueryVo.setOwnerUid(SessionUtils.getCrmAccount().getUid());
        customerQueryVo.setTenantId(SessionUtils.getCrmAccount().getTenantId());
        ReturnPage<CrmCustomerDto> pageList = crmCustomerService.list(customerQueryVo);
        // ReturnPage returnPage = mbShopService.shoplist(queryShop);
        return ResponseBean.buildSuccess(pageList);
    }




}
