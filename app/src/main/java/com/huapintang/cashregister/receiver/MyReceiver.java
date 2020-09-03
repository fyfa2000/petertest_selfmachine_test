package com.huapintang.cashregister.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.bean.PayPushResult;
import com.huapintang.cashregister.dialog.MsgDialog;
import com.huapintang.cashregister.fragment.pay.presenter.PayPresenter;
import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.utils.SharedPreferencesUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private final  String  TAG="极光推送：";
    // 客户端收到通知消息就会调用onReceiver()方法，可以在这个方法里对收到的消息进行处理
    @Override
    public void onReceive(Context context, Intent intent) {
        // 1.拿到消息的action
        Bundle bundle = intent.getExtras();
        LogUtils.d(TAG, "消息的action:" + intent.getAction());
        String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        LogUtils.e(TAG,"推送的regID:"+regId);
        if(!TextUtils.isEmpty(regId)){
            SharedPreferencesUtils.commit(regId);

            LogUtils.e(TAG," SharedPreferences的regID:"+   SharedPreferencesUtils.getRegId());
        }


        // 2.对消息进行判断,注册成功后得到id(ACTION_REGISTRATION_ID)
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
            // .收到了自定义消息,自定义消息不会展示到通知栏，完全要开发者写代码去处理
            System.out.println("收到了自定义消息,消息内容是:"
                    + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//            Bundle[{cn.jpush.android.EXTRA={"order_no":"2018071310052514","status":1}, cn.jpush.android.TITLE=订单2018071310052514支付成功, cn.jpush.android.MESSAGE=订单2018071310052514支付成功, cn.jpush.android.CONTENT_TYPE=, cn.jpush.android.APPKEY=c6a3b58cebb3457e79875c6a, cn.jpush.android.MSG_ID=3700603425}]

            String string = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            {"order_no":"2018071310052514","status":1}
//            PayPushResult payResult = JSON.parseObject(string, new TypeReference<PayPushResult>() {
//            });
//             PayPushResult payResult = JSON.parseObject(string, new TypeReference<PayPushResult>() {
            PayPushResult payResult = JSON.parseObject(string, new TypeReference<PayPushResult>() {
            });
        if(payResult!=null){
            if("1".equals(payResult.getStatus())){
                Message msg = new Message();
                msg.what=1;
                MsgDialog.handler.sendMessage(msg);
                Message msg2 = new Message();
                msg2.what=1;
                PayPresenter.handler.sendMessage(msg2);
//                Intent intent1 = new Intent();
//                Bundle extras = new Bundle();
//                extras.putBoolean("isSucceed",true);
//                intent1.putExtras(extras);
//                intent1.setClass(MyApplication.getContext(), MainHomeActivity.class);
//                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                MyApplication.getContext().startActivity(intent1);
            }else{
                Toast.makeText(MyApplication.getContext(), "付款失败请重新付款", Toast.LENGTH_SHORT).show();
            }

        }


        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            // 客户端收到了通知执行此分支,可以做些统计，或者其他工作
            System.out.println("收到了通知");
            //拿到通知的标题
            String title_str=bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            //拿到通知的内容
            String string = bundle.getString(JPushInterface.EXTRA_ALERT);
            LogUtils.e("通知   ","通知标题："+title_str+"通知内容："+string);
        }else if(JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            //用户点击打开了通知
            System.out.println("用户点击打开了通知");
            //在这里可以自己写代码去定义用户点击后的行为,
            //举例：点击通知后跳转至一个链接，从通知中拿到一个url，
            //但是url不能被用户看到，需要通过发送消息界面低端点击可选设置->附加字段，设置键和值，通过键拿到值，可以添加多个
            //拿到附加字段(json格式的字符串)
            bundle.getString(JPushInterface.EXTRA_EXTRA);
        }else {
            //未处理的消息(Unhandled:未处理)
            LogUtils.d(TAG, "Unhandled intent -"+intent.getAction());
        }
    }
}