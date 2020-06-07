/*
 * https://liuhongwei3.github.io Inc.
 * Name: MainActivity.java
 * Date: 20-5-13 上午11:21
 * Author: Tadm
 * Copyright (c) 2020 All Rights Reserved.
 */

package com.tadm.smswork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Button buton1;
    private Button buton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        buton1 = findViewById(R.id.button);
        buton2 = findViewById(R.id.button2);

        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  写入数据
                writeFile();
            }
        });

        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  读取数据
                readFile();
            }
        });

        //  动态权限申请
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
    }

    private void writeFile() {
        String user = editText1.getText().toString();
        String content = editText2.getText().toString();
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {    //  可用
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, "myFile.txt");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("user=" + user + "\n");
                stringBuilder.append("content=" + content + "\n");
                fileOutputStream.write(stringBuilder.toString().getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFile() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {    //  可用
            //  mnt/sdcard/myFile.txt
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, "myFile.txt");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String line = bufferedReader.readLine();
                String[] strings = new String[2];
                int i = 0;
                while (line!=null){
                    strings[i] = line.split("=")[1];
                    i++;
                    line = bufferedReader.readLine();
                }
                fileInputStream.close();
                bufferedReader.close();
                editText1.setText(strings[0]);
                editText2.setText(strings[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //  读取外存数据
                readFile();
            }
        }
    }
}
