package com.aitongyi.web.bean;

/**
 * Created by hushuang on 2016/11/26.
 */
public class RequestData {
    private Integer currentPage;
    private Integer itemsPerPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }
}
