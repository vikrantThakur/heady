package com.myapplication.heady.webservice;

import com.myapplication.heady.home.MyModels.HomeModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServiceClient {

    @GET("json")
    Call<HomeModel> getHomeResponse();
}
