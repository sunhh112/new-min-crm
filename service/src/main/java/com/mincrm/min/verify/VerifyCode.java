package com.mincrm.min.verify;

/**
 * Created by sunhh on 2020/4/3.
 */
/**
 * 验证码类
 */

public class VerifyCode {

    private long expireTime;
    private String code;

    private byte[] imgBytes;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }


}