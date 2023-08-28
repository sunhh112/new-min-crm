package com.mincrm.entity.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VShopAccountRoleExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public VShopAccountRoleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Integer value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Integer value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Integer value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Integer value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Integer value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Integer> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Integer> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Integer value1, Integer value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Integer value1, Integer value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopCountryIsNull() {
            addCriterion("shop_country is null");
            return (Criteria) this;
        }

        public Criteria andShopCountryIsNotNull() {
            addCriterion("shop_country is not null");
            return (Criteria) this;
        }

        public Criteria andShopCountryEqualTo(String value) {
            addCriterion("shop_country =", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryNotEqualTo(String value) {
            addCriterion("shop_country <>", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryGreaterThan(String value) {
            addCriterion("shop_country >", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryGreaterThanOrEqualTo(String value) {
            addCriterion("shop_country >=", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryLessThan(String value) {
            addCriterion("shop_country <", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryLessThanOrEqualTo(String value) {
            addCriterion("shop_country <=", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryLike(String value) {
            addCriterion("shop_country like", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryNotLike(String value) {
            addCriterion("shop_country not like", value, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryIn(List<String> values) {
            addCriterion("shop_country in", values, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryNotIn(List<String> values) {
            addCriterion("shop_country not in", values, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryBetween(String value1, String value2) {
            addCriterion("shop_country between", value1, value2, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopCountryNotBetween(String value1, String value2) {
            addCriterion("shop_country not between", value1, value2, "shopCountry");
            return (Criteria) this;
        }

        public Criteria andShopProvinceIsNull() {
            addCriterion("shop_province is null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceIsNotNull() {
            addCriterion("shop_province is not null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceEqualTo(String value) {
            addCriterion("shop_province =", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotEqualTo(String value) {
            addCriterion("shop_province <>", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceGreaterThan(String value) {
            addCriterion("shop_province >", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("shop_province >=", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceLessThan(String value) {
            addCriterion("shop_province <", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceLessThanOrEqualTo(String value) {
            addCriterion("shop_province <=", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceLike(String value) {
            addCriterion("shop_province like", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotLike(String value) {
            addCriterion("shop_province not like", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceIn(List<String> values) {
            addCriterion("shop_province in", values, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotIn(List<String> values) {
            addCriterion("shop_province not in", values, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceBetween(String value1, String value2) {
            addCriterion("shop_province between", value1, value2, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotBetween(String value1, String value2) {
            addCriterion("shop_province not between", value1, value2, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopCityIsNull() {
            addCriterion("shop_city is null");
            return (Criteria) this;
        }

        public Criteria andShopCityIsNotNull() {
            addCriterion("shop_city is not null");
            return (Criteria) this;
        }

        public Criteria andShopCityEqualTo(String value) {
            addCriterion("shop_city =", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotEqualTo(String value) {
            addCriterion("shop_city <>", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityGreaterThan(String value) {
            addCriterion("shop_city >", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityGreaterThanOrEqualTo(String value) {
            addCriterion("shop_city >=", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityLessThan(String value) {
            addCriterion("shop_city <", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityLessThanOrEqualTo(String value) {
            addCriterion("shop_city <=", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityLike(String value) {
            addCriterion("shop_city like", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotLike(String value) {
            addCriterion("shop_city not like", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityIn(List<String> values) {
            addCriterion("shop_city in", values, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotIn(List<String> values) {
            addCriterion("shop_city not in", values, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityBetween(String value1, String value2) {
            addCriterion("shop_city between", value1, value2, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotBetween(String value1, String value2) {
            addCriterion("shop_city not between", value1, value2, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopAreaIsNull() {
            addCriterion("shop_area is null");
            return (Criteria) this;
        }

        public Criteria andShopAreaIsNotNull() {
            addCriterion("shop_area is not null");
            return (Criteria) this;
        }

        public Criteria andShopAreaEqualTo(String value) {
            addCriterion("shop_area =", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaNotEqualTo(String value) {
            addCriterion("shop_area <>", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaGreaterThan(String value) {
            addCriterion("shop_area >", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaGreaterThanOrEqualTo(String value) {
            addCriterion("shop_area >=", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaLessThan(String value) {
            addCriterion("shop_area <", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaLessThanOrEqualTo(String value) {
            addCriterion("shop_area <=", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaLike(String value) {
            addCriterion("shop_area like", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaNotLike(String value) {
            addCriterion("shop_area not like", value, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaIn(List<String> values) {
            addCriterion("shop_area in", values, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaNotIn(List<String> values) {
            addCriterion("shop_area not in", values, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaBetween(String value1, String value2) {
            addCriterion("shop_area between", value1, value2, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAreaNotBetween(String value1, String value2) {
            addCriterion("shop_area not between", value1, value2, "shopArea");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNull() {
            addCriterion("shop_address is null");
            return (Criteria) this;
        }

        public Criteria andShopAddressIsNotNull() {
            addCriterion("shop_address is not null");
            return (Criteria) this;
        }

        public Criteria andShopAddressEqualTo(String value) {
            addCriterion("shop_address =", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotEqualTo(String value) {
            addCriterion("shop_address <>", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThan(String value) {
            addCriterion("shop_address >", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressGreaterThanOrEqualTo(String value) {
            addCriterion("shop_address >=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThan(String value) {
            addCriterion("shop_address <", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLessThanOrEqualTo(String value) {
            addCriterion("shop_address <=", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressLike(String value) {
            addCriterion("shop_address like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotLike(String value) {
            addCriterion("shop_address not like", value, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressIn(List<String> values) {
            addCriterion("shop_address in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotIn(List<String> values) {
            addCriterion("shop_address not in", values, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressBetween(String value1, String value2) {
            addCriterion("shop_address between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andShopAddressNotBetween(String value1, String value2) {
            addCriterion("shop_address not between", value1, value2, "shopAddress");
            return (Criteria) this;
        }

        public Criteria andActiveFlagIsNull() {
            addCriterion("active_flag is null");
            return (Criteria) this;
        }

        public Criteria andActiveFlagIsNotNull() {
            addCriterion("active_flag is not null");
            return (Criteria) this;
        }

        public Criteria andActiveFlagEqualTo(Byte value) {
            addCriterion("active_flag =", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagNotEqualTo(Byte value) {
            addCriterion("active_flag <>", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagGreaterThan(Byte value) {
            addCriterion("active_flag >", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("active_flag >=", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagLessThan(Byte value) {
            addCriterion("active_flag <", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagLessThanOrEqualTo(Byte value) {
            addCriterion("active_flag <=", value, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagIn(List<Byte> values) {
            addCriterion("active_flag in", values, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagNotIn(List<Byte> values) {
            addCriterion("active_flag not in", values, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagBetween(Byte value1, Byte value2) {
            addCriterion("active_flag between", value1, value2, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andActiveFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("active_flag not between", value1, value2, "activeFlag");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateUidIsNull() {
            addCriterion("create_uid is null");
            return (Criteria) this;
        }

        public Criteria andCreateUidIsNotNull() {
            addCriterion("create_uid is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUidEqualTo(Integer value) {
            addCriterion("create_uid =", value, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidNotEqualTo(Integer value) {
            addCriterion("create_uid <>", value, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidGreaterThan(Integer value) {
            addCriterion("create_uid >", value, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_uid >=", value, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidLessThan(Integer value) {
            addCriterion("create_uid <", value, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidLessThanOrEqualTo(Integer value) {
            addCriterion("create_uid <=", value, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidIn(List<Integer> values) {
            addCriterion("create_uid in", values, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidNotIn(List<Integer> values) {
            addCriterion("create_uid not in", values, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidBetween(Integer value1, Integer value2) {
            addCriterion("create_uid between", value1, value2, "createUid");
            return (Criteria) this;
        }

        public Criteria andCreateUidNotBetween(Integer value1, Integer value2) {
            addCriterion("create_uid not between", value1, value2, "createUid");
            return (Criteria) this;
        }

        public Criteria andStrDescIsNull() {
            addCriterion("str_desc is null");
            return (Criteria) this;
        }

        public Criteria andStrDescIsNotNull() {
            addCriterion("str_desc is not null");
            return (Criteria) this;
        }

        public Criteria andStrDescEqualTo(String value) {
            addCriterion("str_desc =", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescNotEqualTo(String value) {
            addCriterion("str_desc <>", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescGreaterThan(String value) {
            addCriterion("str_desc >", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescGreaterThanOrEqualTo(String value) {
            addCriterion("str_desc >=", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescLessThan(String value) {
            addCriterion("str_desc <", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescLessThanOrEqualTo(String value) {
            addCriterion("str_desc <=", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescLike(String value) {
            addCriterion("str_desc like", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescNotLike(String value) {
            addCriterion("str_desc not like", value, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescIn(List<String> values) {
            addCriterion("str_desc in", values, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescNotIn(List<String> values) {
            addCriterion("str_desc not in", values, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescBetween(String value1, String value2) {
            addCriterion("str_desc between", value1, value2, "strDesc");
            return (Criteria) this;
        }

        public Criteria andStrDescNotBetween(String value1, String value2) {
            addCriterion("str_desc not between", value1, value2, "strDesc");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIsNull() {
            addCriterion("role_type is null");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIsNotNull() {
            addCriterion("role_type is not null");
            return (Criteria) this;
        }

        public Criteria andRoleTypeEqualTo(Byte value) {
            addCriterion("role_type =", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotEqualTo(Byte value) {
            addCriterion("role_type <>", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeGreaterThan(Byte value) {
            addCriterion("role_type >", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("role_type >=", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeLessThan(Byte value) {
            addCriterion("role_type <", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeLessThanOrEqualTo(Byte value) {
            addCriterion("role_type <=", value, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeIn(List<Byte> values) {
            addCriterion("role_type in", values, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotIn(List<Byte> values) {
            addCriterion("role_type not in", values, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeBetween(Byte value1, Byte value2) {
            addCriterion("role_type between", value1, value2, "roleType");
            return (Criteria) this;
        }

        public Criteria andRoleTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("role_type not between", value1, value2, "roleType");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table v_shop_account_role
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table v_shop_account_role
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}