package com.huapintang.cashregister.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.DialogLoadingBinding;
import com.huapintang.cashregister.widget.MdStyleProgress;

/**
 * Created by cbp on 6/13 0013.
 */
public class LodingDialog  extends AlertDialog {
    DialogLoadingBinding databinding;
    public LodingDialog(Context context) {
        super(context, R.style.dialogload);


        databinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_loading, null, false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = databinding.getRoot();
        setView(root);
//        init();

        if( databinding.progress.getStatus() != MdStyleProgress.Status.LoadSuccess){
            databinding.progress.setStatus(MdStyleProgress.Status.Loading);
            databinding.progress.startAnima();
        }
    }
    public void diss(){

        this.dismiss();
    }


//    private void init() {
//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//
//    }


}
