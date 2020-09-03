package com.huapintang.cashregister.dialog;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.ActivityScanDialogBinding;
import com.huapintang.cashregister.ui.BaseActivity;
import com.huapintang.cashregister.utils.ScanEditText;
import com.huapintang.cashregister.utils.ScanGunKeyEventHelper;

public class ScanDialog extends BaseActivity implements ScanGunKeyEventHelper.OnScanSuccessListener {

    private ActivityScanDialogBinding  binding;
    private ScanGunKeyEventHelper eventHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_dialog);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan_dialog);
            binding.btnBack.setOnClickListener(this);
//        watcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!TextUtils.isEmpty(s)) {
//                    dataBinding.tvCancel.setVisibility(View.VISIBLE);
//                } else {
//                    dataBinding.tvCancel.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        };
        setEtCodeListener(true);
//        binding.etCode.addTextChangedListener(watcher);

        eventHelper = new ScanGunKeyEventHelper(this);



    }
    private void setEtCodeListener(boolean ss) {
        if (ss) {
            binding.etCode.setOnKeyEnterClickListener(new ScanEditText.OnKeyEnterClickListener() {
                @Override
                public void onClick(String text) {
                    getcode(text.trim());
                }
            });
        } else {
            binding.etCode.setOnKeyEnterClickListener(null);

        }

    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (eventHelper.isScanGunEvent(event)) {
//            Log.e("进来了", "名称正确");
            eventHelper.analysisKeyEvent(event);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
    @Override
    public void onScanSuccess(String barcode) {
//        Log.e("扫描枪扫描内容",barcode);
        getcode(barcode);
    }

    public void getcode(String barcode){

        Intent intent=new Intent();
        Bundle extras = new Bundle();
        extras.putString("scan",barcode);
        intent.putExtras(extras)
        ;

        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                    finish();
                break;
        }
        super.onClick(v);
    }
}
