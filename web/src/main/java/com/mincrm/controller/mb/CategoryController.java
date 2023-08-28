package com.mincrm.controller.mb;

import com.mincrm.configuration.SessionUtils;
import com.mincrm.controller.util.ResponseBean;
import com.mincrm.entity.model.MbCategory;
import com.mincrm.entity.pojo.QueryCategory;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.mb.MbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Date;
import java.util.List;

/** 商品分类
 * Created by sunhh on 2019/10/23.
 */
@RequestMapping(value="/mb/category")
@Controller
public class CategoryController {

    @Autowired
    MbCategoryService mbCategoryService;

    @RequestMapping(value="/categorylist")
    @ResponseBody
    public ResponseBean categorylist(@RequestBody QueryCategory queryCategory){
        queryCategory.setCurrentshopid(SessionUtils.getCurrentShopVo().getShopId());
        ReturnPage returnPage = mbCategoryService.categorylist(queryCategory);
        return ResponseBean.buildSuccess(returnPage);
    }

    @RequestMapping(value="/categorys")
    @ResponseBody
    public ResponseBean categorys(){
        List<MbCategory> categorys = mbCategoryService.categorys(SessionUtils.getCurrentShopVo().getShopId());
        return ResponseBean.buildSuccess(categorys);
    }

    @RequestMapping(value="/categorySave")
    @ResponseBody
    public ResponseBean categorySave(@RequestBody MbCategory mbCategory){
        mbCategory.setShopId(SessionUtils.getCurrentShopVo().getShopId());
        mbCategory.setCreateUid(SessionUtils.getUid());
        int i = mbCategoryService.categorySave(mbCategory);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }
    @RequestMapping(value="/categoryUpdateActive")
    @ResponseBody
    public ResponseBean categoryUpdateActive(@RequestBody MbCategory mbCategory){
        mbCategory.setUpdateUid(SessionUtils.getUid());
        mbCategory.setUpdateDate(new Date());
        mbCategory.setActiveFlag(mbCategory.getActiveFlag());
        int i = mbCategoryService.categoryUpdateActive(mbCategory);
        if(i>=0)
            return ResponseBean.buildSuccess();
        else
            return ResponseBean.buildFailure();
    }






}
