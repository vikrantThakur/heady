
package com.myapplication.heady.home.MyModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Ranking {

    @SerializedName("products")
    private List<Product> mProducts;
    @SerializedName("ranking")
    private String mRanking;

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public String getRanking() {
        return mRanking;
    }

    public void setRanking(String ranking) {
        mRanking = ranking;
    }

}
