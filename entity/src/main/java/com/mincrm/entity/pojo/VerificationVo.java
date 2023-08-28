package com.mincrm.entity.pojo;

/**验证码对象
 * Created by sunhh on 2020/3/28.
 */
public class VerificationVo {
    private String smsVerifyCode;//短信验证码
    private String verifyCode; //动态验证码
    private String tel;
    private int minute;
    private String vtype;// '1 用户注册，2 找回密码 ，3 添加员工',

    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public String getVtype() {
        return vtype;
    }

    public void setVtype(String vtype) {
        this.vtype = vtype;
    }



    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }








}
