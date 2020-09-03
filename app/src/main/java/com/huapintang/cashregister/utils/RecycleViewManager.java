package com.huapintang.cashregister.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.widget.CustomLinearLayoutManager;


public class RecycleViewManager {

    // 横向样式的
    public static RecyclerView.LayoutManager getHorizontalManager (){
        LinearLayoutManager ms = new LinearLayoutManager(MyApplication.getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        return ms;
    }

    //不滚动的
    public static RecyclerView.LayoutManager getNoScroll(){
        CustomLinearLayoutManager linearLayoutManager = new CustomLinearLayoutManager(MyApplication.getContext());
        linearLayoutManager.setScrollEnabled(false);
        return  linearLayoutManager;
    }

    public static RecyclerView.LayoutManager getGridManager(int i){
        return  new GridLayoutManager(MyApplication.getContext(),i);
    }

    public static  RecyclerView.LayoutManager getListManager(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyApplication.getContext());

        return linearLayoutManager;
    }

}