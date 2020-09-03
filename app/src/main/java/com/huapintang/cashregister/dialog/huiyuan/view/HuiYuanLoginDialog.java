package com.huapintang.cashregister.dialog.huiyuan.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.ActivityHuiYuanLoginDialogBinding;
import com.huapintang.cashregister.dialog.BaseDialogActivity;
import com.huapintang.cashregister.dialog.huiyuan.model.HuiYuan;
import com.huapintang.cashregister.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


public class HuiYuanLoginDialog extends BaseDialogActivity {

    //        private ActivityH
    private ActivityHuiYuanLoginDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hui_yuan_login_dialog);
//        setContentView(R.layout.activity_hui_yuan_login_dialog);
        binding.llSure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_sure:
                String s = binding.etHuiyuanNo.getText().toString();

                if (TextUtils.isEmpty(s)) {
                    showToast("请输入会员号");
                    return;
                }
                showDialog();
                OkHttpUtils
                        .post()
                        .url(Config.HuiYuanUrl)
                        .addParams("code", s)
                        .addParams("ukey", Config.loginResult.getData().getUkey())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                showErrorToast();

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                dismissDialog();
                                LogUtils.e("会员：",response);

                                HuiYuan huiYuan = JSON.parseObject(response, new TypeReference<HuiYuan>() {
                                });
                                if (huiYuan.isStatus()) {
                                    //下单
                                    HuiYuan.DataBean data = huiYuan.getData();
                                    Intent intent=new Intent();
                                    Bundle extras = new Bundle();
                                    extras.putParcelable("huiyanData",data);
                                    intent.putExtras(extras);
                                    setResult(RESULT_OK,intent);
                                    finish();
//                                    intent.setClass(MyApplication.getContext(), HuiYuanPayActivity.class);
//                                    startActivity(intent);
                                }else{
                                    showToast(huiYuan.getMsg());
                                }
                            }
                        });


                break;
        }
        super.onClick(v);
    }


}
