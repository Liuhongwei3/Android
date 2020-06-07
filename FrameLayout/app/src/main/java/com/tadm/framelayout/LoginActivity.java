package com.tadm.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText1 = findViewById(R.id.editName);
        editText2 = findViewById(R.id.editPwd);
        button = findViewById(R.id.btnRegister);
        editText1.setHint("please input name");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText1.getText().toString();
                String pwd = editText2.getText().toString();

                System.out.println(name);
            }
        });
    }
}
