package com.tadm.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

public class ViewFlipperLayout extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private LinearLayout layout;
    int oldX, newX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        initViews();
        setListeners();
    }

    private void initViews() {
        viewFlipper = findViewById(R.id.viewFlipper);
        layout = findViewById(R.id.point_layout);

        int[] pics = {R.drawable.wifi01, R.drawable.wifi02, R.drawable.wifi03, R.drawable.wifi04};
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(ViewFlipperLayout.this);
            imageView.setImageResource(pics[i]);
            viewFlipper.addView(imageView);
        }

        //添加导航点
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30, 30);
        params.setMargins(8, 0, 8, 0);
        for (int i = 0; i < viewFlipper.getChildCount(); i++) {
            ImageView img = new ImageView(ViewFlipperLayout.this);
            if (i == 0)
                img.setImageResource(R.drawable.page_on);
            else
                img.setImageResource(R.drawable.page_off);
            layout.addView(img, params);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        viewFlipper.setClickable(true);

        viewFlipper.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int id = viewFlipper.getDisplayedChild();
                ImageView point = (ImageView) layout.getChildAt(id);

                //  event 手指在屏幕上所有的信息
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        oldX = (int) event.getX();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        point.setImageResource(R.drawable.page_off);
                        newX = (int) event.getX();
                        //  left
                        if (oldX > newX) {
                            viewFlipper.showNext();
                        } else if (newX > oldX) {
                            viewFlipper.showPrevious();
//                            vflipper.setInAnimation(ViewFlipperActivity.this,R.anim.leftin);
//                            vflipper.setOutAnimation(ViewFlipperActivity.this,R.anim.leftout);
                        }
                        id = viewFlipper.getDisplayedChild();
                        point = (ImageView) layout.getChildAt(id);
                        point.setImageResource(R.drawable.page_on);
                        break;
                    }
                }
                return false;
            }
        });
    }
}
