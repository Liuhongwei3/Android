/*
 * https://liuhongwei3.github.io Inc.
 * Name: MusicActivity.java
 * Date: 20-5-27 下午6:26
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.exp5_z09417216;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.Locale;

public class MusicActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;
    private Cursor cursor = null;
    private MediaPlayer mediaPlayer;
    private int pos = -1;
    private SeekBar seekBar;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initViews();
        mediaPlayer = new MediaPlayer();
        setListeners();
    }

    private void setListeners() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //  music 随着进度条拖动，在最终位置处播放
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(seekBar.getProgress());
                    mediaPlayer.start();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                playMusic(position);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ++pos;
                if (pos == cursor.getCount()) {
                    pos = 0;
                }
                playMusic(pos);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                if (mediaPlayer.isPlaying()) {
                    handler.sendEmptyMessageDelayed(1, 500);
                } else {
                    handler.removeCallbacksAndMessages(null);
                }
            }
        }
    };

    private void playMusic(int position) {
        try {
            cursor.moveToPosition(position);
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            tvTitle.setText(title);
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    seekBar.setMax(mediaPlayer.getDuration());
                    seekBar.setProgress(0);
                }
            });
            handler.sendEmptyMessageDelayed(1, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        seekBar = findViewById(R.id.seekBar);
        listView = findViewById(R.id.listViewMusic);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ContentResolver contentResolver = getContentResolver();
        //  动态权限申请
        if (ContextCompat.checkSelfPermission(MusicActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MusicActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        } else {
            cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            simpleCursorAdapter = new SimpleCursorAdapter(MusicActivity.this, R.layout.user_list_layout, cursor,
                    new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.DURATION},
                    new int[]{R.id.tv_name, R.id.tv_singer, R.id.tv_duration}, 1);
            simpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                    //  index =10
                    int index = cursor.getColumnIndex("duration");
//                    System.out.println(cursor.getString(index));
                    if (view instanceof TextView && columnIndex == index) {
                        ((TextView) view).setText(formatSeconds(Long.valueOf(cursor.getString(index))));
                        return true;
                    }
                    return false;
                }
            });
            listView.setAdapter(simpleCursorAdapter);
        }
    }

    private String formatSeconds(Long seconds) {
        String standardTime;
        seconds /= 1000;
        if (seconds <= 0) {
            standardTime = "00:00";
        } else if (seconds < 60) {
            standardTime = String.format(Locale.getDefault(), "00:%02d", seconds % 60);
        } else if (seconds < 3600) {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d", seconds / 60, seconds % 60);
        } else {
            standardTime = String.format(Locale.getDefault(), "%02d:%02d:%02d", seconds / 3600, seconds % 3600 / 60, seconds % 60);
        }
        return standardTime;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        handler.removeCallbacksAndMessages(null);
        if (cursor != null) {
            cursor.close();
        }
    }
}
