package com.heady.shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductRanking implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("view_count")
    private String viewCount;
    @SerializedName("order_count")
    private String orderCount;
    @SerializedName("shares")
    private String shares;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getShares() {
        return shares;
    }

    public void setShares(String shares) {
        this.shares = shares;
    }
}
