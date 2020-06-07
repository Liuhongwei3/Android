package com.tadm.framelayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initViews();
        setListeners();
    }

    private void setListeners() {
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    private void initViews() {
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
    }

    @Override
    public void onClick(View v) {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);

        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("ok", null);
        final String[] fruits = {"apple", "orange", "mango", "banana"};
        switch (v.getId()) {
            case R.id.button:
                builder.setTitle("单选列表项对话框");
                builder.setSingleChoiceItems(fruits, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DialogActivity.this,
                                "你选中的水果是：" + fruits[which], Toast.LENGTH_LONG).show();
                    }
                });
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.button2:
                builder.setTitle("多选列表项对话框");
                boolean[] isChecked = {false, false, true, false};
                builder.setMultiChoiceItems(fruits, isChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked)
                            Toast.makeText(DialogActivity.this,
                                    "选中了" + fruits[which], Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DialogActivity.this,
                                    "取消了" + fruits[which], Toast.LENGTH_LONG).show();
                    }
                });
                dialog = builder.create();
                dialog.show();
                break;
            case R.id.button3:
                builder.setTitle("自定义布局的对话框");
                builder.setView(R.layout.dialog);
                dialog = builder.create();
                dialog.show();
                break;
        }
    }
}
