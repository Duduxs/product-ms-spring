package com.edudev.productms.utils;

public final class SortParam {

    private String orderBy;

    private String direction;

    public SortParam() {
        this.orderBy = "id";
        this.direction = "DESC";
    }

    public String getOrderBy() { return orderBy; }

    public void setOrderBy(final String orderBy) { this.orderBy = orderBy; }

    public String getDirection() { return direction; }

    public void setDirection(final String direction) { this.direction = direction; }
}
