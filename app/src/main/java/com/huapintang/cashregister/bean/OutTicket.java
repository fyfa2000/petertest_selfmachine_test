package com.huapintang.cashregister.bean;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.huapintang.cashregister.BR;

import java.util.List;

/**
 * Created by cbp on 10/31 0031.
 */
public class OutTicket implements Observable {


    /**
     * status : false
     * msg : 未出票
     * data : []
     * ext_data : []
     */

    private boolean status;
    private String msg;
    private List<?> data;
    private List<?> ext_data;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();


    @Bindable
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
        notifyChange(BR.status);
    }

    @Bindable
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyChange(BR.msg);
    }

    @Bindable
    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
        notifyChange(BR.data);
    }

    @Bindable
    public List<?> getExt_data() {
        return ext_data;
    }

    public void setExt_data(List<?> ext_data) {
        this.ext_data = ext_data;
        notifyChange(BR.ext_data);
    }

    private synchronized void notifyChange(int propertyId) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.notifyChange(this, propertyId);
    }

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.add(callback);

    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.remove(callback);
        }
    }
}
