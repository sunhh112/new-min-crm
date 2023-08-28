package com.mincrm.util;

/**
 * Created by sunhh on 2020/3/22.
 */

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.sms.v20190711.SmsClient;

import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//短信api文档  https://cloud.tencent.com/document/api/382/38778
//sdk代码 https://console.cloud.tencent.com/api/explorer?Product=sms&Version=2019-07-11&Action=SendSms&SignVersion=
public class SendSms
{

    public static String verificationCode(String verificationCode,String tel){
        return verificationCode+"#"+tel;
    }

    public static String send(String phone,String smsCode,int minute){
        try{
            Credential cred = new Credential("AKIDnMxLUdbTsxpUv7wixABxiIdifomW6E1m", "XqJYaM1nPtJXXHnAJFxb7QDk9KI6sVs4");
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "ap-beijing", clientProfile);
           // String params = "{\"PhoneNumberSet\":[\"+8613331044593\"],\"TemplateID\":\"560483\",\"Sign\":\"简洁saas分享\",\"TemplateParamSet\":[\"1234\",\"50\"],\"SmsSdkAppid\":\"1400338189\"}";
            String params = "{\"PhoneNumberSet\":[\"+86"+phone+"\"],\"TemplateID\":\"560483\",\"Sign\":\"简洁saas分享\",\"TemplateParamSet\":[\""+smsCode+"\",\""+minute+"\"],\"SmsSdkAppid\":\"1400338189\"}";
            //String params = "{\"PhoneNumberSet\":[\"+"+phone+"\"],\"TemplateID\":\"560483\",\"Sign\":\"简洁saas分享\",\"TemplateParamSet\":[\""+smsCode+"\"],\"SmsSdkAppid\":\"1400338189\"}";
            SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);
            SendSmsResponse resp = client.SendSms(req);
            System.out.println(SendSmsRequest.toJsonString(resp));
            SendStatus sendStatus = resp.getSendStatusSet()[0];
            return  sendStatus.getCode() ;//SendSmsRequest.toJsonString(resp) ;
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            return  e.toString();
        }
    }

    public static void main(String [] args) {
        SendSms.send("8613331044593","12345",50);
    }

    public static String isTel(String phone) {
        if(StringUtils.isEmpty(phone))
        return "请输入手机号";
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return "手机号应为11位数";
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return"请填入正确的手机号";
            }
            return "1";
        }
    }

/*
    中国电信号段 133、149、153、173、177、180、181、189、199
    中国联通号段 130、131、132、145、155、156、166、175、176、185、186
    中国移动号段 134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
    其他号段
14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
    虚拟运营商
    电信：1700、1701、1702
    移动：1703、1705、1706
    联通：1704、1707、1708、1709、171
    卫星通信：1349
*/

}