package com.tadm.mystarapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        tv1.setText(getResources().getString(R.string.introduction));
        tv2.setTextColor(getResources().getColor(R.color.colorAccent));

//        Button mBtnmain = findViewById(R.id.btn1);
//        mBtnmain.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivity(intent);
//            }
//        });

//        Button btn1 = findViewById(R.id.btn1);
        //  内部类
//        BtnListener btnListener = new BtnListener();
//        btn1.setOnClickListener(btnListener);
        //  匿名内部类
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("匿名内部类");
//            }
//        });
    }

    public void showDateDialog(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                EditText dateEt = findViewById(R.id.et2);
                dateEt.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
            //  month is not the real month,is position
            //  set by ourselves
//        }, 2020, 3, 10);
            //  set by calendar
        }, year, month, day);
        dialog.show();
    }

    public void doJump(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        EditText et = findViewById(R.id.et1);
        String name = et.getText().toString();
        EditText dateEt = findViewById(R.id.et2);
        String birth = dateEt.getText().toString();


        if (birth.equals("")) {
            Toast.makeText(MainActivity.this, "The birthday is null,Please choose it!", Toast.LENGTH_SHORT).show();
        } else if (name.equals("")) {
            Toast.makeText(MainActivity.this, "The name is null,Please input your name!", Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("name", name);
            intent.putExtra("birth", birth);
        }
        startActivity(intent);
    }

    class BtnListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            EditText et = findViewById(R.id.et1);
            String name = et.getText().toString();
            EditText dateEt = findViewById(R.id.et2);
            String birth = dateEt.getText().toString();

            if (birth.equals("")) {
                Toast.makeText(MainActivity.this, "The birthday is null,Please choose it!", Toast.LENGTH_SHORT).show();
            } else if (name.equals("")) {
                Toast.makeText(MainActivity.this, "The name is null,Please input your name!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
