package com.huapintang.cashregister.dialog.huiyuanpay.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.ActivityHuiYuanPayBinding;
import com.huapintang.cashregister.dialog.BaseDialogActivity;
import com.huapintang.cashregister.dialog.huiyuan.model.HuiYuan;
import com.huapintang.cashregister.fragment.pay.model.PayResult;
import com.huapintang.cashregister.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class HuiYuanPayActivity extends BaseDialogActivity {

    private ActivityHuiYuanPayBinding binding;
    private String payState;
    private String cardNo;
    private String orderNo;
    private String price;
    private String times;
    private HuiYuan.DataBean huiyanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hui_yuan_pay);
        binding.tvSure.setOnClickListener(this);
        binding.llCardTimes.setOnClickListener(this);
        binding.llCardYue.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
        } else {
            huiyanData = extras.getParcelable("huiyanData");
            orderNo = extras.getString("orderNo");
            cardNo = huiyanData.getCard_no();
            price = extras.getString("price");
            times = extras.getString("times");
            binding.setHuiuanPay(huiyanData);
//            tv_deductions_msg
//            String money = huiyanData.getMoney();
//            String card_no = huiyanData.getCard_no();
//            String times = huiyanData.getTimes();
        }
        binding.tvDeductionsMsg.setVisibility(View.GONE);
//        setContentView(R.layout.activity_hui_yuan_pay);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_card_times:
                huiyanData.setIsSelectTimes(true);
                huiyanData.setIsSelectYue(false);
                payState = "card";
                if (!TextUtils.isEmpty(times)) {
                    binding.tvDeductionsMsg.setText(times + "次");

                }
                binding.llMsg.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_card_yue:
                binding.llMsg.setVisibility(View.VISIBLE);

                huiyanData.setIsSelectTimes(false);
                huiyanData.setIsSelectYue(true);
                payState = "card_yue";
                if (!TextUtils.isEmpty(price)) {
                    binding.tvDeductionsMsg.setText(price+"元");
                    binding.tvDeductionsMsg.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.tv_sure:
                if (TextUtils.isEmpty(payState)) {
                    showToast("请先选择扣减方式");
                    return;
                }
                showDialog();
                PostFormBuilder postFormBuilder = OkHttpUtils
                        .post()
                        .url(Config.OrderPayUrl)
                        .addParams("ukey", Config.loginResult.getData().getUkey())
                        .addParams("order_no", orderNo);
//                        .addParams("pay_type", "yue");

                if (!TextUtils.isEmpty(cardNo)) {
                    postFormBuilder
                            .addParams("code", cardNo);
                    if (!TextUtils.isEmpty(payState)) {
                        postFormBuilder.addParams("pay_type", payState);
                    }
                }
                postFormBuilder
                        .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showErrorToast();
                        dismissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("支付：", response);
                        PayResult payResult = JSON.parseObject(response, new TypeReference<PayResult>() {
                        });
                        if (!payResult.isStatus()) {
                            showToast(payResult.getMsg());
                            return;
                        } else {
//                            showToast("成功扣减");
                            //跳转到打印页面
                            Intent data = new Intent();
                            Bundle extras = new Bundle();
                            extras.putString("orderNo", orderNo);
                            data.putExtras(extras);
                            setResult(RESULT_OK, data);
                            finish();
                        }
                    }
                });


                break;

        }
        super.onClick(v);
    }
}
