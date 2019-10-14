
package com.myapplication.heady.home.MyModels;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("date_added")
    private String mDateAdded;
    @SerializedName("id")
    private Long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("tax")
    private Tax mTax;
    @SerializedName("variants")
    private List<Variant> mVariants;

    @SerializedName("view_count")
    private Long mViewCount;

    @SerializedName("order_count")
    private Long mOrderCount;

    @SerializedName("shares")
    private Long mShares;

    private List<ProductRanking> rankings;

    public String getDateAdded() {
        return mDateAdded;
    }

    public void setDateAdded(String dateAdded) {
        mDateAdded = dateAdded;
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

    public Tax getTax() {
        return mTax;
    }

    public void setTax(Tax tax) {
        mTax = tax;
    }

    public List<Variant> getVariants() {
        return mVariants;
    }

    public void setVariants(List<Variant> variants) {
        mVariants = variants;
    }

    public Long getViewCount() {
        return mViewCount;
    }

    public Long getmOrderCount() {
        return mOrderCount;
    }

    public void setmOrderCount(Long mOrderCount) {
        this.mOrderCount = mOrderCount;
    }

    public Long getmShares() {
        return mShares;
    }

    public void setmShares(Long mShares) {
        this.mShares = mShares;
    }

    public void setViewCount(Long viewCount) {
        mViewCount = viewCount;
    }


    public List<ProductRanking> getRankings() {
        return rankings;
    }

    public void setRankings(List<ProductRanking> rankings) {
        this.rankings = rankings;
    }

    public String getVariantsAvailable() {
        Variant variant;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getVariants().size(); i++) {
            variant = getVariants().get(i);
            if (i == 0)
                stringBuilder.append(variant.getPrice());
            else
                stringBuilder.append("," + variant.getPrice());
        }
        return "Price range : " + stringBuilder.toString();
    }

    public String getRankingData() {
        if (getRankings() == null || getRankings().size() == 0) {
            return "";
        }
        ProductRanking productRanking;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getRankings().size(); i++) {
            if (i == 0) {
                stringBuilder.append(getRankings().get(i).getRankingName() + " : " + getRankings().get(i).getRanking());
            } else {
                stringBuilder.append("\n" + getRankings().get(i).getRankingName() + " : " + getRankings().get(i).getRanking());
            }
        }
        return stringBuilder.toString();
    }
}
