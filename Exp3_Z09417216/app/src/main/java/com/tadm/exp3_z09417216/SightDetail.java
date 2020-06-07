package com.tadm.exp3_z09417216;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class SightDetail extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView1;
    private TextView textView2;
    private int[] sightImgs = {R.drawable.lijiang, R.drawable.sanya, R.drawable.huangshan, R.drawable.jiuzhaigou, R.drawable.guilin,
            R.drawable.gulangyu, R.drawable.changcheng, R.drawable.zhangjiajie, R.drawable.budalagong, R.drawable.xihu};
    private String[] sightTexts = {"1、丽江", "2、三亚", "3、黄山", "4、九寨沟", "5、桂林山水",
            "6、鼓浪屿", "7、长城", "8、张家界", "9、布达拉宫", "10、西湖"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_detail);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);

        imageView = findViewById(R.id.image);
        textView1 = findViewById(R.id.subtitle);
        textView2 = findViewById(R.id.text);

        imageView.setImageResource(sightImgs[index]);
        textView1.setText(sightTexts[index]);
        String string = readFromRaw(index);
        textView2.setText(string);

        setTitle(sightTexts[index]);
    }

    private String readFromRaw(int index) {
        String result = "";
        int[] filenames = {R.raw.lijiang, R.raw.sanya, R.raw.huangshan, R.raw.jiuzhaigou, R.raw.guilin,
                R.raw.gulangyu, R.raw.changcheng, R.raw.zhangjiajie, R.raw.budalagong, R.raw.xihu};
        InputStream inputStream = getResources().openRawResource(filenames[index]);
        byte[] buffer = new byte[0];
        try {
            buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
