package com.huapintang.cashregister.fragment.take.codetake;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.MyApplication;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.bean.PrintTicket;
import com.huapintang.cashregister.databinding.FragmentCodeTakeBinding;
import com.huapintang.cashregister.fragment.BaseFragment;
import com.huapintang.cashregister.fragment.BaseFragmetView;
import com.huapintang.cashregister.fragment.Home.view.HomeFragment;
import com.huapintang.cashregister.fragment.take.eqtake.EqTakeFragment;
import com.huapintang.cashregister.fragment.take.order.OrderFragment;
import com.huapintang.cashregister.utils.LogUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

import static com.zhy.http.okhttp.OkHttpUtils.post;

/**
 * A simple {@link Fragment} subclass.
 */
public class CodeTakeFragment extends BaseFragment implements BaseFragmetView {
    private FragmentCodeTakeBinding binding;
private Context context;
    public CodeTakeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
context=MyApplication.getContext();
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_code_take, container, false);
        editText = binding.etTicketCode;
        initListener();
//        ABCKeyboard keyboard = new ABCKeyboard(this, R.xml.qwerty);
        binding.myNumkeyboard.setKeyboard(new Keyboard(context, R.xml.num));
        binding.myNumkeyboard.setEnabled(true);
        binding.myNumkeyboard.setOnKeyboardActionListener(listener);
        binding.myAbcKeyboardView.setKeyboard(new Keyboard(context, R.xml.qwerty));
        binding.myAbcKeyboardView.setEnabled(true);
        binding.myAbcKeyboardView.setOnKeyboardActionListener(listener);
        binding.myAsdKeyboardView.setKeyboard(new Keyboard(context, R.xml.asd));
        binding.myAsdKeyboardView.setEnabled(true);
        binding.myAsdKeyboardView.setOnKeyboardActionListener(listener);
        binding.myAbc2KeyboardView.setKeyboard(new Keyboard(context, R.xml.zxc));
        binding.myAbc2KeyboardView.setEnabled(true);
        binding.myAbc2KeyboardView.setOnKeyboardActionListener(listener);

        binding.btnBack.setOnClickListener(this);
        binding.ivDelete.setOnClickListener(this);
        binding.ivSure.setOnClickListener(this);

        binding.llEqcodeTake.setOnClickListener(this);

        binding.etTicketCode.setInputType(InputType.TYPE_NULL);
        binding.etTicketCode.requestFocus();
        return binding.getRoot();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                replaceFragment(new HomeFragment());
                 break;
            case R.id.ll_eqcode_take:
                EqTakeFragment eqTakeFragment = new EqTakeFragment();
                replaceFragment(eqTakeFragment);
//                Intent intent=new Intent();
//                intent.setClass(CodeTakeActivity.this, com.huapintang.selfcollecttickets.activity.EQCodeActivity.class);
//                startActivity(intent);
//                finish();
                break;

            case R.id.iv_delete:
                Editable editable = editText.getText();
                int start = editText.getSelectionStart();
                if (start > 0) {
                    editable.delete(start - 1, start);
                }
                break;

            case R.id.iv_sure:
                showDialog();
                post()
                        .url(Config.PrintUrl)
                        .addParams("code",editText.getText().toString())
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
//                                Toast.makeText(CodeTakeActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                setNetErrer();
                                dismissDialog();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtils.e("测试数据:",response);
                                dismissDialog();
                                PrintTicket printTicket
                                        = JSON.parseObject(response, new TypeReference<PrintTicket>() {
                                });

                                if (printTicket.isStatus()) {
//                                    Intent intent = new Intent();
                                    OrderFragment orderFragment = new OrderFragment();
                                    Bundle extras = new Bundle();
                                    extras.putParcelableArrayList("PrintTicket", (ArrayList< PrintTicket.DataBean>) printTicket.getData());
                                    orderFragment.setArguments(extras);
                                    replaceFragment(orderFragment);
//                                    intent.putExtras(extras);
//                                    intent.setClass(MyApplication.getContext(), com.huapintang.selfcollecttickets.activity.OrderActivity.class);
//                                    startActivity(intent);
                                    editText.setText("");
                                } else {
                                    showPrompt(printTicket.getMsg());
//                                    Toast.makeText(MyApplication.getContext(), printTicket.getMsg(), Toast.LENGTH_SHORT).show();
//                                    Intent intent=new Intent();
//                                    Bundle extras = new Bundle();
//                                    extras.putString ("Style","eqcode");
//                                    extras.putString("eqMsg",printTicket.getMsg());
//                                    intent.putExtras(extras);
//                                    intent.setClass(MyApplication.getContext(), MsgDialogActivity.class);
//                                    startActivityForResult(intent,IntentCode);
                                }


                            }
                        });


                break;

        }


        super.onClick(v);
    }


    private final int IntentCode=1530;

    private KeyboardView.OnKeyboardActionListener listener;
    private EditText editText;

    private void initListener() {
        listener = new KeyboardView.OnKeyboardActionListener() {

            @Override
            public void swipeUp() {
            }

            @Override
            public void swipeRight() {
            }

            @Override
            public void swipeLeft() {
            }

            @Override
            public void swipeDown() {
            }

            @Override
            public void onText(CharSequence text) {
            }

            @Override
            public void onRelease(int primaryCode) {
            }

            @Override
            public void onPress(int primaryCode) {
            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                Editable editable = editText.getText();
                int start = editText.getSelectionStart();
//            case Keyboard.KEYCODE_DELETE:
//                if (editable != null && editable.length() > 0) {
//                    if (start > 0) {
//                        editable.delete(start - 1, start);
//                    }
//                }
//                break;
//            case Keyboard.KEYCODE_CANCEL:
//                keyboardView.setVisibility(View.GONE);
//                break;
                //小写转大写
                if (primaryCode <= 122 && primaryCode >= 97) {
                    primaryCode -= 32;
                }
                editable.insert(start, Character.toString((char) primaryCode));
            }
        };
    }
}
