package com.huapintang.cashregister.fragment.pay.view;

import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.fragment.BaseFragmetView;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;

/**
 * Created by cbp on 3/12 0012.
 */
public interface PayView extends BaseFragmetView {
    void goToSellFragment(MoveBean moveBean);

    void setLvTicket(BaseRecycleViewAdapter adapter);

    void setTotal(String string);
}
