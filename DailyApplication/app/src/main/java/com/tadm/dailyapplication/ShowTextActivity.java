/*
 * https://liuhongwei3.github.io Inc.
 * Name: ShowTextActivity.java
 * Date: 20-6-7 上午8:38
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.dailyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tadm.dailyapplication.bean.TextBean;

public class ShowTextActivity extends AppCompatActivity {
    private TextView tvShowDateTime;
    private TextView tvShowWeather;
    private TextView tvShowContent;
    private TextView tvShowMood;
    private Button btnShowBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);
        initViews();
        btnShowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowTextActivity.this, MainActivity.class));
            }
        });
    }

    private void initViews() {
        tvShowDateTime = (TextView) findViewById(R.id.tv_show_date_time);
        tvShowWeather = (TextView) findViewById(R.id.tv_show_weather);
        tvShowContent = (TextView) findViewById(R.id.tv_show_content);
        btnShowBack = (Button) findViewById(R.id.btn_show_back);
        tvShowMood = (TextView) findViewById(R.id.tv_show_mood);

        Intent intent = getIntent();
        TextBean textBean = (TextBean) intent.getSerializableExtra("text");
        tvShowDateTime.setText(textBean.getDate() + " - - - " + textBean.getTime());
        String weather = (textBean.getWeather() != null) ? textBean.getWeather() : "暂无数据";
        tvShowWeather.setText(weather);
        tvShowMood.setText(textBean.getMood());
        tvShowContent.setText(textBean.getContent());
    }
}