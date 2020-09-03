package com.huapintang.cashregister.bean;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.huapintang.cashregister.BR;

/**
 * Created by cbp on 9/17 0017.
 */
public class MsgDialogBean implements Observable {

    private String msg1;
    private String msg2;
    private String btnMsg;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();


    @Bindable
    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
        notifyChange(BR.msg1);
    }

    @Bindable
    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
        notifyChange(BR.msg2);
    }

    @Bindable
    public String getBtnMsg() {
        return btnMsg;
    }

    public void setBtnMsg(String btnMsg) {
        this.btnMsg = btnMsg;
        notifyChange(BR.btnMsg);
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
