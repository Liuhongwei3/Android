package com.tadm.video;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tadm.framelayout.R;

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
