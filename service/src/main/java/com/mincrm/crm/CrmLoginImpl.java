package com.mincrm.crm;

import com.mincrm.crm.request.CrmAccountRequest;
import com.mincrm.crm.request.CrmAccountResponse;
import com.mincrm.entity.dao.CrmAccountMapper;
import com.mincrm.entity.dao.MinAccountMapper;
import com.mincrm.entity.dao.MinVerificationMapper;
import com.mincrm.entity.model.*;
import com.mincrm.entity.pojo.MinAccountVo;
import com.mincrm.mb.MbShopService;
import com.mincrm.util.BeanCopyUtil;
import com.mincrm.util.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by sunhh on 2019/12/15.
 */
@Service
@Transactional
public class CrmLoginImpl implements ICrmLogin {
    @Autowired
    CrmAccountMapper crmAccountMapper ;

    @Autowired
    MinVerificationMapper minVerificationMapper ;

    @Autowired //minAccountMapper 后期去改造
    MinAccountMapper minAccountMapper ;

    @Override
    public MinAccountVo createMinAccount(CrmAccountRequest minAccountVo) {
        return null;
    }

    @Override
    public CrmAccountResponse loginOK(CrmAccountRequest crmAccountRequest){
        List<CrmAccount> minAccountList =  getMinAccount(crmAccountRequest);
        CrmAccount crmAccount = minAccountList.stream().findFirst().orElse(null);
        return BeanCopyUtil.copy(crmAccount, CrmAccountResponse.class);
    }

    @Override
    public List<CrmAccount> getMinAccount(CrmAccountRequest crmAccountRequest){
        CrmAccountExample crmAccountExample = new CrmAccountExample();
        CrmAccountExample.Criteria criteria = crmAccountExample.createCriteria();
        if(StringUtils.isNotEmpty(crmAccountRequest.getAccountTel()))
            criteria.andAccountTelEqualTo(crmAccountRequest.getAccountTel());
        if(StringUtils.isNotEmpty(crmAccountRequest.getAccountPas()))
            criteria.andAccountPasEqualTo(crmAccountRequest.getAccountPas());
        criteria.andActiveFlagEqualTo(Boolean.TRUE);
        List<CrmAccount> crmAccountList = crmAccountMapper.selectByExample(crmAccountExample);
        return crmAccountList;
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
            return null;
        }
    }



}
