package com.tadm.framelayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tadm.db.MyDBDao;

import java.io.File;
import java.io.IOException;

public class UserListActivity extends AppCompatActivity {
    private ListView userList;
    MyDBDao myDBDao = null;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    int pos = 0;
    private SoundPool soundPool;
    int spId1;
    int spId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        myDBDao = new MyDBDao(UserListActivity.this);
        initViews();
        registerForContextMenu(userList);
        soundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 0);
        spId1 = soundPool.load(UserListActivity.this, R.raw.sd1, 1);
        spId2 = soundPool.load(UserListActivity.this, R.raw.sd2, 1);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.oper, menu);
    }

    private void refresh() {
        Toast.makeText(UserListActivity.this, "删除成功", Toast.LENGTH_LONG).show();
        cursor = myDBDao.queryAll("user");
        simpleCursorAdapter.changeCursor(cursor);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_one:
                cursor.moveToPosition(pos);
                int id = cursor.getInt(0);
//                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                myDBDao.deleteOne("user", id);
                refresh();
                break;
            case R.id.menu_delete_all:
                if (myDBDao.deleteAll("user")) {
                    refresh();
                } else {
                    Toast.makeText(UserListActivity.this, "删除不成功", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menu_update_one:
                soundPool.play(spId2, 1, 1, 1, 1, 1);
                cursor.moveToPosition(pos);
                id = cursor.getInt(cursor.getColumnIndex("_id"));
                Intent intent = new Intent(UserListActivity.this, TableLayout.class);
                int avatar = cursor.getInt(cursor.getColumnIndex("avatar"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String dept = cursor.getString(cursor.getColumnIndex("dept"));
                String sdept = cursor.getString(cursor.getColumnIndex("sdept"));
                UserBean userBean = new UserBean(avatar, name, pwd, sex, dept, sdept);
                intent.putExtra("user", userBean);
                intent.putExtra("id", id);
                intent.putExtra("update", true);
                startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void initViews() {
        userList = (ListView) findViewById(R.id.userList);
        cursor = myDBDao.queryAll("user");
        simpleCursorAdapter = new SimpleCursorAdapter(UserListActivity.this, R.layout.user_list_layout, cursor,
                new String[]{"name", "dept", "avatar"}, new int[]{R.id.tv_name, R.id.tv_dept, R.id.img_head}, 1);
        simpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view instanceof ImageView && columnIndex == cursor.getColumnIndex("avatar")) {
                    int i = cursor.getInt(columnIndex);
                    int[] avatars = {
                            R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04, R.drawable.img05,
                            R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09
                    };
                    ((ImageView) view).setImageResource(avatars[i]);
                    return true;
                }
                return false;
            }
        });
        userList.setAdapter(simpleCursorAdapter);
        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                soundPool.play(spId1, 1, 1, 1, 1, 1);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.musicmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play_menu:
                //                mediaPlayer = MediaPlayer.create(UserListActivity.this, R.raw.music01);
                //                mediaPlayer.start();

                //  动态权限申请
                if (ContextCompat.checkSelfPermission(UserListActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UserListActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);
                } else {
                    readMusicFile();
                }
                break;
            case R.id.stop_menu:
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  读取外存数据
                readMusicFile();
            }
        }
    }

    private void readMusicFile() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {    //  可用
            //  sdcard/Music/music01.mp3
            File path = Environment.getExternalStorageDirectory();
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }

            try {
                mediaPlayer.setDataSource(path + "/Music/music01.mp3");
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

    @Override
    protected void onPause() {
        super.onPause();
        if (cursor != null) {
            cursor.close();
        }
        if (myDBDao != null) {
            myDBDao.close();
        }
        //  释放资源
        soundPool.release();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    //  activity stop
    @Override
    protected void onStop() {
        super.onStop();
        if (myDBDao != null) {
            myDBDao.close();
        }
    }
}
