package com.dg.pojo.vo;

import java.util.Collection;

public class PageResult {
    // 当前页
    private int currentPage;
    // 每页显示多少数据
    private int pageSize;
    // 总页数
    private int totalPage;
    // 总记录数
    private long totalRows;
    // 数据列表
    private Collection list;

    public PageResult() {
    }

    /**
     * page结果集
     *
     * @param currentPage：当前页
     * @param totalRows：总记录数
     * @param list：数据列表
     */
    public PageResult(int currentPage, long totalRows, Collection list) {
        this.currentPage = currentPage;
        this.totalRows = totalRows;
        this.list = list;
    }
}
