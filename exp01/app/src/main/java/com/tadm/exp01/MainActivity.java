package com.tadm.exp01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("System.out输出");
        System.err.println("System.err输出");
        Log.v("Aty_Z09417216","Log.v输出");
        Log.d("Aty_Z09417216","Log.d输出");
        Log.i("Aty_Z09417216","Log.i输出");
        Log.w("Aty_Z09417216","Log.w输出");
        Log.e("Aty_Z09417216","Log.e输出");

    }
}
