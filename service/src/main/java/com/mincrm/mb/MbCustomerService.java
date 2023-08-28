package com.mincrm.mb;

import com.mincrm.entity.dao.MbCustomerMapper;
import com.mincrm.entity.dao.MbOrderScoreMapper;
import com.mincrm.entity.model.MbCustomer;
import com.mincrm.entity.model.MbCustomerExample;
import com.mincrm.entity.model.MbOrderScore;
import com.mincrm.entity.model.MbOrderScoreExample;
import com.mincrm.entity.pojo.QueryCustomer;
import com.mincrm.entity.pojo.ReturnPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by sunhh on 2019/12/21.
 */
@Service
@Transactional
public class MbCustomerService {
    @Autowired
    MbCustomerMapper mbCustomerMapper;

    public ReturnPage customerlist(QueryCustomer queryCustomer){
        MbCustomerExample example = new MbCustomerExample();
        MbCustomerExample.Criteria criteria = example.createCriteria();
        criteria.andActiveFlagEqualTo((byte)1);
        criteria.andShopIdEqualTo(queryCustomer.getCurrentshopid());

        if(!StringUtils.isEmpty(queryCustomer.getCustomerName()))
            criteria.andCustomerNameEqualTo(queryCustomer.getCustomerName());
       //     criteria.andCustomerNameLike("%"+queryCustomer.getCustomerName()+"%");
        if(!StringUtils.isEmpty(queryCustomer.getCustomerTel()))
            criteria.andCustomerTelEqualTo(queryCustomer.getCustomerTel());
        long total = mbCustomerMapper.countByExample(example);//总数量
        example.setLimit(queryCustomer.getRows()); //select ... limit 20, 10
        example.setOffset(queryCustomer.getOffgset());
        example.setOrderByClause(" create_date desc ");
        List<MbCustomer> mbCustomerList = mbCustomerMapper.selectByExample(example);
        return ReturnPage.buildPage((int)total,mbCustomerList);
    }
    @Autowired
    OrderScoreService orderScoreService;
    @Autowired
    MbOrderScoreMapper mbOrderScoreMapper;
    public int customerSave(MbCustomer mbCustomer){
        MbOrderScore mbOrderScore = new MbOrderScore();
        if(mbCustomer.getCustomerId()!=null) {
            mbCustomerMapper.updateByPrimaryKeySelective(mbCustomer);
            MbOrderScoreExample mbOrderScoreExample = new MbOrderScoreExample();
            MbOrderScoreExample.Criteria criteria = mbOrderScoreExample.createCriteria() ;
            criteria.andScoreTypeEqualTo((byte)0);
            criteria.andCustomerIdEqualTo(mbCustomer.getCustomerId());
            List<MbOrderScore> mbOrderScoreList =  mbOrderScoreMapper.selectByExample(mbOrderScoreExample);
            if(mbOrderScoreList!=null&&mbOrderScoreList.size()==1)
                mbOrderScore.setOrderId(mbOrderScoreList.get(0).getOrderId());
        }
        else
            mbCustomerMapper.insertSelective(mbCustomer);
        mbOrderScore.setCustomerId(mbCustomer.getCustomerId());
        mbOrderScore.setScoreType((byte)0);
        mbOrderScore.setStrDesc("初始积分");
        mbOrderScore.setGoodsScore(mbCustomer.getInitScore());
        mbOrderScore.setCreateUid(mbCustomer.getCreateUid());
        mbOrderScore.setShopId(mbCustomer.getShopId());
        return  orderScoreService.scoreSave(mbOrderScore);
    }
    public MbCustomer customerEdit(MbCustomer mbCustomer){
        return mbCustomerMapper.selectByPrimaryKey(mbCustomer.getCustomerId());
    }
    public int customerRemove(MbCustomer mbCustomer){
        return mbCustomerMapper.updateByPrimaryKeySelective(mbCustomer);
    }

    public int customerUpdate(MbCustomer mbCustomer){
        return   mbCustomerMapper.updateByPrimaryKey(mbCustomer);
    }

    public List<Map<String, Object>> firstpage(List<Integer>  shopids ){
        List<Map<String, Object>> mapList = mbCustomerMapper.firstpage(shopids);
        return  mapList ;
    }


}
