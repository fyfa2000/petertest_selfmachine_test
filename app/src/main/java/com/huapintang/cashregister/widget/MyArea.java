package com.huapintang.cashregister.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by cbp on 3/15 0015.
 */
public class MyArea  extends View{
    private ArrayList<Bitmap> bitmapArrayList=new ArrayList<>();

    public MyArea(Context context) {
        super(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}
