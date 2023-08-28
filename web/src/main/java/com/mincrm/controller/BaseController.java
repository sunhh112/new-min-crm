package com.mincrm.controller;

import com.mincrm.configuration.AppConfig;
import com.mincrm.configuration.SessionUtils;
import com.mincrm.crm.request.CrmAccountResponse;
import com.mincrm.entity.pojo.MinAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sunhh on 2020/1/27.
 */
@Component
public class BaseController {

    @Autowired
    public AppConfig appConfig;

    protected void initSession(CrmAccountResponse crmAccountResponse) {
        SessionUtils.setCrmAccount(crmAccountResponse);

    }


}
