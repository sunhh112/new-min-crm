package com.mincrm.entity.pojo;

import com.mincrm.entity.model.MinAccount;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunhh on 2019/12/15. 登陆用户
 */

public class MinAccountVo extends MinAccount implements Serializable {
    private static final long serialVersionUID = 9120765712970813L;
    private String newPas1,newPas2,smsVerifyCode;

    private ShopVo currentShopVo ; //当前操作的店铺

   private List<ShopVo> shopVoList ; //拥有店铺列表


    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public ShopVo getCurrentShopVo() {
        return currentShopVo;
    }

    public void setCurrentShopVo(ShopVo currentShopVo) {
        this.currentShopVo = currentShopVo;
    }

    public List<ShopVo> getShopVoList() {
        return shopVoList;
    }

    public void setShopVoList(List<ShopVo> shopVoList) {
        this.shopVoList = shopVoList;
    }

    public String getNewPas1() {
        return newPas1;
    }

    public void setNewPas1(String newPas1) {
        this.newPas1 = newPas1;
    }

    public String getNewPas2() {
        return newPas2;
    }

    public void setNewPas2(String newPas2) {
        this.newPas2 = newPas2;
    }


}
