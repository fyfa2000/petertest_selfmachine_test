package com.huapintang.cashregister.fragment.area.presenter;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.area.model.Area;
import com.huapintang.cashregister.fragment.area.view.AreaView;
import com.huapintang.cashregister.fragment.seat.view.SeatFragment;
import com.huapintang.cashregister.fragment.sellTicket.model.SellTicket;
import com.huapintang.cashregister.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by cbp on 3/15 0015.
 */
public class AreaPresenter {

    private AreaView view;
    private MoveBean.DataBean dataBean;
    private Area area;
    private SellTicket sellTicket;

    public AreaPresenter(AreaView view) {
        this.view = view;
    }

    public AreaPresenter(AreaView view, MoveBean.DataBean dataBean) {
        this.view = view;
        this.dataBean = dataBean;
    }

    public void netArea() {

        view.showloadDialog();
        OkHttpUtils
                .post()
                .url(Config.GetAreaUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("film_id", dataBean.getId())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                view.dissloadDialog();
                view.setNetErrer();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.e("区域信息：", response);
                view.dissloadDialog();
                area = JSON.parseObject(response, new TypeReference<Area>() {
                });
                if (!area.isStatus()) {
                    if (area.getData() != null) {
//                        gotoReplace("");
                        //直接跳转到选座
                        SeatFragment seatFragment = new SeatFragment();
                        Bundle args = new Bundle();
                        args.putString("SelectDate", sellTicket.getDate());
                        args.putString("SelectTime", sellTicket.getTime());
                        args.putParcelable("MoveBean", dataBean);
                        seatFragment.setArguments(args);
                        view.goToSeat(seatFragment);
                    }
                    return;
                } else {
                    ArrayList<String> imgList = new ArrayList<>();
                    for (int i = 0; i < area.getData().size(); i++) {
                        imgList.add(area.getData().get(i).getLogo());
                    }
                    view.setAreaView(imgList);

                }
//                view.set

            }
        });

    }

    public void gotoSeat(String str) {
        List<Area.DataBean> data = area.getData();
        String areaId ="";
        for (int i = 0; i < data.size(); i++) {
            Area.DataBean dataBean = data.get(i);
            if(
            dataBean.getTitle().equals(str)){
                areaId=dataBean.getId();
            }
        }


        //去座位界面
        SeatFragment seatFragment = new SeatFragment();
        Bundle args = new Bundle();
        args.putString("SelectDate", sellTicket.getDate());
        args.putString("SelectTime", sellTicket.getTime());
        args.putParcelable("MoveBean", dataBean);
        args.putString("AreaId", areaId);
        seatFragment.setArguments(args);
        view.goToSeat(seatFragment);
    }

    public void initDat(Bundle arguments) {
        dataBean = arguments.getParcelable("MoveBean");
        sellTicket=   arguments.getParcelable("myTicket");

    }

        public void initMove() {
            view.showloadDialog();
            //初始化商品数据
            OkHttpUtils
                    .post()
                    .url(Config.HomePageUrl)
                    .addParams("ukey", Config.loginResult.getData().getUkey())
                    .addParams("type", "2")
                    .addParams("page", "1")
                    .addParams("num", "100")
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            view.setNetErrer();
                            view.dissloadDialog();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            view.dissloadDialog();
                            LogUtils.e("电影列表信息的json", response);
                            MoveBean moveBean = JSON.parseObject(response, new TypeReference<MoveBean>() {
                            });
                            if (!moveBean.isStatus()) {
                                Toast.makeText(MyApplication.getContext(), "暂无商品，请在后台上传商品", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            view.goToSellFragment(moveBean);
                        }
                    });

    }
}
