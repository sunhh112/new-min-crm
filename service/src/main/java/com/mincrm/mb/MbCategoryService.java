package com.mincrm.mb;

import com.mincrm.entity.dao.MbCategoryMapper;
import com.mincrm.entity.dao.MbCustomerMapper;
import com.mincrm.entity.model.MbCategory;
import com.mincrm.entity.model.MbCategoryExample;
import com.mincrm.entity.model.MbCustomer;
import com.mincrm.entity.model.MbCustomerExample;
import com.mincrm.entity.pojo.QueryCategory;
import com.mincrm.entity.pojo.QueryCustomer;
import com.mincrm.entity.pojo.ReturnPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunhh on 2019/12/21.
 */
@Service
@Transactional
public class MbCategoryService {

    @Autowired
    MbCategoryMapper mbCategoryMapper;

    public List<MbCategory> categorys(Integer shopid){
        MbCategoryExample example = new MbCategoryExample();
        MbCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopid);
        //criteria.andActiveFlagEqualTo((byte)1);
        example.setOrderByClause(" category_name desc ");
        List<MbCategory> mbCategoryList = mbCategoryMapper.selectmbCategoryListByshopid(example);
        //mbCategoryList.forEach(a->maps.put(a.getCategoryId().toString(),a.getCategoryName()));
        return mbCategoryList;
    }

    public ReturnPage categorylist(QueryCategory queryCategory){
        MbCategoryExample example = new MbCategoryExample();
        MbCategoryExample.Criteria criteria = example.createCriteria();
        //criteria.andActiveFlagEqualTo((byte)1);
        criteria.andShopIdEqualTo(queryCategory.getCurrentshopid());
        if(!StringUtils.isEmpty(queryCategory.getCategoryName()))
            criteria.andCategoryNameLike("%"+queryCategory.getCategoryName()+"%");

        long total = mbCategoryMapper.countByExample(example);//总数量
        example.setLimit(queryCategory.getRows()); //select ... limit 20, 10
        example.setOffset(queryCategory.getOffgset());
        example.setOrderByClause(" create_date desc ");
        List<MbCategory> mbCategoryList = mbCategoryMapper.selectByExample(example);
        return ReturnPage.buildPage((int)total,mbCategoryList);
    }
    public int categorySave(MbCategory mbCategory){
        if(mbCategory.getCategoryId()!=null){
            return   mbCategoryMapper.updateByPrimaryKeySelective(mbCategory);
        }else
            return   mbCategoryMapper.insertSelective(mbCategory);
    }

    public int categoryUpdateActive(MbCategory mbCategory){
        return mbCategoryMapper.updateByPrimaryKeySelective(mbCategory);
    }

    public int categoryUpdate(MbCategory mbCategory){
        return   mbCategoryMapper.updateByPrimaryKey(mbCategory);
    }


}
