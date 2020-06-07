package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioButtonActivity extends AppCompatActivity {
    private RadioGroup mRg1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        mRg1 = findViewById(R.id.rg_1);
        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = radioGroup.findViewById(i);
                Toast.makeText(RadioButtonActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
