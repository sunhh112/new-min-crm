package com.mincrm.mb;

import com.mincrm.entity.dao.MbGoodsMapper;
import com.mincrm.entity.dao.MinAccountMapper;
import com.mincrm.entity.dao.MinAccountShopRoleMapper;
import com.mincrm.entity.dao.VAccountnameShopRoleMapper;
import com.mincrm.entity.model.*;
import com.mincrm.entity.pojo.QueryGoods;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.entity.pojo.VAccountnameShopRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by sunhh on 2019/12/21.
 */
@Service
@Transactional
public class MbShopUserService {
    @Autowired
    VAccountnameShopRoleMapper vAccountnameShopRoleMapper;
    @Autowired
    MbGoodsMapper mbGoodsMapper;
    public ReturnPage shopuserlist(Integer shopId){
        VAccountnameShopRoleExample example = new VAccountnameShopRoleExample();
        VAccountnameShopRoleExample.Criteria criteria = example.createCriteria();
        criteria.andActiveFlagEqualTo((byte)1);
        criteria.andShopIdEqualTo(shopId);
        long total = vAccountnameShopRoleMapper.countByExample(example);//总数量
        example.setLimit(2000); //select ... limit 20, 10
        example.setOffset(0);
        example.setOrderByClause(" create_date desc ");
        List<VAccountnameShopRole> vAccountnameShopRoleList = vAccountnameShopRoleMapper.selectByExample(example);
        return ReturnPage.buildPage((int)total,vAccountnameShopRoleList);
    }
    @Autowired
    MinAccountMapper minAccountMapper;
    @Autowired
    MinAccountShopRoleMapper minAccountShopRoleMapper;
    public String shopuserSave(VAccountnameShopRoleVo vAccountnameShopRole){
        MinAccount minAccount = new MinAccount();
        minAccount.setAccountName(vAccountnameShopRole.getAccountName());
        minAccount.setAccountTel(vAccountnameShopRole.getAccountTel());
        minAccount.setUid(vAccountnameShopRole.getUid());
        String i = "0";
        if(vAccountnameShopRole.getUid()==null){//第一次创建账号
            minAccount.setAccountPas(vAccountnameShopRole.getSmsVerifyCode());//默认使用短信验证码作为密码
            minAccount.setCreateUid(vAccountnameShopRole.getCreateUid());
            minAccountMapper.insertSelective(minAccount);
            i = "1";
        }
        else{
            minAccount.setUdate(new Date());
            minAccount.setUpdateUid(vAccountnameShopRole.getCreateUid());
            minAccountMapper.updateByPrimaryKeySelective(minAccount);
        }
        if(vAccountnameShopRole.getRoleId()==null){
            MinAccountShopRole minAccountShopRole = new MinAccountShopRole();
            minAccountShopRole.setRoleType(vAccountnameShopRole.getRoleType());
            minAccountShopRole.setUid(minAccount.getUid());
            minAccountShopRole.setShopId(vAccountnameShopRole.getShopId());
            minAccountShopRole.setCreateUid(vAccountnameShopRole.getCreateUid());
            minAccountShopRoleMapper.insertSelective(minAccountShopRole);
        }
        return i;
    }

    public List<MinAccountShopRole> queryMinAccountShopRole(VAccountnameShopRole minAccountShopRole ){
        MinAccountShopRoleExample example = new MinAccountShopRoleExample();
        example.createCriteria().andActiveFlagEqualTo((byte)1)
        .andShopIdEqualTo(minAccountShopRole.getShopId())
        .andUidEqualTo(minAccountShopRole.getUid());
        List<MinAccountShopRole> minAccountShopRoles = minAccountShopRoleMapper.selectByExample(example);
        return minAccountShopRoles;
    }

    public int shopuserRemove(MinAccountShopRole minAccountShopRole){
        return minAccountShopRoleMapper.updateByPrimaryKeySelective(minAccountShopRole);
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
