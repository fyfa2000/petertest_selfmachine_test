package com.huapintang.cashregister.ui.login.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.ui.login.model.LoginResult;
import com.huapintang.cashregister.ui.login.view.LoginView;
import com.huapintang.cashregister.utils.SharedPreferencesUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by cbp on 3/4 0004.
 */
public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void NetLogin(final String user, final String password) {
        if (TextUtils.isEmpty(user)) {
            loginView.setUserErrer();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            loginView.setPasswordErrer();
            return;
        }
        loginView.showloadDialog();
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
                        loginView.dissloadDialog();
                        loginView.setNetErrer();
                        Intent home=new Intent(Intent.ACTION_MAIN);
                        home.addCategory(Intent.CATEGORY_HOME);
                        loginView.intentTo(home);
                        loginView.finsh();
//                        startActivity(i);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Config.loginResult = JSON.parseObject(response, new TypeReference<LoginResult>() {
                        });
                        if (!Config.loginResult.isStatus()) {
                            loginView.setNetErrer(Config.loginResult.getMsg());
                            return;
                        } else {
                            SharedPreferencesUtils.saveUser(user,password);
                            loginView.setNetSucceed();
                            SharedPreferencesUtils.saveFrist();
                        }
                    }
                });
    }


}
