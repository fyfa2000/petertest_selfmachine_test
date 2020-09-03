package com.huapintang.cashregister.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.text.TextUtils;

import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.bean.PrintTicket;
import com.tx.printlib.Const;
import com.tx.printlib.UsbPrinter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cbp on 9/6 0006.
 */
public class PrintUtils {

    public static UsbPrinter mUsbPrinter;
    private static final String tisi1 = "一人一票，1.2米以下婴幼儿童禁止入场，副卷撕下无效，";
    private static final String tisi2 = "票张涂改无效。请提前入场，避开安检高峰，";
    private static final String tisi3 = "并配合安检人员检查。";
    private static final String tisi4 = "官方购票就上喵票微信和miaopiao.me   全国统一订票电话： 400-600-800";

    public static ArrayList<String> CreateTicketBitmap(List<PrintTicket.DataBean> beanList) {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < beanList.size(); i++) {
            PrintTicket.DataBean dataBean = beanList.get(i);
            Paint paint = new Paint();
            paint.setTextSize(30);
            paint.setColor(Color.BLACK);
            Bitmap qrCode = EncodingUtils.createQRCode(dataBean.getCode(), 250, 250, null);
            Bitmap bitmap3 = Bitmap.createBitmap(550, 1300, Bitmap.Config.ARGB_8888);

            Canvas canvas3 = new Canvas(bitmap3);
            canvas3.drawColor(MyApplication.getContext().getResources().getColor(R.color.white));
            paint.setTextSize(55);
            drawText(canvas3, dataBean.getName(), 450, 300, paint, 90);
            drawText(canvas3, dataBean.getPtitle(), 450, 1050, paint, 90);
            paint.setTextSize(35);
            drawText(canvas3, "日期：" + dataBean.getTime(), 370, 300, paint, 90);
            if (!TextUtils.isEmpty(dataBean.getPname())) {
                drawText(canvas3, "区域：" + dataBean.getPname(), 320, 300, paint, 90);
                drawText(canvas3, "区域：" + dataBean.getPname(), 370, 1050, paint, 90);
            }else{
                drawText(canvas3, "区域：无" , 320, 300, paint, 90);
                drawText(canvas3, "区域：无" , 370, 1050, paint, 90);
            }
            if (TextUtils.isEmpty(dataBean.getSeat())) {
                drawText(canvas3, "座位：无座" , 270, 300, paint, 90);
                drawText(canvas3, "座位：无座", 320, 1050, paint, 90);
            }else{
                drawText(canvas3, "座位：" + dataBean.getSeat(), 270, 300, paint, 90);
                drawText(canvas3, "座位：" + dataBean.getSeat(), 320, 1050, paint, 90);
            }
            if(!TextUtils.isEmpty(dataBean.getPlace1())){
                drawText(canvas3, "场馆:" + dataBean.getPlace1(), 220, 300, paint, 90);
            }else{
                drawText(canvas3, "场馆:无" , 220, 300, paint, 90);

            }
            drawText(canvas3, "票价：" + dataBean.getPrice(), 170, 300, paint, 90);
            drawText(canvas3, "票价：" + dataBean.getPrice(), 270, 1050, paint, 90);

            paint.setTextSize(30);
            canvas3.drawBitmap(qrCode, 150, 0, paint);
            drawText(canvas3, dataBean.getCode(), 120, 30, paint, 90);
            drawText(canvas3, dataBean.getCode(), 180, 1050, paint, 90);
            paint.setTextSize(25);
            drawText(canvas3, tisi1, 100, 300, paint, 90);
            drawText(canvas3, tisi2, 75, 300, paint, 90);
            drawText(canvas3, tisi3, 50, 300, paint, 90);
            drawText(canvas3, tisi4, 0, 0, paint, 90);
            String s = saveBitmap(MyApplication.getContext(), rotateBitmap(bitmap3, 0), "list" + i);
            strings.add(s);
        }


        return strings;
    }

    /**
     * time : 2018-09-20 19:00-22:00
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
    public static String CreateTicketBitmap(PrintTicket.DataBean dataBean) {
        Paint paint = new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.BLACK);
        Bitmap qrCode = EncodingUtils.createQRCode(dataBean.getCode(), 250, 250, null);
        Bitmap bitmap3 = Bitmap.createBitmap(550, 1300, Bitmap.Config.ARGB_8888);

        Canvas canvas3 = new Canvas(bitmap3);
        canvas3.drawColor(MyApplication.getContext().getResources().getColor(R.color.white));
        paint.setTextSize(55);
        drawText(canvas3, dataBean.getName(), 450, 300, paint, 90);
        drawText(canvas3, dataBean.getPtitle(), 450, 1050, paint, 90);
        paint.setTextSize(35);
        drawText(canvas3, "日期：" + dataBean.getTime(), 370, 300, paint, 90);
        drawText(canvas3, "区域：" + dataBean.getPname(), 320, 300, paint, 90);
        drawText(canvas3, "座位：" + dataBean.getSeat(), 270, 300, paint, 90);
        drawText(canvas3, "场馆:" + dataBean.getPlace1(), 220, 300, paint, 90);
        drawText(canvas3, "票价：" + dataBean.getPrice(), 170, 300, paint, 90);

        drawText(canvas3, "区域：" + dataBean.getPname(), 370, 1050, paint, 90);
        drawText(canvas3, "座位：" + dataBean.getSeat(), 320, 1050, paint, 90);
        drawText(canvas3, "票价：" + dataBean.getPrice(), 270, 1050, paint, 90);

        paint.setTextSize(30);
        canvas3.drawBitmap(qrCode, 150, 0, paint);
        drawText(canvas3, dataBean.getCode(), 120, 30, paint, 90);
        drawText(canvas3, dataBean.getCode(), 180, 1050, paint, 90);
        paint.setTextSize(25);
        drawText(canvas3, tisi1, 100, 300, paint, 90);
        drawText(canvas3, tisi2, 75, 300, paint, 90);
        drawText(canvas3, tisi3, 50, 300, paint, 90);
        drawText(canvas3, tisi4, 0, 0, paint, 90);


//        return rotateBitmap(bitmap3, 270);
        return saveBitmap(MyApplication.getContext(), rotateBitmap(bitmap3, 0));
    }

    static void drawText(Canvas canvas, String text, float x, float y, Paint paint, float angle) {
        if (angle != 0) {
            canvas.rotate(angle, x, y);

        }
        canvas.drawText(text, x, y, paint);
        if (angle != 0) {
            canvas.rotate(-angle, x, y);
        }
    }


    private static Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }


    public static final String IMAGE_NAME = "BFAHHIOK456852";
    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";


    public static String saveBitmap(Context context, Bitmap mBitmap, String name) {
        String savePath;
        File filePic;
        savePath = context.getApplicationContext().getFilesDir()
                .getAbsolutePath()
                + IN_PATH;
        try {
            filePic = new File(savePath + name + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();

    }


    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath;
        File filePic;
        savePath = context.getApplicationContext().getFilesDir()
                .getAbsolutePath()
                + IN_PATH;
        try {
            filePic = new File(savePath + IMAGE_NAME + ".jpg");
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return filePic.getAbsolutePath();
    }


    public static void printTicket(Context context) {
        mUsbPrinter = new UsbPrinter(context);

        final UsbDevice dev = getCorrectDevice(context);
        if (dev != null && mUsbPrinter.open(dev)) {
            mUsbPrinter.init();

            mUsbPrinter.doFunction(Const.TX_CHK_BMARK, 0, 0); //打印前寻找黑标
            mUsbPrinter.resetFont();  //重置 字体 恢复字体效果（大小、粗体等）为原始状态。
//            mUsbPrinter.doFunction(Const.TX_FONT_ULINE, Const.TX_ON, 0);
            mUsbPrinter.doFunction(Const.TX_CHK_BMARK, 0, 0); //寻找黑标黑标
            mUsbPrinter.doFunction(Const.TX_FONT_ULINE, Const.TX_OFF, 0); //没有下划线
            mUsbPrinter.doFunction(Const.TX_FONT_ROTATE, Const.TX_ON, 0);  //旋转 90 度功能 横向变竖向
//            mUsbPrinter.doFunction(Const.TX_CH_ROTATE, Const.TX_CH_ROTATE_RIGHT, 0); //右旋转
            mUsbPrinter.printQRcode("B1234568");
            mUsbPrinter.outputStringLn("");
            mUsbPrinter.outputStringLn("B1234568");
            mUsbPrinter.doFunction(Const.TX_FONT_SIZE, Const.TX_SIZE_3X, Const.TX_SIZE_2X);//字体，宽，高
            mUsbPrinter.doFunction(Const.TX_FONT_BOLD, Const.TX_ON, 0);//字体加粗
            mUsbPrinter.outputStringLn("标题");

            mUsbPrinter.doFunction(Const.TX_ALIGN, Const.TX_ALIGN_CENTER, 0);//居中
//            mUsbPrinter.doFunction(Const.TX_ALIGN, Const.TX_ALIGN_RIGHT, 0);  //右对齐
            mUsbPrinter.doFunction(Const.TX_CHINESE_MODE, Const.TX_ON, 0);//进入中文模式
            mUsbPrinter.doFunction(Const.TX_FEED, 30, 0);//走纸

//            mUsbPrinter.doFunction(Const.TX_BARCODE_HEIGHT, 15, 0);  //设置条形码高度

//            mUsbPrinter.printBarcode(Const.TX_BAR_UPCA, "12345678901");//打印条形码类型
//            mUsbPrinter.printImage(getExternalFilesDir(null).getPath() + "/../../../../a1.png");//打印目录下的图片
//            mUsbPrinter.doFunction(Const.TX_UNIT_TYPE, Const.TX_UNIT_PIXEL, 0);
//            mUsbPrinter.doFunction(Const.TX_FEED, 140, 0);
            mUsbPrinter.doFunction(Const.TX_CHK_BMARK, 0, 0); //寻找黑标黑标
//            mUsbPrinter.doFunction(Const.TX_SET_BMARK,Const.TX_BM_TEAR ,0);
//            TxDoFunction(TX_SET_BMARK,TX_BM_START,参数)，这样是设置起始打印位置相对
//            于黑标检测位置的偏移量。
//            TxDoFunction(TX_SET_BMARK,TX_BM_TEAR ,参数)，这样是切/撕纸位置相对于黑标
//            检测位置的偏移量。
            mUsbPrinter.doFunction(Const.TX_CUT, Const.TX_CUT_PARTIAL, 0);
            mUsbPrinter.close();
        }


    }


    public static UsbDevice getCorrectDevice(Context context) {
        final UsbManager usbMgr = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        final Map<String, UsbDevice> devMap = usbMgr.getDeviceList();
        for (String name : devMap.keySet()) {
            if (UsbPrinter.checkPrinter(devMap.get(name)))
                return devMap.get(name);
        }
        return null;
    }

}
