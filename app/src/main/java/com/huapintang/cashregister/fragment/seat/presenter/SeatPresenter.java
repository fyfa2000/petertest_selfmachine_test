package com.huapintang.cashregister.fragment.seat.presenter;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.BR;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.area.view.AreaFragment;
import com.huapintang.cashregister.fragment.pay.view.PayFragment;
import com.huapintang.cashregister.fragment.seat.model.Seat;
import com.huapintang.cashregister.fragment.seat.view.SeatView;
import com.huapintang.cashregister.fragment.sellTicket.model.SellTicket;
import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.widget.SeatTable;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by cbp on 3/18 0018.
 */
public class SeatPresenter {
    private SeatView view;
    private Seat seat;
    private MoveBean.DataBean bean;
    private String areaId;
    private String selectDate;
    private String selectTime;

    private Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    final List<List<Seat.DataBean.SeatInfoBean>> seat_infoList = seat.getData().getSeat_infoList();
//                    dataBinding.tvDistrict.setText(eareName);
                    int column = 0;
                    int row = seat_infoList.size();
                    for (List<Seat.DataBean.SeatInfoBean> seatInfoBeanList : seat_infoList) {
                        int size1 = seatInfoBeanList.size();
                        if (size1 > column) {
                            column = size1;
                        }
                    }
                    List<Seat.DataBean.PriceInfoBean> price_info = seat.getData().getPrice_info();
                    ArrayList<String> colorStringList = new ArrayList<>();
                    List<String> priceList = new ArrayList<>();
                    for (Seat.DataBean.PriceInfoBean bean : price_info
                            ) {
                        colorStringList.add(bean.getColor());
                        priceList.add(bean.getPrice() + "元");
                    }
                    view.setSeatTableData(row, column, colorStringList, priceList);
                    view.setLineNumbers(((ArrayList<String>) seat.getData().getSide()));
                    createChecker(seat_infoList);
                    view.setSeatChecker(seatChecker);
//                    售价
//                    dataBinding.seattable.setLineNumbers();
//                    dataBinding.seattable.setSeatChecker();

                    break;

            }
            super.handleMessage(msg);
        }


    };
    private ArrayList<Seat.DataBean.SeatInfoBean> dataList;
    private BaseRecycleViewAdapter selectAdapter;


    private void createChecker(final List<List<Seat.DataBean.SeatInfoBean>> seat_infoList) {
        seatChecker = new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                return seat_infoList.get(row).get(column).isIsShow();
            }

            @Override
            public String Color(int row, int column) {
                return seat_infoList.get(row).get(column).getColor();
            }


            @Override
            public boolean isSold(int row, int column) {
                boolean equals = seat_infoList.get(row).get(column).getT().equals("0");
                return !equals;
            }

            @Override
            public void checked(int row, int column) {
                selectAdapter.addItem(seat_infoList.get(row).get(column));
            }

            @Override
            public void unCheck(int row, int column) {
                selectAdapter.removeItem(seat_infoList.get(row).get(column));
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                String seat_title = seat_infoList.get(row).get(column).getSeat_title();
                return new String[]{seat_title};
            }
        };
    }



    private SeatTable.SeatChecker seatChecker;

    public SeatPresenter(SeatView view) {
        this.view = view;

    }

    public void initArg(Bundle arguments) {
        areaId = arguments.getString("AreaId");
        selectDate = arguments.getString("SelectDate");
        selectTime = arguments.getString("SelectTime");
        bean = arguments.getParcelable("MoveBean");
        netSeat();
        initXuanzuo();
    }

    private void initXuanzuo(){
        dataList = new ArrayList<>();
        selectAdapter = new BaseRecycleViewAdapter(dataList, R.layout.item_ticket_select, BR.seatInfoBean ,R.id.iv_del);
        selectAdapter.setBtnClickListener(new BaseRecycleViewAdapter.OnBtnClickListener() {
            @Override
            public void OnClick(ViewDataBinding dataBinding, int position) {
                Seat.DataBean.SeatInfoBean seatInfoBean = dataList.get(position);
                view.seatRemoveItem(seatInfoBean.getRow(),seatInfoBean.getColumn());
//                binding.seatable.removeItem(seatInfoBean.getRow(),seatInfoBean.getColumn());
                selectAdapter.removeItem(position);
                selectAdapter.notifyDataSetChanged();
                //删除所选的值
//                setTicketTotalPrice();
//                setListShow();
            }
        });
        view.setSeatAdapter(selectAdapter);
    }
    public void netSeat() {
        view.showloadDialog();
        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .url(Config.GetChooseSeat)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("film_id", bean.getId())  //商品ID
                .addParams("date", selectDate)
                .addParams("times", selectTime);
        //区域ID
        if (!TextUtils.isEmpty(areaId)) {
            postFormBuilder.addParams("hall_id", areaId);
        }
        postFormBuilder.build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        view.dissloadDialog();
                        view.setNetErrer();
                    }

                    @Override
                    public void onResponse(final String response, int id) {
                        view.dissloadDialog();
                        LogUtils.e("座位列表", response.toString());
                        seat = JSON.parseObject(response, new TypeReference<Seat>() {
                        });
                        if (!seat.isStatus()) {
//                            dataBinding.llNum.setVisibility(View.VISIBLE);
                            view.setNetErrer(seat.getMsg());
                            return;
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean satus = jsonObject.getBoolean("status");
//                                    List<List<Seat.DataBean.SeatInfoBean>> seat_infoList = new ArrayList<List<Seat.DataBean.SeatInfoBean>>();
                                    if (satus) {
                                        JSONObject data = jsonObject.getJSONObject("data");
//                                        JSONArray side = data.getJSONArray("side");
//                                        ArrayList<String> sideArray = new ArrayList<>();
//                                        for (int i = 0; i < side.length(); i++) {
//                                            Object o = side.get(i);
//                                            sideArray.add(o.toString());
//                                        }

                                        JSONObject seat_info = data.getJSONObject("seat_info");
                                        int i = 1;

                                        List<List<Seat.DataBean.SeatInfoBean>> seatInfoBeanLists = new ArrayList<List<Seat.DataBean.SeatInfoBean>>();

                                        while (seat_info.keys().hasNext()) {
                                            JSONObject jsonObject1;
                                            try {
                                                jsonObject1 = seat_info.getJSONObject("" + i);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                break;
                                            }
                                            List<Seat.DataBean.SeatInfoBean> seatInfoBeanList = new ArrayList<Seat.DataBean.SeatInfoBean>();
                                            int j = 1;
                                            while (jsonObject1.keys().hasNext()) {

                                                JSONObject r;
                                                try {
                                                    r = jsonObject1.getJSONObject("" + j);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                    break;
                                                }
                                                Seat.DataBean.SeatInfoBean seatInfoBean = new Seat.DataBean.SeatInfoBean();
                                                seatInfoBean.setC(r.getString("c"));
                                                seatInfoBean.setT(r.getString("t"));
                                                seatInfoBean.setN(r.getString("n"));
                                                seatInfoBean.setPrice(r.getString("price"));
                                                seatInfoBean.setColor(r.getString("color"));
                                                seatInfoBean.setSeat_title(r.getString("seat_title"));
                                                seatInfoBean.setIsSelected(r.getBoolean("isSelected"));
                                                seatInfoBean.setStatus(r.getString("status"));
                                                seatInfoBean.setIsShow(r.getBoolean("isShow"));
                                                seatInfoBean.setSeat_id(r.getString("seat_id"));
                                                seatInfoBean.setSeat(r.getString("seat"));

                                                seatInfoBean.setRow(i);
                                                seatInfoBean.setColumn(j);
                                                try {
                                                    seatInfoBean.setVip_price(r.getJSONArray("vip_price"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                seatInfoBeanList.add(seatInfoBean);

                                                j++;
                                            }
                                            seatInfoBeanLists.add(seatInfoBeanList);


                                            i++;
                                        }
                                        seat.getData().setSeat_infoList(seatInfoBeanLists);

                                    }

                                    Message message = new Message();
                                    message.what = 1;
                                    handler.sendMessage(message);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                });

    }
    public void initMove() {
        //回到选区
        AreaFragment fragment = new AreaFragment();
        Bundle args = new Bundle();
//            MoveBean.DataBean
        args.putParcelable("MoveBean", bean);
        SellTicket sellTicket = new SellTicket();
        sellTicket.setDate(selectDate);
        sellTicket.setTime(selectTime);
        sellTicket.setTitile(bean.getTitle());
        args.putParcelable("myTicket", sellTicket);
        fragment.setArguments(args);
        view.replaceFragment(fragment);
    }


    public void gotoPay() {
            if(dataList==null||dataList.size()<1){
                view.showPrompt("请先选择座位");
                return;
            }
        PayFragment fragment = new PayFragment();
        Bundle args = new Bundle();
        args.putBoolean("isSeat",true);
        args.putParcelableArrayList("dataList",dataList);
        args.putParcelable("move", bean);
        args.putString("date",selectDate);
        args.putString("time",selectTime);
        if(!TextUtils.isEmpty(areaId)){
            args.putString("AreaId",areaId);
        }
//        args.putString ("",""); //影厅

        fragment.setArguments(args);
        view.replaceFragment(fragment);

    }
}
