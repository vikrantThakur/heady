
package com.myapplication.heady.home.MyModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("child_categories")
    private List<Object> mChildCategories;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("products")
    private List<Product> mProducts;

    public List<Object> getChildCategories() {
        return mChildCategories;
    }

    public void setChildCategories(List<Object> childCategories) {
        mChildCategories = childCategories;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

}
