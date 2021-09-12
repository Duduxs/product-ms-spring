package com.edudev.productms.utils;

public final class PageParam {

    private Integer page;

    private Integer size;

    public PageParam() {
        this.page = 0;
        this.size = 1000;
    }

    public Integer getPage() { return page; }

    public void setPage(final Integer page) { this.page = page; }

    public Integer getSize() { return size; }

    public void setSize(final Integer size) { this.size = size; }
}
