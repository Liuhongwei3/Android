package com.tadm.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tadm.db.MyDBDao;

public class TableLayout extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    private EditText editText1, editText2;
    private RadioButton radioButtonMale, radioButtonFemale;
    private Spinner spinner1, spinner2;
    private Button buttonRegister, buttonLogin;
    private int[] depts = {R.array.jsj, R.array.wgy, R.array.renwen};
    private int[] avatars = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04, R.drawable.img05,
            R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09
    };
    MyDBDao myDBDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        setTitle("TableLayout-Z09417216");
        initViews();
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(TableLayout.this,
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
                Toast.makeText(TableLayout.this, "你当前选择的是：" +
                                parent.getSelectedItem().toString()
//                        parent.getItemAtPosition(position).toString()
//                        parent.getSelectedItemPosition()
                        , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        Intent intent = getIntent();
        Boolean isUpdate = intent.getBooleanExtra("update", false);
        if (isUpdate) {
            textView.setText("保存");
            buttonRegister.setText("保存");
            UserBean userBean = (UserBean) intent.getSerializableExtra("user");
            int id = intent.getIntExtra("id", -1);
            editText1.setText(userBean.getName());
            editText2.setText(userBean.getPwd());
            if (userBean.getSex().equals("男")) {
                radioButtonMale.setChecked(true);
            } else {
                radioButtonFemale.setChecked(true);
            }
            String dept = userBean.getDept();
            System.out.println(dept);
            String[] arrs1 = getResources().getStringArray(R.array.school);
            for (int i = 0; i < arrs1.length; i++) {
                if (dept.equals(arrs1[i])) {
                    spinner1.setSelection(i, true);
                    break;
                }
            }
            String sdept = userBean.getSdept();
            int m = spinner1.getSelectedItemPosition();
            String[] arrs2 = getResources().getStringArray(depts[m]);
            for (int i = 0; i < arrs2.length; i++) {
                if (sdept.equals(arrs2[i])) {
                    final int finalI = i;
                    spinner2.post(new Runnable() {
                        @Override
                        public void run() {
                            spinner2.setSelection(finalI, true);
                        }
                    });
                    break;
                }
            }
            int avatarId = userBean.getAvatar();
            imageView.setImageResource(avatars[avatarId]);
        }
    }

    private void initViews() {
        textView = findViewById(R.id.textView);
        editText1 = findViewById(R.id.editName);
        editText2 = findViewById(R.id.editPwd);
        radioButtonMale = findViewById(R.id.radioButton1);
        radioButtonFemale = findViewById(R.id.radioButton2);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        buttonRegister = findViewById(R.id.btnReg);
        buttonLogin = findViewById(R.id.btnLog);
        imageView = findViewById(R.id.imageAvatar);
    }

    int avatarId = 0;

    public void chooseAvatar(View view) {
        ++avatarId;
        if (avatarId == avatars.length) {
            avatarId = 0;
        }
        imageView.setImageResource(avatars[avatarId]);
    }

    private void doSave() {
        myDBDao = new MyDBDao(TableLayout.this);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", editText1.getText().toString());
        contentValues.put("pwd", editText2.getText().toString());
        if (radioButtonMale.isChecked()) {
            contentValues.put("sex", "男");
        } else if (radioButtonFemale.isChecked()) {
            contentValues.put("sex", "女");
        } else {
            contentValues.put("sex", "未知");
        }
        contentValues.put("dept", spinner1.getSelectedItem().toString());
        contentValues.put("sdept", spinner2.getSelectedItem().toString());
        contentValues.put("avatar", avatarId);
        if (buttonRegister.getText().toString().equals("注册")) {
            //  path: data/data/packageName/database
            boolean existed = myDBDao.isExisted("user", null, "name=?", new String[]{editText1.getText().toString()});
            if (existed) {
                Toast.makeText(TableLayout.this, "该用户名已经被注册，请重新输入其他用户名~", Toast.LENGTH_LONG).show();
                editText1.requestFocus(editText1.getText().toString().length());
                return;
            }
            boolean flag = myDBDao.insert("user", contentValues);
            if (flag) {
                Toast.makeText(TableLayout.this, "注册成功~", Toast.LENGTH_LONG).show();
                startActivity(new Intent(TableLayout.this, UserListActivity.class));
            } else {
                Toast.makeText(TableLayout.this, "注册失败~", Toast.LENGTH_LONG).show();
            }
        } else {
            int id = getIntent().getIntExtra("id", -1);
            boolean flag = myDBDao.update("user", contentValues, id);
            if (flag) {
                Toast.makeText(TableLayout.this, "更新成功~", Toast.LENGTH_LONG).show();
                startActivity(new Intent(TableLayout.this, UserListActivity.class));
            } else {
                Toast.makeText(TableLayout.this, "更新失败~", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void doLogin() {
        String name = editText1.getText().toString();
        String pwd = editText2.getText().toString();
        if (!name.equals("") && !pwd.equals("")) {
            MyDBDao myDBDao = new MyDBDao(TableLayout.this);
            boolean flag = myDBDao.isExisted("user", null,
                    "name=? and pwd=?", new String[]{name, pwd});
            if (!flag) {
                Toast.makeText(TableLayout.this, "用户名或者密码错误~", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(TableLayout.this, "登录成功~", Toast.LENGTH_LONG).show();
                startActivity(new Intent(TableLayout.this, UserListActivity.class));
            }
        }
    }

    //  activity stop
    @Override
    protected void onStop() {
        super.onStop();
        if (myDBDao != null) {
            myDBDao.close();
        }
    }
}
