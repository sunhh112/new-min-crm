package com.mincrm.controller.util;



/**
 * Created by sunhh on 2018/10/17.
 */
public class ResponseBean<T> {

    private ResponseBean() {
    }

    public static ResponseBean buildSuccess() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        return responseBean;
    }

    public static <T> ResponseBean buildSuccess(T data) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        responseBean.setData(data);
        return responseBean;
    }
    public static <T> ResponseBean buildSuccess(String code,T data) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        responseBean.setData(data);
        responseBean.setCode(code);
        return responseBean;
    }


    public static ResponseBean buildFailure() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        return responseBean;
    }

    public static ResponseBean buildFailure(String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setMessage(message);
        return responseBean;
    }

    public static ResponseBean buildFailure(EnumErrorCode errorCode) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setCode(errorCode.code);
        responseBean.setMessage(errorCode.desc);
        return responseBean;
    }

    public static ResponseBean buildFailure(String code,String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(false);
        responseBean.setCode(code);
        responseBean.setMessage(message);
        return responseBean;
    }

    private boolean success;
    private T data;
    private String message;
    private String code = "";
    // private String date ;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
