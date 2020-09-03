package com.huapintang.cashregister.dialog;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.bean.OutTicket;
import com.huapintang.cashregister.bean.PrintTicket;
import com.huapintang.cashregister.databinding.ActivityErrotDialogBinding;
import com.huapintang.cashregister.ui.main.view.MainHomeActivity;
import com.huapintang.cashregister.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

public class ErrotDialog extends BaseDialogActivity {

    private ActivityErrotDialogBinding binding;
    private String code;
    private String msg;
    private ArrayList<PrintTicket.DataBean> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_errot_dialog);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_errot_dialog);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            code = extras.getString("code");
            msg = extras.getString("msg");
            array = extras.getParcelableArrayList("array");
        }
        if (TextUtils.isEmpty(msg)) {
            binding.tishi1.setText("请联系工作人员,检查设备，待工作人员修复完毕后，再重新点击重新打印");
        } else {
            binding.tishi1.setText(msg + ",请联系工作人员,检查设备，待工作人员修复完毕后，再重新点击重新打印");
        }


        binding.tvAgainPrinter.setOnClickListener(this);
        binding.tvCancelPrinting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent data = new Intent();
        switch (v.getId()) {
            case R.id.tv_again_printer:
                setResult(RESULT_OK, data);
                finish();
                break;
            case R.id.tv_cancel_printing:
                if(array==null||array.size()<1){
                    //先登录
                    PostFormBuilder postFormBuilder = OkHttpUtils
                            .post()
                            .url(Config.OutTicketUrl)
                            .addParams("code", code);

                    postFormBuilder.build().execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            showErrorToast();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtils.e("返回的报文", response);
                            dismissDialog();
                            OutTicket outTicket = JSON.parseObject(response, new TypeReference<OutTicket>() {
                            });
                            if (outTicket.isStatus()) {
                                Intent intent = new Intent();
                                intent.setClass(MyApplication.getContext(), MainHomeActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(ErrotDialog.this, outTicket.getMsg() , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    for (int i = 0; i <array.size() ; i++) {
                        PostFormBuilder postFormBuilder = OkHttpUtils
                                .post()
                                .url(Config.OutTicketUrl)
                                .addParams("code", array.get(i).getCode());

                        postFormBuilder.build().execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                showErrorToast();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtils.e("返回的报文", response);
                                dismissDialog();
                                OutTicket outTicket = JSON.parseObject(response, new TypeReference<OutTicket>() {
                                });
                                if (outTicket.isStatus()) {

                                } else {
                                    Toast.makeText(ErrotDialog.this, outTicket.getMsg() , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    Intent intent = new Intent();
                    intent.setClass(MyApplication.getContext(), MainHomeActivity.class);
                    startActivity(intent);
                    finish();



                }



                break;


        }
        super.onClick(v);
    }
}
