/*
 * https://liuhongwei3.github.io Inc.
 * Name: VideoActivity.java
 * Date: 20-5-27 下午6:26
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.exp5_z09417216;

import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath(Environment.getExternalStorageDirectory() + "/Movies/video.mp4");
        MediaController mediaController = new MediaController(VideoActivity.this);
        videoView.setMediaController(mediaController);
    }
}
