package com.mincrm.crm.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by sunhh on 2023/8/13.
 */
@Data
public class CrmAccountResponse {

    private Integer uid;

    private Integer tenantId;

    private String accountTel;

    private String accountPas;

    private String accountName;

    private Boolean activeFlag;

    private Date createDate;

    private Integer createUid;

    private Date updateDate;

    private Integer updateUid;

}
