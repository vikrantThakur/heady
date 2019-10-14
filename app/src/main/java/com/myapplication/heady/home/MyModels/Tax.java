
package com.myapplication.heady.home.MyModels;

import com.google.gson.annotations.SerializedName;

public class Tax {

    @SerializedName("name")
    private String mName;
    @SerializedName("value")
    private Double mValue;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getValue() {
        return mValue;
    }

    public void setValue(Double value) {
        mValue = value;
    }

}
