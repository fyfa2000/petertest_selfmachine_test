package com.huapintang.cashregister.ui.welcome.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.ui.BaseActivity;
import com.huapintang.cashregister.ui.login.view.LoginSellActivity;
import com.huapintang.cashregister.ui.main.view.MainHomeActivity;
import com.huapintang.cashregister.ui.welcome.presenter.WelcomePresenter;
import com.huapintang.cashregister.utils.LogUtils;

public class WelcomeActivity extends BaseActivity implements WelcomeView {


    private WelcomePresenter welcomePresenter;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    setContentView(R.layout.activity_welcome);
                    welcomePresenter = new WelcomePresenter(WelcomeActivity.this);
                    welcomePresenter.selectIntent();
                    showMenu();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("跳转到主页");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what=1;
                handler.sendMessage(msg);
            }
        },15000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode==404){
////            showMenu();
//            hideBottomUIMenu();
//            welcomePresenter.selectIntent();
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void intentLogin() {
        startActivity(new Intent().setClass(this, LoginSellActivity.class));
        finish();
    }

    @Override
    public void intentMain() {
        startActivity(new Intent().setClass(this, MainHomeActivity.class));
        finish();
    }

    @Override
    public void loginSucceed() {
        startActivity(new Intent().setClass(this, MainHomeActivity.class));
        finish();
    }


}
