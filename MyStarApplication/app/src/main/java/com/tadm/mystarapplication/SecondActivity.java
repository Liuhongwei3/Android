package com.tadm.mystarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String birth = intent.getStringExtra("birth");
        String star = "";

        TextView tv1 = findViewById(R.id.sec_tv1);
        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String s1 = getResources().getString(R.string.hello, name, birth);
        tv1.setText(s1);
        TextView tv3 = findViewById(R.id.sec_tv3);

        ImageView imageView = findViewById(R.id.img1);
        String[] data = birth.split("-");
        if (data[2].length() == 1) {
            String temp = data[2];
            data[2] = "0" + temp;
        }
        String md = data[1] + data[2];
        int mdI = Integer.parseInt(md);
//        int month = Integer.parseInt(data[1]);
//        int day = Integer.parseInt(data[2]);
        if (mdI >= 120 && mdI <= 218) {
            star = "水瓶座";
            imageView.setImageResource(R.drawable.shuiping);
            tv3.setText(R.string.shuiping);
        } else if (mdI >= 219 && mdI <= 320) {
            star = "双鱼座";
            imageView.setImageResource(R.drawable.shuangyu);
            tv3.setText(R.string.shuangyu);
        } else if (mdI >= 321 && mdI <= 419) {
            star = "白羊座";
            imageView.setImageResource(R.drawable.baiyang);
            tv3.setText(R.string.baiyang);
        } else if (mdI >= 420 && mdI <= 520) {
            star = "金牛座";
            imageView.setImageResource(R.drawable.jinniu);
            tv3.setText(R.string.jinniu);
        } else if (mdI >= 521 && mdI <= 621) {
            star = "双子座";
            imageView.setImageResource(R.drawable.shuangzi);
            tv3.setText(R.string.shuangzi);
        } else if (mdI >= 622 && mdI <= 722) {
            star = "巨蟹座";
            imageView.setImageResource(R.drawable.juxie);
            tv3.setText(R.string.juxie);
        } else if (mdI >= 723 && mdI <= 822) {
            star = "狮子座";
            imageView.setImageResource(R.drawable.shizi);
            tv3.setText(R.string.shizi);
        } else if (mdI >= 823 && mdI <= 922) {
            star = "处女座";
            imageView.setImageResource(R.drawable.chunv);
            tv3.setText(R.string.chunv);
        } else if (mdI >= 923 && mdI <= 1023) {
            star = "天秤座";
            imageView.setImageResource(R.drawable.tiancheng);
            tv3.setText(R.string.tiancheng);
        } else if (mdI >= 1024 && mdI <= 1122) {
            star = "天蝎座";
            imageView.setImageResource(R.drawable.tianxie);
            tv3.setText(R.string.tianxie);
        } else if (mdI >= 1123 && mdI <= 1221) {
            star = "射手座";
            imageView.setImageResource(R.drawable.sheshou);
            tv3.setText(R.string.sheshou);
        } else {
            star = "摩羯座";
            imageView.setImageResource(R.drawable.mojie);
            tv3.setText(R.string.mojie);
        }

        setTitle(star);
        TextView tv2 = findViewById(R.id.sec_tv2);
        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String s2 = getResources().getString(R.string.yourStar,star);
        tv2.setText(s2);
    }
}
