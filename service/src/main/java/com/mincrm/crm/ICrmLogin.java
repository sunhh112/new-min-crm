package com.mincrm.crm;

import com.mincrm.crm.request.CrmAccountRequest;
import com.mincrm.crm.request.CrmAccountResponse;
import com.mincrm.entity.model.CrmAccount;
import com.mincrm.entity.model.MinVerification;
import com.mincrm.entity.pojo.MinAccountVo;

import java.util.List;

/**
 */
public interface ICrmLogin {
    MinAccountVo createMinAccount(CrmAccountRequest minAccountVo);

    CrmAccountResponse loginOK(CrmAccountRequest crmAccountRequest);

    List<CrmAccount> getMinAccount(CrmAccountRequest crmAccountRequest);

    boolean fuserPasUpdate(MinAccountVo minAccountVo);

    int saveVerification(MinVerification minVerification);

    MinVerification getVerification(MinVerification minVerification);

}
