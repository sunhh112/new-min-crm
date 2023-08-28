package com.mincrm.entity.dao;

import com.mincrm.entity.model.MinAccountShopRole;
import com.mincrm.entity.model.MinAccountShopRoleExample;
import java.util.List;
import java.util.Map;

import com.mincrm.entity.pojo.ShopVo;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface MinAccountShopRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    long countByExample(MinAccountShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    int deleteByExample(MinAccountShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    @Delete({
        "delete from min_account_shop_role",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    @Insert({
        "insert into min_account_shop_role (uid, shop_id, ",
        "role_type, active_flag, ",
        "create_date, update_date, ",
        "create_uid, update_uid)",
        "values (#{uid,jdbcType=INTEGER}, #{shopId,jdbcType=INTEGER}, ",
        "#{roleType,jdbcType=TINYINT}, #{activeFlag,jdbcType=TINYINT}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{createUid,jdbcType=INTEGER}, #{updateUid,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="roleId", before=false, resultType=Integer.class)
    int insert(MinAccountShopRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    int insertSelective(MinAccountShopRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    List<MinAccountShopRole> selectByExample(MinAccountShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "role_id, uid, shop_id, role_type, active_flag, create_date, update_date, create_uid, ",
        "update_uid",
        "from min_account_shop_role",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    @ResultMap("com.mincrm.entity.dao.MinAccountShopRoleMapper.BaseResultMap")
    MinAccountShopRole selectByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MinAccountShopRole record, @Param("example") MinAccountShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MinAccountShopRole record, @Param("example") MinAccountShopRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MinAccountShopRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_account_shop_role
     *
     * @mbg.generated
     */
    @Update({
        "update min_account_shop_role",
        "set uid = #{uid,jdbcType=INTEGER},",
          "shop_id = #{shopId,jdbcType=INTEGER},",
          "role_type = #{roleType,jdbcType=TINYINT},",
          "active_flag = #{activeFlag,jdbcType=TINYINT},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP},",
          "create_uid = #{createUid,jdbcType=INTEGER},",
          "update_uid = #{updateUid,jdbcType=INTEGER}",
        "where role_id = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MinAccountShopRole record);
    List<ShopVo> selectShopVoByUid(Integer uid);
    List<Map<String, Object>> selectByShopid(Integer shopid);

}