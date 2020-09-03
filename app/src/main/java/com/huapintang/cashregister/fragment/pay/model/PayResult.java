package com.huapintang.cashregister.fragment.pay.model;

/**
 * Created by cbp on 4/24 0024.
 */
public class PayResult {


    /**
     * status : true
     * msg : success
     * data : {}
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

    public static class DataBean {
    }
}
