package com.huapintang.cashregister.dialog.SelectDialog.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.huapintang.cashregister.Config;
import com.huapintang.cashregister.R;
import com.huapintang.cashregister.databinding.ActivityCalendarBinding;
import com.huapintang.cashregister.dialog.BaseDialogActivity;
import com.huapintang.cashregister.dialog.SelectDialog.mode.EventDateResult;
import com.huapintang.cashregister.utils.LogUtils;
import com.huapintang.cashregister.utils.MyDateUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.Call;

public class CalendarActivity extends BaseDialogActivity {
    private ActivityCalendarBinding binding;
    private String goodSId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar);


        final Bundle extras = getIntent().getExtras();
        goodSId = extras.getString("id");

        showDialog();


        binding = DataBindingUtil.setContentView(CalendarActivity.this, R.layout.activity_calendar);

        this.setFinishOnTouchOutside(false);

        binding.calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                LogUtils.e("date:", date.toString());
                //选取时间
                Intent intent = getIntent();
                Bundle extras1 = new Bundle();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String format = sdf.format(date.getDate());
                extras1.putString("selectDate", format);
                intent.putExtras(extras1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

//        不能选择

//        binding.calendarView.addDecorators();
//        binding.calendarView.addDecorators();
//        binding.calendarView.setOnMonthChangedListener();
//        binding.calendarView.setOnRangeSelectedListener();

        selectData();


    }

    private EventDateResult eventDateResult;


    private void selectData() {
        OkHttpUtils
                .post()
                .url(Config.EventDateUrl)
                .addParams("ukey", Config.loginResult.getData().getUkey())
                .addParams("id", goodSId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        showErrorToast();
                        dismissDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dismissDialog();
                        LogUtils.e("获取日期：", response);
                        eventDateResult = JSON.parseObject(response, new TypeReference<EventDateResult>() {
                        });
                        if(eventDateResult.isStatus()){


                            int status = eventDateResult.getData().getStatus();

                            binding.calendarView.addDecorator(new EnableOneToTenDecorator(status, eventDateResult));
                        }else{
                            showToast(eventDateResult.getMsg());
                            finish();
                        }
                    }


                });

    }


    private static class EnableOneToTenDecorator implements DayViewDecorator {
        private int status;
        private EventDateResult eventDateResult;

        public EnableOneToTenDecorator(int status, EventDateResult eventDateResult) {
            this.status = status;
            this.eventDateResult = eventDateResult;
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            Date date = day.getDate();
            if (day.isBefore(CalendarDay.from(new Date()))) {
                return true;
            }


            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (status == 3) {
                //指定日期卖票

                List<String> date1 = eventDateResult.getData().getInfo().getDate();
                for (String dateStr : date1) {
                    sdf.setLenient(false);
                    Date thisdate = null;
                    try {
                        thisdate = sdf.parse(dateStr);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    Date thisdate = new Date(dateStr);
                    if (MyDateUtils.isSameDate(thisdate, date)) {
                        return false;
                    }
                }
                return true;
            } else if (status == 1) {
                //每周循环
                EventDateResult.DataBean.InfoBean info = eventDateResult.getData().getInfo();
//                                List<String> disable = info.getDisable();
                List<String> disableDateStr = info.getDisable(); //非卖票日
                List<String> week = info.getWeek();
                for (int i = 0; i < week.size(); i++) {
                    Integer.parseInt(week.get(i));//0是周一
                }

                Calendar calendar = day.getCalendar();
                int i = calendar.get(Calendar.DAY_OF_WEEK);

                for (int a = 0; a < week.size(); a++) {
                    int weekdate = Integer.parseInt(week.get(a));//0是周一
                    if (weekdate == 6 && i == 1) {
                        //周日
                        return false;
                    } else if ((weekdate + 1) == i) {
                        return false;
                    }

                }
                return true;
            } else {
                //不限制日期
                return false;
            }
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }
    }

}
