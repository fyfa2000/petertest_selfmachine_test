package com.huapintang.cashregister.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.InputDevice;
import android.view.KeyEvent;

import com.huapintang.cashregister.MyApplication;


/**
 * Created by Administrator on 2017/10/11 0011.
 * 扫码枪事件解析类
 */

public class ScanGunKeyEventHelper {
    private final static long MESSAGE_DELAY = 200;             //延迟500ms，判断扫码是否完成。
    private StringBuffer mStringBufferResult;                  //扫码内容
    private boolean mCaps;                                     //大小写区分
    private final Handler mHandler;
    private final BluetoothAdapter mBluetoothAdapter;
    private final Runnable mScanningFishedRunnable;
    private OnScanSuccessListener mOnScanSuccessListener;
    private String mDeviceName="NEWTOLOGIC NEWTOLOGIC";

    public ScanGunKeyEventHelper(OnScanSuccessListener onScanSuccessListener) {
        mOnScanSuccessListener = onScanSuccessListener;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mStringBufferResult = new StringBuffer();
        mHandler = new Handler();
        mScanningFishedRunnable = new Runnable() {
            @Override
            public void run() {
                performScanSuccess();
            }
        };
    }


    /**
     * 返回扫码成功后的结果
     */
    private void performScanSuccess() {
        String barcode = mStringBufferResult.toString();
        if (mOnScanSuccessListener != null)
            mOnScanSuccessListener.onScanSuccess(barcode);
        mStringBufferResult.setLength(0);
    }


    /**
     * 扫码枪事件解析
     *
     * @param event
     */
    public void analysisKeyEvent(KeyEvent event) {

        int keyCode = event.getKeyCode();

        //字母大小写判断
        checkLetterStatus(event);

        if (event.getAction() == KeyEvent.ACTION_DOWN) {

            char aChar = getInputCode(event);

            LogUtils.e("aChar",""+aChar);
            if (aChar != 0) {
                mStringBufferResult.append(aChar);
            }

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                //若为回车键，直接返回
                mHandler.removeCallbacks(mScanningFishedRunnable);
                mHandler.post(mScanningFishedRunnable);
            } else if (keyCode == KeyEvent.KEYCODE_TAB) {
                //若为TAB键，直接返回
                mHandler.removeCallbacks(mScanningFishedRunnable);
                mHandler.post(mScanningFishedRunnable);
            } else {
                //延迟post，若500ms内，有其他事件
                mHandler.removeCallbacks(mScanningFishedRunnable);
                mHandler.postDelayed(mScanningFishedRunnable, MESSAGE_DELAY);
            }

        }
    }

    //检查shift键
    private void checkLetterStatus(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT || keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //按着shift键，表示大写
                mCaps = true;
            } else {
                //松开shift键，表示小写
                mCaps = false;
            }
        }
    }


    //获取扫描内容
    public char getInputCode(KeyEvent event) {

        int keyCode = event.getKeyCode();
        LogUtils.d("keyCode-->" , keyCode+"");
        char aChar;

        if (keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z) {
            //字母
            aChar = (char) ((mCaps ? 'A' : 'a') + keyCode - KeyEvent.KEYCODE_A);
        } else if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            //数字
            aChar = (char) ('0' + keyCode - KeyEvent.KEYCODE_0);
        } else {
            //其他符号
            switch (keyCode) {
                case KeyEvent.KEYCODE_PERIOD:
                    aChar = '.';
                    break;
                case KeyEvent.KEYCODE_MINUS:
                    aChar = mCaps ? '_' : '-';
                    break;
                case KeyEvent.KEYCODE_SLASH:
                    aChar = '/';
                    break;
                case KeyEvent.KEYCODE_BACKSLASH:
                    aChar = mCaps ? '|' : '\\';
                    break;
                default:
                    aChar = 0;
                    break;
            }
        }

        return aChar;

    }


    public interface OnScanSuccessListener {
        void onScanSuccess(String barcode);
    }


    public void onDestroy() {
        mHandler.removeCallbacks(mScanningFishedRunnable);
        mOnScanSuccessListener = null;
    }


    //部分手机如三星，无法使用该方法
//    private void hasScanGun() {
//        Configuration cfg = getResources().getConfiguration();
//        return cfg.keyboard != Configuration.KEYBOARD_NOKEYS;
//    }



    /**
     * 输入设备是否存在
     *
     * @param deviceName
     * @return
     */
    private boolean isInputDeviceExist(String deviceName) {
        int[] deviceIds = InputDevice.getDeviceIds();

        for (int id : deviceIds) {
            String name = InputDevice.getDevice(id).getName();
            if (name.equals(deviceName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 是否为扫码枪事件(部分机型KeyEvent获取的名字错误)
     *
     * @param event
     * @return
     */
    @Deprecated
    public boolean isScanGunEvent(KeyEvent event) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(MyApplication.getContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + MyApplication.getContext().getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            } else {
                if(event.getDevice()!=null) {
                    String name = event.getDevice().getName();
                    LogUtils.d("键盘设备名：" , name);
                    return name.equals(mDeviceName);
                }
            }
        }else {
            if(event.getDevice()!=null) {
                String name = event.getDevice().getName();
                LogUtils.d("键盘设备名：" ,name);
                return name.equals(mDeviceName);
            }
        }
        return false;
    }

    public String getmDeviceName() {
        return mDeviceName;
    }

    public void setmDeviceName(String mDeviceName) {
        this.mDeviceName = mDeviceName;
    }
}
