package com.huapintang.cashregister.fragment.area.model;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import com.huapintang.cashregister.BR;

import java.util.List;

/**
 * Created by cbp on 6/25 0025.
 */
public class Area implements Parcelable {


    /**
     * status : true
     * msg : success
     * data : [{"id":"22","title":"大堂"},{"id":"21","title":"特邀嘉宾演出室"},{"id":"19","title":"喵票国际大厦"},{"id":"17","title":"喵票产品展示大厅"},{"id":"16","title":"test"},{"id":"15","title":"7号厅"},{"id":"9","title":"6号厅"}]
     * ext_data : []
     */

    private boolean status;
    private String msg;
    private List<DataBean> data;
//    private List<?> ext_data;

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


    public static class DataBean implements Parcelable, Observable {
        /**
         * id : 22
         * title : 大堂
         */


        private String id;
        private String title;
        private String logo;
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
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
            notifyChange(BR.title);
        }

        public DataBean() {
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
        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
            notifyChange(BR.logo);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.title);
            dest.writeString(this.logo);
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.title = in.readString();
            this.logo = in.readString();
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
        dest.writeTypedList(this.data);
    }

    public Area() {
    }

    protected Area(Parcel in) {
        this.status = in.readByte() != 0;
        this.msg = in.readString();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<Area> CREATOR = new Parcelable.Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel source) {
            return new Area(source);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };
}
