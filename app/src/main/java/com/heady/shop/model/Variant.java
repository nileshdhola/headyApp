package com.heady.shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Variant implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("color")
    private String color;
    @SerializedName("size")
    private String size;
    @SerializedName("price")
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
