package com.mincrm.mb;

import com.mincrm.entity.dao.MbCategoryMapper;
import com.mincrm.entity.dao.MbCustomerMapper;
import com.mincrm.entity.dao.MbGoodsMapper;
import com.mincrm.entity.model.*;
import com.mincrm.entity.pojo.QueryCategory;
import com.mincrm.entity.pojo.QueryCustomer;
import com.mincrm.entity.pojo.QueryGoods;
import com.mincrm.entity.pojo.ReturnPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by sunhh on 2019/12/21.
 */
@Service
@Transactional
public class MbGoodsService {


    @Autowired
    MbGoodsMapper mbGoodsMapper;
    public ReturnPage goodslist(QueryGoods queryGoods){
        MbGoodsExample example = new MbGoodsExample();
        MbGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andActiveFlagEqualTo((byte)1);
        criteria.andShopIdEqualTo(queryGoods.getCurrentshopid());
        if(!StringUtils.isEmpty(queryGoods.getGoodsName()))
            criteria.andGoodsNameLike("%"+queryGoods.getGoodsName()+"%");

        long total = mbGoodsMapper.countByExample(example);//总数量
        example.setLimit(queryGoods.getRows()); //select ... limit 20, 10
        example.setOffset(queryGoods.getOffgset());
        example.setOrderByClause(" create_date desc ");
        List<MbGoods> mbCategoryList = mbGoodsMapper.selectByExample(example);
        return ReturnPage.buildPage((int)total,mbCategoryList);
    }
    public int goodsSave(MbGoods mbGoods){
        if(mbGoods.getGoodsId()!=null){
            return   mbGoodsMapper.updateByPrimaryKeySelective(mbGoods);
        }else
            return   mbGoodsMapper.insertSelective(mbGoods);
    }

    public int goodsRemove(MbGoods mbGoods){
        return mbGoodsMapper.updateByPrimaryKeySelective(mbGoods);
    }

    public  List<MbGoods>  goodsBycategoryList(MbGoods mbGoods){
        MbGoodsExample example = new MbGoodsExample();
        MbGoodsExample.Criteria criteria = example.createCriteria();
        criteria.andActiveFlagEqualTo((byte)1);
        criteria.andShopIdEqualTo(mbGoods.getShopId());
        criteria.andCategoryIdEqualTo(mbGoods.getCategoryId());
        List<MbGoods> mbGoods1 = mbGoodsMapper.selectByExample(example);
        for(MbGoods gs :mbGoods1){
            gs.setGoodsName(gs.getGoodsName()+"("+gs.getGoodsVersion()+")");
        }
        return mbGoods1;
    }

    public MbGoods  goodsById(MbGoods mbGoods){

        MbGoods goods = mbGoodsMapper.selectByPrimaryKey(mbGoods.getGoodsId());
        return goods;
    }


}
