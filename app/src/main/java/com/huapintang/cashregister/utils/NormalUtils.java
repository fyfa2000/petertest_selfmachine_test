package com.huapintang.cashregister.utils;


import com.huapintang.cashregister.MyApplication;

/**
 * Created by cbp on 9/17 0017.
 */
public class NormalUtils {

    public static int getColor(int colorResources){
     return  MyApplication.getContext().getResources().getColor(colorResources);
    }

    public static String getString(int stringResources){
     return  MyApplication.getContext().getResources().getString(stringResources);
    }



}
