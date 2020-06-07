package com.tadm.music;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
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

import com.tadm.framelayout.R;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleCursorAdapter simpleCursorAdapter;
    private Cursor cursor = null;
    private MediaPlayer mediaPlayer;
    private int pos = -1;
    private SeekBar seekBar;

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
        ContentResolver contentResolver = getContentResolver();
        cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        simpleCursorAdapter = new SimpleCursorAdapter(MusicActivity.this, R.layout.user_list_layout, cursor,
                new String[]{MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST},
                new int[]{R.id.tv_name, R.id.tv_dept}, 1);
        listView.setAdapter(simpleCursorAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        handler.removeCallbacksAndMessages(null);
        if(cursor!=null){
            cursor.close();
        }
    }
}
