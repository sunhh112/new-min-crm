package com.mincrm.entity.pojo;

import java.io.Serializable;

/**
 * Created by sunhh on 2019/12/15.  当前店铺
 */
public class ShopVo  implements Serializable {
    private static final long serialVersionUID = 12;
    private Integer shopId;
    private Byte shopType;
    private String shopName;

    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Byte getShopType() {
        return shopType;
    }

    public void setShopType(Byte shopType) {
        this.shopType = shopType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }




}
