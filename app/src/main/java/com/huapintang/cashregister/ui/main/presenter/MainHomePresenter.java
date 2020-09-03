package com.huapintang.cashregister.ui.main.presenter;

import android.support.v4.app.Fragment;

import com.huapintang.cashregister.ui.main.view.MainHomeView;

/**
 * Created by cbp on 3/5 0005.
 */
public class MainHomePresenter {
    private MainHomeView view;

    public MainHomePresenter(MainHomeView view) {
        this.view = view;
    }

    private final static String[] tags = {"HomeFragment", "BuyFragment"};

    public void changeFragment(Fragment from, Fragment to, int position) {


    }


}
