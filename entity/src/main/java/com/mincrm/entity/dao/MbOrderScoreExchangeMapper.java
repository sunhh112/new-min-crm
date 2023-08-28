package com.mincrm.entity.dao;

import com.mincrm.entity.model.MbOrderScoreExchange;
import com.mincrm.entity.model.MbOrderScoreExchangeExample;
import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface MbOrderScoreExchangeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    long countByExample(MbOrderScoreExchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    int deleteByExample(MbOrderScoreExchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    @Delete({
        "delete from mb_order_score_exchange",
        "where exchange_id = #{exchangeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer exchangeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    @Insert({
        "insert into mb_order_score_exchange (customer_id, shop_id, ",
        "goods_name, exchange_score, ",
        "active_flag, create_date, ",
        "update_date, create_uid, ",
        "update_uid, str_desc)",
        "values (#{customerId,jdbcType=INTEGER}, #{shopId,jdbcType=INTEGER}, ",
        "#{goodsName,jdbcType=VARCHAR}, #{exchangeScore,jdbcType=INTEGER}, ",
        "#{activeFlag,jdbcType=TINYINT}, #{createDate,jdbcType=TIMESTAMP}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{createUid,jdbcType=INTEGER}, ",
        "#{updateUid,jdbcType=INTEGER}, #{strDesc,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="exchangeId", before=false, resultType=Integer.class)
    int insert(MbOrderScoreExchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    int insertSelective(MbOrderScoreExchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    List<MbOrderScoreExchange> selectByExample(MbOrderScoreExchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "exchange_id, customer_id, shop_id, goods_name, exchange_score, active_flag, ",
        "create_date, update_date, create_uid, update_uid, str_desc",
        "from mb_order_score_exchange",
        "where exchange_id = #{exchangeId,jdbcType=INTEGER}"
    })
    @ResultMap("com.mincrm.entity.dao.MbOrderScoreExchangeMapper.BaseResultMap")
    MbOrderScoreExchange selectByPrimaryKey(Integer exchangeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") MbOrderScoreExchange record, @Param("example") MbOrderScoreExchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") MbOrderScoreExchange record, @Param("example") MbOrderScoreExchangeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MbOrderScoreExchange record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mb_order_score_exchange
     *
     * @mbg.generated
     */
    @Update({
        "update mb_order_score_exchange",
        "set customer_id = #{customerId,jdbcType=INTEGER},",
          "shop_id = #{shopId,jdbcType=INTEGER},",
          "goods_name = #{goodsName,jdbcType=VARCHAR},",
          "exchange_score = #{exchangeScore,jdbcType=INTEGER},",
          "active_flag = #{activeFlag,jdbcType=TINYINT},",
          "create_date = #{createDate,jdbcType=TIMESTAMP},",
          "update_date = #{updateDate,jdbcType=TIMESTAMP},",
          "create_uid = #{createUid,jdbcType=INTEGER},",
          "update_uid = #{updateUid,jdbcType=INTEGER},",
          "str_desc = #{strDesc,jdbcType=VARCHAR}",
        "where exchange_id = #{exchangeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(MbOrderScoreExchange record);
}