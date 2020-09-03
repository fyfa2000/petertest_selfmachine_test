package com.huapintang.cashregister.fragment.pay.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.huapintang.cashregister.BR;
import com.huapintang.cashregister.utils.EncodingUtils;

import java.util.ArrayList;

/**
 * Created by cbp on 4/25 0025.
 */
public class OrderPrintTicket {

    /**
     * status : true
     * msg : success
     * data : [{"id":"2454","order_id":"1557","og_id":"1489","code":"43V8UDZHY8","seat":null,"cancel":"0","share":"0","time":null,"ticket":"0","ticket_type":null,"solder":"0","title":"柚子轮滑 |青少年速滑培训班","place":"广州","address":"天河区天河北路705号D座1007","lat":"23.14204788","lng":"113.34365082","is_through":"1","stype":"0","b_time":"1522425600","e_time":"1554048000","date1":null,"times":null,"hall_id":"0","p_title":"速度轮滑专项体验课程","h_title":null}]
     */

    private boolean status;
    private String msg;
    private ArrayList<DataBean> data;

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

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Observable, Parcelable {



        private String id;
        private String order_id;
        private String og_id;
        private String code;
        private String seat;
        private String cancel;
        private String share;
        private String time;
        private String ticket;
        private String ticket_type;
        private String solder;
        private String title;
        private String place;
        private String address;
        private String lat;
        private String lng;
        private String is_through;
        private String stype;
        private String b_time;
        private String e_time;
        private String date1;
        private String times;
        private String hall_id;
        private String p_title;
        private String h_title;
        private String price;
        private String place1;
        private Drawable drawable;
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
        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
            notifyChange(BR.order_id);
        }

        @Bindable
        public String getOg_id() {
            return og_id;
        }

        public void setOg_id(String og_id) {
            this.og_id = og_id;
            notifyChange(BR.og_id);
        }

        @Bindable
        public String getCode() {
            if (TextUtils.isEmpty(code)) {

                Bitmap qrCode = EncodingUtils.createQRCode(code, 80, 80, null);
                drawable = new BitmapDrawable(qrCode);
            }


            return code;
        }

        public void setCode(String code) {
            this.code = code;
            notifyChange(BR.code);
        }

        @Bindable
        public String getSeat() {
            return seat;
        }

        public void setSeat(String seat) {
            this.seat = seat;
            notifyChange(BR.seat);
        }

        @Bindable
        public String getCancel() {
            return cancel;
        }

        public void setCancel(String cancel) {
            this.cancel = cancel;
            notifyChange(BR.cancel);
        }

        @Bindable
        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
            notifyChange(BR.share);
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
        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
            notifyChange(BR.ticket);
        }

        @Bindable
        public String getTicket_type() {
            return ticket_type;
        }

        public void setTicket_type(String ticket_type) {
            this.ticket_type = ticket_type;
            notifyChange(BR.ticket_type);
        }

        @Bindable
        public String getSolder() {
            return solder;
        }

        public void setSolder(String solder) {
            this.solder = solder;
            notifyChange(BR.solder);
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
        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
            notifyChange(BR.place);
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
        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
            notifyChange(BR.lat);
        }

        @Bindable
        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
            notifyChange(BR.lng);
        }

        @Bindable
        public String getIs_through() {
            return is_through;
        }

        public void setIs_through(String is_through) {
            this.is_through = is_through;
            notifyChange(BR.is_through);
        }

        @Bindable
        public String getStype() {
            return stype;
        }

        public void setStype(String stype) {
            this.stype = stype;
            notifyChange(BR.stype);
        }

        @Bindable
        public String getB_time() {
            return b_time;
        }

        public void setB_time(String b_time) {
            this.b_time = b_time;
            notifyChange(BR.b_time);
        }

        @Bindable
        public String getE_time() {
            return e_time;
        }

        public void setE_time(String e_time) {
            this.e_time = e_time;
            notifyChange(BR.e_time);
        }

        @Bindable
        public String getDate1() {
            return date1;
        }

        public void setDate1(String date1) {
            this.date1 = date1;
            notifyChange(BR.date1);
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
        public String getHall_id() {
            return hall_id;
        }

        public void setHall_id(String hall_id) {
            this.hall_id = hall_id;
            notifyChange(BR.hall_id);
        }

        @Bindable
        public String getP_title() {
            return p_title;
        }

        public void setP_title(String p_title) {
            this.p_title = p_title;
            notifyChange(BR.p_title);
        }

        @Bindable
        public String getH_title() {
            return h_title;
        }

        public void setH_title(String h_title) {
            this.h_title = h_title;
            notifyChange(BR.h_title);
        }

        @Bindable
        public Drawable getDrawable() {
            return drawable;
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
            notifyChange(BR.drawable);
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.order_id);
            dest.writeString(this.og_id);
            dest.writeString(this.code);
            dest.writeString(this.seat);
            dest.writeString(this.cancel);
            dest.writeString(this.share);
            dest.writeString(this.time);
            dest.writeString(this.ticket);
            dest.writeString(this.ticket_type);
            dest.writeString(this.solder);
            dest.writeString(this.title);
            dest.writeString(this.place);
            dest.writeString(this.address);
            dest.writeString(this.lat);
            dest.writeString(this.lng);
            dest.writeString(this.is_through);
            dest.writeString(this.stype);
            dest.writeString(this.b_time);
            dest.writeString(this.e_time);
            dest.writeString(this.date1);
            dest.writeString(this.times);
            dest.writeString(this.hall_id);
            dest.writeString(this.p_title);
            dest.writeString(this.h_title);
            dest.writeString(this.price);
            dest.writeString(this.place1);
//            dest.writeParcelable(this.drawable, flags);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.order_id = in.readString();
            this.og_id = in.readString();
            this.code = in.readString();
            this.seat = in.readString();
            this.cancel = in.readString();
            this.share = in.readString();
            this.time = in.readString();
            this.ticket = in.readString();
            this.ticket_type = in.readString();
            this.solder = in.readString();
            this.title = in.readString();
            this.place = in.readString();
            this.address = in.readString();
            this.lat = in.readString();
            this.lng = in.readString();
            this.is_through = in.readString();
            this.stype = in.readString();
            this.b_time = in.readString();
            this.e_time = in.readString();
            this.date1 = in.readString();
            this.times = in.readString();
            this.hall_id = in.readString();
            this.p_title = in.readString();
            this.h_title = in.readString();
            this.price = in.readString();
            this.place1 = in.readString();
//            this.drawable = in.readParcelable(Drawable.class.getClassLoader());
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
