package com.myapplication.heady.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.heady.R;
import com.myapplication.heady.databinding.CategoryHomeItemBinding;
import com.myapplication.heady.home.MyModels.Category;

import java.util.List;

public class CategoryDataAdapter extends RecyclerView.Adapter<CategoryDataAdapter.MyViewHolder> {

    private List<Category> categories;
    private int layoutId;
    private HomeViewModel homeViewModel;

    public CategoryDataAdapter(@LayoutRes int layoutId, HomeViewModel homeViewModel) {
        this.homeViewModel = homeViewModel;
        this.layoutId = layoutId;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        CategoryHomeItemBinding categoryHomeItemBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.category_home_item, viewGroup, false);
        return new MyViewHolder(categoryHomeItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(homeViewModel, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int getItemCount() {
        if (categories != null) {
            return categories.size();
        } else {
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CategoryHomeItemBinding categoryHomeItemBinding;

        public MyViewHolder(@NonNull CategoryHomeItemBinding categoryHomeItemBinding) {
            super(categoryHomeItemBinding.getRoot());
            this.categoryHomeItemBinding = categoryHomeItemBinding;
        }

        void bind(HomeViewModel viewModel, Integer position) {
            viewModel.fetchCategoryAtPosition(position);
            categoryHomeItemBinding.setVariable(BR.viewModel, viewModel);
            categoryHomeItemBinding.setVariable(BR.position, position);
            categoryHomeItemBinding.executePendingBindings();
        }
    }
}