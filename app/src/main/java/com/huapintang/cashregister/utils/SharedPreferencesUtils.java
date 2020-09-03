package com.huapintang.cashregister.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.huapintang.cashregister.MyApplication;


/**
 * Created by cbp on 3/1 0001.
 */
public class SharedPreferencesUtils {
    private static SharedPreferences user;
    private static Context context;


    public static SharedPreferences sp = MyApplication.getContext().getSharedPreferences("User", Context.MODE_PRIVATE);

    public static void commit(String regId) {
        SharedPreferences.Editor edit = sp.edit();
        //通过editor对象写入数据
        edit.putString("RegId", regId);
        edit.commit();
    }

    public static void saveUser(String account, String password) {
        if (user == null) {
            getPrefer();
        }
        user.edit().putString("Account", account).commit();
        user.edit().putString("Password", password).commit();

    }

    public static String getAccount() {
        if (user == null) {
            getPrefer();
        }
        return user.getString("Account", "");

    }

    public static String getPassword() {
        if (user == null) {
            getPrefer();
        }
        return user.getString("Password", "");
    }

    public static String getRegId() {
        return sp.getString("RegId", "");
    }

    public static void getPrefer() {
        context = MyApplication.getContext();
        user = context.getSharedPreferences("User", Context.MODE_PRIVATE);
    }

    public static boolean getFrist() {
        if (user == null) {
            getPrefer();
        }
        return user.getBoolean("frist", true);
    }

    //第一次启动后，后面不再进行跳转到登陆页面
    public static void saveFrist() {
        if (user == null) {
            getPrefer();
        }
        user.edit().putBoolean("frist", false).commit();
    }

}
