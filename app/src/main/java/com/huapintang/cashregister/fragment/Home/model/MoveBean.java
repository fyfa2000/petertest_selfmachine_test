package com.huapintang.cashregister.fragment.Home.model;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.huapintang.cashregister.BR;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbp on 4/18 0018.
 */
public class MoveBean implements Parcelable {



    /**
     * status : true
     * msg : success
     * data : [{"id":"22","title":"柚子轮滑 |青少年速滑培训班","backimg":"http://miaopiao-1251250816.file.myqcloud.com/film/5a1d29ba360d2.png","place":"广州","stype":"0","is_through":"1","hall_id":"0","place_id":"0","date":"2018/03/31-2019/04/01","is_seat":0}]
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
         * id : 22
         * title : 柚子轮滑 |青少年速滑培训班
         * backimg : http://miaopiao-1251250816.file.myqcloud.com/film/5a1d29ba360d2.png
         * place : 广州
         * stype : 0
         * is_through : 1
         * hall_id : 0
         * place_id : 0
         * date : 2018/03/31-2019/04/01
         * is_seat : 0
         * seat_type
         */

        private String id;
        private String title;
        private String backimg;
        private String place;
        private String stype;
        private String is_through;
        private String hall_id;
        private String place_id;
        private String date;
        private int is_seat;

        private String b_time;
        private String chooseDate;
        private String chooseTime;
        private String e_time;
        private String seat_type;


        private String showTime;

        private boolean isSelection;


        @BindingAdapter({"bind:imageUrl"})
        public static void      loadImage(ImageView view, String imageUrl) {
                Picasso.with(view.getContext())
                        .load(imageUrl)
//                        .placeholder(R.drawable.ic_mi)
//                    .transform(new CircleImageTransformation())
                    .into(view);
        }

        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();


//        @BindingAdapter({"bind:imageUrl"})
//        public static void
//            Picasso.with(view.getContext())
//                    .load(imageUrl)
////                    .placeholder(R.drawable.placeholder)
//                    .into(view);
//        }


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
        public String getBackimg() {
            return backimg;
        }

        public void setBackimg(String backimg) {
            this.backimg = backimg;
            notifyChange(BR.backimg);
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
        public String getStype() {
            return stype;
        }

        public void setStype(String stype) {
            this.stype = stype;
            notifyChange(BR.stype);
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
        public String getHall_id() {
            return hall_id;
        }

        public void setHall_id(String hall_id) {
            this.hall_id = hall_id;
            notifyChange(BR.hall_id);
        }

        @Bindable
        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
            notifyChange(BR.place_id);
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
        public int getIs_seat() {
            return is_seat;
        }

        public void setIs_seat(int is_seat) {
            this.is_seat = is_seat;
            notifyChange(BR.is_seat);
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
        public String getChooseDate() {
            return chooseDate;
        }

        public void setChooseDate(String chooseDate) {
            this.chooseDate = chooseDate;
            notifyChange(BR.chooseDate);
        }

        @Bindable
        public String getChooseTime() {
            return chooseTime;
        }

        public void setChooseTime(String chooseTime) {
            this.chooseTime = chooseTime;
            notifyChange(BR.chooseTime);
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
        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
            notifyChange(BR.showTime);
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
        public boolean getIsSelection() {
            return isSelection;
        }

        public void setIsSelection(boolean isSelection) {
            this.isSelection = isSelection;
            notifyChange(BR.isSelection);
        }

        @Bindable
        public String getSeat_type() {
            return seat_type;
        }

        public void setSeat_type(String seat_type) {
            this.seat_type = seat_type;
            notifyChange(BR.seat_type);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.title);
            dest.writeString(this.backimg);
            dest.writeString(this.place);
            dest.writeString(this.stype);
            dest.writeString(this.is_through);
            dest.writeString(this.hall_id);
            dest.writeString(this.place_id);
            dest.writeString(this.date);
            dest.writeInt(this.is_seat);
            dest.writeString(this.b_time);
            dest.writeString(this.chooseDate);
            dest.writeString(this.chooseTime);
            dest.writeString(this.e_time);
            dest.writeString(this.seat_type);
            dest.writeString(this.showTime);
            dest.writeByte(this.isSelection ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.title = in.readString();
            this.backimg = in.readString();
            this.place = in.readString();
            this.stype = in.readString();
            this.is_through = in.readString();
            this.hall_id = in.readString();
            this.place_id = in.readString();
            this.date = in.readString();
            this.is_seat = in.readInt();
            this.b_time = in.readString();
            this.chooseDate = in.readString();
            this.chooseTime = in.readString();
            this.e_time = in.readString();
            this.seat_type = in.readString();
            this.showTime = in.readString();
            this.isSelection = in.readByte() != 0;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
        dest.writeString(this.msg);
        dest.writeList(this.data);
    }

    public MoveBean() {
    }

    protected MoveBean(Parcel in) {
        this.status = in.readByte() != 0;
        this.msg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<MoveBean> CREATOR = new Parcelable.Creator<MoveBean>() {
        @Override
        public MoveBean createFromParcel(Parcel source) {
            return new MoveBean(source);
        }

        @Override
        public MoveBean[] newArray(int size) {
            return new MoveBean[size];
        }
    };
}
