package com.tadm.framelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
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

public class GameListActivity extends AppCompatActivity {
    private ListView listView;
    private GridView gridView;

    //    private ArrayAdapter<String>arrayAdapter;
    private String[] gameNames = {"打地鼠", "石头剪刀布", "扑克牌"};
    private int[] gameImgs = {R.drawable.wifi01, R.drawable.wifi02, R.drawable.wifi03};

    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> data = new ArrayList<>();

    private MyBaseAdapter myBaseAdapter;

    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        initViews();
        setListeners();

        //  绑定 contextmenu 到某个控件
        registerForContextMenu(listView);
    }

    private void setListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(GameListActivity.this, "你当前选择的游戏是；" +
//                        gameNames[position], Toast.LENGTH_LONG).show();
                index = position;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                return false;
            }
        });
    }

    //  选项菜单
    //  加载 menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  加载 menu.xml 文件
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private String readFromRaw() {
        String result = "";
        int[] filenames = {R.raw.game01, R.raw.game02, R.raw.game03};
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
            case R.id.menu1: {
                AlertDialog.Builder builder = new AlertDialog.Builder(GameListActivity.this);
                builder.setTitle("游戏介绍");
                builder.setIcon(R.mipmap.ic_launcher);
                String str = readFromRaw();
                builder.setMessage(str);
//                builder.setMessage("this is description");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(GameListActivity.this, "你单击了确定", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNeutralButton("重置", null);
                builder.setNegativeButton("取消", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }
            case R.id.menu2: {
//                startActivity(new Intent(GameListActivity.this, TableLayout.class));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //  上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.my_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1: {
                AlertDialog.Builder builder = new AlertDialog.Builder(GameListActivity.this);
                builder.setTitle("游戏介绍");
                builder.setIcon(R.mipmap.ic_launcher);
                String str = readFromRaw();
                builder.setMessage(str);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(GameListActivity.this, "你单击了确定", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNeutralButton("重置", null);
                builder.setNegativeButton("取消", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            }
            case R.id.menu2: {
                startActivity(new Intent(GameListActivity.this, TableLayout.class));
                break;
            }
            case R.id.menu3: {
                Toast.makeText(GameListActivity.this, "sub1", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.menu4: {
                Toast.makeText(GameListActivity.this, "sub2", Toast.LENGTH_LONG).show();
                break;
            }
        }
        return super.onContextItemSelected(item);
    }

    public void initViews() {
        listView = findViewById(R.id.listView);
        gridView = (GridView) findViewById(R.id.gridView);

//        arrayAdapter = new ArrayAdapter<>(GameListActivity.this, android.R.layout.simple_list_item_1,gameNames);
////        也可直接在 xml 中 entries 定义
//        listView.setAdapter(arrayAdapter);

//        for (int i = 0; i < gameNames.length; i++) {
//            Map<String,Object> map = new HashMap<>();
//            map.put("img",gameImgs[i]);
//            map.put("text", gameNames[i]);
//            data.add(map);
//        }
//        simpleAdapter = new SimpleAdapter(GameListActivity.this, data, R.layout.gamelist_item,
//                new String[]{"img","text"},new int[]{R.id.img_listitem,R.id.text_listitem});
//        listView.setAdapter(simpleAdapter);

        listView.setAdapter(new MyBaseAdapter());
        gridView.setAdapter(new MyBaseAdapter());
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return gameNames.length;
        }

        @Override
        public Object getItem(int position) {
            return gameNames[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //  加载布局文件
            //  减少 inflate 次数，通过复用 convertView 实现
//            View view = getLayoutInflater().inflate(R.layout.gamelist_item, null);
            //  减少 findViewById 的次数，通过 viewHolder 来实现
//            ImageView imageView = view.findViewById(R.id.img_listitem);
//            TextView textView = view.findViewById(R.id.text_listitem);

            MyViewHolder myViewHolder;
            if (convertView == null) {
                //  第一次必须加载
                convertView = getLayoutInflater().inflate(R.layout.gamelist_item, null);
                myViewHolder = new MyViewHolder();
                myViewHolder.imageView = convertView.findViewById(R.id.img_listitem);
                myViewHolder.textView = convertView.findViewById(R.id.text_listitem);
                convertView.setTag(myViewHolder);
            }
            else {
                myViewHolder = (MyViewHolder) convertView.getTag();
            }
            myViewHolder.imageView.setImageResource(gameImgs[position]);
            myViewHolder.textView.setText(gameNames[position]);

            return convertView;
//            return view;
        }
    }

    class MyViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
}
