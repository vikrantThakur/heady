package com.myapplication.heady.home;

import android.content.Context;
import android.os.AsyncTask;


import com.myapplication.heady.home.MyModels.HomeModel;
import com.myapplication.heady.home.MyModels.Product;
import com.myapplication.heady.home.MyModels.ProductRanking;
import com.myapplication.heady.home.MyModels.Ranking;
import com.myapplication.heady.webservice.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {


    private IWebServiceResponseListener iWebServiceResponseListener;

    public Repository(Context context, IWebServiceResponseListener iWebServiceResponseListener) {
        this.iWebServiceResponseListener = iWebServiceResponseListener;
    }

    HashMap<String, List<ProductRanking>> productIdVsRankingValue = new HashMap<>();

    public HashMap<String, List<ProductRanking>> getProductIdVsRankingValue() {
        return productIdVsRankingValue;
    }

    public void callWebService() {

        Call<HomeModel> call = RetrofitClient.getService().getHomeResponse();
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                if (response.isSuccessful() && response.body() != null) {

                    HomeModel homeModel = response.body();
                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {

                            List<Ranking> rankings = homeModel.getRankings();

                            if (rankings.size() > 0) {

                                ProductRanking productRanking = new ProductRanking();
                                for (int i = 0; i < rankings.size(); i++) {
                                    List<ProductRanking> productRankings = new ArrayList<>();
                                    String ranking_name = rankings.get(i).getRanking();
                                    List<Product> products = rankings.get(i).getProducts();

                                    for (int j = 0; j < products.size(); j++) {

                                        if (productIdVsRankingValue.containsKey("" + products.get(j).getId())) {
                                            productRankings = productIdVsRankingValue.get("" + products.get(j).getId());

                                            productRanking = new ProductRanking();

                                            if (products.get(j).getViewCount() != null)
                                                productRanking.setRanking(products.get(j).getViewCount());
                                            else if (products.get(j).getmShares() != null)
                                                productRanking.setRanking(products.get(j).getmShares());
                                            else
                                                productRanking.setRanking(products.get(j).getmOrderCount());


                                            productRanking.setRankingName(ranking_name);

                                            productRankings.add(productRanking);
                                            productIdVsRankingValue.put("" + products.get(j).getId(), productRankings);

                                        } else {
                                            productRankings = new ArrayList<>();
                                            productRanking = new ProductRanking();

                                            if (products.get(j).getViewCount() != null)
                                                productRanking.setRanking(products.get(j).getViewCount());
                                            else if (products.get(j).getmShares() != null)
                                                productRanking.setRanking(products.get(j).getmShares());
                                            else
                                                productRanking.setRanking(products.get(j).getmOrderCount());


                                            productRanking.setRankingName(ranking_name);
                                            productRankings.add(productRanking);
                                            productIdVsRankingValue.put("" + products.get(j).getId(), productRankings);
                                        }
                                    }
                                }
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);


                            iWebServiceResponseListener.onSuccess(homeModel, "" + response.code());
                        }
                    }.execute();


                } else {
                    iWebServiceResponseListener.onFailure(response.message(), "" + response.code());
                }
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                iWebServiceResponseListener.onFailure("Web service failure", "");
            }
        });
    }
}
