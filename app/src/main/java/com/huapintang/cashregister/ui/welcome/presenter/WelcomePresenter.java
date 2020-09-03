package com.huapintang.cashregister.ui.welcome.presenter;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.ui.login.model.LoginResult;
import com.huapintang.cashregister.ui.welcome.view.WelcomeView;
import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.utils.SharedPreferencesUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by cbp on 3/5 0005.
 */
public class WelcomePresenter  {

    private WelcomeView view;

    private boolean isFristNetErr=true;

    public WelcomePresenter(WelcomeView view) {
        this.view = view;
    }

    public void selectIntent() {
        if (SharedPreferencesUtils.getFrist()) {
            view.intentLogin();
        } else {
            netLogin();
        }
    }
    private
    Handler handler=new Handler();




    public void netLogin() {
        String user = SharedPreferencesUtils.getAccount();
        String password = SharedPreferencesUtils.getPassword();

        if (TextUtils.isEmpty(user)) {
          view.intentLogin();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            view.intentLogin();
            return;
        }
        view.showloadDialog();
        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .url(Config.Loginurl)
                .addParams("username", user)
                .addParams("pwd", password)
                .addParams("push_id", SharedPreferencesUtils.getRegId());
        postFormBuilder
                .addParams("type", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        view.dissloadDialog();
                        view.setNetErrer();
                        Intent home=new Intent(Intent.ACTION_MAIN);
                        home.addCategory(Intent.CATEGORY_HOME);
                        view.intentTo(home);
                        view.finish();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("登陆返回报文",response.toString());
                        Config.loginResult = JSON.parseObject(response, new TypeReference<LoginResult>() {
                        });
                        if (!Config.loginResult.isStatus()) {
                            view.setNetErrer(Config.loginResult.getMsg());
                            return;
                        } else {
                            view.loginSucceed();
                        }
                    }
                });
    }
}
