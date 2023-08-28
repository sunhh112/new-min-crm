package com.mincrm.entity.pojo;

import com.mincrm.entity.model.VAccountnameShopRole;

/**
 * Created by sunhh on 2020/3/29.
 */
public class VAccountnameShopRoleVo extends VAccountnameShopRole  {
   // private String verifyCode; //动态验证吗

    private String smsVerifyCode; //短信验证码

    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }


}
