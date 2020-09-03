package com.huapintang.cashregister.fragment.sellTicket.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import com.huapintang.cashregister.BR;

import java.util.List;

/**
 * Created by cbp on 4/18 0018.
 */
public class GetTicketResult {


    /**
     * status : true
     * msg : success
     * data : [{"id":"56","title":"asdf","color":"#e36c09","price":"11.00","vip_price":"10.00","f_price":null,"price1":"0.00","new_money":"11.00","old_money":"11.00"}]
     */

    private boolean status;
    private String msg;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Observable, Parcelable {



        /**
         * id : 56
         * title : asdf
         * color : #e36c09
         * price : 11.00
         * vip_price : 10.00
         * f_price : null
         * price1 : 0.00
         * new_money : 11.00
         * old_money : 11.00
         */

        private String id;
        private String title;
        private String color;
        private String price;
        private String vip_price;

        private String f_price;
        private String price1;
        private String new_money;
        private String old_money;
        private boolean isSelect;

        private String date;
        private String time;

        private String num;

        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }

        @Bindable
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            notifyChange(BR.id);
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
            notifyChange(BR.title);
        }

        @Bindable
        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
            notifyChange(BR.color);
        }

        @Bindable
        public String getPrice() {
            return price + "元";
        }

        public String getPricefloat() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
            notifyChange(BR.price);
        }


        @Bindable
        public String getF_price() {
            return f_price;
        }

        public void setF_price(String f_price) {
            this.f_price = f_price;
            notifyChange(BR.f_price);
        }

        @Bindable
        public String getPrice1() {
            return price1;
        }

        public void setPrice1(String price1) {
            this.price1 = price1;
            notifyChange(BR.price1);
        }

        @Bindable
        public String getNew_money() {
            return new_money;
        }

        public void setNew_money(String new_money) {
            this.new_money = new_money;
            notifyChange(BR.new_money);
        }

        @Bindable
        public String getOld_money() {
            return old_money;
        }

        public void setOld_money(String old_money) {
            this.old_money = old_money;
            notifyChange(BR.old_money);
        }

        @Bindable
        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
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

        @Bindable
        public boolean getIsSelect() {
            return isSelect;
        }

        public void setIsSelect(boolean isSelect) {
            this.isSelect = isSelect;
            notifyChange(BR.isSelect);
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.title);
            dest.writeString(this.color);
            dest.writeString(this.price);
            dest.writeString(this.vip_price);
            dest.writeString(this.f_price);
            dest.writeString(this.price1);
            dest.writeString(this.new_money);
            dest.writeString(this.old_money);
            dest.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
            dest.writeString(this.date);
            dest.writeString(this.time);
            dest.writeString(this.num);
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.title = in.readString();
            this.color = in.readString();
            this.price = in.readString();
            this.vip_price = in.readString();
            this.f_price = in.readString();
            this.price1 = in.readString();
            this.new_money = in.readString();
            this.old_money = in.readString();
            this.isSelect = in.readByte() != 0;
            this.date = in.readString();
            this.time = in.readString();
            this.num = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
