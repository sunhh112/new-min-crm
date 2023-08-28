package com.mincrm.mb;

import com.mincrm.entity.dao.*;
import com.mincrm.entity.model.*;
import com.mincrm.entity.pojo.QueryGoods;
import com.mincrm.entity.pojo.QueryShop;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.entity.pojo.ShopVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by sunhh on 2019/12/21.
 */
@Service
@Transactional
public class MbShopService {
    @Autowired
    MinGoodsTemplateMapper minGoodsTemplateMapper;
    @Autowired
    MbGoodsMapper mbGoodsMapper;
    @Autowired
    VShopAccountRoleMapper vShopAccountRoleMapper;
    @Autowired
    MinShopMapper minShopMapper;

    public ReturnPage shoplist(QueryShop queryShop){
        VShopAccountRoleExample example = new VShopAccountRoleExample();
        VShopAccountRoleExample.Criteria criteria = example.createCriteria();
        criteria.andActiveFlagEqualTo((byte)1);
        criteria.andUidEqualTo(queryShop.getUid());
       // criteria.andShopIdEqualTo(queryGoods.getCurrentshopid());
        long total = vShopAccountRoleMapper.countByExample(example);//总数量
        example.setLimit(queryShop.getRows()); //select ... limit 20, 10
        example.setOffset(queryShop.getOffgset());
        example.setOrderByClause(" create_date desc ");
        List<VShopAccountRole> mbCategoryList = vShopAccountRoleMapper.selectByExample(example);
        return ReturnPage.buildPage((int)total,mbCategoryList);
    }

    public ShopVo getShopById(Integer shopId){
        MinShop minShop = minShopMapper.selectByPrimaryKey(shopId );
        ShopVo vo = new ShopVo();
        vo.setShopType(minShop.getShopType());
        vo.setShopId(minShop.getShopId());
        vo.setShopName(minShop.getShopName());
        return vo;
    }


    public int shopSave(MinShop minShop){
        if(minShop.getShopId()!=null){
            return   minShopMapper.updateByPrimaryKeySelective(minShop);
        }else {
             minShopMapper.insertSelective(minShop);
             int  ii = shopRoleSave(minShop);
            return ii;
        }
    }
    @Autowired
    MinAccountShopRoleMapper minAccountShopRoleMapper;
    @Autowired
    MinCategoryTemplateMapper minCategoryTemplateMapper ;
    @Autowired
    MbCategoryMapper mbCategoryMapper ;
    private int shopRoleSave(MinShop minShop){
        MinAccountShopRole minAccountShopRole = new MinAccountShopRole();
        minAccountShopRole.setCreateUid(minShop.getCreateUid());
        minAccountShopRole.setUid(minShop.getCreateUid());
        minAccountShopRole.setShopId(minShop.getShopId());
        minAccountShopRole.setRoleType((byte)1);
        minAccountShopRoleMapper.insertSelective(minAccountShopRole);//授予用户店铺管理员角色
        MinCategoryTemplateExample minCategoryTemplateExample = new MinCategoryTemplateExample();
        minCategoryTemplateExample.createCriteria().andActiveFlagEqualTo((byte)1).andMinCategoryEqualTo("mb");
        List<MinCategoryTemplate> minCategoryTemplates =  minCategoryTemplateMapper.selectByExample(minCategoryTemplateExample);
        minCategoryTemplates.forEach(m->{
            MbCategory mbCategory = new MbCategory();
            BeanUtils.copyProperties(m,mbCategory);
            mbCategory.setCategoryName(m.getCategoryTypeName());
            mbCategory.setCreateDate(new Date());
            mbCategory.setShopId(minShop.getShopId());
            mbCategory.setCategoryId(null);
            mbCategoryMapper.insertSelective(mbCategory); //初始化商品分类
            initGoods(m.getCategoryType(),mbCategory,minShop.getShopId());
        });

        MbCustomer mbCustomer = new MbCustomer();
        mbCustomer.setCustomerName("李三 ");
        mbCustomer.setShopId(minShop.getShopId());
        mbCustomer.setCustomerScore(0);
        mbCustomer.setInitScore(0);
        mbCustomer.setCustomerTel("13311111111");
        mbCustomer.setStrDesc("测试数据-可删除");

        return  mbCustomerService.customerSave(mbCustomer); //初始化顾客

    }

    private void initGoods(Byte categoryType,MbCategory mbCategory,Integer shopId){
        MinGoodsTemplateExample minGoodsTemplateExample = new MinGoodsTemplateExample();
        minGoodsTemplateExample.createCriteria().andActiveFlagEqualTo((byte)1).
                andMinCategoryEqualTo("mb").andCategoryTypeEqualTo(categoryType);

        List<MinGoodsTemplate>  minGoodsTemplateList = minGoodsTemplateMapper.selectByExample(minGoodsTemplateExample);
        minGoodsTemplateList.forEach(m->{
            MbGoods mbGoods = new MbGoods();
            BeanUtils.copyProperties(m,mbGoods);
            mbGoods.setGoodsId(null);
            mbGoods.setStrDesc("系统初始化");
            mbGoods.setCategoryId(mbCategory.getCategoryId());
            mbGoods.setShopId(shopId);//minShop.getShopId()
            mbGoodsMapper.insertSelective(mbGoods); //初始化商品
        });
    }

    @Autowired
    MbCustomerService mbCustomerService ;

    public int shopRemove(MinShop minShop){
        return minShopMapper.updateByPrimaryKeySelective(minShop);
    }



}
