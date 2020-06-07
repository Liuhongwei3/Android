/*
 * https://liuhongwei3.github.io Inc.
 * Name: MainActivity.java
 * Date: 20-5-27 下午6:26
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.exp5_z09417216;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnMusic;
    private Button btnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMusic = (Button) findViewById(R.id.btn_music);
        btnVideo = (Button) findViewById(R.id.btn_video);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VideoActivity.class));
            }
        });
    }
}
