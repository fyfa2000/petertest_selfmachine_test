package com.huapintang.cashregister.dialog.selectTime.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.huapintang.cashregister.BR;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.databinding.ActivitySelectTimeDialogBinding;
import com.huapintang.cashregister.dialog.BaseDialogActivity;
import com.huapintang.cashregister.fragment.sellTicket.model.SelectString;
import com.huapintang.cashregister.utils.RecycleViewManager;

import java.util.ArrayList;

public class SelectTimeDialog extends BaseDialogActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_select_time_dialog);
//    }

    private ActivitySelectTimeDialogBinding binding;
    private BaseRecycleViewAdapter adapter;
    private   ArrayList<SelectString> lists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_district_dialog);
        final Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            showToast("参数传递错误");
            return;
        } else {
            binding = DataBindingUtil.setContentView(this, R.layout.activity_select_time_dialog);
            lists = ((ArrayList<SelectString>) extras.getSerializable("list"));
            adapter = new BaseRecycleViewAdapter(lists, R.layout.item_string, BR.str);
            binding.lvSelectDistrict.setAdapter(adapter);
            binding.lvSelectDistrict.setLayoutManager(RecycleViewManager.getListManager());
            adapter.setListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(ViewDataBinding dataBinding, int position) {
                    String s = lists.get(position).getStr();
                    Intent data = new Intent();
                    Bundle extras1 = new Bundle();
                    extras1.putString("selectTime", s);
                    data.putExtras(extras1);
                    setResult(RESULT_OK, data);
                    finish();
                }

                @Override
                public void onLongItemClick(ViewDataBinding dataBinding, int position) {

                }
            });
        }
    }


}
