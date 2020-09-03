package com.huapintang.cashregister.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;


/**
 * God Knows me.
 * Created by kam on 17/7/17.
 */

public class ScanEditText extends android.support.v7.widget.AppCompatEditText {
    private final static long MESSAGE_DELAY = 800;
    private OnKeyEnterClickListener mListener;
    private boolean mIsClean = true;
    private long mDoubleClickTime = 0;// 退出事件用到的事件
    private int DOUBLE_TILE = 500;//双击间隔时间
    private boolean isFirstFocus = true;
//    private int mScanCode = AppConfig.APPKEY.KEY_SCAN;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    setText(msg.obj.toString());
                    if (getText().toString().length() > 0) {
                        setSelection(getText().toString().length());
                    }
                    if (mListener != null) {
                        mListener.onClick(getText().toString());
                        if (mIsClean) {
                            setText("");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private final Runnable mScanningFishedRunnable = new Runnable() {
        @Override
        public void run() {
            if (mListener != null) {
                mListener.onClick(getText().toString());
            }
        }
    };

    public ScanEditText(Context context) {
        this(context, null);
    }

    public ScanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ScanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }


    private void initView(final Context context) {
        this.setInputType(InputType.TYPE_NULL);
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) ||
                        (keyCode == KeyEvent.KEYCODE_TAB && event.getAction() == KeyEvent.ACTION_UP)) {
                    if (mListener != null) {
                        mListener.onClick(getText().toString());
                        if (mIsClean) {
                            setText("");
                        }
                        return true;
                    }
                }
                return false;
            }
        });


        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFirstFocus) {
//                    scanCode(context, mScanCode);
                }
                if (isFirstFocus) {
                    isFirstFocus = false;
                }
            }
        });
        setNextFocusDownId(getId());
        setSingleLine(true);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //延迟post 发送，若200ms内，有其他事件
                handler.removeCallbacks(mScanningFishedRunnable);
                handler.postDelayed(mScanningFishedRunnable, MESSAGE_DELAY);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

//    private void scanCode(Context context, int scanCode) {
//        ScanUtil.getInstance(EScannerType.REAR).setScanListener(new ScanUtil.ScanListener() {
//            @Override
//            public void onFail() {
//            }
//        }).scan(handler);
//    }

    /**
     * 设置扫码成功后是否自动清除数据
     *
     * @param isClean
     */
    public void setIsEnterCleanText(boolean isClean) {
        mIsClean = isClean;
    }

//    public void setScanCode(int code) {
//        this.mScanCode = code;
//    }

    /**
     * 设置扫码成功的监听器
     *
     * @param listener
     */
    public void setOnKeyEnterClickListener(OnKeyEnterClickListener listener) {
        mListener = listener;
    }

    public interface OnKeyEnterClickListener {
        void onClick(String text);
    }
}
