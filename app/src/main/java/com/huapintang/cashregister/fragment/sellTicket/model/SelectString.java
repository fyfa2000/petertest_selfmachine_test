package com.huapintang.cashregister.fragment.sellTicket.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import com.huapintang.cashregister.BR;

/**
 * Created by cbp on 5/16 0016.
 */
public class SelectString implements Observable ,Parcelable {

    private String str;
    private boolean isSelect;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    @Override
    public String toString() {
        return
                str;
    }

    @Bindable
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        notifyChange(BR.str);
    }

    @Bindable
    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
        notifyChange(BR.isSelect);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.str);
        dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
    }

    public SelectString() {
    }

    protected SelectString(Parcel in) {
        this.str = in.readString();
        this.isSelect = in.readByte() != 0;
    }

    public static final Creator<SelectString> CREATOR = new Creator<SelectString>() {
        @Override
        public SelectString createFromParcel(Parcel source) {
            return new SelectString(source);
        }

        @Override
        public SelectString[] newArray(int size) {
            return new SelectString[size];
        }
    };
}
