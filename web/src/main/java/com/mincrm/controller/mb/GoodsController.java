package com.mincrm.controller.mb;

import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.entity.dao.MbGoodsMapper;
import com.mincrm.entity.model.MbCategory;
import com.mincrm.entity.model.MbGoods;
import com.mincrm.entity.pojo.QueryCategory;
import com.mincrm.entity.pojo.QueryGoods;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.mb.MbCategoryService;
import com.mincrm.mb.MbGoodsService;
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
@RequestMapping(value="/mb/goods")
@Controller
public class GoodsController {

    @Autowired
    MbGoodsService mbGoodsService;

    @RequestMapping(value="/goodslist")
    @ResponseBody
    public ResponseBean goodslist(@RequestBody QueryGoods queryGoods){
        queryGoods.setCurrentshopid(SessionUtils.getCurrentShopVo().getShopId());
        ReturnPage returnPage = mbGoodsService.goodslist(queryGoods);
        return ResponseBean.buildSuccess(returnPage);
    }



    @RequestMapping(value="/goodsSave")
    @ResponseBody
    public ResponseBean goodsSave(@RequestBody MbGoods mbGoods){
        mbGoods.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        mbGoods.setCreateUid(SessionUtils.getUid());
        int i = mbGoodsService.goodsSave(mbGoods);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }
    @RequestMapping(value="/goodsRemove")
    @ResponseBody
    public ResponseBean goodsRemove(@RequestBody MbGoods mbGoods){
        mbGoods.setUpdateUid(SessionUtils.getUid());
        mbGoods.setUpdateDate(new Date());
        mbGoods.setActiveFlag((byte)2);
        int i = mbGoodsService.goodsRemove(mbGoods);
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
