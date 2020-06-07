/*
 * https://liuhongwei3.github.io Inc.
 * Name: FruitGridViewActivity.java
 * Date: 20-5-6 下午12:49
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.middletest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FruitGridViewActivity extends AppCompatActivity {
    private GridView gridView;
//    private String[] gameNames = {"刘宏伟\nZ09417216\n常熟理工学院", "苹果", "香蕉", "猕猴桃", "龙眼", "芒果", "橘子"};
//    private int[] gameImgs = {R.mipmap.ic_launcher, R.drawable.apple, R.drawable.banana, R.drawable.kiwi,
//            R.drawable.longan, R.drawable.mango, R.drawable.orange};
    List<FruitBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_grid_view);

        initDatas();
        initViews();
        setTitle("水果列表");
    }

    private void initDatas() {
        List datas = readFromRaw();
        String[] string;
        for (int i = 1; i < datas.size(); i++) {
            FruitBean fruitBean = new FruitBean();
            string = datas.get(i).toString().split(":");
            fruitBean.setFruitImage(getResources().getIdentifier(string[0], "drawable", getPackageName()));
            fruitBean.setFruitName(string[1]);
            fruitBean.setFruitPrice(string[2]);
            fruitBean.setFruitShore(string[3]);
            list.add(fruitBean);
        }
    }

    private List readFromRaw() {
        List<String> result = new ArrayList<String>();
        InputStream inputStream = getResources().openRawResource(R.raw.products);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String content;
            while ((content = bufferedReader.readLine()) != null) {
                result.add(content);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  加载 menu.xml 文件
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu2: {
                System.out.println("放大了");
                break;
            }
            case R.id.menu3: {
                gridView.setBackgroundColor(Color.RED);
                break;
            }
            case R.id.menu4: {
                gridView.setBackgroundColor(Color.GREEN);
                break;
            }
            case R.id.menu5: {
                gridView.setBackgroundColor(Color.BLUE);
                break;
            }
            case R.id.menu6: {
                gridView.setBackgroundColor(Color.WHITE);
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    public void initViews() {
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new MyBaseAdapter());
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FruitBean fruitBean = list.get(position);

            MyViewHolder myViewHolder;
            if (convertView == null) {
                //  第一次必须加载
                convertView = getLayoutInflater().inflate(R.layout.gamelist_item, null);
                myViewHolder = new MyViewHolder();
                myViewHolder.imageView = convertView.findViewById(R.id.img_listitem);
                myViewHolder.textView1 = convertView.findViewById(R.id.text_listitem1);
                myViewHolder.textView2 = convertView.findViewById(R.id.text_listitem2);
                myViewHolder.textView3 = convertView.findViewById(R.id.text_listitem3);
                convertView.setTag(myViewHolder);
            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
//            myViewHolder.imageView.setImageResource(gameImgs[position]);
//            myViewHolder.textView.setText(gameNames[position]);
            myViewHolder.imageView.setImageResource(fruitBean.getFruitImage());
            myViewHolder.textView1.setText(fruitBean.getFruitName());
            myViewHolder.textView2.setText(fruitBean.getFruitPrice());
            myViewHolder.textView3.setText(fruitBean.getFruitShore());
            return convertView;
        }
    }

    class MyViewHolder {
        private ImageView imageView;
        private TextView textView1;
        private TextView textView2;
        private TextView textView3;
    }
}
