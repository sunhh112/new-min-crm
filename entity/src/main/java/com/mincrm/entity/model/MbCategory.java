package com.mincrm.entity.model;

import java.util.Date;

public class MbCategory {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.category_id
     *
     * @mbg.generated
     */
    private Integer categoryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.category_name
     *
     * @mbg.generated
     */
    private String categoryName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.shop_id
     *
     * @mbg.generated
     */
    private Integer shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.active_flag
     *
     * @mbg.generated
     */
    private Byte activeFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.create_date
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.update_date
     *
     * @mbg.generated
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.create_uid
     *
     * @mbg.generated
     */
    private Integer createUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mb_category.update_uid
     *
     * @mbg.generated
     */
    private Integer updateUid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.category_id
     *
     * @return the value of mb_category.category_id
     *
     * @mbg.generated
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.category_id
     *
     * @param categoryId the value for mb_category.category_id
     *
     * @mbg.generated
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.category_name
     *
     * @return the value of mb_category.category_name
     *
     * @mbg.generated
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.category_name
     *
     * @param categoryName the value for mb_category.category_name
     *
     * @mbg.generated
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.shop_id
     *
     * @return the value of mb_category.shop_id
     *
     * @mbg.generated
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.shop_id
     *
     * @param shopId the value for mb_category.shop_id
     *
     * @mbg.generated
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.active_flag
     *
     * @return the value of mb_category.active_flag
     *
     * @mbg.generated
     */
    public Byte getActiveFlag() {
        return activeFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.active_flag
     *
     * @param activeFlag the value for mb_category.active_flag
     *
     * @mbg.generated
     */
    public void setActiveFlag(Byte activeFlag) {
        this.activeFlag = activeFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.create_date
     *
     * @return the value of mb_category.create_date
     *
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.create_date
     *
     * @param createDate the value for mb_category.create_date
     *
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.update_date
     *
     * @return the value of mb_category.update_date
     *
     * @mbg.generated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.update_date
     *
     * @param updateDate the value for mb_category.update_date
     *
     * @mbg.generated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.create_uid
     *
     * @return the value of mb_category.create_uid
     *
     * @mbg.generated
     */
    public Integer getCreateUid() {
        return createUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.create_uid
     *
     * @param createUid the value for mb_category.create_uid
     *
     * @mbg.generated
     */
    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mb_category.update_uid
     *
     * @return the value of mb_category.update_uid
     *
     * @mbg.generated
     */
    public Integer getUpdateUid() {
        return updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mb_category.update_uid
     *
     * @param updateUid the value for mb_category.update_uid
     *
     * @mbg.generated
     */
    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }
}