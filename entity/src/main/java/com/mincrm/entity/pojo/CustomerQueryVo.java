package com.mincrm.entity.pojo;

import lombok.Data;

/**
 * Created by sunhh on 2023/8/19.
 */
@Data
public class CustomerQueryVo extends QueryPage {
    private String customerTel ;
    private String customerName ;
    private String customerType ;
    private Integer tenantId ;
    private Integer ownerUid ;

}
