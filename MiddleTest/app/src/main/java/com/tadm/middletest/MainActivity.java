/*
 * https://liuhongwei3.github.io Inc.
 * Name: MainActivity.java
 * Date: 20-5-6 下午12:49
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.middletest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private Animation animation;
    int oldX, newX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();

        //  after 7s go to fruit list(GridView)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, FruitGridViewActivity.class);
                startActivity(intent);
            }
        }, 7000);
    }

    private void initViews() {
        viewFlipper = findViewById(R.id.viewFlipper);

        int[] pics = {R.drawable.apple, R.drawable.banana, R.drawable.kiwi, R.drawable.longan,
                R.drawable.mango, R.drawable.orange};
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(pics[i]);
            viewFlipper.addView(imageView);
        }
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpah_animation);
        viewFlipper.startAnimation(animation);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        viewFlipper.setClickable(true);

        viewFlipper.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //  event 手指在屏幕上所有的信息
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        oldX = (int) event.getX();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        newX = (int) event.getX();
                        //  left
                        if (oldX > newX) {
                            viewFlipper.showNext();
                        } else if (newX > oldX) {
                            viewFlipper.showPrevious();
                        }
                        break;
                    }
                }
                return false;
            }
        });
    }
}
