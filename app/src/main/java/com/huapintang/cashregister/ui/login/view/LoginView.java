package com.huapintang.cashregister.ui.login.view;


import com.huapintang.cashregister.ui.BaseView;

/**
 * Created by cbp on 3/4 0004.
 */
 public interface LoginView extends BaseView {
    void setUserErrer();
    void setPasswordErrer();
    void setNetErrer();
    void setNetErrer(String str);
    void setNetSucceed();

   void finsh();
//    void navigateToSellTicket();
}
