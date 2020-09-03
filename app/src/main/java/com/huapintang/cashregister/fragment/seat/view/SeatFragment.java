package com.huapintang.cashregister.fragment.seat.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.databinding.FragmentSeatBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.seat.presenter.SeatPresenter;
import com.huapintang.cashregister.fragment.sellTicket.view.SellTicketFragment;
import com.huapintang.cashregister.utils.RecycleViewManager;
import com.huapintang.cashregister.widget.SeatTable;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeatFragment extends BaseFragment implements SeatView {


    private FragmentSeatBinding dataBinding;
    private SeatPresenter presenter;


    public SeatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_seat, container, false);
//        args.putParcelable("MoveBean",dataBean);
//        args.putString("AreaId",id);
        Bundle arguments = getArguments();
        presenter=new SeatPresenter(this);
        if(arguments!=null){
            presenter.initArg(arguments);
        }

        dataBinding.btnBack.setOnClickListener(this);
        dataBinding.btnNext.setOnClickListener(this);
        // Inflate the layout for this fragment
        return dataBinding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
            presenter.initMove();
                break;
            case R.id.btn_next:
                presenter.gotoPay();

                break;
        }
        super.onClick(v);
    }

    @Override
    public void setSeatAdapter(BaseRecycleViewAdapter adapter) {
        dataBinding.lvSeat.setLayoutManager(RecycleViewManager.getListManager());
        dataBinding.lvSeat.setAdapter(adapter);
    }

    @Override
    public void setSeatTableData(int row, int column, ArrayList<String> colorStringList, List<String> priceList) {
        dataBinding.seatTable.setData(row, column, colorStringList, priceList);
    }

    @Override
    public void setSeatChecker(SeatTable.SeatChecker checker) {
        dataBinding.seatTable.setSeatChecker(checker);
        dataBinding.seatTable.setScreenName("舞台中央");
    }



    @Override
    public void setLineNumbers(ArrayList<String> side) {
        dataBinding.seatTable.setLineNumbers(side);
    }

    @Override
    public void seatRemoveItem(int row, int column) {
        dataBinding.seatTable.removeItem(row,column);
    }

    @Override
    public void goToSellFragment(MoveBean moveBean) {
        SellTicketFragment fragment = new SellTicketFragment();
        Bundle args = new Bundle();
        args.putParcelable("moveBean", moveBean);
        fragment.setArguments(args);
        replaceFragment(fragment);
    }
}
