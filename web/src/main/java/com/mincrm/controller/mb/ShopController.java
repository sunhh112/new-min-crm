package com.mincrm.controller.mb;

import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.BaseController;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.entity.model.MbGoods;
import com.mincrm.entity.model.MinShop;
import com.mincrm.entity.pojo.*;
import com.mincrm.mb.MbGoodsService;
import com.mincrm.mb.MbShopService;
import com.mincrm.min.IMin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/** 商品管理
 * Created by sunhh on 2019/10/23.
 */
@RequestMapping(value="/mb/shop")
@Controller
public class ShopController extends BaseController{

    @Autowired
    MbShopService mbShopService;
    @Autowired
    MbGoodsService mbGoodsService;


    @RequestMapping(value="/shoplist")
    @ResponseBody
    public ResponseBean shoplist(@RequestBody QueryShop queryShop){
        queryShop.setUid(SessionUtils.getMinAccountVo().getUid());
        ReturnPage returnPage = mbShopService.shoplist(queryShop);
        return ResponseBean.buildSuccess(returnPage);
    }

    @RequestMapping(value="/shopSave")
    @ResponseBody
    public ResponseBean shopSave(@RequestBody MinShop minShop){
        //.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        minShop.setCreateUid(SessionUtils.getUid());
        int i = mbShopService.shopSave(minShop);
        List<ShopVo> shopVoList = minImpl.selectShopList(SessionUtils.getUid());
        SessionUtils.setShopVoList(shopVoList);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }
    @Autowired
    IMin minImpl;
    @RequestMapping(value="/shopRemove")
    @ResponseBody
    public ResponseBean shopRemove(@RequestBody MinShop minShop){
        minShop.setUpdateUid(SessionUtils.getUid());
        minShop.setUpdateDate(new Date());
        minShop.setActiveFlag((byte)2);
        int i = mbShopService.shopRemove(minShop);
        List<ShopVo> shopVoList = minImpl.selectShopList(SessionUtils.getUid());
        SessionUtils.setShopVoList(shopVoList);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }

    @RequestMapping(value="/goodsBycategoryList")
    @ResponseBody
    public List<MbGoods> goodsBycategoryList(MbGoods mbGoods){
        mbGoods.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        List<MbGoods> mbGoodsList = mbGoodsService.goodsBycategoryList(mbGoods);
        return mbGoodsList;
    }
    @RequestMapping(value="/goodsById")
    @ResponseBody
    public ResponseBean goodsById(MbGoods mbGoods){
        mbGoods.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        MbGoods mbGoods1 = mbGoodsService.goodsById(mbGoods);
        return ResponseBean.buildSuccess(mbGoods1);
    }
    @RequestMapping(value="/shopSessionList")
    @ResponseBody
    public ResponseBean shopSessionList(){
        List<ShopVo> list = SessionUtils.getMinAccountVo().getShopVoList();
        return ResponseBean.buildSuccess(list);
    }
    @RequestMapping(value="/setCurrentShop")
    @ResponseBody
    public ResponseBean setCurrentShop(Integer shopId){
        ShopVo shopVo =  mbShopService.getShopById(shopId);
        SessionUtils.setCurrentShopVo(shopVo);

        return ResponseBean.buildSuccess();
    }

}
