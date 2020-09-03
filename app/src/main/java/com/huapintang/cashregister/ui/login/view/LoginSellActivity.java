package com.huapintang.cashregister.ui.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.ActivityLoginSellBinding;
import com.huapintang.cashregister.ui.BaseActivity;
import com.huapintang.cashregister.ui.login.presenter.LoginPresenter;
import com.huapintang.cashregister.ui.main.view.MainHomeActivity;

public class LoginSellActivity extends BaseActivity implements LoginView {

    private ActivityLoginSellBinding binding;
    private LoginPresenter presenter;
    private boolean isExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
//            extras.putBoolean("isExit",true);
            isExit = extras.getBoolean("isExit",false);
        }
//        setContentView(R.layout.activity_login_sell);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_sell);
        binding.tvLogin.setOnClickListener(this);
        binding.tvBack.setOnClickListener(this);
        presenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                Intent intent = new Intent();
                intent.setClass(this, MainHomeActivity.class);
                intentTo(intent);
                finish();
                break;
            case R.id.tv_login:
                String user = binding.etUser.getText().toString();
                String password = binding.etPassword.getText().toString();
                presenter.NetLogin(user,password);
                break;
        }
        super.onClick(v);
    }

    @Override
    public void setUserErrer() {
        showToast("账号不能为空");
    }

    @Override
    public void setPasswordErrer() {
        showToast("密码不能为空");
    }

    @Override
    public void setNetErrer() {
        showErrorToast();
    }

    @Override
    public void setNetErrer(String str) {
        showToast(str);
    }

    @Override
    public void setNetSucceed() {
        if(isExit){
            Intent home=new Intent(Intent.ACTION_MAIN);
            home.addCategory(Intent.CATEGORY_HOME);
            intentTo(home);
            finish();
        }else{
            Intent intent = new Intent();
            intent.setClass(this, MainHomeActivity.class);
            intentTo(intent);
            finish();
        }
    }

    @Override
    public void finsh() {
        finish();
    }

//    @Override
//    public void navigateToSellTicket() {
//    }


    @Override
    public void showloadDialog() {
        showDialog();
    }

    @Override
    public void dissloadDialog() {
        dismissDialog();
    }
}
