package com.mincrm.entity.pojo;

/**
 * Created by sunhh on 2019/12/21.
 */
public class QueryPage {
    private int page;//当前查询页号
    private int rows; //查询行数
    private int currentshopid ; //当前操作的店铺id

    public int getCurrentshopid() {
        return currentshopid;
    }

    public void setCurrentshopid(int currentshopid) {
        this.currentshopid = currentshopid;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    /**
     * 开始查询起始行  limit的第一个参数
     *  select * from mb_customer  ORDER BY create_date desc limit 20,10
     * @return
     */
    public int getOffgset() {
        return (page-1) * rows;
    }

}
