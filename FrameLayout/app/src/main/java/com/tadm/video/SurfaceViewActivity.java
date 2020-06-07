package com.tadm.video;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tadm.framelayout.R;

import java.io.IOException;

public class SurfaceViewActivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        mediaPlayer = new MediaPlayer();
        holder = surfaceView.getHolder();
        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/Movies/video.mp4");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer.setDisplay(holder);
                mediaPlayer.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}
