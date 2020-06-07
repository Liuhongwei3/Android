package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity {
    private Button mBtn;
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        mBtn = findViewById(R.id.btn_5);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonActivity.this, "click button test Toast with clickListener", Toast.LENGTH_SHORT).show();
            }
        });
        mTv = findViewById(R.id.tv);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ButtonActivity.this, "click textview test Toast with clickListener", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showToast(View view){
        Toast.makeText(this, "click test Toast", Toast.LENGTH_SHORT).show();
    }
}
