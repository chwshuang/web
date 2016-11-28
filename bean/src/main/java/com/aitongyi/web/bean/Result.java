package com.aitongyi.web.bean;

import java.util.List;

/**
 * Created by hushuang on 2016/11/26.
 */
public class Result {
    private Integer total;

    private List<User> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
