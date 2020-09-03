package com.huapintang.cashregister.fragment.seat.model;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.utils.SeatBitmapUtils;

import java.util.List;

/**
 * Created by cbp on 6/26 0026.
 */
public class Seat {


    /**
     * status : true
     * msg : success
     * data : {"status":1,"side":["1","2","3","4","5","6","7","8","9"],"price_info":[{"id":"37","title":"套票660（380*2）","color":"#f79646","price":"660.00","vip_price":"0.01","f_price":null,"price1":"0.00","new_money":"660.00","old_money":"660.00"},{"id":"36","title":"580","color":"#4bacc6","price":"580.00","vip_price":"520.00","f_price":null,"price1":"0.00","new_money":"580.00","old_money":"580.00"},{"id":"35","title":"380","color":"#8064a2","price":"380.00","vip_price":"340.00","f_price":null,"price1":"0.00","new_money":"380.00","old_money":"380.00"},{"id":"34","title":"280","color":"#9bbb59","price":"280.00","vip_price":"250.00","f_price":null,"price1":"0.00","new_money":"280.00","old_money":"280.00"},{"id":"33","title":"180","color":"#c0504d","price":"180.00","vip_price":"150.00","f_price":null,"price1":"0.00","new_money":"180.00","old_money":"180.00"},{"id":"32","title":"120","color":"#4f81bd","price":"0.01","vip_price":"0.00","f_price":null,"price1":"0.00","new_money":"0.01","old_money":"0.01"}],"seat_info":{"1":{"1":{"c":"1","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_1","seat":"1-1"},"2":{"c":"2","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_2","seat":"1-2"},"3":{"c":"3","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_3","seat":"1-3"},"4":{"c":"4","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"1排4座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_1_4","seat":"1-4"},"5":{"c":"5","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"1排5座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_1_5","seat":"1-5"},"6":{"c":"6","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"1排6座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_1_6","seat":"1-6"},"7":{"c":"7","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_7","seat":"1-7"},"8":{"c":"8","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_8","seat":"1-8"},"9":{"c":"9","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_9","seat":"1-9"},"10":{"c":"10","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_10","seat":"1-10"},"11":{"c":"11","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_11","seat":"1-11"},"12":{"c":"12","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_12","seat":"1-12"},"13":{"c":"13","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_13","seat":"1-13"}},"2":{"1":{"c":"1","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_1","seat":"2-1"},"2":{"c":"2","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_2","seat":"2-2"},"3":{"c":"3","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_3","seat":"2-3"},"4":{"c":"4","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_4","seat":"2-4"},"5":{"c":"5","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"2排5座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_2_5","seat":"2-5"},"6":{"c":"6","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"2排6座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_2_6","seat":"2-6"},"7":{"c":"7","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"2排7座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_2_7","seat":"2-7"},"8":{"c":"8","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_8","seat":"2-8"},"9":{"c":"9","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_9","seat":"2-9"},"10":{"c":"10","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_10","seat":"2-10"},"11":{"c":"11","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_11","seat":"2-11"},"12":{"c":"12","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_12","seat":"2-12"},"13":{"c":"13","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_13","seat":"2-13"}},"3":{"1":{"c":"1","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_1","seat":"3-1"},"2":{"c":"2","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_2","seat":"3-2"},"3":{"c":"3","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_3","seat":"3-3"},"4":{"c":"4","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_4","seat":"3-4"},"5":{"c":"5","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_5","seat":"3-5"},"6":{"c":"6","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_6","seat":"3-6"},"7":{"c":"7","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_7","seat":"3-7"},"8":{"c":"8","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_8","seat":"3-8"},"9":{"c":"9","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_9","seat":"3-9"},"10":{"c":"10","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_10","seat":"3-10"},"11":{"c":"11","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_11","seat":"3-11"},"12":{"c":"12","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_12","seat":"3-12"},"13":{"c":"13","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_13","seat":"3-13"}},"4":{"1":{"c":"1","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_1","seat":"4-1"},"2":{"c":"2","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_2","seat":"4-2"},"3":{"c":"3","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_3","seat":"4-3"},"4":{"c":"4","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_4","seat":"4-4"},"5":{"c":"5","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_5","seat":"4-5"},"6":{"c":"6","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_6","seat":"4-6"},"7":{"c":"7","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_7","seat":"4-7"},"8":{"c":"8","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_8","seat":"4-8"},"9":{"c":"9","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_9","seat":"4-9"},"10":{"c":"10","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_10","seat":"4-10"},"11":{"c":"11","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_11","seat":"4-11"},"12":{"c":"12","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_12","seat":"4-12"},"13":{"c":"13","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_13","seat":"4-13"}},"5":{"1":{"c":"1","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_1","seat":"5-1"},"2":{"c":"2","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_2","seat":"5-2"},"3":{"c":"3","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_3","seat":"5-3"},"4":{"c":"4","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_4","seat":"5-4"},"5":{"c":"5","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_5","seat":"5-5"},"6":{"c":"6","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_6","seat":"5-6"},"7":{"c":"7","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_7","seat":"5-7"},"8":{"c":"8","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_8","seat":"5-8"},"9":{"c":"9","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_9","seat":"5-9"},"10":{"c":"10","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_10","seat":"5-10"},"11":{"c":"11","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排11座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_11","seat":"5-11"},"12":{"c":"12","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_12","seat":"5-12"},"13":{"c":"13","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_13","seat":"5-13"}},"6":{"1":{"c":"1","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_1","seat":"6-1"},"2":{"c":"2","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_2","seat":"6-2"},"3":{"c":"3","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_3","seat":"6-3"},"4":{"c":"4","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_4","seat":"6-4"},"5":{"c":"5","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_5","seat":"6-5"},"6":{"c":"6","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_6","seat":"6-6"},"7":{"c":"7","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_7","seat":"6-7"},"8":{"c":"8","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_8","seat":"6-8"},"9":{"c":"9","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_9","seat":"6-9"},"10":{"c":"10","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排10座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_10","seat":"6-10"},"11":{"c":"11","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排11座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_11","seat":"6-11"},"12":{"c":"12","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_12","seat":"6-12"},"13":{"c":"13","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_13","seat":"6-13"}},"7":{"1":{"c":"1","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_1","seat":"7-1"},"2":{"c":"2","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_2","seat":"7-2"},"3":{"c":"3","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_3","seat":"7-3"},"4":{"c":"4","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_4","seat":"7-4"},"5":{"c":"5","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_5","seat":"7-5"},"6":{"c":"6","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_6","seat":"7-6"},"7":{"c":"7","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_7","seat":"7-7"},"8":{"c":"8","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_8","seat":"7-8"},"9":{"c":"9","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_9","seat":"7-9"},"10":{"c":"10","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_10","seat":"7-10"},"11":{"c":"11","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_11","seat":"7-11"},"12":{"c":"12","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_12","seat":"7-12"},"13":{"c":"13","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_13","seat":"7-13"}},"8":{"1":{"c":"1","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_1","seat":"8-1"},"2":{"c":"2","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_2","seat":"8-2"},"3":{"c":"3","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_3","seat":"8-3"},"4":{"c":"4","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_4","seat":"8-4"},"5":{"c":"5","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_5","seat":"8-5"},"6":{"c":"6","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_6","seat":"8-6"},"7":{"c":"7","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_7","seat":"8-7"},"8":{"c":"8","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_8","seat":"8-8"},"9":{"c":"9","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_9","seat":"8-9"},"10":{"c":"10","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_10","seat":"8-10"},"11":{"c":"11","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_11","seat":"8-11"},"12":{"c":"12","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_12","seat":"8-12"},"13":{"c":"13","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_13","seat":"8-13"}},"9":{"1":{"c":"1","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_1","seat":"9-1"},"2":{"c":"2","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_2","seat":"9-2"},"3":{"c":"3","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排3座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_3","seat":"9-3"},"4":{"c":"4","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_4","seat":"9-4"},"5":{"c":"5","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_5","seat":"9-5"},"6":{"c":"6","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_6","seat":"9-6"},"7":{"c":"7","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_7","seat":"9-7"},"8":{"c":"8","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_8","seat":"9-8"},"9":{"c":"9","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_9","seat":"9-9"},"10":{"c":"10","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_10","seat":"9-10"},"11":{"c":"11","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_11","seat":"9-11"},"12":{"c":"12","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_12","seat":"9-12"},"13":{"c":"13","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_13","seat":"9-13"}}}}
     * ext_data : []
     */

    private boolean status;
    private String msg;
    /**
     * status : 1
     * side : ["1","2","3","4","5","6","7","8","9"]
     * price_info : [{"id":"37","title":"套票660（380*2）","color":"#f79646","price":"660.00","vip_price":"0.01","f_price":null,"price1":"0.00","new_money":"660.00","old_money":"660.00"},{"id":"36","title":"580","color":"#4bacc6","price":"580.00","vip_price":"520.00","f_price":null,"price1":"0.00","new_money":"580.00","old_money":"580.00"},{"id":"35","title":"380","color":"#8064a2","price":"380.00","vip_price":"340.00","f_price":null,"price1":"0.00","new_money":"380.00","old_money":"380.00"},{"id":"34","title":"280","color":"#9bbb59","price":"280.00","vip_price":"250.00","f_price":null,"price1":"0.00","new_money":"280.00","old_money":"280.00"},{"id":"33","title":"180","color":"#c0504d","price":"180.00","vip_price":"150.00","f_price":null,"price1":"0.00","new_money":"180.00","old_money":"180.00"},{"id":"32","title":"120","color":"#4f81bd","price":"0.01","vip_price":"0.00","f_price":null,"price1":"0.00","new_money":"0.01","old_money":"0.01"}]
     * seat_info : {"1":{"1":{"c":"1","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_1","seat":"1-1"},"2":{"c":"2","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_2","seat":"1-2"},"3":{"c":"3","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_3","seat":"1-3"},"4":{"c":"4","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"1排4座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_1_4","seat":"1-4"},"5":{"c":"5","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"1排5座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_1_5","seat":"1-5"},"6":{"c":"6","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"1排6座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_1_6","seat":"1-6"},"7":{"c":"7","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_7","seat":"1-7"},"8":{"c":"8","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_8","seat":"1-8"},"9":{"c":"9","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_9","seat":"1-9"},"10":{"c":"10","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_10","seat":"1-10"},"11":{"c":"11","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_11","seat":"1-11"},"12":{"c":"12","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_12","seat":"1-12"},"13":{"c":"13","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"1排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_1_13","seat":"1-13"}},"2":{"1":{"c":"1","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_1","seat":"2-1"},"2":{"c":"2","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_2","seat":"2-2"},"3":{"c":"3","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_3","seat":"2-3"},"4":{"c":"4","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_4","seat":"2-4"},"5":{"c":"5","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"2排5座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_2_5","seat":"2-5"},"6":{"c":"6","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"2排6座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_2_6","seat":"2-6"},"7":{"c":"7","t":"2","n":"","price":"380.00","color":"#8064a2","seat_title":"2排7座","isSelected":false,"status":"2","isShow":true,"seat_id":"seat_id_2_7","seat":"2-7"},"8":{"c":"8","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_8","seat":"2-8"},"9":{"c":"9","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_9","seat":"2-9"},"10":{"c":"10","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_10","seat":"2-10"},"11":{"c":"11","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_11","seat":"2-11"},"12":{"c":"12","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_12","seat":"2-12"},"13":{"c":"13","t":"0","n":"","price":"380.00","color":"#8064a2","seat_title":"2排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_2_13","seat":"2-13"}},"3":{"1":{"c":"1","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_1","seat":"3-1"},"2":{"c":"2","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_2","seat":"3-2"},"3":{"c":"3","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_3","seat":"3-3"},"4":{"c":"4","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_4","seat":"3-4"},"5":{"c":"5","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_5","seat":"3-5"},"6":{"c":"6","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_6","seat":"3-6"},"7":{"c":"7","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_7","seat":"3-7"},"8":{"c":"8","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_8","seat":"3-8"},"9":{"c":"9","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_9","seat":"3-9"},"10":{"c":"10","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_10","seat":"3-10"},"11":{"c":"11","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_11","seat":"3-11"},"12":{"c":"12","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_12","seat":"3-12"},"13":{"c":"13","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"3排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_3_13","seat":"3-13"}},"4":{"1":{"c":"1","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_1","seat":"4-1"},"2":{"c":"2","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排2座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_2","seat":"4-2"},"3":{"c":"3","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_3","seat":"4-3"},"4":{"c":"4","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_4","seat":"4-4"},"5":{"c":"5","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_5","seat":"4-5"},"6":{"c":"6","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_6","seat":"4-6"},"7":{"c":"7","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_7","seat":"4-7"},"8":{"c":"8","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_8","seat":"4-8"},"9":{"c":"9","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_9","seat":"4-9"},"10":{"c":"10","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_10","seat":"4-10"},"11":{"c":"11","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_11","seat":"4-11"},"12":{"c":"12","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排12座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_12","seat":"4-12"},"13":{"c":"13","t":"0","n":"","price":"280.00","color":"#9bbb59","seat_title":"4排13座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_4_13","seat":"4-13"}},"5":{"1":{"c":"1","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排1座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_1","seat":"5-1"},"2":{"c":"2","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_2","seat":"5-2"},"3":{"c":"3","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_3","seat":"5-3"},"4":{"c":"4","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_4","seat":"5-4"},"5":{"c":"5","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_5","seat":"5-5"},"6":{"c":"6","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_6","seat":"5-6"},"7":{"c":"7","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_7","seat":"5-7"},"8":{"c":"8","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_8","seat":"5-8"},"9":{"c":"9","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_9","seat":"5-9"},"10":{"c":"10","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"5排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_5_10","seat":"5-10"},"11":{"c":"11","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排11座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_11","seat":"5-11"},"12":{"c":"12","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_12","seat":"5-12"},"13":{"c":"13","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"5排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_5_13","seat":"5-13"}},"6":{"1":{"c":"1","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_1","seat":"6-1"},"2":{"c":"2","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_2","seat":"6-2"},"3":{"c":"3","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_3","seat":"6-3"},"4":{"c":"4","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_4","seat":"6-4"},"5":{"c":"5","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_5","seat":"6-5"},"6":{"c":"6","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_6","seat":"6-6"},"7":{"c":"7","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_7","seat":"6-7"},"8":{"c":"8","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_8","seat":"6-8"},"9":{"c":"9","t":"0","n":"","price":"180.00","color":"#c0504d","seat_title":"6排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_6_9","seat":"6-9"},"10":{"c":"10","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排10座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_10","seat":"6-10"},"11":{"c":"11","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排11座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_11","seat":"6-11"},"12":{"c":"12","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_12","seat":"6-12"},"13":{"c":"13","t":"1","n":"","price":"180.00","color":"#c0504d","seat_title":"6排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_6_13","seat":"6-13"}},"7":{"1":{"c":"1","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_1","seat":"7-1"},"2":{"c":"2","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_2","seat":"7-2"},"3":{"c":"3","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_3","seat":"7-3"},"4":{"c":"4","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_4","seat":"7-4"},"5":{"c":"5","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_5","seat":"7-5"},"6":{"c":"6","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_6","seat":"7-6"},"7":{"c":"7","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_7","seat":"7-7"},"8":{"c":"8","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_8","seat":"7-8"},"9":{"c":"9","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_9","seat":"7-9"},"10":{"c":"10","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_10","seat":"7-10"},"11":{"c":"11","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_7_11","seat":"7-11"},"12":{"c":"12","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_12","seat":"7-12"},"13":{"c":"13","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"7排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_7_13","seat":"7-13"}},"8":{"1":{"c":"1","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_1","seat":"8-1"},"2":{"c":"2","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_2","seat":"8-2"},"3":{"c":"3","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排3座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_3","seat":"8-3"},"4":{"c":"4","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_4","seat":"8-4"},"5":{"c":"5","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_5","seat":"8-5"},"6":{"c":"6","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_6","seat":"8-6"},"7":{"c":"7","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_7","seat":"8-7"},"8":{"c":"8","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_8","seat":"8-8"},"9":{"c":"9","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_9","seat":"8-9"},"10":{"c":"10","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_10","seat":"8-10"},"11":{"c":"11","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_8_11","seat":"8-11"},"12":{"c":"12","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_12","seat":"8-12"},"13":{"c":"13","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"8排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_8_13","seat":"8-13"}},"9":{"1":{"c":"1","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排1座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_1","seat":"9-1"},"2":{"c":"2","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排2座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_2","seat":"9-2"},"3":{"c":"3","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排3座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_3","seat":"9-3"},"4":{"c":"4","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排4座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_4","seat":"9-4"},"5":{"c":"5","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排5座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_5","seat":"9-5"},"6":{"c":"6","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排6座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_6","seat":"9-6"},"7":{"c":"7","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排7座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_7","seat":"9-7"},"8":{"c":"8","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排8座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_8","seat":"9-8"},"9":{"c":"9","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排9座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_9","seat":"9-9"},"10":{"c":"10","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排10座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_10","seat":"9-10"},"11":{"c":"11","t":"0","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排11座","isSelected":false,"status":"0","isShow":true,"seat_id":"seat_id_9_11","seat":"9-11"},"12":{"c":"12","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排12座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_12","seat":"9-12"},"13":{"c":"13","t":"1","n":"","price":"0.01","color":"#4f81bd","seat_title":"9排13座","isSelected":false,"status":"1","isShow":false,"seat_id":"seat_id_9_13","seat":"9-13"}}}
     */

    private DataBean data;
    private List<?> ext_data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<?> getExt_data() {
        return ext_data;
    }

    public void setExt_data(List<?> ext_data) {
        this.ext_data = ext_data;
    }

    public static class DataBean {
        /**
         * id : 37
         * title : 套票660（380*2）
         * color : #f79646
         * price : 660.00
         * vip_price : 0.01
         * f_price : null
         * price1 : 0.00
         * new_money : 660.00
         * old_money : 660.00
         */
        private int status;
        private List<String> side;
        private List<PriceInfoBean> price_info;

        private List<List<SeatInfoBean>> seat_infoList;


        public List<List<SeatInfoBean>> getSeat_infoList() {
            return seat_infoList;
        }

        public void setSeat_infoList(List<List<SeatInfoBean>> seat_infoList) {
            this.seat_infoList = seat_infoList;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public List<String> getSide() {
            return side;
        }

        public void setSide(List<String> side) {
            this.side = side;
        }

        public List<PriceInfoBean> getPrice_info() {
            return price_info;
        }

        public void setPrice_info(List<PriceInfoBean> price_info) {
            this.price_info = price_info;
        }


        public static class SeatInfoBean implements Parcelable {
            @Override
            public String toString() {
                return "SeatInfoBean{" +
                        "c='" + c + '\'' +
                        ", t='" + t + '\'' +
                        ", n='" + n + '\'' +
                        ", price='" + price + '\'' +
                        ", color='" + color + '\'' +
                        ", seat_title='" + seat_title + '\'' +
                        ", isSelected=" + isSelected +
                        ", status='" + status + '\'' +
                        ", isShow=" + isShow +
                        ", seat_id='" + seat_id + '\'' +
                        ", seat='" + seat + '\'' +
                        '}';
            }

            /**
             * c : 21
             * t : 1
             * n :
             * price : 280.00
             * color : #9bbb59
             * seat_title : 1排21座
             * isSelected : false
             * status : 1
             * isShow : false
             * seat_id : seat_id_1_1
             * seat : 1-1
             */

            private String c;
            private String t;
            private String n;
            private String price;
            private String color;
            private String seat_title;
            private boolean isSelected;
            private String status;
            private boolean isShow;
            private String seat_id;
            private String seat;
            private Object vip_price;

            @BindingAdapter({"bind:seatImage"})
            public static void      loadImage(ImageView view, String colorString) {

                Bitmap bitmap = SeatBitmapUtils.replaceBitmapColor(  BitmapFactory.decodeResource(  MyApplication.getContext().getResources(), R.drawable.seat_normal),
                        MyApplication.getContext().getResources().getColor(R.color.white), Color.parseColor(colorString));

                view.setImageBitmap(bitmap);
//                Picasso.with(view.getContext())
//                        .load(imageUrl)
////                        .placeholder(R.drawable.ic_mi)
////                    .transform(new CircleImageTransformation())
//                        .into(view);
            }


            public Object getVip_price() {
                return vip_price;
            }

            public void setVip_price(Object vip_price) {
                this.vip_price = vip_price;
            }

            private int row;
            private int column;

            public int getRow() {
                return row;
            }

            public void setRow(int row) {
                this.row = row;
            }

            public int getColumn() {
                return column;
            }

            public void setColumn(int column) {
                this.column = column;
            }

            public String getC() {
                return c;
            }

            public void setC(String c) {
                this.c = c;
            }

            public String getT() {
                return t;
            }

            public void setT(String t) {
                this.t = t;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getSeat_title() {
                return seat_title;
            }

            public void setSeat_title(String seat_title) {
                this.seat_title = seat_title;
            }

            public boolean isIsSelected() {
                return isSelected;
            }

            public void setIsSelected(boolean isSelected) {
                this.isSelected = isSelected;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public boolean isIsShow() {
                return isShow;
            }

            public void setIsShow(boolean isShow) {
                this.isShow = isShow;
            }

            public String getSeat_id() {
                return seat_id;
            }

            public void setSeat_id(String seat_id) {
                this.seat_id = seat_id;
            }

            public String getSeat() {
                return seat;
            }

            public void setSeat(String seat) {
                this.seat = seat;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.c);
                dest.writeString(this.t);
                dest.writeString(this.n);
                dest.writeString(this.price);
                dest.writeString(this.color);
                dest.writeString(this.seat_title);
                dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
                dest.writeString(this.status);
                dest.writeByte(this.isShow ? (byte) 1 : (byte) 0);
                dest.writeString(this.seat_id);
                dest.writeString(this.seat);
                dest.writeInt(this.row);
                dest.writeInt(this.column);
            }

            public SeatInfoBean() {
            }

            protected SeatInfoBean(Parcel in) {
                this.c = in.readString();
                this.t = in.readString();
                this.n = in.readString();
                this.price = in.readString();
                this.color = in.readString();
                this.seat_title = in.readString();
                this.isSelected = in.readByte() != 0;
                this.status = in.readString();
                this.isShow = in.readByte() != 0;
                this.seat_id = in.readString();
                this.seat = in.readString();
                this.row = in.readInt();
                this.column = in.readInt();
            }

            public static final Parcelable.Creator<SeatInfoBean> CREATOR = new Parcelable.Creator<SeatInfoBean>() {
                @Override
                public SeatInfoBean createFromParcel(Parcel source) {
                    return new SeatInfoBean(source);
                }

                @Override
                public SeatInfoBean[] newArray(int size) {
                    return new SeatInfoBean[size];
                }
            };
        }

        public static class PriceInfoBean {
            private String id;
            private String title;
            private String color;
            private String price;
            private String vip_price;
            private Object f_price;
            private String price1;
            private String new_money;
            private String old_money;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public Object getF_price() {
                return f_price;
            }

            public void setF_price(Object f_price) {
                this.f_price = f_price;
            }

            public String getPrice1() {
                return price1;
            }

            public void setPrice1(String price1) {
                this.price1 = price1;
            }

            public String getNew_money() {
                return new_money;
            }

            public void setNew_money(String new_money) {
                this.new_money = new_money;
            }

            public String getOld_money() {
                return old_money;
            }

            public void setOld_money(String old_money) {
                this.old_money = old_money;
            }
        }
    }
}