package com.huapintang.cashregister.bean;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import com.huapintang.cashregister.BR;

import java.util.List;



/**
 * Created by cbp on 4/25 0025.
 */
public class PrintTicket {


    /**
     * status : true
     * msg : success
     * data : [{"time":"2018-09-20 19:00-22:00","paytime":"09/17 15:35","address":"G","name":"石进钢琴作品音乐会----广州站","ptitle":"套票660（380*2）","pname":"G","code":"UKGX6TOYX7","username":null,"id_card":null,"price":"660.00","place1":"XX剧院","seat":"2排7座"}]
     * ext_data : []
     */

    private boolean status;
    private String msg;
    /**
     * time : 2018-09-20 19:00-22:00
     * paytime : 09/17 15:35
     * address : G
     * name : 石进钢琴作品音乐会----广州站
     * ptitle : 套票660（380*2）
     * pname : G
     * code : UKGX6TOYX7
     * username : null
     * id_card : null
     * price : 660.00
     * place1 : XX剧院
     * seat : 2排7座
     */

    private List<DataBean> data;
    private List<?> ext_data;

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

    public List<?> getExt_data() {
        return ext_data;
    }

    public void setExt_data(List<?> ext_data) {
        this.ext_data = ext_data;
    }

    public static class DataBean implements Observable, Parcelable {


        private String time;
        private String paytime;
        private String address;
        private String name;
        private String ptitle;
        private String pname;
        private String code;
        private String username;
        private String id_card;
        private String price;
        private String place1;
        private String seat;
        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

        @Bindable
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
            notifyChange(BR.time);
        }

        @Bindable
        public String getPaytime() {
            return paytime;
        }

        public void setPaytime(String paytime) {
            this.paytime = paytime;
            notifyChange(BR.paytime);
        }

        @Bindable
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
            notifyChange(BR.address);
        }

        @Bindable
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
            notifyChange(BR.name);
        }

        @Bindable
        public String getPtitle() {
            return ptitle;
        }

        public void setPtitle(String ptitle) {
            this.ptitle = ptitle;
            notifyChange(BR.ptitle);
        }

        @Bindable
        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
            notifyChange(BR.pname);
        }

        @Bindable
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
            notifyChange(BR.code);
        }

        @Bindable
        public Object getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
            notifyChange(BR.username);
        }

        @Bindable
        public Object getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
            notifyChange(BR.id_card);
        }

        @Bindable
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
            notifyChange(BR.price);
        }

        @Bindable
        public String getPlace1() {
            return place1;
        }

        public void setPlace1(String place1) {
            this.place1 = place1;
            notifyChange(BR.place1);
        }

        @Bindable
        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
            notifyChange(BR.seat);
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
            dest.writeString(this.time);
            dest.writeString(this.paytime);
            dest.writeString(this.address);
            dest.writeString(this.name);
            dest.writeString(this.ptitle);
            dest.writeString(this.pname);
            dest.writeString(this.code);
            dest.writeString(this.username);
            dest.writeString(this.id_card);
            dest.writeString(this.price);
            dest.writeString(this.place1);
            dest.writeString(this.seat);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.time = in.readString();
            this.paytime = in.readString();
            this.address = in.readString();
            this.name = in.readString();
            this.ptitle = in.readString();
            this.pname = in.readString();
            this.code = in.readString();
            this.username = in.readParcelable(Object.class.getClassLoader());
            this.id_card = in.readParcelable(Object.class.getClassLoader());
            this.price = in.readString();
            this.place1 = in.readString();
            this.seat = in.readString();
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
