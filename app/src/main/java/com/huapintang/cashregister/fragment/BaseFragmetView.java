package com.huapintang.cashregister.fragment;

import android.content.Intent;

/**
 * Created by cbp on 3/5 0005.
 */
public interface BaseFragmetView {

    void showloadDialog();

    void dissloadDialog();

    void setNetErrer();

    void setNetErrer(String string);

    void showPrompt(String string);

//    替换
    void replaceFragment(BaseFragment fragment);

    void intentTo(Intent intent);
    void intentForResult(Intent intent,int requestCode);
}
