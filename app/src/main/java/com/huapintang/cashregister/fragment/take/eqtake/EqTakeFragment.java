package com.huapintang.cashregister.fragment.take.eqtake;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.bean.PrintTicket;
import com.huapintang.cashregister.databinding.FragmentEqTakeBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.view.HomeFragment;
import com.huapintang.cashregister.fragment.take.codetake.CodeTakeFragment;
import com.huapintang.cashregister.fragment.take.order.OrderFragment;
import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.widget.ScanEditText;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class EqTakeFragment extends BaseFragment {

    private FragmentEqTakeBinding binding;
    boolean devicestate = false;


    public EqTakeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_eq_take,container,false);
        binding.llCodeTakeTicket.setOnClickListener(this);
        binding.etEqcode.setInputType(InputType.TYPE_NULL);
        binding.etEqcode.requestFocus();
        binding.btnBack.setOnClickListener(this);
        binding.etEqcode.setOnKeyEnterClickListener(new ScanEditText.OnKeyEnterClickListener() {
            @Override
            public void onClick(String text) {
                if (!TextUtils.isEmpty(text)) {
                    binding.llTestMsg.setVisibility(View.VISIBLE);
                    LogUtils.e("获取到的二维码信息：" + text);
                    String trim = text.trim();
                    showDialog();
                    OkHttpUtils
                            .post()
                            .url(Config.PrintUrl)
                            .addParams("code", trim)
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    setNetErrer();
                                    dismissDialog();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    dismissDialog();
                                    PrintTicket printTicket
                                            = JSON.parseObject(response, new TypeReference<PrintTicket>() {
                                    });
                                    if (printTicket.isStatus()) {
//                                        Intent intent = new Intent();
                                        OrderFragment orderFragment = new OrderFragment();
                                        Bundle extras = new Bundle();
                                        extras.putParcelableArrayList("PrintTicket", (ArrayList< PrintTicket.DataBean>) printTicket.getData());
                                        orderFragment.setArguments(extras);
                                        replaceFragment(orderFragment);
//                                        intent.setClass(MyApplication.getContext(), OrderActivity.class);
//                                        startActivity(intent);
                                    } else {
                                        dismissDialog();
                                        showPrompt(printTicket.getMsg());
//                                        Toast.makeText(EQCodeActivity.this, printTicket.getMsg(), Toast.LENGTH_SHORT).show();
//                                        Intent intent=new Intent();
//                                        Bundle extras = new Bundle();
//                                        extras.putString ("Style","eqcode");
//                                        extras.putString("eqMsg",printTicket.getMsg());
//                                        intent.putExtras(extras);
//                                        intent.setClass(MyApplication.getContext(), MsgDialogActivity.class);
//                                        startActivityForResult(intent,IntentCode);
                                    }
                                }
                            });
                    binding.etEqcode.setText("");
                    binding.llTestMsg.setVisibility(View.INVISIBLE);
                }
            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
    private final int IntentCode=1525;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==IntentCode){
                binding.llTestMsg.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_code_take_ticket:
                replaceFragment(         new CodeTakeFragment());
//                Intent intent = new Intent();
//                intent.setClass(EQCodeActivity.this, CodeTakeActivity.class);
//                startActivity(intent);
//                finish();
                break;
            case R.id.btn_back:
                replaceFragment(new HomeFragment());
                break;

        }
        super.onClick(v);
    }

}
