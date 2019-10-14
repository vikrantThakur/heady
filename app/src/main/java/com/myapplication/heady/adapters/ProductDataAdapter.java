package com.myapplication.heady.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.heady.R;
import com.myapplication.heady.databinding.ProductListItemBinding;
import com.myapplication.heady.home.HomeViewModel;
import com.myapplication.heady.home.MyModels.Product;

import java.util.List;

public class ProductDataAdapter extends RecyclerView.Adapter<ProductDataAdapter.MyViewHolder> {

    private List<Product> products;
    private int layoutId;
    private HomeViewModel homeViewModel;

    public ProductDataAdapter(@LayoutRes int layoutId, HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.layoutId = layoutId;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @NonNull
    @Override
    public ProductDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ProductListItemBinding productListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.product_list_item, viewGroup, false);
        return new ProductDataAdapter.MyViewHolder(productListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDataAdapter.MyViewHolder holder, int position) {
        holder.bind(homeViewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int getItemCount() {
        if (products != null) {
            return products.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ProductListItemBinding productListItemBinding;

        public MyViewHolder(@NonNull ProductListItemBinding productListItemBinding) {
            super(productListItemBinding.getRoot());
            this.productListItemBinding = productListItemBinding;
        }

        void bind(HomeViewModel viewModel, Integer position) {

            viewModel.bindVariantAdapter(position);
            productListItemBinding.setHomeViewModel(viewModel);
            productListItemBinding.setPosition(position);
            productListItemBinding.executePendingBindings();
        }
    }
}
