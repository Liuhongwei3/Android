/*
 * https://liuhongwei3.github.io Inc.
 * Name: MusicService.java
 * Date: 20-6-7 上午8:38
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.dailyapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tadm.dailyapplication.bean.MusicBean;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer = null;
    private int[] ids = {1345171, 1297709, 461347998, 32019002, 1313107065, 28468154};
    private int index = 0;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //  不随 activity
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }
//        readMusicFile();
        String flag = intent.getStringExtra("flag");
        switch (flag) {
            case "play":
                Toast.makeText(MusicService.this, "开始播放背景音乐！请稍后！", Toast.LENGTH_LONG).show();
                okHttpConnMusic();
                break;
            case "next":
                next();
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
        Toast.makeText(MusicService.this, "已停止播放背景音乐！", Toast.LENGTH_LONG).show();
    }

    //  注意：由于 Android P 限制了明文流量的网络请求，非加密的流量请求都会被系统禁止掉 导致 mediaPlayer 播放uri发生Error
    //  解决：清单文件 application add android:usesCleartextTraffic="true"
    private void playMusic(String path) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
        try {
            System.out.println("-----------" + path);
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    next();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void next() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        Toast.makeText(MusicService.this, "加载中！请稍后！", Toast.LENGTH_LONG).show();
        ++index;
        if (index >= ids.length) {
            index = 0;
        }
        okHttpConnMusic();
    }

    private void readMusicFile() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {    //  可用
            //  /sdcard/Music/鹿先森乐队 - 春风十里.mp3
            File path = Environment.getExternalStorageDirectory();
            playMusic(path + "/Music/鹿先森乐队 - 春风十里.mp3");
        }
    }

    private void okHttpConnMusic() {
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        builder.url("https://api.mtnhao.com/song/url?id=" + ids[index]);
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Looper.prepare();
                Toast.makeText(MusicService.this, "网络连接不成功！请检查！", Toast.LENGTH_LONG).show();
                Looper.loop();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    //  Gson
                    Gson gson = new Gson();
                    MusicBean musicBean = gson.fromJson(result, MusicBean.class);
                    String url = musicBean.getData().get(0).getUrl();
                    playMusic(url);
                }
            }
        });
    }
}
