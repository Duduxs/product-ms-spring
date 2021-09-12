package com.edudev.productms.utils;

public final class FilterParam {

    private String q;

    private Double minPrice;

    private Double maxPrice;

    public FilterParam() {
        this.q = "";
        this.minPrice = 0.0;
        this.maxPrice = 0.0;
    }

    public FilterParam(final String q, final Double minPrice, final Double maxPrice) {
        this.q = q;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getQ() { return q; }

    public void setQ(final String q) { this.q = q; }

    public Double getMinPrice() { return minPrice; }

    public void setMinPrice(final Double minPrice) { this.minPrice = minPrice; }

    public Double getMaxPrice() { return maxPrice; }

    public void setMaxPrice(final Double maxPrice) { this.maxPrice = maxPrice;}
}
