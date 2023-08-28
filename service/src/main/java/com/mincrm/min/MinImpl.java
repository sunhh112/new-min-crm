package com.mincrm.min;

import com.mincrm.entity.dao.*;
import com.mincrm.entity.model.*;

import com.mincrm.entity.pojo.MinAccountVo;
import com.mincrm.entity.pojo.ShopVo;
import com.mincrm.mb.MbShopService;
import com.mincrm.util.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sunhh on 2019/12/15.
 */
@Service
@Transactional
public class MinImpl implements IMin {
    @Autowired
    MinAccountMapper minAccountMapper ;
    @Autowired
    MinShopMapper minShopMapper;
    @Autowired
    MinAccountShopRoleMapper minAccountShopRoleMapper;
    @Autowired
    MinCategoryTemplateMapper minCategoryTemplateMapper ;
    @Autowired
    MbCategoryMapper mbCategoryMapper ;
    @Override
    public MinAccountVo loginOK(MinAccountVo minAccountVo){
       /* MinAccountExample minAccountExample = new MinAccountExample();
        minAccountExample.createCriteria().
                andAccountTelEqualTo(minAccountVo.getAccountTel()).
                andAccountPasEqualTo(minAccountVo.getAccountPas()).
                andActiveFlagEqualTo((byte)1);
        List<MinAccount> minAccountList = minAccountMapper.selectByExample(minAccountExample);
        */
        minAccountVo.setActiveFlag((byte)1);
        List<MinAccount> minAccountList =  getMinAccount(minAccountVo);
        if(!CollectionUtils.isEmpty(minAccountList)) {
            BeanUtils.copyProperties(minAccountList.get(0),minAccountVo);
           /* List<ShopVo> minAccountShopRoleList = selectShopList(minAccountVo.getUid());
            minAccountVo.setShopVoList(minAccountShopRoleList);
            if(!CollectionUtils.isEmpty(minAccountShopRoleList) && minAccountShopRoleList.size()==1){//如果只有一个店铺，则设置为默认
                minAccountVo.setCurrentShopVo(minAccountShopRoleList.get(0));
           }else{

            }*/
            return minAccountVo;//登录成功
        }else{
            return null;
        }
    }

    @Override
    public List<MinAccount> getMinAccount(MinAccountVo minAccountVo){
        MinAccountExample minAccountExample = new MinAccountExample();
        MinAccountExample.Criteria criteria = minAccountExample.createCriteria();
        if(StringUtils.isNotEmpty(minAccountVo.getAccountTel()))
            criteria.andAccountTelEqualTo(minAccountVo.getAccountTel());
        if(StringUtils.isNotEmpty(minAccountVo.getAccountPas()))
            criteria.andAccountPasEqualTo(minAccountVo.getAccountPas());
        if(minAccountVo.getActiveFlag()!=null&&StringUtils.isNotEmpty(minAccountVo.getActiveFlag().toString()))
            criteria.andActiveFlagEqualTo(minAccountVo.getActiveFlag());
        List<MinAccount> minAccountList = minAccountMapper.selectByExample(minAccountExample);
        return minAccountList;
    }

    public List<ShopVo>  selectShopList(Integer uid){
        List<ShopVo> minAccountShopRoleList = minAccountShopRoleMapper.selectShopVoByUid(uid);
        return minAccountShopRoleList;
    }

    /**
     * 获取当前店铺的成员列表
     * @param currentShopVo
     */
    public Map<String, String> shopAccountMaps(ShopVo currentShopVo){
        List<Map<String, Object>>  listMap = minAccountShopRoleMapper.selectByShopid(currentShopVo.getShopId());//根据店铺id
        Map<String, String> maps = new HashMap<>();
      //  listMap.forEach(a->maps.put((Integer)a.get("uid"),(String)a.get("account_name")));
        listMap.forEach(a->maps.put(a.get("uid").toString(),(String)a.get("account_name")));
        return maps;
    }


    @Autowired
    MbShopService mbShopService;
    @Override
    public MinAccountVo createMinAccount(MinAccountVo minAccountVo){
        MinAccount minAccount = new MinAccount();
        minAccountVo.setAccountName(minAccountVo.getAccountTel());
        BeanUtils.copyProperties(minAccountVo,minAccount);
        int i = minAccountMapper.insertSelective(minAccount);
        if(i<=0)
            return null;
        else{
            BeanUtils.copyProperties(minAccount,minAccountVo);
            // minAccount = minAccountList.get(0);
         //   MinAccountShopVo minAccountShopVo = new MinAccountShopVo();
          //  BeanUtils.copyProperties(minAccount,minAccountShopVo);
         //创建店铺
            MinShop minShop = new MinShop();
            minShop.setShopName(minAccount.getAccountTel()+"门店");
            minShop.setCreateUid(minAccount.getUid());
           // minShopMapper.insertSelective(minShop);//创建店铺
            mbShopService.shopSave(minShop);
            return minAccountVo;
/*
            MinAccountShopRole minAccountShopRole = new MinAccountShopRole();
            minAccountShopRole.setCreateUid(minAccount.getUid());
            minAccountShopRole.setUid(minAccount.getUid());
            minAccountShopRole.setShopId(minShop.getShopId());
            minAccountShopRole.setRoleType((byte)1);
            minAccountShopRoleMapper.insertSelective(minAccountShopRole);//授予用户店铺管理员角色
            MinCategoryTemplateExample minCategoryTemplateExample = new MinCategoryTemplateExample();
            minCategoryTemplateExample.createCriteria().andActiveFlagEqualTo((byte)1).andMinCategoryEqualTo("mb");
            List<MinCategoryTemplate> minCategoryTemplates =  minCategoryTemplateMapper.selectByExample(minCategoryTemplateExample);
            minCategoryTemplates.forEach(m->{
                MbCategory mbCategory = new MbCategory();
                BeanUtils.copyProperties(m,mbCategory);
                mbCategory.setShopId(minShop.getShopId());
                mbCategory.setCategoryId(null);
                mbCategoryMapper.insertSelective(mbCategory); //初始化商品分类
            });
            MinGoodsTemplateExample minGoodsTemplateExample = new MinGoodsTemplateExample();
            minGoodsTemplateExample.createCriteria().andActiveFlagEqualTo((byte)1).andMinCategoryEqualTo("mb");
            List<MinGoodsTemplate>  minGoodsTemplateList = minGoodsTemplateMapper.selectByExample(minGoodsTemplateExample);
            minGoodsTemplateList.forEach(m->{
                MbGoods mbGoods = new MbGoods();
                BeanUtils.copyProperties(m,mbGoods);
                mbGoods.setGoodsId(null);
                mbGoods.setShopId(minShop.getShopId());
                mbGoodsMapper.insertSelective(mbGoods); //初始化商品
            });
            MbCustomer mbCustomer = new MbCustomer();
            mbCustomer.setCustomerName("李三 ");
            mbCustomer.setShopId(minShop.getShopId());
            mbCustomer.setCustomerScore(0);
            mbCustomer.setCustomerTel("1331111110");
            mbCustomer.setStrDesc("测试数据-可删除");
            mbCustomerMapper.insertSelective(mbCustomer);//初始化顾客
            return  minAccountVo;
            */
        }
    }

    public void fuserupdate(MinAccountVo minAccountVo){
        MinAccount vo = new MinAccount();
        BeanUtils.copyProperties(minAccountVo,vo);
        minAccountMapper.updateByPrimaryKeySelective(vo);
    }

    public boolean fuserPasUpdate(MinAccountVo minAccountVo){
        if(minAccountVo.getUid()!=null) {
            MinAccount m = minAccountMapper.selectByPrimaryKey(minAccountVo.getUid());
            if (m != null) {
                if (m.getAccountPas().equals(minAccountVo.getAccountPas())) {
                    MinAccount vo = new MinAccount();
                    vo.setUid(minAccountVo.getUid());
                    vo.setAccountPas(minAccountVo.getNewPas1());
                    minAccountMapper.updateByPrimaryKeySelective(vo);
                    return true;
                }
            }
        }else{
            MinAccountExample example = new MinAccountExample();
            example.createCriteria().andAccountTelEqualTo(minAccountVo.getAccountTel());
            minAccountMapper.updateByExampleSelective(minAccountVo,example);
            return true;
        }
        return false;
    }

    @Autowired
    MinVerificationMapper minVerificationMapper ;
    @Override
    public int saveVerification(MinVerification  minVerification){
        int m = minVerificationMapper.insertSelective(minVerification);
        return m;
    }
    @Override
    public MinVerification getVerification(MinVerification  minVerification){
        Date  overtime = StringFunction.timeCalculation(new Date(),minVerification.getMinute());
        MinVerificationExample example = new MinVerificationExample();
        example.createCriteria().andTelEqualTo(minVerification.getTel()).andCreateDateGreaterThan(overtime);
        List<MinVerification> list = minVerificationMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }else{
            return  null;
        }


    }




}
