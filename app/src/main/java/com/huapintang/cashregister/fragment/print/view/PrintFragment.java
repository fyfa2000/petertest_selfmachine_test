package com.huapintang.cashregister.fragment.print.view;


import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.bean.PrintTicket;
import com.huapintang.cashregister.databinding.FragmentPrintBinding;
import com.huapintang.cashregister.dialog.ErrotDialog;
import com.huapintang.cashregister.dialog.MsgPrintDialogActivity;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.printover.view.PrintOverFragment;
import com.huapintang.cashregister.utils.PrintUtils;
import com.tx.printlib.Const;
import com.tx.printlib.UsbPrinter;

import java.util.ArrayList;
import java.util.Map;

import com.printer.sdk.PrinterInstance;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.annotation.SuppressLint;
import android.util.Log;
import com.printer.sdk.PrinterConstants.Connect;
import com.huapintang.cashregister.fragment.print.GlobalContants;
import android.widget.Toast;
import com.printer.sdk.utils.XLog;





/**
 * A simple {@link Fragment} subclass.
 */
public class PrintFragment extends BaseFragment {

    private static UsbDevice mUSBDevice;
    public static PrinterInstance myPrinter;
    public static String devicesName = "未知设备";
    private static String devicesAddress;
    private Context mContext;
    private static final String ACTION_USB_PERMISSION = "com.android.usb.USB_PERMISSION";
    protected static final String TAG = "SettingActivity";
    public static boolean isConnected = false;// 蓝牙连接状态
    private String strStatus = null;



    private PrintTicket.DataBean dataBean;
    //    private String saveString;//图片存储路径
    private UsbPrinter mUsbPrinter;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            binding.tvLoad1.setProgress(msg.what);

        }
    };
    private String saveString;
    private ArrayList<String> strings;
    //处理打印事项
    private Handler handler2 = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    if (array == null) {
                        saveString = PrintUtils.CreateTicketBitmap(dataBean);
                        if (TextUtils.isEmpty(saveString)) {
                            showToast("生成打印内容错误");
                            //请检查
                            return;
                        }
                    } else {
                        strings = PrintUtils.CreateTicketBitmap(array);
                    }
                    index = 50;
//            handler.sendEmptyMessage(index);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(index);

                        }
                    }, 500);

                    boolean isAlltrue=false;


                    if (array == null) {
                        isAlltrue = printing(saveString);

                    } else {
                        for (String str : strings                                ) {
                            isAlltrue=   printing(str);
                        }
//                        replaceFragment(new PrintOverFragment());
                    }
                    if(isAlltrue){
                        replaceFragment(new PrintOverFragment());
                    }
                    break;
            }
        }
    };

    // 用于接受连接状态消息的 Handler
    private Handler mHandler = new Handler() {
        @SuppressLint("ShowToast")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Connect.SUCCESS:
                    isConnected = true;
                    GlobalContants.ISCONNECTED = isConnected;
                    GlobalContants.DEVICENAME = devicesName;
//                    if (interfaceType == 0) {
//                        PrefUtils.setString(mContext, GlobalContants.DEVICEADDRESS, devicesAddress);
//                        bluDisconnectFilter = new IntentFilter();
//                        bluDisconnectFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
//                        mContext.registerReceiver(myReceiver, bluDisconnectFilter);
//                        hasRegDisconnectReceiver = true;
//                    }
                    // TOTO 暂时将TSPL指令设置参数的设置放在这
                    // if (setPrinterTSPL(myPrinter)) {
                    // if (interfaceType == 0) {
                    // Toast.makeText(mContext,
                    // R.string.settingactivitty_toast_bluetooth_set_tspl_successful,
                    // 0)
                    // .show();
                    // } else if (interfaceType == 1) {
                    // Toast.makeText(mContext,
                    // R.string.settingactivity_toast_usb_set_tspl_succefful,
                    // 0).show();
                    // }
                    // }
                    break;
                case Connect.FAILED:
                    isConnected = false;

//                    Toast.makeText(mContext, R.string.conn_failed, Toast.LENGTH_SHORT).show();
                    XLog.i(TAG, "ZL at SettingActivity Handler() 连接失败!");
                    break;
                case Connect.CLOSED:
                    isConnected = false;
                    GlobalContants.ISCONNECTED = isConnected;
                    GlobalContants.DEVICENAME = devicesName;
//                    Toast.makeText(mContext, R.string.conn_closed, Toast.LENGTH_SHORT).show();
                    XLog.i(TAG, "ZL at SettingActivity Handler() 连接关闭!");
                    break;
                case Connect.NODEVICE:
                    isConnected = false;
//                    Toast.makeText(mContext, R.string.conn_no, Toast.LENGTH_SHORT).show();
                    break;
                // case 10:
                // if (setPrinterTSPL(myPrinter)) {
                // Toast.makeText(mContext, "蓝牙连接设置TSPL指令成功", 0).show();
                // }
                default:
                    break;
            }

//            updateButtonState(isConnected);

//            if (dialog != null && dialog.isShowing()) {
//                dialog.dismiss();
//            }
        }

    };

    private FragmentPrintBinding binding;

    public PrintFragment() {
        // Required empty public constructor
    }

    private ArrayList<PrintTicket.DataBean> array;

    //  bundle.putParcelableArrayList("array",printData);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_print, container, false);
//        mUsbPrinter = new UsbPrinter(MyApplication.getContext());
        myPrinter = PrinterInstance.getPrinterInstance(mContext, mUSBDevice, mHandler);
        Bundle extras = getArguments();
        if (extras != null) {
            array = extras.getParcelableArrayList("array");
            dataBean = extras.getParcelable("ticket");
        }
        initAni2();
        return binding.getRoot();
    }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
            return;
        if (requestCode == 1) {// 连接设备
            mUSBDevice = data.getParcelableExtra(UsbManager.EXTRA_DEVICE);
            myPrinter = PrinterInstance.getPrinterInstance(mContext, mUSBDevice, mHandler);
            devicesName = mUSBDevice.getDeviceName();
            devicesAddress = "vid: " + mUSBDevice.getVendorId() + "  pid: " + mUSBDevice.getProductId();
            UsbManager mUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
            //android-26自身方法，获取所有USB设备
            if (mUsbManager.hasPermission(mUSBDevice)) {
                //判断mUSBDevice有没有权限，有就连接。
                myPrinter.openConnection();
                if (!isConnected) {
                    int i = myPrinter.getCurrentStatus();
                    if (i == -1) {
                        strStatus = "接收数据失败";
                    } else if (i == -2) {
                        strStatus = "打印机缺纸";
                    } else if (i == -3) {
                        strStatus = "打印机纸将尽";
                    } else if (i == -4) {
                        strStatus = "打印机开盖";
                    } else if (i == -5) {
                        strStatus = "发送数据失败";
                    }
                    showToast(strStatus);  //前台显示报错信息
                    myPrinter.closeConnection();  //关闭打印接口
                }
            } else {
                // 没有权限询问用户是否授予权限
                PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0,
                        new Intent(ACTION_USB_PERMISSION), 0);
                IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
                filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
                filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
                mContext.registerReceiver(mUsbReceiver, filter);
                mUsbManager.requestPermission(mUSBDevice, pendingIntent); // 该代码执行后，系统弹出一个对话框
            }
        }








//        if (resultCode == Activity.RESULT_OK && requestCode == 505) {
//            boolean isAlltrue=false;
//            if (array == null) {
//                isAlltrue =   printing(saveString);
//            } else {
//                for (String str:strings
//                     ) {
//                    isAlltrue = printing(str);
//                }
//            }
//            if(isAlltrue){
//                replaceFragment(new PrintOverFragment());
//            }
//        }
    }

    private boolean printing(String saveString) {
//        final UsbDevice dev = getCorrectDevice();
//        if (dev != null && mUsbPrinter.open(dev)) {
//            mUsbPrinter.init();
//            int status = mUsbPrinter.getStatus();
        devicesName = mUSBDevice.getDeviceName();
        devicesAddress = "vid: " + mUSBDevice.getVendorId() + "  pid: " + mUSBDevice.getProductId();
        UsbManager mUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        //android-26自身方法，获取所有USB设备
        if (mUsbManager.hasPermission(mUSBDevice)) {
            //判断mUSBDevice有没有权限，有就连接。
            myPrinter.openConnection();
            if (!isConnected) {
                int i = myPrinter.getCurrentStatus();
                if (i == -1) {
                    strStatus = "接收数据失败";
                } else if (i == -2) {
                    strStatus = "打印机缺纸";
                } else if (i == -3) {
                    strStatus = "打印机纸将尽";
                } else if (i == -4) {
                    strStatus = "打印机开盖";
                } else if (i == -5) {
                    strStatus = "发送数据失败";
                }
                showToast(strStatus);  //前台显示报错信息
                myPrinter.closeConnection();  //关闭打印接口
            }else{
                strStatus = "打印机连接成功";
                showToast(strStatus);  //前台显示报错信息
                myPrinter.closeConnection();  //关闭打印接口
                return false;
            }





//            //88不知道什么，但是能正常打印
//            if (status != Const.TX_STAT_NOERROR && status != 88) {
//                String msg = "";
//                switch (status) {
//                    case Const.TX_STAT_SELECT:
//                        msg = "处于联机状态";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_PAPEREND:
//                        msg = "处于缺纸状态";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_BUSY:
//                        msg = "处于繁忙状态";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_DRAW_HIGH:
//                        msg = "钱箱接口的电平";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_COVER:
//                        msg = "打印机机芯的盖子打开";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_ERROR:
//                        msg = "打印机错误";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_RCV_ERR:
//                        msg = "可恢复错误（需要人工干预）";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_CUT_ERR:
//                        msg = "切刀错误";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_URCV_ERR:
//                        msg = "不可恢复错误";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_ARCV_ERR:
//                        msg = "可自动恢复的错误";
//                      //  showPrompt(msg);
//                        break;
//                    case Const.TX_STAT_PAPER_NE:
//                        break;
//                    case 112:
//                        msg = "缺纸，重新进纸";
//                      //  showPrompt(msg);
//                        break;
//                    default:
//                        msg = "未知错误";
//                      //  showPrompt(msg);
//                        break;
//
//                }
//                showToast(msg);
//                mUsbPrinter.close();

//                Intent intent = new Intent();
//                Bundle extras = new Bundle();
//                extras.putString("Style", "prining");
//                extras.putString("msg", msg);
//                if(dataBean==null){
//                    extras.putParcelableArrayList("array",array);
//                }else{
//                    extras.putString("code", dataBean.getCode());
//
//                }
//
//                intent.putExtras(extras);
//                intent.setClass(MyApplication.getContext(), ErrotDialog.class);
//                startActivityForResult(intent, 505);
//
//
//                return false;
//            }
//
//            mUsbPrinter.doFunction(Const.TX_FEED_REV, 30, 0);
//            byte[] buf = new byte[]{
//                    0X1D, 0X28, 0X46,
//                    0X04, 0X00, 0X02,
//                    0X00, (byte) 0X24, 0X01
//            };
//            mUsbPrinter.write(buf);
//            mUsbPrinter.doFunction(Const.TX_ALIGN, Const.TX_ALIGN_CENTER, 0);
////                    TxDoFunction(TX_ALIGN,TX_ALIGN_CENTER,0);
//            boolean b = mUsbPrinter.printImage(saveString);
//            if (b) {
//                //成功打印
//                mUsbPrinter.doFunction(Const.TX_CUT, Const.TX_CUT_PARTIAL, 0);
//            } else {
//                //打印失败  弹出提示框
//                showPrompt("打印失败，请联系工作人员");
//            }
//            mUsbPrinter.close();
//            if (b) {
//
//                //删除图片
//                if (array != null && array.size() > 0) {
//                    array.remove(saveString);
//                }
//
////                Intent intent = new Intent();
////                intent.setClass(MyApplication.getContext(), PriningOverActivity.class);
////                startActivity(intent);
//            } else {
//                Intent intent = new Intent();
//                Bundle extras = new Bundle();
//                intent.putExtras(extras);
//                extras.putString("Style", "prining");
//                intent.setClass(MyApplication.getContext(), MsgPrintDialogActivity.class);
//                startActivityForResult(intent, 505);
//            }


        }
        return true;
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @SuppressLint("NewApi")
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.w(TAG, "receiver action: " + action);

            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    mContext.unregisterReceiver(mUsbReceiver);
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)
                            && mUSBDevice.equals(device)) {
                        myPrinter.openConnection();
                    } else {
                        mHandler.obtainMessage(Connect.FAILED).sendToTarget();
                        Log.e(TAG, "permission denied for device " + device);
                    }
                }
            }
        }
    };


    private void initAni() {
        Animation translateAnimation = new TranslateAnimation(0, 0, 0, 40);//平移动画  从0,0,平移到100,100
        translateAnimation.setDuration(3000);
        translateAnimation.setRepeatCount(ValueAnimator.INFINITE);
        binding.ivDown.setAnimation(translateAnimation);//给imageView添加的动画效果
        translateAnimation.startNow();
    }

    private int index = 0;

    //动画二，走到百分之49
    private void initAni2() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                //初始化动画
                initAni();
                //设置动画
                handler.sendEmptyMessage(index);
                //请求生成图片
                handler2.sendEmptyMessage(1);
                while (index < 50) {
                    //最多走到49
                    if (index >= 49) {
                        break;
                    }
                    index++;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(index);
                        }
                    }, 500);
                }
            }
        };
        thread.start();
    }

    private void initAni3() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                handler.sendEmptyMessage(index);
                handler2.sendEmptyMessage(1);
                while (index < 100) {
                    //最多走到49
                    if (index >= 99) {
                        break;
                    }
                    index++;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handler.sendEmptyMessage(index);
                        }
                    }, 500);
                }
            }
        };
        thread.start();


    }


//    private void intent() {
//        Intent intent = new Intent();
//        intent.setClass(this, PriningOverActivity.class);
//        startActivity(intent);
//    }

}
