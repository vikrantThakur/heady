
package com.myapplication.heady.home.MyModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class HomeModel {

    @SerializedName("categories")
    private List<Category> mCategories;

    @SerializedName("rankings")
    private List<Ranking> mRankings;

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories = categories;
    }

    public List<Ranking> getRankings() {
        return mRankings;
    }

    public void setRankings(List<Ranking> rankings) {
        mRankings = rankings;
    }

}
