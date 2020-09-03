package com.huapintang.cashregister;


import android.content.Context;

import com.huapintang.cashregister.ui.login.model.LoginResult;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by cbp on 9/17 0017.
 */
public class Config {

    public static LoginResult loginResult;


    //    public static final String url="https://ticket.miaopiao.me/generalf/";  //通用版
//    public static final String AddressUrl = "http://c.miaopiao.me/miaopiao1/";  //通用版
    public static final String AddressUrl = "http://c.miaopiao.me/petertest/";  //通用版

    //取票打印门票
    public static final String PrintUrl = AddressUrl + "index.php?g=App&m=Ticket1&a=print_code";
    //登录
    public static final String Loginurl = AddressUrl + "index.php?g=App&m=Login&a=login";
    //出票失败（自助取票）
    public static final String OutTicketUrl = AddressUrl + "index.php?g=App&m=Ticket1&a=ticket_back";


    //首页获取数据
    public static final String HomePageUrl = AddressUrl + "index.php?g=App&m=Index&a=index";
    //获取票种
    public static final String GetTicketUrl = AddressUrl + "index.php?g=App&m=Index&a=get_prices";
    //获取活动日期
    public static final String EventDateUrl = AddressUrl + "index.php?g=App&m=Index&a=get_date";
    //获取时间段
    public static final String EventTimeUrl = AddressUrl + "index.php?g=App&m=Index&a=get_times";
    //下单
    public static final String PlacOrderUrl = AddressUrl + "index.php?g=App&m=Order&a=do_order";
    //订单扫码支付
    public static final String OrderPayUrl = AddressUrl + "index.php?g=App&m=Order&a=code_pay";
    //打印信息  //小票信息
    public static final String PrintOrderUrl = AddressUrl + "index.php?g=App&m=Order&a=order_print";
    //会员登录
    public static final String HuiYuanUrl = AddressUrl + "index.php?g=App&m=Order&a=vip_info";

    //获取区域信息
    public static final String GetAreaUrl = AddressUrl + "index.php?g=App&m=Seat&a=get_area";
    //获取选座信息
    public static final String GetChooseSeat = AddressUrl + "index.php?g=App&m=Seat&a=get_seat";

    //出票失败(收银)
//    public static final String OutTicketUrl = AddressUrl + " index.php?g=App&m=Ticket&a=ticket_back";
    //初始化极光推送
    public static void initJPush(Context context) {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(context);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
        Set<String> set = new HashSet<>();
        set.add("andfixdemo");//名字任意，可多添加几个
        JPushInterface.setTags(context, set, null);//设置标签
    }

}
