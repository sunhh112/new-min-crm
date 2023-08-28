package com.mincrm.entity.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created by sunhh on 2023/8/19.
 */
@Data
public class CrmCustomerDto {

    private Integer id;

    private Integer tenantId;

    private Integer ownerUid;

    private String customerTel;

    private String customerName;

    private String customerType;

    private Date expirationTime;

    private String remark;

    private Boolean activeFlag;
    private Date createDate;
    private Integer createUid;
    private Date updateDate;
    private Integer updateUid;

}
