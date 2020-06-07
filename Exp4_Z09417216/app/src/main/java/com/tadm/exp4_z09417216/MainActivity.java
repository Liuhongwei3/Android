package com.tadm.exp4_z09417216;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tadm.exp4_z09417216.bean.UserBean;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<UserBean> list;
    private int pos = 0;
    private int[] avatars = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08, R.drawable.img09
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        LitePal.getDatabase();
        registerForContextMenu(listView);
    }

    public void initViews() {
        listView = findViewById(R.id.listView);
        refresh();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                return false;
            }
        });
    }

    public void addUser(View v) {
        Intent intent = new Intent(MainActivity.this, EditInfoActivity.class);
        intent.putExtra("add", true);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        list = LitePal.findAll(UserBean.class);
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", list.get(i).getName());
            map.put("phone", list.get(i).getPhone());
            int id = list.get(i).getAvatar();
            map.put("avatar", avatars[id]);
            data.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, data, R.layout.list_item,
                new String[]{"name", "phone", "avatar"}, new int[]{R.id.text_name, R.id.text_phone, R.id.img_avatar});
        listView.setAdapter(simpleAdapter);
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
                int id = list.get(pos).getId();
                int flag1 = LitePal.delete(UserBean.class, id);
                if (flag1 > 0) {
                    Toast.makeText(MainActivity.this, "删除成功~", Toast.LENGTH_LONG).show();
                    refresh();
                } else {
                    Toast.makeText(MainActivity.this, "删除失败~", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_delete_all:
                int flag2 = LitePal.deleteAll(UserBean.class);
                if (flag2 > 0) {
                    Toast.makeText(MainActivity.this, "删除成功~", Toast.LENGTH_LONG).show();
                    refresh();
                } else {
                    Toast.makeText(MainActivity.this, "删除失败~", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_update_one:
                Intent intent = new Intent(MainActivity.this, EditInfoActivity.class);
                id = list.get(pos).getId();
                UserBean userBean = list.get(pos);
                intent.putExtra("user", userBean);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }

}
