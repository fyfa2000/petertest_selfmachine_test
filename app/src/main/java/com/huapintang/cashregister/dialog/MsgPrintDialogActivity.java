package com.huapintang.cashregister.dialog;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.bean.MsgDialogBean;
import com.huapintang.cashregister.databinding.ActivityMsgPrintDialogBinding;
import com.huapintang.cashregister.utils.NormalUtils;

public class MsgPrintDialogActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_msg_print_dialog);
//    }
    private ActivityMsgPrintDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_select_date_dialog);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_msg_print_dialog);
        MsgDialogBean msgdialog = new MsgDialogBean();
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String string = extras.getString("Style");
            if(string.equals("eqcode")){
                //打印出錯的時候返回
                String eqMsg = extras.getString("eqMsg");
                msgdialog.setMsg1(eqMsg);
                msgdialog.setMsg2("");
                binding.tvMsg2.setVisibility(View.GONE);
                msgdialog.setBtnMsg("确认");
            }
            if(string.equals("prining")){
                String msg = extras.getString("msg");

                msgdialog.setMsg1(NormalUtils.getString(R.string.check_msg));
                msgdialog.setMsg2(msg+NormalUtils.getString(R.string.check_reprint));
                msgdialog.setBtnMsg(NormalUtils.getString(R.string.reprint_msg));
            }

        }


        binding.setMsgdialog(msgdialog);
        binding.tvAgainPrinter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                setResult(RESULT_OK, data);
                finish();
            }
        });





    }
}
