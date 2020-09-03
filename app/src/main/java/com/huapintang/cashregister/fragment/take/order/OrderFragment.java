package com.huapintang.cashregister.fragment.take.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.bean.PrintTicket;
import com.huapintang.cashregister.databinding.FragmentOrderBinding;
import com.huapintang.cashregister.fragment.BaseFragment;

import java.util.ArrayList;


public class OrderFragment extends BaseFragment {

    private FragmentOrderBinding binding;
    private ArrayList<PrintTicket.DataBean> printData;
    private PrintTicket.DataBean dataBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order ,container, false);
        binding.tvPrint.setOnClickListener(this);
        Bundle extras = getArguments();
        if (extras == null) {
            showToast("获取票据信息失败");
//            Toast.makeText(OrderActivity.this, "获取票据信息失败", Toast.LENGTH_SHORT).show();
//            finish();
//            return;
        }
        printData = extras.getParcelableArrayList("PrintTicket");
        dataBean = printData.get(0);
        binding.setTicketData(dataBean);
        binding.tvReturn.setOnClickListener(this);
        String seat = dataBean.getSeat();//座位
        String pname = dataBean.getPname();//区域
        if (TextUtils.isEmpty(pname) && TextUtils.isEmpty(seat)) {
            binding.tvSeat.setVisibility(View.GONE);
        } else if (!TextUtils.isEmpty(pname)&&!TextUtils.isEmpty(seat)) {
            binding.tvSeat.setText("区域："+pname + "   座位：" + seat);
        } else if (!TextUtils.isEmpty(pname)) {
            binding.tvSeat.setText("区域："+pname);
        } else if (!TextUtils.isEmpty(seat)) {
            binding.tvSeat.setText("座位："+seat);
        }
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_print:
                PrintFragment printFragment = new PrintFragment();
                Bundle extras = new Bundle();
                extras.putParcelable("ticket",dataBean);
                printFragment.setArguments(extras);
                replaceFragment(printFragment);
//                Intent intent = new Intent();

//                intent.putExtras(extras);
//                intent.setClass(this, PriningActivity.class);
//                startActivity(intent);
                break;

            case R.id.tv_return:
//                finish();
                break;


        }
    }

}
