package com.huapintang.cashregister.ui.main.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.ActivityMainHomeBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.view.HomeFragment;
import com.huapintang.cashregister.ui.BaseActivity;
import com.huapintang.cashregister.ui.main.presenter.MainHomePresenter;

import java.util.ArrayList;

public class MainHomeActivity extends BaseActivity implements MainHomeView {

    private ActivityMainHomeBinding binding;
    private MainHomePresenter mainHomePresenter;
    private FragmentManager fragmentManager;

    private ArrayList<BaseFragment> fragmentList;
    private final static String[] tags = {"HomeFragment", "BuyFragment"};
    private int postion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_home);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_home);
        if(Config.loginResult==null||Config.loginResult.getData()==null||Config.loginResult.getData().getUkey()==null){
            finish();
            return;
        }
        mainHomePresenter = new MainHomePresenter(this);
        fragmentManager = getSupportFragmentManager();
        fragmentList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        fragmentList.add(homeFragment);
//        BuyFragment buyFragment = new BuyFragment();
//        fragmentList.add(buyFragment);
//        mainHomePresenter.changeFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        postion=0;
        transaction.add(binding.llMain.getId(), homeFragment, tags[postion]).commit(); // 隐藏当前的fragment，add下一个到Activity中


    }


    public void switchContent(Fragment from, Fragment to, int position) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!to.isAdded()) { // 先判断是否被add过
            transaction.hide(from).add(binding.llMain.getId(), to, tags[position]).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }


}
