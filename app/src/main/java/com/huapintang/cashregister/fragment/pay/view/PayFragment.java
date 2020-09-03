package com.huapintang.cashregister.fragment.pay.view;


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
import com.huapintang.cashregister.databinding.FragmentPayBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.pay.presenter.PayPresenter;
import com.huapintang.cashregister.fragment.sellTicket.view.SellTicketFragment;
import com.huapintang.cashregister.utils.RecycleViewManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayFragment extends BaseFragment implements PayView {


    private FragmentPayBinding dataBinding;
    private PayPresenter presenter;

    public PayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay, container, false);
        presenter = new PayPresenter(this);
        presenter.initData(getArguments());
//        dataBinding.setPay();

        dataBinding.lvSelectTicket.setLayoutManager(RecycleViewManager.getListManager());
        dataBinding.llAli.setOnClickListener(this);
        dataBinding.llWeixin.setOnClickListener(this);
        dataBinding.llCard.setOnClickListener(this);
        dataBinding.btnBack.setOnClickListener(this);
        return dataBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_ali:
                presenter.aliPay();

                break;
            case R.id.ll_weixin:
                presenter.weixinPay();

                break;
            case R.id.ll_card:
                presenter.cardPay();

                break;
            case R.id.btn_back:
                presenter.initMove();
                break;

        }
        super.onClick(v);
    }

    @Override
    public void goToSellFragment(MoveBean moveBean) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        SellTicketFragment fragment = new SellTicketFragment();
        Bundle args = new Bundle();
        args.putParcelable("moveBean", moveBean);
        fragment.setArguments(args);
        replaceFragment(fragment);
//        transaction.replace(R.id.ll_main, fragment);
//        transaction.commit();
    }

    @Override
    public void setLvTicket(BaseRecycleViewAdapter adapter) {
        dataBinding.lvSelectTicket.setAdapter(adapter);
    }

    @Override
    public void setTotal(String string) {
        dataBinding.setPay(string);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PayPresenter.ScanAliRequstCode) {
                presenter.resultPay(requestCode,data);

            }
            if(requestCode==PayPresenter.ScanWeixinRequstCode){
                presenter.resultPay(requestCode,data);
            }
            if(requestCode==PayPresenter.CardRequstCode){
                presenter.resultPay(requestCode,data);
            }
            if(requestCode==PayPresenter.CardPayRequstCode){
                presenter.printTicket(data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
