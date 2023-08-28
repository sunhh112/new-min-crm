/*
package com.mincrm.controller.min;

import com.mincrm.configuration.AppConfig;
import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.BaseController;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.entity.model.MinAccount;
import com.mincrm.entity.model.MinVerification;
import com.mincrm.entity.pojo.VerificationVo;
import com.mincrm.entity.pojo.MinAccountVo;
import com.mincrm.min.IMin;
import com.mincrm.min.verify.IVerifyCodeGen;
import com.mincrm.min.verify.VerifyCode;
import com.mincrm.util.SendSms;
import com.mincrm.util.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

*/
/**
 * Created by sunhh on 2019/12/14.
 *//*


@RequestMapping(value = "/min")
@Controller
public class MinController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(MinController.class);
    @Autowired
    public IMin minImpl;

    @RequestMapping("indexpage")
    public String indexpage(HttpServletRequest request) {
        return "indexpage";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request) {
        LOG.info("login page--------------------");
        return "login";
    }

    @RequestMapping("findpas")
    public String findpas(HttpServletRequest request) {
        return "findpas";
    }

    @RequestMapping(value = "/loginOK")
    @ResponseBody
    public ResponseBean loginOK(@RequestBody MinAccountVo minAccountVo) {
        minAccountVo = minImpl.loginOK(minAccountVo);
        //   SessionUtils.setSessionAttributes("abc","1111是的是的");
        if (minAccountVo != null) {
            initSession(minAccountVo);
            SessionUtils.cleanRegisterAccount();
            return ResponseBean.buildSuccess(minAccountVo);
        } else
            return ResponseBean.buildFailure("301", "用户名与密码不一致，请重新登录");
    }

    @RequestMapping(value = "/loginOut")
    @ResponseBody
    public ResponseBean loginOut() {
        SessionUtils.removeUser();
        return ResponseBean.buildSuccess();
    }

    @RequestMapping("register")
    public String register(HttpServletRequest request) {
        return "register";
    }

    @RequestMapping(value = "/registerOK")
    @ResponseBody
    public ResponseBean registerOK(@RequestBody MinAccountVo minAccountVo, HttpServletRequest request) {
        String smsVerifyCode = minAccountVo.getSmsVerifyCode();
        String telvcode = SendSms.verificationCode(smsVerifyCode, minAccountVo.getAccountTel());
        if (!SessionUtils.checkSmsVerifyCode(telvcode)) {
            return ResponseBean.buildFailure("短信验证码错误，请重新输入");
        }

        String pas = minAccountVo.getAccountPas();
        minAccountVo.setAccountPas(null);
        minAccountVo.setActiveFlag((byte) 1);
        List<MinAccount> minAccountList = minImpl.getMinAccount(minAccountVo);
        if (minAccountList != null && minAccountList.size() > 0) {
            return ResponseBean.buildFailure("该手机号已经注册，请直接登录");
        }
        minAccountVo.setAccountPas(pas);
        minAccountVo = minImpl.createMinAccount(minAccountVo);
        if (minAccountVo != null) {
            SessionUtils.setRegisterAccount(minAccountVo);
            return ResponseBean.buildSuccess(minAccountVo);
        } else
            return ResponseBean.buildFailure("301", "注册失败，请重试");
    }

    @RequestMapping(value = "/findPasBut")
    @ResponseBody
    public ResponseBean findPasBut(@RequestBody VerificationVo verificationVo) {
        String smsVerifyCode = verificationVo.getSmsVerifyCode();
        String telvcode = SendSms.verificationCode(smsVerifyCode, verificationVo.getTel());
        if (!SessionUtils.checkSmsVerifyCode(telvcode)) {
            return ResponseBean.buildFailure("短信验证码错误，请重新输入");
        } else {
            MinAccountVo minAccountVo = new MinAccountVo();
            minAccountVo.setAccountTel(verificationVo.getTel());
            minAccountVo.setAccountPas(verificationVo.getSmsVerifyCode());//短信验证码为新密码
            minImpl.fuserPasUpdate(minAccountVo);
            return ResponseBean.buildSuccess("密码重置成功，最新密码为刚刚发送的短信验证码<font color='red'>" +
                    verificationVo.getSmsVerifyCode() + "</font>");
        }
    }

    @RequestMapping(value = "/sendSms")
    @ResponseBody
    public ResponseBean sendSms(@RequestBody VerificationVo verificationVo) {
        String smsStatus = "";
        String time = "";
        if (!StringUtils.equals(SendSms.isTel(verificationVo.getTel()), "1")) {
            return ResponseBean.buildFailure(SendSms.isTel(verificationVo.getTel()));
        }
        String verifyCode = verificationVo.getVerifyCode();
        if (!SessionUtils.checkVerifyCode(verifyCode)) {
            return ResponseBean.buildFailure("动态验证码错误，请重新输入");
        }
        MinAccountVo minAccountVo = new MinAccountVo();
        minAccountVo.setAccountTel(verificationVo.getTel());
        List<MinAccount> minAccountList = minImpl.getMinAccount(minAccountVo);
        if (StringUtils.equals(verificationVo.getVtype(), "1")) { //'1 用户注册，2 找回密码 ，3 添加员工',
            if (minAccountList != null && minAccountList.size() > 0) {
                return ResponseBean.buildFailure("该手机号<" + verificationVo.getTel() + ">已经注册，请直接登录输入");
            }
        } else if (StringUtils.equals(verificationVo.getVtype(), "2")) {
            if (minAccountList == null || minAccountList.size() <= 0) {
                return ResponseBean.buildFailure("该手机号<" + verificationVo.getTel() + ">未注册");
            }
        }
        String sendcode = "Ok";//测试系统，默认短信111111
        String verificationCode = "111111";
        boolean isSendSms = appConfig.isSendSms;
        if (isSendSms) {
            MinVerification minVerification = new MinVerification();
            minVerification.setCreateUid(SessionUtils.getUid());
            minVerification.setIp(SessionUtils.getRequest().getRemoteAddr());
            minVerification.setSessionid(SessionUtils.getSession().getId());
            minVerification.setTel(verificationVo.getTel());
            //minVerification.setVerificationCode(verificationCode);
            minVerification.setMinute(verificationVo.getMinute());
            minVerification.setVtype(Integer.valueOf(verificationVo.getVtype()));
            MinVerification minVerification1 = minImpl.getVerification(minVerification);//判断刚刚发送过
            if (minVerification1 == null) {
                verificationCode = StringFunction.getFixLengthString(6);//获取短信验证码
                sendcode = SendSms.send(verificationVo.getTel(), verificationCode, verificationVo.getMinute());///生产系统 短息发送
                minVerification.setVerificationCode(verificationCode);
                minImpl.saveVerification(minVerification);
                smsStatus = "1"; //刚刚发送
            } else {
                verificationCode = minVerification1.getVerificationCode();
                smsStatus = "2";//50分钟前刚刚发送
                TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
                TimeZone.setDefault(tz);
                time = StringFunction.dateToString(minVerification1.getCreateDate(), "HH:mm");
            }
        }
        if (!StringUtils.equals("Ok", sendcode)) {
            return ResponseBean.buildFailure(sendcode);
        }
        SessionUtils.setSmsVerifyCode(SendSms.verificationCode(verificationCode, verificationVo.getTel()));
        //SessionUtils.cleanToken();
        return ResponseBean.buildSuccess(smsStatus, time);
    }

    @Autowired
    public IVerifyCodeGen iVerifyCodeGen;

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        /// IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            // LOGGER.info(code);
            //将VerifyCode绑定session
            SessionUtils.setVerifyCode(code);

            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            //LOGGER.info("", e);
        }
    }

}
*/
