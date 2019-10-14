package com.myapplication.heady;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.myapplication.heady.databinding.LayoutMainActivityBinding;
import com.myapplication.heady.home.HomeFragment;
import com.myapplication.heady.home.HomeViewModel;
import com.myapplication.heady.home.MyModels.HomeModel;

public class MainActivity extends AppCompatActivity implements HomeViewModel.IFragmentTranscation {

    LayoutMainActivityBinding layoutMainActivityBinding;
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);
        gotoHomeFragment();
        init();
    }

    private void init() {
        layoutMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.layout_main_activity);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.setInterfaceListener(this);
        homeViewModel.getHomeLiveData().observe(this, new Observer<HomeModel>() {
            @Override
            public void onChanged(HomeModel homeModel) {
                homeViewModel.setCategoryListInAdapter(homeModel.getCategories());
            }
        });
        layoutMainActivityBinding.setLifecycleOwner(this);
    }

    public HomeViewModel getHomeViewModel() {
        return homeViewModel;
    }

    private void gotoHomeFragment() {
        addFragment(R.id.flFragmentContainer, new HomeFragment(), "home");
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void replaceFragment(Fragment fragment, String tagName) {
        replaceFragment(R.id.flFragmentContainer, fragment, tagName, tagName);
    }
}
