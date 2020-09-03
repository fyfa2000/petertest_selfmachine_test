package com.huapintang.cashregister.fragment.sellTicket.model;

import java.util.List;

/**
 * Created by cbp on 4/18 0018.
 */
public class EventTimeResult {


    /**
     * status : true
     * msg : success
     * data : [{"b_time":"11:07","e_time":"12:07"}]
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

    public static class DataBean {
        /**
         * b_time : 11:07
         * e_time : 12:07
         */

        private String b_time;
        private String e_time;

        public String getB_time() {
            return b_time;
        }

        public void setB_time(String b_time) {
            this.b_time = b_time;
        }

        public String getE_time() {
            return e_time;
        }

        public void setE_time(String e_time) {
            this.e_time = e_time;
        }
    }
}
