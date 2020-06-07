package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity{
    private Button mButton;
    private Button nButton;
    private Button edittext;
    private Button mRadioBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mButton = findViewById(R.id.btn);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SecondActivity.this, TextViewActivity.class);
//                intent.putExtra("data","this is a send value");
//                startActivity(intent);
//            }
//        });
        nButton = findViewById(R.id.btn_button);
//        nButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SecondActivity.this, ButtonActivity.class);
//                startActivity(intent);
//            }
//        });
        edittext = findViewById(R.id.btn_edit);
//        edittext.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent = new Intent(SecondActivity.this, EditTextActivity.class);
////                startActivity(intent);
////            }
////        });
        mRadioBtn = findViewById(R.id.btn_Radio);
        setListeners();
    }
    private void setListeners(){
        OnClick onClick = new OnClick();
        mButton.setOnClickListener(onClick);
        nButton.setOnClickListener(onClick);
        edittext.setOnClickListener(onClick);
        mRadioBtn.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.btn:
                    intent = new Intent(SecondActivity.this, TextViewActivity.class);
                    intent.putExtra("data","this is a send value");
                    break;
                case R.id.btn_button:
                    intent = new Intent(SecondActivity.this, ButtonActivity.class);
                    break;
                case R.id.btn_edit:
                    intent = new Intent(SecondActivity.this, EditTextActivity.class);
                    break;
                case R.id.btn_Radio:
                        intent = new Intent(SecondActivity.this, RadioButtonActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
