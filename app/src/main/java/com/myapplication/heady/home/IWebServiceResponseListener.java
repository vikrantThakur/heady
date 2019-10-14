package com.myapplication.heady.home;

public interface IWebServiceResponseListener {
    void onSuccess(Object response, String statusCode);

    void onFailure(String message, String statusCode);
}
