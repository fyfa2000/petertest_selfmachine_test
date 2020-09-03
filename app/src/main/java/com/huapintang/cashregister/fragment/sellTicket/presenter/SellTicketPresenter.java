package com.huapintang.cashregister.fragment.sellTicket.presenter;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.BR;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.dialog.SelectDialog.view.CalendarActivity;
import com.huapintang.cashregister.dialog.selectTime.view.SelectTimeDialog;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.area.model.Area;
import com.huapintang.cashregister.fragment.area.view.AreaFragment;
import com.huapintang.cashregister.fragment.pay.view.PayFragment;
import com.huapintang.cashregister.fragment.seat.view.SeatFragment;
import com.huapintang.cashregister.fragment.sellTicket.model.EventTimeResult;
import com.huapintang.cashregister.fragment.sellTicket.model.GetTicketResult;
import com.huapintang.cashregister.fragment.sellTicket.model.SelectString;
import com.huapintang.cashregister.fragment.sellTicket.model.SellTicket;
import com.huapintang.cashregister.fragment.sellTicket.view.SellTicketView;
import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.utils.MyDateUtils;
import com.huapintang.cashregister.utils.StringCheck;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by cbp on 3/8 0008.
 */
public class SellTicketPresenter {

    private SellTicketView view;

    private SellTicket sellTicket;

    private List<MoveBean.DataBean> data;
    private BaseRecycleViewAdapter adapter;
    private BaseRecycleViewAdapter.OnItemClickListener listener;
    private BaseRecycleViewAdapter.OnItemClickListener sellTicketListener;
    //选中的电影
    private MoveBean.DataBean selectMove;
    //选中票样
    private GetTicketResult.DataBean selectTicket;

    public SellTicketPresenter(SellTicketView view) {
        this.view = view;
    }

    /**
     * 选择电影的监听
     */
    private void initListener() {
        listener = new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewDataBinding dataBinding, int position) {
                selectMove(position);
            }

            @Override
            public void onLongItemClick(ViewDataBinding dataBinding, int position) {
            }
        };
    }

    /**
     * 选择电影
     *
     * @param position
     */
    private void selectMove(int position) {
        reset();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIsSelection(false);
        }
        data.get(position).setIsSelection(true);
        //点击选中
        MoveBean.DataBean dataBean = data.get(position);
        String title = dataBean.getTitle();
        sellTicket.setTitile(title);
        selectMove = dataBean;

        if (selectMove.getIs_seat() == 1) {
            //选座
            view.isSeat(true);
            isSeat = true;
        } else {
            view.isSeat(false);
            isSeat = false;
            netToGetTick(dataBean);
        }
        if(selectMove.getIs_through().equals("1")){
            view.isThrough(true);
            isThrough=true;
        }else{
            view.isThrough(false);
            isThrough=false;
        }
        sellTicket.setDate(MyDateUtils.getToday());
        netGetTime(selectMove.getId(),MyDateUtils.getToday());
    }



        private boolean isThrough;

     private void reset() {
        //清空時間和票种/数量
        selectTicket=new GetTicketResult.DataBean();
        sellTicket.setNum(0);
        view.reset();
//        sellTicket.setTime("点击选择时间段");
//        sellTicket.setDate("点击选择购票日期");
        if(
    speciesData!=null){
            speciesData.clear();

        }
    }

    private boolean isSeat;

    /**
     * @param data
     * @param position
     */
    private void selectTicket(List<GetTicketResult.DataBean> data, int position) {

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setIsSelect(false);
        }
        data.get(position).setIsSelect(true);
        selectTicket = data.get(position);
        //设置日期
//        sellTicket.setDate(MyDateUtils.getToday());
//                                sellTicket
//        netGetTime(selectMove.getId(), MyDateUtils.getToday());
    }

    //票种 种类
    private  List<GetTicketResult.DataBean> speciesData;


    /**
     * 点击选中电影后获取日期时间
     *
     * @param dataBean
     */
    public void netToGetTick(final MoveBean.DataBean dataBean) {
        OkHttpUtils
                .post()
                .url(Config.GetTicketUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("id", dataBean.getId())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        view.dissloadDialog();
                        view.setNetErrer();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("选中电影之后", response.toString());
                        view.dissloadDialog();
                        GetTicketResult getTicketResult = JSON.parseObject(response, new TypeReference<GetTicketResult>() {
                        });
                        if (!getTicketResult.isStatus()) {
                            view.setNetErrer(getTicketResult.getMsg());
                            return;
                        }
                        speciesData = getTicketResult.getData();
                        adapter = new BaseRecycleViewAdapter(speciesData, R.layout.item_sell_ticket, BR.itemSellTicket);
                        sellTicketListener = new BaseRecycleViewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(ViewDataBinding dataBinding, int position) {
                                selectTicket(speciesData, position);
                            }

                            @Override
                            public void onLongItemClick(ViewDataBinding dataBinding, int position) {
                            }
                        };
                        adapter.setListener(sellTicketListener);
                        view.setSellTicketList(adapter);
                        if(speciesData.size()>0){
                            selectTicket(speciesData, 0);
                        }

                    }
                });
    }

    /**
     * 初始化界面
     *
     * @param sellTicket
     * @param arguments
     */
    public void initView(final SellTicket sellTicket, Bundle arguments) {
        this.sellTicket = sellTicket;
        MoveBean moveBean = arguments.getParcelable("moveBean");
        data = moveBean.getData();
        if(data!=null&&data.size()>0){
            selectMove(0);
        }
        adapter = new BaseRecycleViewAdapter(data, R.layout.item_movelist, BR.item);
        initListener();
        adapter.setListener(listener);
        view.setMoveList(adapter);
    }

    /**
     * 跳转到选择时间段
     */
    public void timeListener() {
        netGetTime();
    }

    private void intentSelectTime(ArrayList<SelectString> list) {
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putSerializable("list", list);
        extras.putString("id", selectMove.getId());
        intent.putExtras(extras);
        intent.setClass(MyApplication.getContext(), SelectTimeDialog.class);
        view.intnetGo(intent, 104);

    }

    private void netGetTime() {
        if(TextUtils.isEmpty(sellTicket.getDate())){
            view.showPrompt("请先选择日期");
            return;
        }
        OkHttpUtils
                .post()
                .url(Config.EventTimeUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("id", selectMove.getId())
                .addParams("date", sellTicket.getDate())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        view.dissloadDialog();
                        view.setNetErrer();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        EventTimeResult eventTimeResult = JSON.parseObject(response, new TypeReference<EventTimeResult>() {
                        });
                        List<EventTimeResult.DataBean> data = eventTimeResult.getData();
//                        final List<String> dataTime = new ArrayList<String>();
                        ArrayList<SelectString> list = new ArrayList<SelectString>();
                        for (EventTimeResult.DataBean bean : data
                                ) {
                            String s = bean.getB_time() + "-" + bean.getE_time();
                            SelectString object = new SelectString();
                            object.setStr(s);
                            object.setIsSelect(false);
                            list.add(object);
                        }
                        intentSelectTime(list);
                    }
                });
    }


    /**
     * 选择日期的监听
     *
     * @return
     */
    public void dateListener() {
        if(selectMove==null){
            view.showPrompt("请先选择商品");
            return;
        }
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putString("id", selectMove.getId());
        intent.putExtras(extras);
        intent.setClass(MyApplication.getContext(), CalendarActivity.class);
        view.intnetGo(intent, 103);
    }

    /**
     * 选择日期返回
     *
     * @param intent
     */
    public void dateResult(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String selectDate = extras.getString("selectDate");
            sellTicket.setDate(selectDate);
        }
    }

    public void timeResult(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String time = extras.getString("selectTime");
            sellTicket.setTime(time);
        }
    }


    public void netGetTime(String selectId, String selectDate) {
        view.showloadDialog();
        OkHttpUtils
                .post()
                .url(Config.EventTimeUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("id", selectId)
                .addParams("date", selectDate)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        view.dissloadDialog();
                        view.setNetErrer();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        view.dissloadDialog();
                        EventTimeResult eventTimeResult = JSON.parseObject(response, new TypeReference<EventTimeResult>() {
                        });
                        final List<EventTimeResult.DataBean> data = eventTimeResult.getData();
                        List<SelectString> list = new ArrayList<SelectString>();
                        for (int i = 0; i <data.size() ; i++) {

                                EventTimeResult.DataBean bean=data.get(i);
                            String s = bean.getB_time() + "-" + bean.getE_time();
                            SelectString object = new SelectString();
                            object.setStr(s);
                            object.setIsSelect(false);
                            list.add(object);
                            if(i==0){
                                sellTicket.setTime(s);
                            }
                        }
                        final BaseRecycleViewAdapter selectTimeAdapter = new BaseRecycleViewAdapter<>(list, R.layout.item_select_time, BR.timeSelect);
//                        view.setTimeList(selectTimeAdapter);
                        selectTimeAdapter.setListener(selectTimeListener(list));
                    }
                });
    }

    /**
     * 选择时间段
     *
     * @return
     */
    private BaseRecycleViewAdapter.OnItemClickListener selectTimeListener(final List<SelectString> list) {
        return new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewDataBinding dataBinding, int position) {
                SelectString selectString = list.get(position);
            }

            @Override
            public void onLongItemClick(ViewDataBinding dataBinding, int position) {
            }
        };

    }

    public void addTicket() {
        int num = sellTicket.getNum();
        sellTicket.setNum(num + 1);

    }

    public void subTicket() {
        int num = sellTicket.getNum();
        if (num < 1) {
            sellTicket.setNum(0);
        } else {
            sellTicket.setNum(num - 1);
        }
    }

    public void goPay() {
//        sellTicket.get


    }

    public void next() {
        if (selectMove == null) {
            view.showPrompt("请先选择电影");
            return;
        }
        if (!isSeat) {
            if (selectTicket == null) {
                view.showPrompt("请先选择票种");
                return;
            }
        }
        if(!isThrough){
            if (TextUtils.isEmpty(sellTicket.getDate())) {
                view.showPrompt("请先选择日期");
                return;
            }
            if (TextUtils.isEmpty(sellTicket.getTime())) {
                view.showPrompt("请先选择时间段");
                return;
            }
        }

        if (!isSeat) {
            if (sellTicket.getNum() < 1) {
                view.showPrompt("购票数量不能少于1");
                return;
            }
            PayFragment fragment = new PayFragment();
            Bundle args = new Bundle();
            args.putParcelable("myTicket", sellTicket);
            args.putParcelable("move", selectMove);
            args.putParcelable("ticket", selectTicket);
            String price = selectTicket.getPrice();
            price = price.replace("元", "");
            int num = sellTicket.getNum();
            double v = 0;
            try {
                v = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                view.showPrompt("请先选择票种");
                return;
            }
            double v1 = v * num;
            args.putString("total", StringCheck.decimalFormat(v1));
            fragment.setArguments(args);
            view.replaceFragment(fragment);
        }else{
            netArea();
        }
    }

    private void gotoArea() {
        AreaFragment fragment = new AreaFragment();
        Bundle args = new Bundle();
//            MoveBean.DataBean
        args.putParcelable("MoveBean",selectMove);
        args.putParcelable("myTicket", sellTicket);
        fragment.setArguments(args);
        view.replaceFragment(fragment);
    }

    public void netArea() {

        view.showloadDialog();
        OkHttpUtils
                .post()
                .url(Config.GetAreaUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("film_id", selectMove.getId())
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
               Area area = JSON.parseObject(response, new TypeReference<Area>() {
                });
                if (!area.isStatus()) {
                    if (area.getData() != null) {
//                        gotoReplace("");
                        //直接跳转到选座
                        SeatFragment seatFragment = new SeatFragment();
                        Bundle args = new Bundle();
                        args.putString("SelectDate", sellTicket.getDate());
                        args.putString("SelectTime", sellTicket.getTime());
                        args.putParcelable("MoveBean", selectMove);
                        seatFragment.setArguments(args);
                        view.goToSeat(seatFragment);
                    }
                    return;
                } else {
                    ArrayList<String> imgList = new ArrayList<>();
                    for (int i = 0; i < area.getData().size(); i++) {
                        imgList.add(area.getData().get(i).getLogo());
                    }
                    gotoArea();
                }
//                view.set

            }
        });

    }



}
