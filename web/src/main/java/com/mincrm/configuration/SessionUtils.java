package com.mincrm.configuration;

import com.mincrm.crm.request.CrmAccountResponse;
import com.mincrm.entity.model.MbCategory;
import com.mincrm.entity.pojo.MinAccountVo;
import com.mincrm.entity.pojo.ShopVo;
import com.mincrm.enums.CustomerTypeEnum;
import com.mincrm.mb.MbCategoryService;
import com.mincrm.min.IMin;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by sunhh on 2019/12/23.
 */
public class SessionUtils {
    final  static String MINACCOUNTVO = "MinAccountVo";
    final  static String SHOPACCOUNTMAPS = "shopAccountMaps";
    final  static String MBCATEGORYlist = "mbCategoryList";

    final  static String crm_account = "CrmAccountVo";
    final  static String customerTypes = "CustomerTypes";

    public static HttpSession getSession() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        return session;
    }

    public static void setSessionAttributes(String id ,String v){
        getSession().setAttribute(id,v);
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs.getRequest();
    }


    public static void setCrmAccount(CrmAccountResponse crmAccount) {
        getSession().setAttribute(crm_account,crmAccount);
        setCustomerType();
       // MinAccountVo minAccountVo = new MinAccountVo();
       // minAccountVo.setUid(crmAccount.getUid());
      //  minAccountVo.setAccountName(crmAccount.getAccountName());
       // setMinAccountVo(minAccountVo);
    }
    public static CrmAccountResponse getCrmAccount() {
        return (CrmAccountResponse) getSession().getAttribute(crm_account);
    }
    public static void setMinAccountVo(MinAccountVo minAccountVo) {
         getSession().setAttribute(MINACCOUNTVO,minAccountVo);
    }
    public static void setShopVoList(List<ShopVo> shopVoList) {
        MinAccountVo vo = (MinAccountVo)getSession().getAttribute(MINACCOUNTVO);
        vo.setShopVoList(shopVoList);
    }

    /***
     * 获取操作用户
     * @return
     */
    public static MinAccountVo getMinAccountVo() {
        return (MinAccountVo) getSession().getAttribute(MINACCOUNTVO);
    }

    final  static String RegisterMINACCOUNT = "RegisterMinAccountVo";

    /**
     * 设置刚刚注册的用户
     * @param minAccountVo
     */
    public static void setRegisterAccount(MinAccountVo minAccountVo) {
        getSession().setAttribute(RegisterMINACCOUNT,minAccountVo);
    }

    /***
     * 清空刚刚注册的用户
     * @param
     */
    public static void cleanRegisterAccount() {
        if(getSession().getAttribute(RegisterMINACCOUNT)!=null)
            getSession().removeAttribute(RegisterMINACCOUNT);
    }

    /**
     * 当前操作店铺
     * @return
     */
    public static ShopVo getCurrentShopVo() {
        MinAccountVo minAccountVo =  getMinAccountVo();
        return minAccountVo.getCurrentShopVo();
    }

    /**
     * 重置当前店铺
     * @param currentShopVo
     */
    public static void setCurrentShopVo(ShopVo currentShopVo) {
        MinAccountVo minAccountVo =  getMinAccountVo();
        minAccountVo.setCurrentShopVo(currentShopVo);
        setMinAccountVo(minAccountVo);
    }

    public static void setCustomerType() {
        getSession().setAttribute(customerTypes, CustomerTypeEnum.getKV());
    }
    /**
     * 存储商品分类全集
     * @param mbCategoryList
     */
    public static void setMbCategoryMaps( List<MbCategory> mbCategoryList) {
        getSession().setAttribute(MBCATEGORYlist,mbCategoryList);
    }
    /**
     * 存储当前店铺的员工成员列表
     * @param shopAccountMaps
     */
    public static void setShopAccountMaps(Map<String, String> shopAccountMaps) {
        getSession().setAttribute(SHOPACCOUNTMAPS,shopAccountMaps);
    }
    /**
     * 获取当前店铺的员工成员列表
     * @param
     */
    public static Map<Integer, String> getShopAccountMaps() {
        return  (Map<Integer, String>)getSession().getAttribute(SHOPACCOUNTMAPS);
    }


    public static Integer getUid() {
        MinAccountVo minAccountVo =  (MinAccountVo) getSession().getAttribute(MINACCOUNTVO);
        if(minAccountVo!=null)
            return minAccountVo.getUid();
        else
            return null;
    }

    public static void removeUser() {
        HttpSession session = getSession();
        session.removeAttribute(MINACCOUNTVO);
        session.invalidate();
    }

    /**
     *
     */
    /**  final  static String tokenid = "token";
    public static void setToken(String token) {
        getSession().setAttribute(tokenid,token);
    }
    public static String getToken() {
        return  (String)getSession().getAttribute(tokenid);
    }
    public static void cleanToken() {
        getSession().removeAttribute(tokenid);
    }

    去掉
     * 验证码

    final  static String verificationcode = "verification";
    public static void setVerificationcode(String token) {
        getSession().setAttribute(verificationcode,token);
    }
    public static String getVerificationcode() {
        return  (String)getSession().getAttribute(verificationcode);
    }
    public static void cleanVerificationcode() {
        getSession().removeAttribute(verificationcode);
    }
     */

/**
 *动态验证码
 */
final static String VerifyCode = "VerifyCode";
public static void setVerifyCode(String verifyCode) {
    getSession().setAttribute(VerifyCode,verifyCode);
}
public static void cleanVerifyCode() {
    getSession().removeAttribute(VerifyCode);
}
public static boolean checkVerifyCode(String verifyCode){
    String sVerifyCode = StringUtils.lowerCase((String)getSession().getAttribute(VerifyCode));
    if(StringUtils.equals(StringUtils.lowerCase(verifyCode),sVerifyCode)){
        cleanVerifyCode();
        return true;
    }else{
        return  false;
    }
}

    /**
     *短信验证码
     */
    final static String SmsVerifyCode = "SmsVerifyCode";
    public static void setSmsVerifyCode(String verifyCode) {
        getSession().setAttribute(SmsVerifyCode,verifyCode);
    }
    public static void cleanSmsVerifyCode() {
        getSession().removeAttribute(SmsVerifyCode);
    }
    public static boolean checkSmsVerifyCode(String verifyCode){
        String sVerifyCode = (String)getSession().getAttribute(SmsVerifyCode);
        if(StringUtils.equals(verifyCode,sVerifyCode)){
            cleanSmsVerifyCode();
            return true;
        }else{
            return  false;
        }
    }
    public static void main(String[] args) {
        int j = 0;
        int a = 0;
        int t=0;
        for (int i = 0; i < 10; i++) {
           j++;
            t = ++a;
        }
        System.out.println("j=" + j);
        System.out.println("a=" + a);
        System.out.println("t=" + t);
    }
}
