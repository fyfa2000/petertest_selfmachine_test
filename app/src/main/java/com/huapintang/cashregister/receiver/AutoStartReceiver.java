package com.huapintang.cashregister.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.huapintang.cashregister.ui.welcome.view.WelcomeActivity;
import com.huapintang.cashregister.utils.LogUtils;

public class AutoStartReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            LogUtils.e("开机跳转到主页");
            Intent i = new Intent(context, WelcomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }

//        Bundle a = new Bundle();
//        Intent i = new Intent(context, WelcomeActivity.class);
////        i.putExtras(a);
////        a.putString("test", "test");
//        LogUtils.e("跳转到主页");
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
    }


} 