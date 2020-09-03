package com.huapintang.cashregister.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtils {
    public  static boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }
     public  static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式


     public  static   SimpleDateFormat sdfTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");; //设置时间格式

    public static String getTime(){
       return sdfTime.format(new Date());// new Date()为获取当前系统时间
    }


    public static String getToday(){
        return sdf.format(new Date());
    }


    public static int getYear(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year;
    }


    public  static String afterToday(int month){
        Date dNow = new Date(); //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(calendar.MONTH, month); //设置为几个月后
        dBefore = calendar.getTime(); //得到前3月的时间
        return sdf.format(dBefore);

    }

}