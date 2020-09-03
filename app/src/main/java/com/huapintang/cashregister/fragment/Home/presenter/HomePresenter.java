package com.huapintang.cashregister.fragment.Home.presenter;

import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.fragment.Home.model.MoveBean;
import com.huapintang.cashregister.fragment.Home.view.HomeView;
import com.huapintang.cashregister.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by cbp on 3/5 0005.
 */
public class HomePresenter {

    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
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
}
