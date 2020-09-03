package com.huapintang.cashregister.ui.login.model;

/**
 * Created by cbp on 4/17 0017.
 */
public class LoginResult {


    /**
     * status : true
     * msg : 登录成功
     * data : {"ukey":"wvkterdk7pz8g2c#0l1ihu3ynaoj4x*m","user":"test","headimg":"http://miaopiao-1251250816.file.myqcloud.com/film/5a0416c67cf20.png"}
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
        /**
         * ukey : wvkterdk7pz8g2c#0l1ihu3ynaoj4x*m
         * user : test
         * headimg : http://miaopiao-1251250816.file.myqcloud.com/film/5a0416c67cf20.png
         */

        private String ukey;
        private String user;
        private String headimg;

        public String getUkey() {
            return ukey;
        }

        public void setUkey(String ukey) {
            this.ukey = ukey;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }
    }
}
