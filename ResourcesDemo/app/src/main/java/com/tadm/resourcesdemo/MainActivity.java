package com.tadm.resourcesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    int index1 = 0;
    int index2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
    }

    public void changeColor(View view){
        int[] colors = {R.color.colorAccent,R.color.colorGreen,
                R.color.colorPrimary, R.color.colorOrange};
        tv.setTextColor(getResources().getColor(colors[index1]));
        if (index1 == colors.length-1) {
            index1 = 0;
        } else {
            index1++;
        }
    }
    public void changeSize(View view){
        int[] sizes = {R.dimen.big,R.dimen.mid,R.dimen.small,R.dimen.self};
        tv.setTextSize(getResources().getDimensionPixelSize(sizes[index2]));
        if (index2 == sizes.length-1) {
            index2 = 0;
        } else {
            index2++;
        }
    }
}
