package com.mincrm.mb;

import com.mincrm.entity.dao.*;
import com.mincrm.entity.model.*;
import com.mincrm.entity.pojo.*;
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
public class OrderScoreService {


    @Autowired
    MbOrderScoreExchangeMapper mbOrderScoreExchangeMapper;
    @Autowired
    MbOrderScoreMapper mbOrderScoreMapper;
    @Autowired
    VMbOrderScoreExchangeMapper vMbOrderScoreExchangeMapper;

    public ReturnPage orderScorelist(QueryVMbOrderScoreExchange vMbOrderScoreExchange){
        VMbOrderScoreExchangeExample example = new VMbOrderScoreExchangeExample();
        VMbOrderScoreExchangeExample.Criteria criteria = example.createCriteria();
        criteria.andActiveFlagEqualTo((byte)1);
        criteria.andShopIdEqualTo(vMbOrderScoreExchange.getCurrentshopid());
        if(!StringUtils.isEmpty(vMbOrderScoreExchange.getGoodsName())){
            criteria.andGoodsNameEqualTo(vMbOrderScoreExchange.getGoodsName());
        }
        if(!StringUtils.isEmpty(vMbOrderScoreExchange.getCustomerName())){
            criteria.andCustomerNameEqualTo(vMbOrderScoreExchange.getCustomerName());
        }
        if(!StringUtils.isEmpty(vMbOrderScoreExchange.getCustomerTel())){
            criteria.andCustomerTelEqualTo(vMbOrderScoreExchange.getCustomerTel());
        }
        if(!StringUtils.isEmpty(vMbOrderScoreExchange.getCategoryId())){
            criteria.andCategoryIdEqualTo(vMbOrderScoreExchange.getCategoryId());
        }
        if(!StringUtils.isEmpty(vMbOrderScoreExchange.getGoodsId())){
            criteria.andGoodsIdEqualTo(vMbOrderScoreExchange.getGoodsId());
        }
        //  if(!StringUtils.isEmpty(queryGoods.getGoodsName()))
        //    criteria.andGoodsNameLike("%"+queryGoods.getGoodsName()+"%");

        long total = vMbOrderScoreExchangeMapper.countByExample(example);//总数量
        example.setLimit(vMbOrderScoreExchange.getRows()); //select ... limit 20, 10
        example.setOffset(vMbOrderScoreExchange.getOffgset());
        example.setOrderByClause(" create_date desc ");
        List<VMbOrderScoreExchange> mbCategoryList = vMbOrderScoreExchangeMapper.selectByExample(example);
        return ReturnPage.buildPage((int)total,mbCategoryList);
    }
    public int scoreSave(MbOrderScore mbOrderScore){
        if(mbOrderScore.getOrderId()!=null)
               mbOrderScoreMapper.updateByPrimaryKeySelective(mbOrderScore);
        else
               mbOrderScoreMapper.insertSelective(mbOrderScore);
        return customerScoreUpdate(mbOrderScore);
    }
    @Autowired
    MbCustomerMapper mbCustomerMapper;

    /**
     * 核心的积分计算
     * @param mbOrderScore
     * @return
     */
    public int customerScoreUpdate(MbOrderScore mbOrderScore){
       int i = mbCustomerMapper.customerScoreUpdate(mbOrderScore.getCustomerId());
        System.out.println(i+"=sssss");
       return i;
    }

    public int orderScoreRemove(VMbOrderScoreExchange vMbOrderScoreExchange){
        MbOrderScore mbOrderScore = new MbOrderScore();
        mbOrderScore.setCustomerId(vMbOrderScoreExchange.getCustomerId());
        if(vMbOrderScoreExchange.getScoreType()==2) {//兑换
            MbOrderScoreExchange mbOrderScoreExchange = new MbOrderScoreExchange();
            mbOrderScoreExchange.setExchangeId(vMbOrderScoreExchange.getOrderId());
            mbOrderScoreExchange.setActiveFlag((byte)2);
            int i = mbOrderScoreExchangeMapper.updateByPrimaryKeySelective(mbOrderScoreExchange);
            System.out.println(i+"=======");
        }
        else{//积分
            mbOrderScore.setOrderId(vMbOrderScoreExchange.getOrderId());
            mbOrderScore.setActiveFlag((byte)2);
            mbOrderScoreMapper.updateByPrimaryKeySelective(mbOrderScore);
        }
        return customerScoreUpdate(mbOrderScore);
    }

    public int scoreExchangeSave(MbOrderScoreExchange mbOrderScoreExchange){
        if(mbOrderScoreExchange.getExchangeId()!=null){
               mbOrderScoreExchangeMapper.updateByPrimaryKeySelective(mbOrderScoreExchange);
        }else
               mbOrderScoreExchangeMapper.insertSelective(mbOrderScoreExchange);
        MbOrderScore mbOrderScore = new MbOrderScore();
        mbOrderScore.setCustomerId(mbOrderScoreExchange.getCustomerId());
        return customerScoreUpdate(mbOrderScore);
    }

}
