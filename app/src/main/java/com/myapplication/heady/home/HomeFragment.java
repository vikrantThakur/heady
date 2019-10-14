package com.myapplication.heady.home;

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
import com.myapplication.heady.databinding.LayoutHomeFragmentBinding;

public class HomeFragment extends Fragment {

    private View view;
    private LayoutHomeFragmentBinding layoutHomeFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            //view = inflater.inflate(R.layout.layout_home_fragment, container, false);
            layoutHomeFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.layout_home_fragment, container, false);
            view = layoutHomeFragmentBinding.getRoot();
            layoutHomeFragmentBinding.setHomeViewModel(((MainActivity) getActivity()).getHomeViewModel());
        }
        return view;
    }
}
