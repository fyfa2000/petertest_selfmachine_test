package com.huapintang.cashregister.fragment.sellTicket.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import com.huapintang.cashregister.BR;

/**
 * Created by cbp on 3/8 0008.
 */
public class SellTicket implements Observable, Parcelable {

    //票种
    private String ticketType;
    private String titile;
    private String numString;
    private int num;
    private String date;
    private String time;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();


    @Bindable
    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
        notifyChange(BR.ticketType);
    }

    @Bindable
    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
        notifyChange(BR.titile);
    }

    @Bindable
    public String getNumString() {
        return numString;
    }

    public void setNumString(String numString) {
        this.numString = numString;
        notifyChange(BR.numString);
    }

    @Bindable
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        setNumString(num+"");
        notifyChange(BR.num);
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

    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        notifyChange(BR.date);
    }

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyChange(BR.time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ticketType);
        dest.writeString(this.titile);
        dest.writeString(this.numString);
        dest.writeInt(this.num);
        dest.writeString(this.date);
        dest.writeString(this.time);
    }

    public SellTicket() {
    }

    protected SellTicket(Parcel in) {
        this.ticketType = in.readString();
        this.titile = in.readString();
        this.numString = in.readString();
        this.num = in.readInt();
        this.date = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<SellTicket> CREATOR = new Parcelable.Creator<SellTicket>() {
        @Override
        public SellTicket createFromParcel(Parcel source) {
            return new SellTicket(source);
        }

        @Override
        public SellTicket[] newArray(int size) {
            return new SellTicket[size];
        }
    };
}
