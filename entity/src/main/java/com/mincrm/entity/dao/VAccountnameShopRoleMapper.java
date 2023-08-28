package com.mincrm.entity.dao;

import com.mincrm.entity.model.VAccountnameShopRole;
import com.mincrm.entity.model.VAccountnameShopRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface VAccountnameShopRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_accountname_shop_role
     *
     * @mbg.generated
     */
    long countByExample(VAccountnameShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_accountname_shop_role
     *
     * @mbg.generated
     */
    int deleteByExample(VAccountnameShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_accountname_shop_role
     *
     * @mbg.generated
     */
    @Insert({
        "insert into v_accountname_shop_role (account_tel, account_name, ",
        "shop_id, uid, role_type, ",
        "role_id, active_flag, ",
        "create_date, create_uid)",
        "values (#{accountTel,jdbcType=VARCHAR}, #{accountName,jdbcType=VARCHAR}, ",
        "#{shopId,jdbcType=INTEGER}, #{uid,jdbcType=INTEGER}, #{roleType,jdbcType=TINYINT}, ",
        "#{roleId,jdbcType=INTEGER}, #{activeFlag,jdbcType=TINYINT}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{createUid,jdbcType=INTEGER})"
    })
    int insert(VAccountnameShopRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_accountname_shop_role
     *
     * @mbg.generated
     */
    int insertSelective(VAccountnameShopRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_accountname_shop_role
     *
     * @mbg.generated
     */
    List<VAccountnameShopRole> selectByExample(VAccountnameShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_accountname_shop_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") VAccountnameShopRole record, @Param("example") VAccountnameShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_accountname_shop_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") VAccountnameShopRole record, @Param("example") VAccountnameShopRoleExample example);
}