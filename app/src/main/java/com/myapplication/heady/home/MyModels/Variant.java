
package com.myapplication.heady.home.MyModels;

import com.google.gson.annotations.SerializedName;

public class Variant {

    @SerializedName("color")
    private String mColor;
    @SerializedName("id")
    private Long mId;
    @SerializedName("price")
    private Long mPrice;
    @SerializedName("size")
    private Long mSize;

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getPrice() {
        return mPrice;
    }

    public void setPrice(Long price) {
        mPrice = price;
    }

    public String getPriceInString() {
        return String.valueOf(mPrice);
    }

    public Long getSize() {
        return mSize;
    }

    public String getSizeinString() {
        return String.valueOf(mSize);
    }

    public void setSize(Long size) {
        mSize = size;
    }

}
