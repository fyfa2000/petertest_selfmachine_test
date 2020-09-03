package com.huapintang.cashregister.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.dialog.LodingDialog;
import com.huapintang.cashregister.dialog.MsgDialog;
import com.huapintang.cashregister.ui.BaseActivity;

/**
 * Created by cbp on 5/2 0002.
 */
public class BaseFragment extends android.support.v4.app.Fragment implements View.OnClickListener, BaseFragmetView {





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DataBindingUtil.setContentView(BaseFragment.this, R.layout.);
        buildProgressDialog();
    }
    @Override
    public void replaceFragment(BaseFragment fragment) {


        try {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.ll_main, fragment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void intentTo(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void intentForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }



    /**
     * 加载框
     */
    public void buildProgressDialog() {
        if (BaseActivity.progressDialog == null) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            Spinner view = new Spinner(getContext());
//            builder.setView(view);
            BaseActivity.progressDialog = new LodingDialog(getContext());
//            BaseActivity.progressDialog = builder.create();
//            BaseActivity.progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
//        progressDialog.setMessage(getString(id));
        BaseActivity.progressDialog.setCancelable(true);
    }

    public static void showDialog() {
        if (BaseActivity.progressDialog == null) {

            return;
        }
        if (BaseActivity.progressDialog.isShowing()) {
            return;
        }
        BaseActivity.progressDialog.show();
    }


    public void showErrorToast() {
        Toast.makeText(MyApplication.getContext(), "请检查网络是否畅通", Toast.LENGTH_SHORT).show();
    }

    public void showToast(String string) {
        if (TextUtils.isEmpty(string)) {
            return;
        }
        Toast.makeText(MyApplication.getContext(), string, Toast.LENGTH_SHORT).show();
    }

    public static void dismissDialog() {
        if (BaseActivity.progressDialog != null)
            if (BaseActivity.progressDialog.isShowing()) {
                BaseActivity.progressDialog.dismiss();
            }
    }

    @Override
    public void onClick(View v) {

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

    //提示
    @Override
    public void showPrompt(String string) {
        Bundle bundle = new Bundle();
        bundle.putString("str",string);
          startActivity(  new Intent().setClass(MyApplication.getContext(), MsgDialog.class).putExtras(bundle));
    }


}
