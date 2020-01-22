package com.heady.shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ranking implements Serializable {

    @SerializedName("ranking")
    private String ranking;
    @SerializedName("products")
    private List<ProductRanking> products = null;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<ProductRanking> getProducts() {
        return products;
    }

    public void setProducts(List<ProductRanking> products) {
        this.products = products;
    }


}
