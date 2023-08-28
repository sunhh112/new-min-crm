package com.mincrm.controller.mb;

import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.entity.model.*;
import com.mincrm.entity.pojo.MinAccountVo;
import com.mincrm.entity.pojo.QueryShop;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.entity.pojo.VAccountnameShopRoleVo;
import com.mincrm.mb.MbGoodsService;
import com.mincrm.mb.MbShopService;
import com.mincrm.mb.MbShopUserService;
import com.mincrm.min.IMin;
import com.mincrm.util.SendSms;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/** 店铺员工
 * Created by sunhh on 2019/10/23.
 */
@RequestMapping(value="/mb/shopuser")
@Controller
public class ShopUserController {

    @Autowired
    MbShopUserService mbShopUserService;

    @RequestMapping(value="/shopuserlist")
    @ResponseBody
    public ResponseBean shopuserlist(Integer shopId){
        //queryShop.setUid(SessionUtils.getMinAccountVo().getUid());
        ReturnPage returnPage = mbShopUserService.shopuserlist(shopId);
        return ResponseBean.buildSuccess(returnPage);
    }

    @RequestMapping(value="/shopuserSave")
    @ResponseBody
    public ResponseBean shopuserSave(@RequestBody VAccountnameShopRoleVo vAccountnameShopRole){
        vAccountnameShopRole.setCreateUid(SessionUtils.getUid());
        if(vAccountnameShopRole.getUid()==null) {//是新增  非编辑
            String smsVerifyCode = vAccountnameShopRole.getSmsVerifyCode();
            String telvcode = SendSms.verificationCode(smsVerifyCode,vAccountnameShopRole.getAccountTel());
            if(!SessionUtils.checkSmsVerifyCode(telvcode)){
                return ResponseBean.buildFailure("短信验证码错误，请重新输入");
            }
            MinAccountVo minAccountVo = new MinAccountVo();
            minAccountVo.setAccountTel(vAccountnameShopRole.getAccountTel());
            List<MinAccount> minAccountList = minImpl.getMinAccount(minAccountVo);
            if (minAccountList != null && minAccountList.size() > 0) {
                MinAccount v = minAccountList.get(0);
                vAccountnameShopRole.setUid(v.getUid());
                List<MinAccountShopRole> roles = mbShopUserService.queryMinAccountShopRole(vAccountnameShopRole);
                if(roles!=null && roles.size()>0){
                    return ResponseBean.buildFailure("该手机号已经绑定");
                }
            }
        }
        /*String verificationcode = SessionUtils.getVerificationcode();
        String vcode = SendSms.verificationCode(vAccountnameShopRole.getVerificationCode(),vAccountnameShopRole.getAccountTel());
        if(!StringUtils.equals(verificationcode,vcode)){
            return ResponseBean.buildFailure("验证码错误，请重新输入");
        }*///VAccountnameShopRole
        String i = mbShopUserService.shopuserSave(vAccountnameShopRole);
        if(StringUtils.equals(i,"1")) {//新创建用户提示默认密码
            return ResponseBean.buildSuccess("保存成功，<font color='red'>"+vAccountnameShopRole.getAccountTel()+"</font>的默认密码是刚刚发送的验证<font color='red'>"+vAccountnameShopRole.getSmsVerifyCode()+"</font>");
        }
        return ResponseBean.buildSuccess();
    }
    @Autowired
    public IMin minImpl;
    @RequestMapping(value="/fuserupdate")
    @ResponseBody
    public ResponseBean fuserupdate(@RequestBody MinAccountVo minAccountVo){
      //  vAccountnameShopRole.setCreateUid(SessionUtils.getUid());
        minImpl.fuserupdate(minAccountVo);
        MinAccountVo minAccountVo1 =  SessionUtils.getMinAccountVo();
        minAccountVo1.setAccountName(minAccountVo.getAccountName());
        SessionUtils.setMinAccountVo(minAccountVo1);
        return ResponseBean.buildSuccess();
    }

    @RequestMapping(value="/fuserPasUpdate")
    @ResponseBody
    public ResponseBean fuserPasUpdate(@RequestBody MinAccountVo minAccountVo){
        boolean b= minImpl.fuserPasUpdate(minAccountVo);
        if(b)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }

    @RequestMapping(value="/shopuserRemove")
    @ResponseBody
    public ResponseBean shopuserRemove(@RequestBody MinAccountShopRole minAccountShopRole){
        minAccountShopRole.setUpdateUid(SessionUtils.getUid());
        minAccountShopRole.setUpdateDate(new Date());
        minAccountShopRole.setActiveFlag((byte)2);
        int i = mbShopUserService.shopuserRemove(minAccountShopRole);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }


}
