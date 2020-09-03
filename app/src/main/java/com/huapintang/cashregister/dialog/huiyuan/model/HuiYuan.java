package com.huapintang.cashregister.dialog.huiyuan.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import com.huapintang.cashregister.BR;

import java.util.List;

/**
 * Created by cbp on 5/18 0018.
 */
public class HuiYuan {


    /**
     * status : true
     * msg : success
     * data : {"id":"2249","card_id":"4","type":"12","card_no":"2016000005","end_time":"2020-01-10 15:14:05","times":"10","phone":"15855858555","name":"徐娇","sex":"2","id_card":"440923199207274823","email":"222@qq.com","birthday":"0000-00-00","address":null,"others":"大股东","money":"179.99","status":"2","add_time":"1547098835","buy_time":"1547104445","send_time":"1547104013","title":"年卡","month":"12","type_title":"年卡","price":"200.00"}
     * ext_data : []
     */

    private boolean status;
    private String msg;
    /**
     * id : 2249
     * card_id : 4
     * type : 12
     * card_no : 2016000005
     * end_time : 2020-01-10 15:14:05
     * times : 10
     * phone : 15855858555
     * name : 徐娇
     * sex : 2
     * id_card : 440923199207274823
     * email : 222@qq.com
     * birthday : 0000-00-00
     * address : null
     * others : 大股东
     * money : 179.99
     * status : 2
     * add_time : 1547098835
     * buy_time : 1547104445
     * send_time : 1547104013
     * title : 年卡
     * month : 12
     * type_title : 年卡
     * price : 200.00
     */

    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<?> getExt_data() {
        return ext_data;
    }

    public void setExt_data(List<?> ext_data) {
        this.ext_data = ext_data;
    }

    public static class DataBean implements Observable, Parcelable {

        private String id;
        private String card_id;
        private String type;
        private String card_no;
        private String end_time;
        private String times;
        private String phone;
        private String name;
        private String sex;
        private String id_card;
        private String email;
        private String birthday;
        private String address;
        private String others;
        private String money;
        private String status;
        private String add_time;
        private String buy_time;
        private String send_time;
        private String title;
        private String month;
        private String type_title;
        private String vip_no;
        private String price;
        private String is_wx;
        private boolean isSelectYue;
        private boolean isSelectTimes;


        public String getIs_wx() {
            return is_wx;
        }

        public void setIs_wx(String is_wx) {
            this.is_wx = is_wx;
        }

        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();


        @Bindable
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
            notifyChange(BR.id);
        }

        @Bindable
        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
            notifyChange(BR.card_id);
        }

        @Bindable
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
            notifyChange(BR.type);
        }

        @Bindable
        public String getCard_no() {
            return card_no;
        }

        public void setCard_no(String card_no) {
            this.card_no = card_no;
            notifyChange(BR.card_no);
        }

        @Bindable
        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
            notifyChange(BR.end_time);
        }

        @Bindable
        public String getTimes() {
            return times;
        }

        public void setTimes(String times) {
            this.times = times;
            notifyChange(BR.times);
        }

        @Bindable
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
            notifyChange(BR.phone);
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
        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
            notifyChange(BR.sex);
        }

        @Bindable
        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
            notifyChange(BR.id_card);
        }

        @Bindable
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
            notifyChange(BR.email);
        }

        @Bindable
        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
            notifyChange(BR.birthday);
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
        public String getOthers() {
            return others;
        }

        public void setOthers(String others) {
            this.others = others;
            notifyChange(BR.others);
        }

        @Bindable
        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
            notifyChange(BR.money);
        }

        @Bindable
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
            notifyChange(BR.status);
        }

        @Bindable
        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
            notifyChange(BR.add_time);
        }

        @Bindable
        public String getBuy_time() {
            return buy_time;
        }

        public void setBuy_time(String buy_time) {
            this.buy_time = buy_time;
            notifyChange(BR.buy_time);
        }

        @Bindable
        public String getSend_time() {
            return send_time;
        }

        public void setSend_time(String send_time) {
            this.send_time = send_time;
            notifyChange(BR.send_time);
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
        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
            notifyChange(BR.month);
        }

        @Bindable
        public String getType_title() {
            return type_title;
        }

        public void setType_title(String type_title) {
            this.type_title = type_title;
            notifyChange(BR.type_title);
        }

        @Bindable
        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
            notifyChange(BR.price);
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

        public DataBean() {
        }

        @Bindable
        public String getVip_no() {
            return vip_no;
        }

        public void setVip_no(String vip_no) {
            this.vip_no = vip_no;
            notifyChange(BR.vip_no);
        }

        @Bindable
        public boolean getIsSelectYue() {
            return isSelectYue;
        }

        public void setIsSelectYue(boolean isSelectYue) {
            this.isSelectYue = isSelectYue;
            notifyChange(BR.isSelectYue);
        }

        @Bindable
        public boolean getIsSelectTimes() {
            return isSelectTimes;
        }

        public void setIsSelectTimes(boolean isSelectTimes) {
            this.isSelectTimes = isSelectTimes;
            notifyChange(BR.isSelectTimes);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.card_id);
            dest.writeString(this.type);
            dest.writeString(this.card_no);
            dest.writeString(this.end_time);
            dest.writeString(this.times);
            dest.writeString(this.phone);
            dest.writeString(this.name);
            dest.writeString(this.sex);
            dest.writeString(this.id_card);
            dest.writeString(this.email);
            dest.writeString(this.birthday);
            dest.writeString(this.address);
            dest.writeString(this.others);
            dest.writeString(this.money);
            dest.writeString(this.status);
            dest.writeString(this.add_time);
            dest.writeString(this.buy_time);
            dest.writeString(this.send_time);
            dest.writeString(this.title);
            dest.writeString(this.month);
            dest.writeString(this.type_title);
            dest.writeString(this.vip_no);
            dest.writeString(this.price);
            dest.writeString(this.is_wx);
            dest.writeByte(this.isSelectYue ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isSelectTimes ? (byte) 1 : (byte) 0);
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.card_id = in.readString();
            this.type = in.readString();
            this.card_no = in.readString();
            this.end_time = in.readString();
            this.times = in.readString();
            this.phone = in.readString();
            this.name = in.readString();
            this.sex = in.readString();
            this.id_card = in.readString();
            this.email = in.readString();
            this.birthday = in.readString();
            this.address = in.readString();
            this.others = in.readString();
            this.money = in.readString();
            this.status = in.readString();
            this.add_time = in.readString();
            this.buy_time = in.readString();
            this.send_time = in.readString();
            this.title = in.readString();
            this.month = in.readString();
            this.type_title = in.readString();
            this.vip_no = in.readString();
            this.price = in.readString();
            this.is_wx = in.readString();
            this.isSelectYue = in.readByte() != 0;
            this.isSelectTimes = in.readByte() != 0;
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
