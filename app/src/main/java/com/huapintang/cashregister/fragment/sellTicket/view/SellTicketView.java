package com.huapintang.cashregister.fragment.sellTicket.view;

import android.content.Intent;

import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.fragment.BaseFragmetView;
import com.huapintang.cashregister.fragment.seat.view.SeatFragment;

/**
 * Created by cbp on 3/8 0008.
 */
public interface SellTicketView extends BaseFragmetView {
    void setMoveList(BaseRecycleViewAdapter adapter);
    void setSellTicketList(BaseRecycleViewAdapter adapter);
//    void setTimeList(BaseRecycleViewAdapter adapter);
    void intentGo(Intent intent);
    void intnetGo(Intent intent ,int requestCode);

    void isSeat(boolean isSeat);

    void reset();

    void isThrough(boolean b);

    void goToSeat(SeatFragment seatFragment);
}
