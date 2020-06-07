package com.tadm.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner1, spinner2;
    private int[] depts = {R.array.jsj, R.array.wgy, R.array.renwen};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("TableLayout-Z09417224");
        initViews();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(MainActivity.this,
                        depts[position], android.R.layout.simple_dropdown_item_1line);
                spinner2.setAdapter(arrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "你当前选择的是：" +
                        parent.getSelectedItem().toString()
//                        parent.getItemAtPosition(position).toString()
//                        parent.getSelectedItemPosition()
                        , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initViews() {
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
    }
}
