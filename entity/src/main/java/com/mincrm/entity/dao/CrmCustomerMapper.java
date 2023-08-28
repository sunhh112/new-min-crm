package com.mincrm.entity.dao;

import com.mincrm.entity.model.CrmCustomer;
import com.mincrm.entity.model.CrmCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectKey;
@Mapper
public interface CrmCustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crm_customer
     *
     * @mbg.generated
     */
    long countByExample(CrmCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crm_customer
     *
     * @mbg.generated
     */
    int deleteByExample(CrmCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crm_customer
     *
     * @mbg.generated
     */
    @Insert({
        "insert into crm_customer (tenant_id, owner_uid, ",
        "customer_tel, customer_name, ",
        "customer_type, expiration_time, ",
        "remark, active_flag, ",
        "create_date, create_uid, ",
        "update_date, update_uid)",
        "values (#{tenantId,jdbcType=INTEGER}, #{ownerUid,jdbcType=INTEGER}, ",
        "#{customerTel,jdbcType=VARCHAR}, #{customerName,jdbcType=VARCHAR}, ",
        "#{customerType,jdbcType=VARCHAR}, #{expirationTime,jdbcType=TIMESTAMP}, ",
        "#{remark,jdbcType=VARCHAR}, #{activeFlag,jdbcType=BIT}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{createUid,jdbcType=INTEGER}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CrmCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crm_customer
     *
     * @mbg.generated
     */
    int insertSelective(CrmCustomer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crm_customer
     *
     * @mbg.generated
     */
    List<CrmCustomer> selectByExample(CrmCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crm_customer
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") CrmCustomer record, @Param("example") CrmCustomerExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table crm_customer
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") CrmCustomer record, @Param("example") CrmCustomerExample example);
}