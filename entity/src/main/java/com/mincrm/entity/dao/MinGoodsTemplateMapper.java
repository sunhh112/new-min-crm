package com.mincrm.entity.dao;

import com.mincrm.entity.model.MinGoodsTemplate;
import com.mincrm.entity.model.MinGoodsTemplateExample;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface MinGoodsTemplateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    long countByExample(MinGoodsTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    int deleteByExample(MinGoodsTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    @Delete({
        "delete from min_goods_template",
        "where goods_id = #{goodsId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    @Insert({
        "insert into min_goods_template (goods_name, category_type, ",
        "goods_purchase_price, goods_sale_price, ",
        "min_category, active_flag, ",
        "create_date, update_date, ",
        "create_uid, update_uid, ",
        "goods_version)",
        "values (#{goodsName,jdbcType=VARCHAR}, #{categoryType,jdbcType=TINYINT}, ",
        "#{goodsPurchasePrice,jdbcType=DOUBLE}, #{goodsSalePrice,jdbcType=DOUBLE}, ",
        "#{minCategory,jdbcType=VARCHAR}, #{activeFlag,jdbcType=TINYINT}, ",
        "#{createDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{createUid,jdbcType=INTEGER}, #{updateUid,jdbcType=INTEGER}, ",
        "#{goodsVersion,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="goodsId", before=false, resultType=Integer.class)
    int insert(MinGoodsTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    int insertSelective(MinGoodsTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    List<MinGoodsTemplate> selectByExample(MinGoodsTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "goods_id, goods_name, category_type, goods_purchase_price, goods_sale_price, ",
        "min_category, active_flag, create_date, update_date, create_uid, update_uid, ",
        "goods_version",
        "from min_goods_template",
        "where goods_id = #{goodsId,jdbcType=INTEGER}"
    })
    @ResultMap("com.mincrm.entity.dao.MinGoodsTemplateMapper.BaseResultMap")
    MinGoodsTemplate selectByPrimaryKey(Integer goodsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MinGoodsTemplate record, @Param("example") MinGoodsTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MinGoodsTemplate record, @Param("example") MinGoodsTemplateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MinGoodsTemplate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table min_goods_template
     *
     * @mbg.generated
     */
    @Update({
        "update min_goods_template",
        "set goods_name = #{goodsName,jdbcType=VARCHAR},",
          "category_type = #{categoryType,jdbcType=TINYINT},",
          "goods_purchase_price = #{goodsPurchasePrice,jdbcType=DOUBLE},",
          "goods_sale_price = #{goodsSalePrice,jdbcType=DOUBLE},",
          "min_category = #{minCategory,jdbcType=VARCHAR},",
          "active_flag = #{activeFlag,jdbcType=TINYINT},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP},",
          "create_uid = #{createUid,jdbcType=INTEGER},",
          "update_uid = #{updateUid,jdbcType=INTEGER},",
          "goods_version = #{goodsVersion,jdbcType=VARCHAR}",
        "where goods_id = #{goodsId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MinGoodsTemplate record);
}