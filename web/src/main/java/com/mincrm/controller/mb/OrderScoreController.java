package com.mincrm.controller.mb;

import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.entity.dao.MbGoodsMapper;
import com.mincrm.entity.dao.MbOrderScoreExchangeMapper;
import com.mincrm.entity.model.*;
import com.mincrm.entity.pojo.QueryCategory;
import com.mincrm.entity.pojo.QueryGoods;
import com.mincrm.entity.pojo.QueryVMbOrderScoreExchange;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.mb.MbCategoryService;
import com.mincrm.mb.MbGoodsService;
import com.mincrm.mb.OrderScoreService;
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
@RequestMapping(value="/mb/orderScore")
@Controller
public class OrderScoreController {


    @Autowired
    MbGoodsService mbGoodsService;
    @Autowired
    OrderScoreService orderScoreService;

    @RequestMapping(value="/orderScorelist")
    @ResponseBody
    public ResponseBean orderScorelist(@RequestBody QueryVMbOrderScoreExchange queryVMbOrderScoreExchange){
        queryVMbOrderScoreExchange.setCurrentshopid(SessionUtils.getCurrentShopVo().getShopId());
        ReturnPage returnPage = orderScoreService.orderScorelist(queryVMbOrderScoreExchange);
        return ResponseBean.buildSuccess(returnPage);
    }

    @RequestMapping(value="/scoreSave")
    @ResponseBody
    public ResponseBean scoreSave(@RequestBody MbOrderScore mbOrderScore){
        mbOrderScore.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        mbOrderScore.setCreateUid(SessionUtils.getUid());
        mbOrderScore.setScoreType((byte)1);
        int i = orderScoreService.scoreSave(mbOrderScore);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }


    @RequestMapping(value="/scoreExchangeSave")
    @ResponseBody
    public ResponseBean scoreExchangeSave(@RequestBody MbOrderScoreExchange mbOrderScoreExchange){
        mbOrderScoreExchange.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        mbOrderScoreExchange.setCreateUid(SessionUtils.getUid());
        int i = orderScoreService.scoreExchangeSave(mbOrderScoreExchange);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }


    @RequestMapping(value="/orderScoreRemove")
    @ResponseBody
    public ResponseBean orderScoreRemove(@RequestBody VMbOrderScoreExchange vMbOrderScoreExchange){
        vMbOrderScoreExchange.setUpdateUid(SessionUtils.getUid());
        vMbOrderScoreExchange.setUpdateDate(new Date());
        vMbOrderScoreExchange.setActiveFlag((byte)2);
        int i = orderScoreService.orderScoreRemove(vMbOrderScoreExchange);
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



}
