package com.heady.shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Ranking implements Serializable {

    @SerializedName("ranking")
    private String ranking;
    @SerializedName("products")
    private ArrayList<ProductRanking> products = null;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public ArrayList<ProductRanking> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductRanking> products) {
        this.products = products;
    }


}
