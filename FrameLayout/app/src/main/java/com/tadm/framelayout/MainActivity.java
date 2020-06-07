package com.tadm.framelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv3);
        textView4 = findViewById(R.id.tv4);

        textView1.setTextColor(android.graphics.Color.RED);

        final TextView[] tvs = {
                textView1, textView2, textView3, textView4
        };

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                if (msg.what == 1) {
                    for (int i = 0; i < tvs.length; i++) {
                        tvs[i].setBackgroundColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
                    }
                    handler.sendEmptyMessageDelayed(1, 1000);
                }
            }
        };
        //  延迟发送一条空消息
        handler.sendEmptyMessageDelayed(1, 1000);
    }
}
