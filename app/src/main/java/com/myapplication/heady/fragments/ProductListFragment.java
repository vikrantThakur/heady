package com.myapplication.heady.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.myapplication.heady.MainActivity;
import com.myapplication.heady.R;
import com.myapplication.heady.databinding.LayoutCategoryFilterFragBinding;

public class ProductListFragment extends Fragment {

    private LayoutCategoryFilterFragBinding layoutCategoryFilterFrag;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            layoutCategoryFilterFrag = DataBindingUtil.inflate(inflater, R.layout.layout_category_filter_frag, container, false);
            view = layoutCategoryFilterFrag.getRoot();
            layoutCategoryFilterFrag.setHomeViewModel(((MainActivity) getActivity()).getHomeViewModel());
        }
        return view;
    }
}
