package com.huapintang.cashregister.fragment.take;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.FragmentTakeBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.view.HomeFragment;
import com.huapintang.cashregister.fragment.take.codetake.CodeTakeFragment;
import com.huapintang.cashregister.fragment.take.eqtake.EqTakeFragment;
import com.tx.printlib.UsbPrinter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeFragment extends BaseFragment {

    private FragmentTakeBinding binding;

    String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (  msg.what){
                case 1:
                    setTime();
                    break;

            }

            super.handleMessage(msg);
        }

    };

    private void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        Date date = new Date();
        String format = df.format(date);// new Date()为获取当前系统时间
        String format2 = df2.format(date);// new Date()为获取当前系统时间
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        int weekday=c.get(Calendar.DAY_OF_WEEK);


        binding.tvDate.setText(format  + " "+weekDays[weekday-1]);

        binding.tvTime.setText(format2);
    }

    private Thread thread;


    private void setTimeThread(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (1<2){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.what=1;
                    handler.sendMessage(msg);
                }


            }
        });
        thread.start();


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_take, container, false);
        setTime();
//        int flags = getIntent().getFlags();
////            String test = extras.getString("test");
////            if(test.equals("test")){
//
//        LogUtils.e("未进入："+flags+"     FLAG_ACTIVITY_NEW_TASK:"+ Intent.FLAG_ACTIVITY_NEW_TASK);
//        if(Intent.FLAG_ACTIVITY_NEW_TASK==flags){
//            LogUtils.e("进入");
//            resetPrint();
//        }

        binding.llCodeTake.setOnClickListener(this);
        binding.llEqcodeTake.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);
//        binding.ivLogin.setOnClickListener(this);
        setTimeThread();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        thread=null;
//
//    }

    @Override
    public void onStop() {
        super.onStop();
        thread=null;
    }



    //开机打印测试页
    private UsbDevice getCorrectDevice() {
        final UsbManager usbMgr = (UsbManager) MyApplication.getContext().getSystemService(Context.USB_SERVICE);
        final Map<String, UsbDevice> devMap = usbMgr.getDeviceList();
        for (String name : devMap.keySet()) {
            if (UsbPrinter.checkPrinter(devMap.get(name)))
                return devMap.get(name);
        }
        return null;
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_code_take:
                replaceFragment(new CodeTakeFragment());
//                intent.setClass(this, com.huapintang.selfcollecttickets.activity.CodeTakeActivity.class);
//                startActivity(intent);
                break;
            case R.id.ll_eqcode_take:
                replaceFragment(new EqTakeFragment());
//                intent.setClass(this, com.huapintang.selfcollecttickets.activity.EQCodeActivity.class);
//                startActivity(intent);
                break;

            case R.id.btn_back:
                replaceFragment(new HomeFragment());
                break;

//            case R.id.iv_login:
//                intent.setClass(this, com.huapintang.selfcollecttickets.activity.LoginActivity.class);
//                startActivity(intent);
//                break;
        }
        super.onClick(v);
    }
}
