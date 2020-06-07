/*
 * https://liuhongwei3.github.io Inc.
 * Name: EditTextActivity.java
 * Date: 20-6-7 上午8:38
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.dailyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tadm.dailyapplication.bean.TextBean;
import com.tadm.dailyapplication.bean.WeatherBean;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditTextActivity extends AppCompatActivity {
    private Button btnBack;
    private Button btnSave;
    private Button btnClear;
    private EditText editContent;
    private Spinner spinnerMood;
    private TextView tvWeather;
    private String city = "巴中";
    private String unit = "℃";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        initViews();

        Intent intent = getIntent();
        new Thread() {
            @Override
            public void run() {
                super.run();
                okHttpConn();
            }
        }.start();
        Boolean isAdd = intent.getBooleanExtra("add", false);
        if (isAdd) {
            btnSave.setText("添加");
        } else {
            btnSave.setText("保存");
            TextBean textBean = (TextBean) intent.getSerializableExtra("text");
            int postion = 0;
//            assert textBean != null;
            String mood = textBean.getMood();
            String[] arrs = getResources().getStringArray(R.array.mood);
            for (int i = 0; i < arrs.length; i++) {
                if (mood.equals(arrs[i])) {
                    spinnerMood.setSelection(postion, true);
                    break;
                }
            }
            tvWeather.setText(textBean.getWeather());
            editContent.setText(textBean.getContent());
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                WeatherBean weatherBean = (WeatherBean) msg.obj;
                tvWeather.setText(weatherBean.getResult().getCity() + " " +
                        weatherBean.getResult().getWeather() + " " +
                        weatherBean.getResult().getTemp() + unit);
            }
        }
    };

    private void okHttpConn() {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url("https://api.jisuapi.com/weather/query?appkey=d546fee6a85fd842&city=" + city);
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(EditTextActivity.this, "网络连接不成功！请检查！", Toast.LENGTH_LONG).show();
                Looper.loop();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    //  Gson
                    Gson gson = new Gson();
                    WeatherBean weather = gson.fromJson(result, WeatherBean.class);
                    Message msg = handler.obtainMessage();
                    msg.what = 2;
                    msg.obj = weather;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    private void initViews() {
        btnBack = (Button) findViewById(R.id.btn_back);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnClear = (Button) findViewById(R.id.btn_clear);
        editContent = (EditText) findViewById(R.id.edit_content);
        spinnerMood = (Spinner) findViewById(R.id.spinner_mood);
        tvWeather = (TextView) findViewById(R.id.tv_weather);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditTextActivity.this, MainActivity.class));
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editContent.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
    }

    private void doSave() {
        String content = editContent.getText().toString();
        if (content.length() != 0 && content != null) {
            TextBean textBean = new TextBean();
            textBean.setTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
            textBean.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            textBean.setWeather(tvWeather.getText().toString());
            textBean.setMood(spinnerMood.getSelectedItem().toString());
            textBean.setContent(content);
            if (btnSave.getText().toString().equals("添加")) {
                boolean flag = textBean.save();
                if (flag) {
                    Toast.makeText(EditTextActivity.this, "添加成功~", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EditTextActivity.this, "添加失败！", Toast.LENGTH_LONG).show();
                }
            } else {
                Intent intent = getIntent();
                int id = intent.getIntExtra("id", 0);
                int flag = textBean.update(id);
                if (flag > 0) {
                    Toast.makeText(EditTextActivity.this, "更新成功~", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(EditTextActivity.this, "更新失败！", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Toast.makeText(EditTextActivity.this, "当前输入内容为空，本次编辑将不会被保存！", Toast.LENGTH_LONG).show();
        }
        startActivity(new Intent(EditTextActivity.this, MainActivity.class));
    }
}