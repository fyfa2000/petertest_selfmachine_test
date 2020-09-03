package com.huapintang.cashregister.ui;

import android.content.Intent;

/**
 * Created by cbp on 3/4 0004.
 */
public interface BaseView {
    void showloadDialog();
    void dissloadDialog();
    void setNetErrer();
    void setNetErrer(String string);
    void showPrompt(String string);
    void intentTo(Intent intent);
    void intentForResult(Intent intent,int requestCode);

    void showMenu();


}
