package com.huapintang.cashregister.dialog;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.huapintang.cashregister.MyApplication;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by cbp on 4/10 0010.
 */
public class BaseDialogActivity extends AutoLayoutActivity implements View.OnClickListener {


    public void showErrorToast() {
        Toast.makeText(MyApplication.getContext(), "请检查网络是否畅通", Toast.LENGTH_SHORT).show();
    }

    public void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Make us non-modal, so that others can receive touch events.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        // ...but notify us that it happened.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        buildProgressDialog();
        // Note that flag changes must happen *before* the content view is set.
        super.onCreate(savedInstanceState);
    }

    private LodingDialog progressDialog = null;

    /**
     * 加载框
     */
    public void buildProgressDialog() {
        if (progressDialog == null) {

            progressDialog=new LodingDialog(this);
        }
//        progressDialog.setMessage(getString(id));
        progressDialog.setCancelable(true);
    }

    public void showDialog() {

        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    public void showDialog(String str){
        if(progressDialog.isShowing()){
            progressDialog.diss();
        }
        progressDialog.setTitle(str);
        progressDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public void dismissDialog() {
        if (progressDialog != null)
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
    }


    //    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_back:
//                finish();
//                break;
//
//        }
//    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // If we've received a touch notification that the user has touched
        // outside the app, finish the activity.
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            finish();
            return true;
        }

        // Delegate everything else to Activity.
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {

    }
}