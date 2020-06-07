/*
 * https://liuhongwei3.github.io Inc.
 * Name: MainActivity.java
 * Date: 20-6-7 上午8:38
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.dailyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tadm.dailyapplication.bean.TextBean;
import com.tadm.dailyapplication.service.MusicService;

import org.litepal.LitePal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class
MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editQuery;
    private Spinner searchItem;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private List<TextBean> list;
    private int pos = 0;
    private int spId1;
    private int spId2;
    private int spId3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        LitePal.getDatabase();
        registerForContextMenu(listView);
        soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 0);
        spId1 = soundPool.load(MainActivity.this, R.raw.add, 1);
        spId2 = soundPool.load(MainActivity.this, R.raw.update, 1);
        spId3 = soundPool.load(MainActivity.this, R.raw.del, 1);
    }

    private void initViews() {
        listView = findViewById(R.id.listView);
        editQuery = (EditText) findViewById(R.id.edit_query);
        searchItem = (Spinner) findViewById(R.id.search_item);
        mediaPlayer = new MediaPlayer();
        refresh(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ShowTextActivity.class);
                TextBean textBean = list.get(position);
                intent.putExtra("text", textBean);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                System.out.println("----------"+pos);
                pos = position;
                return false;
            }
        });
    }

    public void searchText(View view) {
        String keyword = editQuery.getText().toString();
        String queryItem = searchItem.getSelectedItem().toString();
        switch (queryItem) {
            case "按内容":
                if (keyword.equals("")) {
                    refresh(true);
                } else {
                    List<TextBean> textBeanContentList = LitePal.where("content like ?", "%" + keyword + "%").find(TextBean.class);
                    list = textBeanContentList;
                    refresh(false);
                }

                break;
            case "按心情":
                if (keyword.equals("")) {
                    refresh(true);
                } else {
                    List<TextBean> textBeanMoodList = LitePal.where("mood = ?", keyword).find(TextBean.class);
                    list = textBeanMoodList;
                    refresh(false);
                }
                break;
            case "按日期":
                if (keyword.equals("")) {
                    refresh(true);
                } else {
                    List<TextBean> textBeanDateList = LitePal.where("date = ?", keyword).find(TextBean.class);
                    list = textBeanDateList;
                    refresh(false);
                }
                break;
            case "按时间":
                if (keyword.equals("")) {
                    refresh(true);
                } else {
                    List<TextBean> textBeanTimeList = LitePal.where("time like ?", "%" + keyword + "%").find(TextBean.class);
                    list = textBeanTimeList;
                    refresh(false);
                }
                break;
        }
    }

    public void addText(View view) {
        soundPool.play(spId1, 1, 1, 1, 0, 1);
        Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
        intent.putExtra("add", true);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.oper, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_one:
                soundPool.play(spId3, 1, 1, 1, 0, 1);
                int id = list.get(pos).getId();
                int flag1 = LitePal.delete(TextBean.class, id);
                if (flag1 > 0) {
                    Toast.makeText(MainActivity.this, "删除成功~", Toast.LENGTH_LONG).show();
                    refresh(true);
                } else {
                    Toast.makeText(MainActivity.this, "删除失败~", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_delete_all:
                int flag2 = LitePal.deleteAll(TextBean.class);
                if (flag2 > 0) {
                    Toast.makeText(MainActivity.this, "删除成功~", Toast.LENGTH_LONG).show();
                    refresh(true);
                } else {
                    Toast.makeText(MainActivity.this, "删除失败~", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_update_one:
                soundPool.play(spId2, 1, 1, 1, 0, 1);
                Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
                id = list.get(pos).getId();
                TextBean textBean = list.get(pos);
                intent.putExtra("text", textBean);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh(true);
    }

    public void refresh(boolean flag) {
        if (flag) {
            list = LitePal.findAll(TextBean.class);
        }
        List<Map<String, Object>> data = new ArrayList<>();
        if (list.size() == 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("noResult", "暂时没有找到符合条件的内容!");
            data.add(map);
            listView.setAdapter(new SimpleAdapter(MainActivity.this, data, R.layout.no_result_layout,
                    new String[]{"noResult"}, new int[]{R.id.no_result}));
        } else {
            String weather = "";
            for (int i = 0; i < list.size(); i++) {
                weather = (list.get(i).getWeather() != null) ? list.get(i).getWeather() : "暂无数据";
                Map<String, Object> map = new HashMap<>();
                map.put("time", list.get(i).getTime());
                map.put("date", list.get(i).getDate());
                map.put("weather", weather);
                map.put("mood", list.get(i).getMood());
                map.put("content", list.get(i).getContent());
                data.add(map);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, data, R.layout.list_item,
                    new String[]{"time", "date", "mood", "weather", "content"},
                    new int[]{R.id.tv_time, R.id.tv_date, R.id.tv_mood, R.id.tv_main_weather, R.id.tv_content});
            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data, String textRepresentation) {
                    if (view instanceof TextView && view.getId() == R.id.tv_content || view.getId() == R.id.tv_main_weather) {
                        ((TextView) view).setSingleLine(true);
                        ((TextView) view).setEllipsize(TextUtils.TruncateAt.MARQUEE);
                        view.setSelected(true);
//                    view.setFocusable(true);
//                    view.setFocusableInTouchMode(true);
                    }
                    return false;
                }
            });
            listView.setAdapter(simpleAdapter);
        }
    }

    //  选项菜单
    //  加载 menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  加载 menu.xml 文件
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //  响应菜单项的点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.about: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Tadm 心情记介绍");
                builder.setIcon(R.drawable.logo);
                String str = readFromRaw();
                builder.setMessage(str);
                builder.setPositiveButton("OK", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }
            case R.id.orange:
                listView.setBackgroundColor(Color.rgb(255, 178, 102));
                break;
            case R.id.pink:
                listView.setBackgroundColor(Color.rgb(255, 153, 153));
                break;
            case R.id.white:
                listView.setBackgroundColor(Color.WHITE);
                break;
            case R.id.play_menu:
                requestPermission();
//                    readMusicFile();
                intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra("flag", "play");
                startService(intent);
                break;
            case R.id.next_menu:
                requestPermission();
//                    readMusicFile();
                intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra("flag", "next");
                startService(intent);
                break;
            case R.id.stop_menu:
//                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
//                    mediaPlayer.stop();
//                }
                intent = new Intent(MainActivity.this, MusicService.class);
                stopService(intent);
                break;
            case R.id.tadm_player:
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://tadm.gitee.io/tadm-player-react/"));
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void requestPermission() {
        //  动态权限申请
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
    }

    private void readMusicFile() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {    //  可用
            //  /sdcard/Music/鹿先森乐队 - 春风十里.mp3
            File path = Environment.getExternalStorageDirectory();
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(path + "/Music/鹿先森乐队 - 春风十里.mp3");
                mediaPlayer.prepare();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String readFromRaw() {
        String result = "";
        InputStream inputStream = getResources().openRawResource(R.raw.about);
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

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
        soundPool.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        soundPool.release();
    }
}
