package com.huapintang.cashregister.fragment.Home.view;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.FragmentHomeBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.Home.presenter.HomePresenter;
import com.huapintang.cashregister.fragment.sellTicket.view.SellTicketFragment;
import com.huapintang.cashregister.fragment.take.eqtake.EqTakeFragment;
import com.huapintang.cashregister.ui.login.view.LoginSellActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements HomeView {


    private FragmentHomeBinding dataBinding;
    private HomePresenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        setTime();
        dataBinding.llCollectingTickets.setOnClickListener(this);
        dataBinding.llTicket.setOnClickListener(this);
        dataBinding.ivLogo.setOnClickListener(this);
        presenter = new HomePresenter(this);
        setTimeThread();
        return dataBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_logo:
//                LoginSellActivity loginSellActivity = new LoginSellActivity();
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                extras.putBoolean("isExit",true);
                intent.putExtras(extras);
                intent.setClass(MyApplication.getContext(),LoginSellActivity.class);
                intentTo(intent);
                getActivity().finish();
                break;
            case R.id.ll_ticket:
//                startActivity(new Intent(this,));
                presenter.initMove();

                break;
            case R.id.ll_collecting_tickets:
                replaceFragment(new EqTakeFragment());
//                replaceFragment(new TakeFragment());
                break;
        }
        super.onClick(v);
    }

    @Override
    public void goToSellFragment(  MoveBean moveBean) {
        FragmentTransaction transaction =getFragmentManager().beginTransaction();
        SellTicketFragment fragment = new SellTicketFragment();
        Bundle args = new Bundle();
        args.putParcelable("moveBean",moveBean);
        fragment.setArguments(args);
        transaction.replace(R.id.ll_main, fragment);
        transaction.commit();
    }
    String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (  msg.what){
                case 1:

                    setTime();
                    break;

            }

            super.handleMessage(msg);
        }

    };

    private void setTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");//设置日期格式
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        Date date = new Date();
        String format = df.format(date);// new Date()为获取当前系统时间
        String format2 = df2.format(date);// new Date()为获取当前系统时间
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        int weekday=c.get(Calendar.DAY_OF_WEEK);


        dataBinding.tvDate.setText(format  + " "+weekDays[weekday-1]);

        dataBinding.tvTime.setText(format2);
    }

    private Thread thread;
    private void setTimeThread(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (1<2){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.what=1;
                    handler.sendMessage(msg);
                }


            }
        });
        thread.start();


    }

    @Override
    public void showMenu() {
        //显示虚拟按键
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            //低版本sdk
            View v = getActivity().getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getActivity().getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
