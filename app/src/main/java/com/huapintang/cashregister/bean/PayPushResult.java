package com.huapintang.cashregister.bean;

/**
 * Created by cbp on 7/13 0013.
 */
public class PayPushResult {

    private String status;
    private String msg;

    @Override
    public String toString() {
        return "PayPushResult{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
