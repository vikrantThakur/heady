package com.myapplication.heady.home;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.myapplication.heady.R;
import com.myapplication.heady.adapters.ProductDataAdapter;
import com.myapplication.heady.adapters.VariantAdapter;
import com.myapplication.heady.fragments.ProductListFragment;
import com.myapplication.heady.home.MyModels.Category;
import com.myapplication.heady.home.MyModels.HomeModel;
import com.myapplication.heady.home.MyModels.Product;
import com.myapplication.heady.home.MyModels.ProductRanking;
import com.myapplication.heady.home.MyModels.Ranking;
import com.myapplication.heady.home.MyModels.Variant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeViewModel extends AndroidViewModel implements IWebServiceResponseListener {

    MutableLiveData<HomeModel> homeModelMutableLiveData;
    private CategoryDataAdapter categoryDataAdapter;
    private ProductDataAdapter productAdapter;
    private VariantAdapter variantAdapter;
    Context context;
    Repository repository;
    private List<Product> currentProductList;
    private List<Variant> currentVariantList;
    private IFragmentTranscation iFragmentTranscation;
    HashMap<String, List<ProductRanking>> productRankingMap;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        repository = new Repository(this.context, this);
    }

    private void setCurrentProductList(List<Product> currentProductList) {
        this.currentProductList = currentProductList;
    }

    private List<Product> getProductList() {
        return currentProductList;
    }

    public void setInterfaceListener(IFragmentTranscation iFragmentTranscation) {
        this.iFragmentTranscation = iFragmentTranscation;
    }

    public MutableLiveData<HomeModel> getHomeLiveData() {
        if (homeModelMutableLiveData == null) {
            homeModelMutableLiveData = new MutableLiveData<>();
            categoryDataAdapter = new CategoryDataAdapter(R.layout.category_home_item, this);
            callWebService();
        }
        return homeModelMutableLiveData;
    }

    public CategoryDataAdapter getCategoryDataAdapter() {
        return categoryDataAdapter;
    }

    public ProductDataAdapter getProductAdapter() {
        return productAdapter;
    }

    public VariantAdapter getVariantAdapter() {
        //variantAdapter = new VariantAdapter(R.layout.variant_list_item, this);
        return variantAdapter;
    }

    public void onItemClick(Integer index) {
        productAdapter = new ProductDataAdapter(R.layout.product_list_item, this);
        iFragmentTranscation.replaceFragment(new ProductListFragment(), "product");
        setCurrentProductList(homeModelMutableLiveData.getValue().getCategories().get(index).getProducts());
        setProductListInAdapter(getProductList());
    }

    public void onProductItemClick(Integer index) {
        Log.i("vikrant_product", getProductList().get(index).getName());
    }

    public Variant fetchVariantByPosition(int position) {
        return currentVariantList.get(position);
    }

    public Category fetchCategoryAtPosition(int position) {
        return homeModelMutableLiveData.getValue().getCategories().get(position);
    }

    public Product fetchProductAtPosition(int position) {
        Product product = getProductList().get(position);
        if (product.getRankings() == null && productRankingMap.containsKey("" + product.getId())) {
            product.setRankings(productRankingMap.get("" + product.getId()));
        }
        return product;
    }

    private void callWebService() {
        repository.callWebService();
    }

    @Override
    public void onSuccess(Object response, String statusCode) {
        HashMap<String, List<Product>> i = getMostViewedList((HomeModel) response);

        homeModelMutableLiveData.setValue((HomeModel) response);

        productRankingMap = repository.getProductIdVsRankingValue();
    }

    private HashMap<String, List<Product>> getMostViewedList(HomeModel homeModel) {

        List<Ranking>rankings = homeModel.getRankings();

        if(rankings != null && rankings.size() > 0) {
            List<Product> mainProductsList = new ArrayList<>();
            List<Product> rankedProductsList = new ArrayList<>();

            HashMap<String, List<Product>> hshMapRankings = new HashMap<>();

            for (int i = 0; i< rankings.size(); i++) {
                List<Product> rankingProducts = rankings.get(i).getProducts();
                for(int j=0; j < rankingProducts.size();j++){

                    long productId = rankingProducts.get(j).getId();
                    List<Product> interimProducts = homeModel.getCategories().get(i).getProducts();
                    final int productPosition = i;
                    Product rankedProduct = interimProducts.stream()
                            .filter(product -> productId == interimProducts.get(productPosition).getId())
                            .findAny()
                            .orElse(null);

                }



//                        .filter(Product -> (rankings.get(i).getProducts().get(i).getId() == homeModel.getCategories().get(i).getProducts().get(i).getId())
//                                .findAny()
//                                .orElse(null);

            }
        }


        return null;
    }

    @Override
    public void onFailure(String message, String statusCode) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void setCategoryListInAdapter(List<Category> categories) {
        categoryDataAdapter.setCategories(categories);

        categoryDataAdapter.notifyDataSetChanged();
    }

    public void setProductListInAdapter(List<Product> products) {
        productAdapter.setProducts(products);
        productAdapter.notifyDataSetChanged();
    }

    public void setVariantListInAdapter(List<Variant> variants) {
        variantAdapter.setVariants(variants);
        variantAdapter.notifyDataSetChanged();
    }

    public void bindVariantAdapter(Integer position) {
        variantAdapter = new VariantAdapter(R.layout.variant_list_item, this);
        currentVariantList = getProductList().get(position).getVariants();
        setVariantListInAdapter(currentVariantList);
    }

    public interface IFragmentTranscation {
        void replaceFragment(Fragment fragment, String tagName);
    }
}

