package com.huapintang.cashregister.fragment.pay.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cbp on 4/20 0020.
 */
public class TicketResult {


    /**
     * status : true
     * msg : 下单成功
     * data : {"order_no":"2018042053991004","price":0.02}
     */

    private boolean status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        /**
         * order_no : 2018042053991004
         * price : 0.02
         */

        private String order_no;
        private String price;

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.order_no);
            dest.writeString(this.price);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.order_no = in.readString();
            this.price = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
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
