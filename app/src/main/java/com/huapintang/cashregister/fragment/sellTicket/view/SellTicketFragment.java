package com.huapintang.cashregister.fragment.sellTicket.view;


import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.databinding.FragmentSellTicketBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.view.HomeFragment;
import com.huapintang.cashregister.fragment.seat.view.SeatFragment;
import com.huapintang.cashregister.fragment.sellTicket.model.SellTicket;
import com.huapintang.cashregister.fragment.sellTicket.presenter.SellTicketPresenter;
import com.huapintang.cashregister.utils.RecycleViewManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellTicketFragment extends BaseFragment implements SellTicketView {


    private FragmentSellTicketBinding dataBinding;
    private SellTicket sellTicket;
    private SellTicketPresenter presenter;

    public SellTicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sell_ticket, container, false);
        presenter = new SellTicketPresenter(this);
        reset();
        sellTicket = new SellTicket();
        //布局bean，
        presenter.initView(sellTicket, getArguments());
        dataBinding.setSellTicket(sellTicket);
        dataBinding.tvDate.setOnClickListener(this);
        dataBinding.tvTime.setOnClickListener(this);
        dataBinding.btnBack.setOnClickListener(this);
        dataBinding.btnNext.setOnClickListener(this);
        dataBinding.btnAdd.setOnClickListener(this);
        dataBinding.btnSub.setOnClickListener(this);

//        sellTicket.setTime("点击选择时间段");
//        sellTicket.setDate("点击选择购票日期");
//        sellTicket.setNum(0);
        return dataBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_date:
                //* 选择日期的监听
                presenter.dateListener();
                break;
            case R.id.tv_time:
                presenter.timeListener();
                break;
            case R.id.btn_add:
                presenter.addTicket();
                break;
            case R.id.btn_sub:
                presenter.subTicket();
                break;
            case R.id.btn_back:
                HomeFragment homeFragment = new HomeFragment();
                replaceFragment(homeFragment);
                break;
            case R.id.btn_next:
                presenter.next();
                break;
        }
        super.onClick(v);
    }

    @Override
    public void setMoveList(BaseRecycleViewAdapter adapter) {
        dataBinding.lvMove.setLayoutManager(RecycleViewManager.getGridManager(5));
        dataBinding.lvMove.setAdapter(adapter);
    }

    @Override
    public void setSellTicketList(BaseRecycleViewAdapter adapter) {
        dataBinding.lvSellTicket.setLayoutManager(RecycleViewManager.getListManager());
        dataBinding.lvSellTicket.setAdapter(adapter);
    }

//    @Override
//    public void setTimeList(BaseRecycleViewAdapter adapter) {
//        dataBinding.lvTimeList.setLayoutManager(RecycleViewManager.getNoScroll());
//        dataBinding.lvTimeList.setAdapter(adapter);
//    }


    @Override
    public void intentGo(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void intnetGo(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    private boolean isSeat;




    @Override
    public void isSeat(boolean isSeat) {
        this.isSeat = isSeat;
        if (isSeat) {
            dataBinding.llNum.setVisibility(View.INVISIBLE);
            dataBinding.tvTisi.setVisibility(View.INVISIBLE);
        } else {
            dataBinding.llNum.setVisibility(View.VISIBLE);
            dataBinding.tvTisi.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 103) {
                presenter.dateResult(data);
            } else if (requestCode == 104) {
                presenter.timeResult(data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void reset(){
        dataBinding.tvTime.setText("点击选择时间段");
        dataBinding.tvDate.setText("点击选择购票日期");
    }

    @Override
    public void isThrough(boolean b) {
        if(b){
            dataBinding.tvDate.setVisibility(View.GONE);
            dataBinding.tvDate1.setVisibility(View.GONE);
            dataBinding.tvTime.setVisibility(View.GONE);
            dataBinding.tvTime1.setVisibility(View.GONE);
        }else{
            dataBinding.tvDate.setVisibility(View.VISIBLE);
            dataBinding.tvDate1.setVisibility(View.VISIBLE);
            dataBinding.tvTime.setVisibility(View.VISIBLE);
            dataBinding.tvTime1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goToSeat(SeatFragment seatFragment) {
        replaceFragment(seatFragment);
    }
}
