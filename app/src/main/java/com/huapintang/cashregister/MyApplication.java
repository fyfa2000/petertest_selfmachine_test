package com.huapintang.cashregister;

import android.app.Application;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.widget.Toast;

import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.utils.Vbar;
import com.tencent.bugly.crashreport.CrashReport;
import com.tx.printlib.Const;
import com.tx.printlib.UsbPrinter;
import com.zhy.autolayout.config.AutoLayoutConifg;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Created by cbp on 4/16 0016.
 */
public class MyApplication extends Application {
    private static Context context;


    private static Vbar b;

    private void initVbar() {    //调测微信扫码器

        b = new Vbar();
        try {
            boolean b = this.b.vbarOpen();
            if (b) {
                Toast.makeText(getContext(), "连接成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "连接失败", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("扫描获取异常"+ e.toString());
        }
        boolean isLight = MyApplication.b.vbarLight(true);
        if (isLight) {
            LogUtils.e("开灯设置：成功");
        } else {
            LogUtils.e("开灯设置:失败");
        }
        b.vbarInterval(3000);
    }



    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();


        AutoLayoutConifg.getInstance().useDeviceSize();


        CrashReport.initCrashReport(getApplicationContext(), "48a076d5ad", false);

        initHttps();    //检查网络连接
//        initVbar();
        Config.initJPush(this);   //初始化极光推送

//        fristPrintTitcket();
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

    public  void fristPrintTitcket() {      //调测打印机

//        mUSBDevice = data.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//        myPrinter = PrinterInstance.getPrinterInstance(mContext, mUSBDevice, mHandler);
//        devicesName = mUSBDevice.getDeviceName();
//        devicesAddress = "vid: " + mUSBDevice.getVendorId() + "  pid: " + mUSBDevice.getProductId();
//        UsbManager mUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        //android-26自身方法，获取所有USB设备

//        UsbPrinter mUsbPrinter = new UsbPrinter(MyApplication.getContext());
//        final UsbDevice dev = getCorrectDevice();
//        if (dev != null) {
//            //判断mUSBDevice有没有权限，有就连接。
//            myPrinter.openConnection();
//        } else {
//            // 没有权限询问用户是否授予权限
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0,
//                    new Intent(ACTION_USB_PERMISSION), 0);
//            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
//            filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
//            filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
//            mContext.registerReceiver(mUsbReceiver, filter);
//            mUsbManager.requestPermission(mUSBDevice, pendingIntent); // 该代码执行后，系统弹出一个对话框
//        }



        UsbPrinter mUsbPrinter = new UsbPrinter(MyApplication.getContext());
        final UsbDevice dev = getCorrectDevice();

        if (dev != null && mUsbPrinter.open(dev)) {

            mUsbPrinter.init();
            int status = mUsbPrinter.getStatus();
            mUsbPrinter.init();
            if (status != Const.TX_STAT_NOERROR && status != 88) {
                String msg = "";
                switch (status) {
                    case Const.TX_STAT_SELECT:
                        msg = "处于联机状态";
                        break;
                    case Const.TX_STAT_PAPEREND:
                        msg = "处于缺纸状态";
                        break;
                    case Const.TX_STAT_BUSY:
                        msg = "处于繁忙状态";
                        break;
                    case Const.TX_STAT_DRAW_HIGH:
                        msg = "钱箱接口的电平";
                        break;
                    case Const.TX_STAT_COVER:
                        msg = "打印机机芯的盖子打开";
                        break;
                    case Const.TX_STAT_ERROR:
                        msg = "打印机错误";
                        break;
                    case Const.TX_STAT_RCV_ERR:
                        msg = "可恢复错误（需要人工干预）";
                        break;
                    case Const.TX_STAT_CUT_ERR:
                        msg = "切刀错误";
                        break;
                    case Const.TX_STAT_URCV_ERR:
                        msg = "不可恢复错误";
                        break;
                    case Const.TX_STAT_ARCV_ERR:
                        msg = "可自动恢复的错误";
                        break;
                    case Const.TX_STAT_PAPER_NE:
                        break;
                    case 112:
                        msg = "缺纸，重新进纸";
                        break;
                    default:
                        msg = "未知错误";
                        break;
                }
                LogUtils.e(msg);
                return;
            }
            mUsbPrinter.doFunction(Const.TX_FEED_REV, 30, 0);
            byte[] buf = new byte[]{
                    0X1D, 0X28, 0X46,
                    0X04, 0X00, 0X02,
                    0X00, (byte) 0X24, 0X01
            };
            mUsbPrinter.write(buf);
            mUsbPrinter.doFunction(Const.TX_ALIGN, Const.TX_ALIGN_CENTER, 0);
            mUsbPrinter.outputStringLn("测试打印OK");
            mUsbPrinter.doFunction(Const.TX_FONT_SIZE, Const.TX_SIZE_3X, Const.TX_SIZE_2X);
            mUsbPrinter.doFunction(Const.TX_UNIT_TYPE, Const.TX_UNIT_MM, 0);
            mUsbPrinter.doFunction(Const.TX_HOR_POS, 20, 0);
            mUsbPrinter.outputStringLn("打印成功");
            mUsbPrinter.doFunction(Const.TX_CUT, Const.TX_CUT_PARTIAL, 0);
            mUsbPrinter.close();

        }else{
//            resetPrint();
        }

    }

    public static void initHttps() {      //判断网络连接是否正常
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }


    //返回
    public static Context getContext() {
        return context;
    }


}

