package com.huapintang.cashregister.fragment.printover.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.FragmentPrintOverBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.view.HomeFragment;

/**

 */
public class PrintOverFragment extends BaseFragment {



    private FragmentPrintOverBinding binding;
    private Handler handler=new Handler() ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

   binding= DataBindingUtil.inflate(inflater, R.layout.fragment_print_over,container,false);

        //8秒后关闭页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent=new Intent();
//                    intent.setClass(m,HomeActivity.class);
//                startActivity(intent);
                replaceFragment(new HomeFragment());
            }
        },8000);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }



}
