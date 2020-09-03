package com.huapintang.cashregister.fragment.seat.view;

import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.BaseFragmetView;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.widget.SeatTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbp on 3/18 0018.
 */
public interface SeatView extends BaseFragmetView {

    void setSeatAdapter(BaseRecycleViewAdapter adapter);

    void setSeatTableData(int row, int column, ArrayList<String> colorStringList, List<String> priceList);

    void setSeatChecker(SeatTable.SeatChecker checker);

    void setLineNumbers(ArrayList<String> side);

    void seatRemoveItem(int row ,int column);

    void goToSellFragment(MoveBean moveBean);

    void replaceFragment(BaseFragment fragment);
}
