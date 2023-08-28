package com.mincrm.entity.pojo;

/**
 * Created by sunhh on 2019/12/31.
 */
public class QueryGoods extends QueryPage{
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    private String goodsName;


}
