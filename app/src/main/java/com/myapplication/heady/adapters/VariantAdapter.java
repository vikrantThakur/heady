package com.myapplication.heady.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.heady.R;
import com.myapplication.heady.databinding.VariantListItemBinding;
import com.myapplication.heady.home.HomeViewModel;
import com.myapplication.heady.home.MyModels.Variant;

import java.util.List;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.MyViewHolder> {

    private List<Variant> variants;
    private int layoutId;
    private HomeViewModel homeViewModel;

    public VariantAdapter(@LayoutRes int layoutId, HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.layoutId = layoutId;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @NonNull
    @Override
    public VariantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        VariantListItemBinding variantListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.variant_list_item, viewGroup, false);
        return new VariantAdapter.MyViewHolder(variantListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VariantAdapter.MyViewHolder holder, int position) {
        holder.bind(homeViewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    @Override
    public int getItemCount() {
        if (variants != null) {
            return variants.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private VariantListItemBinding variantListItemBinding;

        public MyViewHolder(@NonNull VariantListItemBinding variantListItemBinding) {
            super(variantListItemBinding.getRoot());
            this.variantListItemBinding = variantListItemBinding;
        }

        void bind(HomeViewModel viewModel, Integer position) {
            variantListItemBinding.setHomeViewModel(viewModel);
            variantListItemBinding.setPosition(position);
            variantListItemBinding.executePendingBindings();
        }
    }
}

