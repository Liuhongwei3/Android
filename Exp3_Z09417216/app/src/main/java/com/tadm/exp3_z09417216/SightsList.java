package com.tadm.exp3_z09417216;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SightsList extends AppCompatActivity {
    private ListView listView;
    private String[] gameNames = {"1、丽江", "2、三亚", "3、黄山", "4、九寨沟", "5、桂林山水",
            "6、鼓浪屿", "7、长城", "8、张家界", "9、布达拉宫", "10、西湖"};
    private int[] gameImgs = {R.drawable.lijiang, R.drawable.sanya, R.drawable.huangshan, R.drawable.jiuzhaigou, R.drawable.guilin,
            R.drawable.gulangyu, R.drawable.changcheng, R.drawable.zhangjiajie, R.drawable.budalagong, R.drawable.xihu};

    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> data = new ArrayList<>();
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights_list);

        initViews();
        setListeners();
    }

    public void initViews() {
        listView = findViewById(R.id.listView);

        for (int i = 0; i < gameNames.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", gameImgs[i]);
            map.put("text", gameNames[i]);
            data.add(map);
        }
        simpleAdapter = new SimpleAdapter(SightsList.this, data, R.layout.sightslist_items,
                new String[]{"img", "text"}, new int[]{R.id.img_listitem, R.id.text_listitem});
        listView.setAdapter(simpleAdapter);
    }

    private void setListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SightsList.this, SightDetail.class);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
    }

    //  选项菜单
    //  加载 menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  加载 menu.xml 文件
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private String readFromRaw() {
        String result = "";
        int[] filenames = {R.raw.about};
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

    //  响应菜单项的点击事件
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about: {
                AlertDialog.Builder builder = new AlertDialog.Builder(SightsList.this);
                builder.setTitle("最美景点介绍");
                builder.setIcon(R.mipmap.ic_launcher);
                String str = readFromRaw();
                builder.setMessage(str);
//                builder.setMessage("this is description");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SightsList.this, "你单击了OK", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
