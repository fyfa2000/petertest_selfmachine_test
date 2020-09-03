package com.huapintang.cashregister.fragment.area.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.FragmentAreaBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.area.presenter.AreaPresenter;
import com.huapintang.cashregister.fragment.sellTicket.view.SellTicketFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AreaFragment extends BaseFragment implements AreaView {


    private FragmentAreaBinding binding;
    private AreaPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_area, container, false);
        // Inflate the layout for this fragment
//        View inflate = inflater.inflate(R.layout.fragment_area, container, false);
        binding.iv1.setOnClickListener(this);
//        args.putParcelable("MoveBean",selectMove);
        Bundle arguments = getArguments();
        presenter = new AreaPresenter(this);
        if (arguments != null) {
            presenter.initDat(arguments);
        }

        presenter.netArea();
        binding.iv1.setOnClickListener(this);
        binding.iv2.setOnClickListener(this);
        binding.iv3.setOnClickListener(this);
        binding.iv4.setOnClickListener(this);
        binding.iv5.setOnClickListener(this);
        binding.iv6.setOnClickListener(this);
        binding.iv7.setOnClickListener(this);
        binding.btnBack.setOnClickListener(this);

        return binding.getRoot();
    }


    @Override
    public void setAreaView(List<String> imgList) {

    }

    @Override
    public void goToSeat(BaseFragment fragment) {
        replaceFragment(fragment);
    }

    @Override
    public void goToSellFragment(MoveBean moveBean) {
        SellTicketFragment fragment = new SellTicketFragment();
        Bundle args = new Bundle();
        args.putParcelable("moveBean", moveBean);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                    presenter.initMove();
                break;
            case R.id.iv1:
                presenter.gotoSeat("A");
                break;
            case R.id.iv2:
                presenter.gotoSeat("B");
                break;
            case R.id.iv3:
                presenter.gotoSeat("C");
                break;
            case R.id.iv4:
                presenter.gotoSeat("E");
                break;
            case R.id.iv5:
                presenter.gotoSeat("F");
                break;
            case R.id.iv6:
                presenter.gotoSeat("G");
                break;
            case R.id.iv7:
                presenter.gotoSeat("F");
                break;
        }
        super.onClick(v);
    }
}
