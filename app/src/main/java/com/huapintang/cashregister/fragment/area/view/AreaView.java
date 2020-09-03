package com.huapintang.cashregister.fragment.area.view;

import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.BaseFragmetView;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;

import java.util.List;

/**
 * Created by cbp on 3/15 0015.
 */
public interface AreaView  extends BaseFragmetView {

    void setAreaView(List<String > imgList);
    void goToSeat(BaseFragment fragment);

    void goToSellFragment(MoveBean moveBean);
}
