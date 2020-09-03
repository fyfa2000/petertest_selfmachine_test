package com.huapintang.cashregister.ui.welcome.view;

import com.huapintang.cashregister.ui.BaseView;

/**
 * Created by cbp on 3/5 0005.
 */
public interface WelcomeView extends BaseView{
    void intentLogin();
    void intentMain();
    void loginSucceed();

    void finish();
}
