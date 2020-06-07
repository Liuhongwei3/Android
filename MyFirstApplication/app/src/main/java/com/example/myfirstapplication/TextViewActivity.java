package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

public class TextViewActivity extends AppCompatActivity {
    private TextView mtv1;
    private TextView mtv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        Intent intentText = getIntent();
        mtv1 = findViewById(R.id.tv_1);
        mtv1.setText(intentText.getStringExtra("data").toString());
        mtv3 = findViewById(R.id.tv_3);
        mtv3.setSelected(true);
    }
}
