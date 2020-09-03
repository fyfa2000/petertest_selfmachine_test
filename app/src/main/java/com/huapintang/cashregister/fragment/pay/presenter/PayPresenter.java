package com.huapintang.cashregister.fragment.pay.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.BR;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.adapter.BaseRecycleViewAdapter;
import com.huapintang.cashregister.bean.PrintTicket;
import com.huapintang.cashregister.dialog.ScanDialog;
import com.huapintang.cashregister.dialog.huiyuan.model.HuiYuan;
import com.huapintang.cashregister.dialog.huiyuan.view.HuiYuanLoginDialog;
import com.huapintang.cashregister.dialog.huiyuanpay.view.HuiYuanPayActivity;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.pay.model.ItemTicket;
import com.huapintang.cashregister.fragment.pay.model.OrderPrintTicket;
import com.huapintang.cashregister.fragment.pay.model.PayResult;
import com.huapintang.cashregister.fragment.pay.model.TicketResult;
import com.huapintang.cashregister.fragment.pay.view.PayView;
import com.huapintang.cashregister.fragment.print.view.PrintFragment;
import com.huapintang.cashregister.fragment.seat.model.Seat;
import com.huapintang.cashregister.fragment.sellTicket.model.GetTicketResult;
import com.huapintang.cashregister.fragment.sellTicket.model.SellTicket;
import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.utils.StringCheck;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by cbp on 3/12 0012.
 */
public class PayPresenter {
    private static PayView view;
    private SellTicket sellTicket;
    private String total;
    private boolean isSeat;
    private ArrayList<Seat.DataBean.SeatInfoBean> dataList;
    //选中的电影
    private MoveBean.DataBean selectMove;
    //选中票样
    private GetTicketResult.DataBean selectTicket;
    private BaseRecycleViewAdapter selectAdapter;

    private String selectTime;
    private String selectDate;
    private String hall_id;//分区ID


    public static   Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(!TextUtils.isEmpty(payOrderN0)){
                        netGetPrint(payOrderN0);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public PayPresenter(PayView view) {
        this.view = view;
    }


    public void initData(Bundle bundle) {
        if (bundle == null) {
            return;
        }
            isSeat=bundle.getBoolean("isSeat",false);
        if(!isSeat){
            selectMove = bundle.getParcelable("move");
            selectTicket = bundle.getParcelable("ticket");
            sellTicket = bundle.getParcelable("myTicket");
            //总金额
            String total = bundle.getString("total");
            view.setTotal(total);
            int num = sellTicket.getNum();
            if (num > 0) {
                //非选座
                isSeat = false;
                ItemTicket itemTicket = new ItemTicket();
                itemTicket.setNum(num + "");
                itemTicket.setPrice(selectTicket.getPrice());
                itemTicket.setTitle(sellTicket.getTitile());
//            itemTicket.setTotal(total);
                ArrayList<ItemTicket> lists = new ArrayList<>();
                lists.add(itemTicket);
                BaseRecycleViewAdapter adapter = new BaseRecycleViewAdapter(lists, R.layout.item_no_seat_ticket, BR.noSeat);
                view.setLvTicket(adapter);
            }
        }else{
            dataList=  bundle.getParcelableArrayList("dataList");
            selectMove = bundle.getParcelable("move");
            selectDate=   bundle.getString("date");
            selectTime=       bundle.getString("time");
            hall_id = bundle.getString("AreaId");
            selectAdapter = new BaseRecycleViewAdapter(dataList, R.layout.item_seat_pay, BR.seatPay);
            view.setLvTicket(selectAdapter);
            double d=0;
            for (int i = 0; i <dataList.size() ; i++) {
                String price = dataList.get(i).getPrice();
                d=d+  Double.parseDouble(price);
            }
            String s = StringCheck.decimalFormat(d);
            view.setTotal(s);

        }
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


    public SellTicket getSellTicket() {
        return sellTicket;
    }

    public void setSellTicket(SellTicket sellTicket) {
        this.sellTicket = sellTicket;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public boolean isSeat() {
        return isSeat;
    }

    public void setSeat(boolean seat) {
        isSeat = seat;
    }

    public void aliPay() {
        if (ticketResult == null || !ticketResult.isStatus()) {

            netXiadan(ScanAliRequstCode);
        } else {
            intentScan(ScanAliRequstCode);
        }
    }

    public void weixinPay() {
        if (ticketResult == null || !ticketResult.isStatus()) {
            netXiadan(ScanWeixinRequstCode);
        } else {
            intentScan(ScanWeixinRequstCode);
        }
    }

    public void cardPay() {
        Intent intent = new Intent();
        intent.setClass(MyApplication.getContext(), HuiYuanLoginDialog.class);
        view.intentForResult(intent, CardRequstCode);
//        intentTo(intent);
    }


    private void netXiadan(int code) {
        netXiadan(null, code);
    }

    private void netXiadan(final HuiYuan.DataBean huiyanData, final int code) {

        if(!isSeat){
            PostFormBuilder postFormBuilder = OkHttpUtils
                    .post()
                    .url(Config.PlacOrderUrl)
                    .addParams("ukey", Config.loginResult.getData().getUkey())
                    .addParams("num", sellTicket.getNumString())
                    .addParams("film_id", selectMove.getId())
                    .addParams("price_id", selectTicket.getId());
            //检查数据对错
            if (!selectMove.getIs_through().equals("1")) {
                postFormBuilder.addParams("date1", sellTicket.getDate())
                        .addParams("times", sellTicket.getTime());
            }


            //会员
            if (huiyanData != null) {
                String card_no = huiyanData.getCard_no();
                if (!TextUtils.isEmpty(card_no)) {
                    postFormBuilder.addParams("card_no", card_no);
                } else {

                    postFormBuilder.addParams("vip_id", huiyanData.getId());
                }
            }
            //选座
//        ArrayList<String> seatStringList = new ArrayList<>();
//        for (int i = 0; i < dataList.size(); i++) {
//            seatStringList.add(dataList.get(i).getSeat());
//        }
//        if (seatStringList != null && seatStringList.size() > 0) {
//            String s = JSON.toJSON(seatStringList).toString();
//            postFormBuilder.addParams("seat", s);
//            postFormBuilder.addParams("date1", selectDate)
//                    .addParams("times", selectTime);
//            if (!TextUtils.isEmpty(hall_id)) {
//                postFormBuilder.addParams("hall_id", hall_id);
//            }
//        }
            postFormBuilder.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    view.setNetErrer();
                    view.dissloadDialog();
                }

                @Override
                public void onResponse(String response, int id) {
                    view.dissloadDialog();
                    LogUtils.e("下单返回信息:", response);
                    ticketResult = JSON.parseObject(response, new TypeReference<TicketResult>() {
                    });
                    if (ticketResult.isStatus()) {
                        if (huiyanData == null) {
                            intentScan(code);
                        } else {
                            TicketResult.DataBean ticketResultData = ticketResult.getData();
                            intentCradPay(huiyanData, ticketResultData, sellTicket.getNumString());
                        }
                    } else {
                        view.setNetErrer(ticketResult.getMsg());
                    }
                }
            });
        }else{
            PostFormBuilder postFormBuilder = OkHttpUtils
                    .post()
                    .url(Config.PlacOrderUrl)
                    .addParams("ukey", Config.loginResult.getData().getUkey())
                    .addParams("film_id", selectMove.getId());
            ArrayList<String> seatStringList = new ArrayList<>();
            if(dataList==null||dataList.size()<1){
//                Toast.makeText(SelectSeatActivity.this, "请选择座位再下单", Toast.LENGTH_SHORT).show();
//                return;
            }
            for (int i = 0; i < dataList.size(); i++) {
                seatStringList.add(dataList.get(i).getSeat());
            }

            if (seatStringList != null && seatStringList.size() > 0) {
                String s = JSON.toJSON(seatStringList).toString();
                postFormBuilder.addParams("seat", s);
                postFormBuilder.addParams("date1", selectDate)
                        .addParams("times", selectTime);
                if (!TextUtils.isEmpty(hall_id )){
                    postFormBuilder.addParams("hall_id",hall_id);
                }
            }
           view.showloadDialog();
            postFormBuilder.build().execute(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    view.dissloadDialog();
                    view.setNetErrer();
                }

                @Override
                public void onResponse(String response, int id) {
                    view.dissloadDialog();
                    ticketResult = JSON.parseObject(response, new TypeReference<TicketResult>() {
                    });
                    if(!ticketResult.isStatus()){
                        view.setNetErrer(ticketResult.getMsg());
                        return;
                    }else           {
                        if (huiyanData == null) {
                            intentScan(code);
                        } else {
                            TicketResult.DataBean ticketResultData = ticketResult.getData();
                            intentCradPay(huiyanData, ticketResultData, dataList.size()+"");
                        }
                    }
                }
            });


        }

    }

    private TicketResult ticketResult;

    public static final int ScanWeixinRequstCode = 1025;
    public static final int ScanAliRequstCode = 1026;
    public static final int CardRequstCode = 1027;

    public static final int CardPayRequstCode = 1017;


    private void intentCradPay( HuiYuan.DataBean huiyanData, TicketResult.DataBean dataBean,String times) {
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        extras.putParcelable("huiyanData", huiyanData);
        extras.putString("orderNo", dataBean.getOrder_no());
        extras.putString("price",dataBean.getPrice());
       extras.putString("times",times);
        intent.putExtras(extras);
        intent.setClass(MyApplication.getContext(), HuiYuanPayActivity.class);
        view.intentForResult(intent, CardPayRequstCode);
    }

    private void intentScan(int code) {
        Intent intent = new Intent();
        intent.setClass(MyApplication.getContext(), ScanDialog.class);
        view.intentForResult(intent, code);
//        startActivityForResult(intent, ScanAliRequstCode);
    }

    //扫码返回
    public void resultPay(int requestCode, Intent data) {
        if (requestCode == ScanAliRequstCode) {
            payOrder(data, "zfb");
        }
        if (requestCode == ScanWeixinRequstCode) {
            payOrder(data, "wxsm");
        }
        if (requestCode == CardRequstCode) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                HuiYuan.DataBean huiyanData = extras.getParcelable("huiyanData");
                netXiadan(huiyanData, 0);
            }

        }
    }


    private static   String payOrderN0="";
    /**
     * 支付订单
     */
    public void payOrder(Intent data, String payType) {
        TicketResult.DataBean data1 = ticketResult.getData();
        final String order_no = data1.getOrder_no();
        payOrderN0=order_no;
        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .url(Config.OrderPayUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("order_no", order_no)
                .addParams("pay_type", payType);
//        if (!TextUtils.isEmpty(vip_id)) {
//            postFormBuilder
//                    .addParams("vip_id", vip_id);
//        }
        String scan = data.getExtras().getString("scan");
        if (!TextUtils.isEmpty(scan)) {
            postFormBuilder
                    .addParams("code", scan);
        }
        postFormBuilder
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
                        LogUtils.e("支付返回：", response);
                        PayResult payResult = JSON.parseObject(response, new TypeReference<PayResult>() {
                        });

                        if(payResult.getMsg().equals("提交成功")){
                            view.showPrompt("请在手机输入支付密码");
                           return;
                        }
                        if (!payResult.getMsg().equals("success")) {
                            view.setNetErrer(payResult.getMsg());
                            return;
                        }
                        //跳转到出票
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelable("DataBean", PrintTicket.DataBean);
                        netGetPrint(order_no);
                    }
                });
    }

    private  static  void netGetPrint(String orderNo) {
        payOrderN0=null;
        view.showloadDialog();
        OkHttpUtils
                .post()
                .url(Config.PrintOrderUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("order_no", orderNo)
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
                        LogUtils.e("获取到的打印信息:", response);
                        OrderPrintTicket printTicket
                                = JSON.parseObject(response, new TypeReference<OrderPrintTicket>() {
                        });
                        //打印信息
                        PrintFragment fragment = new PrintFragment();
                        Bundle bundle = new Bundle();
                        ArrayList<OrderPrintTicket.DataBean> data = printTicket.getData();
                        ArrayList<PrintTicket.DataBean> printData=new ArrayList<PrintTicket.DataBean>();

                        /**
                         *      * time : 2018-09-20 19:00-22:00
                         * paytime : 09/17 15:35
                         * address : G
                         * name : 石进钢琴作品音乐会----广州站
                         * ptitle : 套票660（380*2）
                         * pname : G
                         * code : UKGX6TOYX7
                         * username : null
                         * id_card : null
                         * price : 660.00
                         * place1 : XX剧院
                         * seat : 2排7座
                         */
                        for (int i = 0; i <data.size() ; i++) {
                            PrintTicket.DataBean dataBean = new PrintTicket.DataBean();
                            OrderPrintTicket.DataBean dataBean1 = data.get(i);
                            dataBean.setTime(dataBean1.getTime());
//                            dataBean.setPaytime(dataBean1.getp);
                            dataBean.setAddress(dataBean1.getAddress());
                            dataBean.setName(dataBean1.getTitle());
                            dataBean.setPrice(dataBean1.getPrice());
                            dataBean.setPtitle(dataBean1.getP_title());
                            dataBean.setTime(dataBean1.getDate1()+dataBean1.getTimes());
                            dataBean.setPname(dataBean1.getH_title());
                            dataBean.setCode(dataBean1.getCode());
//                            dataBean.setUsername(dataBean1.get);
//                            dataBean.setId_card(dataBean1.getid_);
                            dataBean.setPrice(dataBean1.getPrice());
                            dataBean.setPlace1(dataBean1.getPlace1());
                            dataBean.setSeat(dataBean1.getSeat());
                            printData.add(dataBean);
                        }

                        bundle.putParcelableArrayList("array",printData);
                        fragment.setArguments(bundle);
                        view.replaceFragment(fragment);
                    }
                });
    }

    public void printTicket(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String orderNo = extras.getString("orderNo");
            netGetPrint(orderNo);
        }
    }
}
