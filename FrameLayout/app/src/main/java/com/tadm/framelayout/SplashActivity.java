package com.tadm.framelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Handler handler;
    private ImageView imageView;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setTheme(R.style.FullScreen);

//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
//        for (int i = 0; i < progressBar.getMax(); i++){
//            progressBar.setProgress(i);
//            try {
//                Thread.sleep(1000); //  耗时任务，阻塞主线程
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        progressBar.setProgress(30);
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1 && progressBar.getProgress() < 100) {
                    progressBar.setProgress(progressBar.getProgress() + 10);
                    handler.sendEmptyMessageDelayed(1, 1000);
                }else if(progressBar.getProgress()==100){
                    handler.removeCallbacksAndMessages(null);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }
            }
        };
        //  延迟发送一条空消息
        handler.sendEmptyMessageDelayed(1, 1000);

        imageView = findViewById(R.id.imageView);
        //  放大旋转位移 动画 --- anim/alpah_animation.xml
        animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.alpah_animation);
        imageView.startAnimation(animation);

        //  帧动画 --- drawable/mydrawbleanim.xml
//        imageView.setImageResource(R.drawable.mydrawbleanim);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();

        //  应用属性动画 --- animator/myobjectanim.xml
//        Animator animator = AnimatorInflater.loadAnimator(SplashActivity.this, R.animator.myobjectanim);
//        animator.setTarget(imageView);
//        animator.start();

//        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
//        alphaAnimation.setDuration(3000);
//        //  运行后处于最后一帧的状态
//        alphaAnimation.setFillAfter(true);
//        imageView.startAnimation(alphaAnimation);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Toast.makeText(SplashActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(SplashActivity.this,MainActivity.class));
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }
}
