package com.heady.shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("products")
    private ArrayList<Product> products = null;
    @SerializedName("child_categories")
    private ArrayList<String> childCategories = null;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<String> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(ArrayList<String> childCategories) {
        this.childCategories = childCategories;
    }


}
