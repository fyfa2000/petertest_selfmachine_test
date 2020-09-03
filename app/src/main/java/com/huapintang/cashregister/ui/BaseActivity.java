package com.huapintang.cashregister.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.dialog.MsgDialog;
import com.zhy.autolayout.AutoLayoutActivity;

public class BaseActivity extends AutoLayoutActivity implements View.OnClickListener, BaseView {
    public static AlertDialog progressDialog = null;

    //提示
    public void showPrompt(String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("str", msg);
        new Intent().setClass(MyApplication.getContext(), MsgDialog.class).putExtras(bundle);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            return true;
        } else if(keyCode == KeyEvent.KEYCODE_MENU) {//MENU键
            //监控/拦截菜单键
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void intentTo(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void intentForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void showMenu() {
        //显示虚拟按键
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            //低版本sdk
            View v = getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    public interface IBaseXView {
        /**
         * 获取 Activity 对象
         *
         * @return activity
         */
        <T extends Activity> T getSelfActivity();
    }

    public void showErrorToast() {
        Toast.makeText(MyApplication.getContext(), "请检查网络是否畅通", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        buildProgressDialog();
        hideBottomUIMenu();
        super.onCreate(savedInstanceState);

    }


    /**
     * 隐藏虚拟按键，并且全屏
     */
    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showMenu();
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 加载框
     */
    public void buildProgressDialog() {
        if (progressDialog == null) {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    new ContextThemeWrapper(this, R.style.dialogload));
            View dialogView = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_loading, null, false).getRoot();
            dialogView.setLayoutParams(new WindowManager.LayoutParams());
            // 为dialog设置view
            builder.setView(dialogView);
            progressDialog = builder.create();

        }

        progressDialog.setCancelable(true);
    }


    public void showDialog() {
        if (progressDialog == null) {
            buildProgressDialog();
        }
        if (progressDialog.isShowing()) {
            return;
        }
        progressDialog.show();
    }

    public void dismissDialog() {
        if (progressDialog != null)
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
    }

    @Override
    public void showloadDialog() {
        showDialog();
    }

    @Override
    public void dissloadDialog() {
        dismissDialog();
    }

    @Override
    public void setNetErrer() {
        showErrorToast();
    }

    @Override
    public void setNetErrer(String string) {
        showToast(string);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_back:
//                finish();
//                break;

        }
    }
}
