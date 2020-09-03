package com.huapintang.cashregister.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{
    private T dataBing;

    public BaseViewHolder(View itemView) {
        super(itemView);
        AutoUtils.autoSize(itemView);
    }

    public T getDataBing() {
        return dataBing;
    }

    public void setDataBing(T dataBing) {
        this.dataBing = dataBing;
    }
}