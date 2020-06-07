package com.tadm.exp3_z09417216;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerHome extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout layout;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_home);
        initViews();
    }

    public void doJump(View view){
        Intent intent = new Intent(ViewPagerHome.this,SightsList.class);
        startActivity(intent);
    }

    private void initViews() {
        layout = findViewById(R.id.layout);
        viewPager = findViewById(R.id.viewpager);

        int[] imgs = {R.drawable.w02, R.drawable.w03, R.drawable.w04};
        View view = getLayoutInflater().inflate(R.layout.activity_game_list, null);

        final List<View> pages = new ArrayList<>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView img = new ImageView(ViewPagerHome.this);
            img.setImageResource(imgs[i]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setFitsSystemWindows(true);
            pages.add(img);
        }
        pages.add(view);
        viewPager.setAdapter(new MyNewPagerAdapter(pages));

        button = pages.get(3).findViewById(R.id.doJump);
        System.out.println(button);
        //  jump
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");
                Intent intent = new Intent(ViewPagerHome.this,SightsList.class);
                startActivity(intent);
            }
        });

        int id = Integer.MAX_VALUE - Integer.MAX_VALUE % pages.size();
        viewPager.setCurrentItem(id);

        //动态添加导航点到LinearLayout中
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(25, 25);
        params.setMargins(8, 5, 8, 0);
        for (int i = 0; i < pages.size(); i++) {
            ImageView img = new ImageView(ViewPagerHome.this);
            if (i == 0)
                img.setImageResource(R.drawable.page_on);
            else
                img.setImageResource(R.drawable.page_off);
            layout.addView(img, params);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                int pos = i % pages.size();
                for (int j = 0; j < pages.size(); j++) {
                    ImageView img = (ImageView) layout.getChildAt(j);
                    if (j == pos) {
                        img.setImageResource(R.drawable.page_on);
                    } else {
                        img.setImageResource(R.drawable.page_off);
                    }
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}

class MyNewPagerAdapter extends PagerAdapter {
    private List<View> pages;

    public MyNewPagerAdapter(List<View> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int pos = position % pages.size();
        container.addView(pages.get(pos));
        return pages.get(pos);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        int pos = position % pages.size();
        container.removeView(pages.get(pos));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}