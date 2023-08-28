package com.mincrm.entity.pojo;

import java.util.List;

/**
 * Created by sunhh on 2019/12/21.
 */
public class ReturnPage<T> {

    public static <T> ReturnPage buildPage(int total,List rows) {
        ReturnPage returnPage = new ReturnPage();
        returnPage.setTotal(total);
        returnPage.setRows(rows);
        return returnPage;
    }

    private int total;

    private List<T> rows;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }




}
