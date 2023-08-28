package com.mincrm.crm;

import com.mincrm.entity.dao.CrmCustomerMapper;
import com.mincrm.entity.model.CrmCustomer;
import com.mincrm.entity.model.CrmCustomerExample;
import com.mincrm.entity.model.VShopAccountRole;
import com.mincrm.entity.model.VShopAccountRoleExample;
import com.mincrm.entity.pojo.CrmCustomerDto;
import com.mincrm.entity.pojo.CustomerQueryVo;
import com.mincrm.entity.pojo.QueryShop;
import com.mincrm.entity.pojo.ReturnPage;
import com.mincrm.util.BeanCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunhh on 2023/8/19.
 */
@Service
public class CrmCustomerService {

    @Autowired
    CrmCustomerMapper crmCustomerMapper;

    public ReturnPage<CrmCustomerDto> list(CustomerQueryVo customerQueryVo){
        CrmCustomerExample example = new CrmCustomerExample();
        CrmCustomerExample.Criteria criteria = example.createCriteria();
        criteria.andTenantIdEqualTo(customerQueryVo.getTenantId());
        criteria.andOwnerUidEqualTo(customerQueryVo.getOwnerUid());
        // criteria.andShopIdEqualTo(queryGoods.getCurrentshopid());
        long total = crmCustomerMapper.countByExample(example);//总数量
        example.setLimit(customerQueryVo.getRows()); //select ... limit 20, 10
        example.setOffset(customerQueryVo.getOffgset());
        example.setOrderByClause(" create_date desc ");
        List<CrmCustomer> crmCustomers = crmCustomerMapper.selectByExample(example);
        List<CrmCustomerDto> crmCustomerDtos = BeanCopyUtil.copy(crmCustomers, CrmCustomerDto.class);
        return ReturnPage.buildPage((int)total,crmCustomerDtos);
    }

}
