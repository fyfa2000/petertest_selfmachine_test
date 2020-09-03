package com.huapintang.cashregister.dialog;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.ActivityMsgDialogBinding;

public class MsgDialog extends BaseDialogActivity {

    private String string;
    private ActivityMsgDialogBinding binding;
        private static MsgDialog dialog;

    public static Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    voer();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private static void voer(){
        dialog.finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dialog=this;
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            string = extras.getString("str");
        }
        if (TextUtils.isEmpty(string)) {
            string = "未知错误，请联系工作人员";
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_msg_dialog);
        binding.setMsg(string);
//        setContentView(R.layout.activity_msg_dialog);
    }
}
