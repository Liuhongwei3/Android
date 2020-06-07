package com.tadm.exp4_z09417216;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tadm.exp4_z09417216.bean.UserBean;
import com.tadm.exp4_z09417216.db.MyDBDao;

public class EditInfoActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText editName;
    private EditText editPhone;
    private EditText editAddress;
    private EditText editEmail;
    private Button btnCancel;
    private Button btnReset;
    private Button btnSave;
    private int[] avatars = {
            R.drawable.img01, R.drawable.img02, R.drawable.img03, R.drawable.img04, R.drawable.img05,
            R.drawable.img06, R.drawable.img07, R.drawable.img08, R.drawable.img09
    };
    private MyDBDao myDBDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        initViews();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSave();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatarId = 0;
                editName.setText("");
                editPhone.setText("");
                editAddress.setText("");
                editEmail.setText("");
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditInfoActivity.this, MainActivity.class));
            }
        });
        Intent intent = getIntent();
        Boolean isAdd = intent.getBooleanExtra("add", false);
        if (isAdd) {
            btnSave.setText("添加");
        } else {
            btnSave.setText("保存");
            UserBean userBean = (UserBean) intent.getSerializableExtra("user");
            imageView.setImageResource(avatars[userBean.getAvatar()]);
            editName.setText(userBean.getName());
            editPhone.setText(userBean.getPhone());
            editAddress.setText(userBean.getAddress());
            editEmail.setText(userBean.getEmail());
        }
    }

    private void initViews() {
        editName = (EditText) findViewById(R.id.editName);
        editPhone = (EditText) findViewById(R.id.editPhone);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editEmail = (EditText) findViewById(R.id.editEmail);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnSave = (Button) findViewById(R.id.btnSave);
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
        UserBean userBean = new UserBean();
        userBean.setAvatar(avatarId);
        userBean.setName(editName.getText().toString());
        userBean.setPhone(editPhone.getText().toString());
        userBean.setAddress(editAddress.getText().toString());
        userBean.setEmail(editEmail.getText().toString());
        if(btnSave.getText().toString().equals("添加")){
            boolean flag = userBean.save();
            if (flag) {
                Toast.makeText(EditInfoActivity.this, "添加成功~", Toast.LENGTH_LONG).show();
//            startActivity(new Intent(EditInfoActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(EditInfoActivity.this, "添加失败~", Toast.LENGTH_LONG).show();
            }
        }else {
            Intent intent = getIntent();
            int id = intent.getIntExtra("id", 0);
            int flag = userBean.update(id);
            if(flag>0){
                Toast.makeText(EditInfoActivity.this, "更新成功~", Toast.LENGTH_LONG).show();
                finish();
            }else {
                Toast.makeText(EditInfoActivity.this, "更新失败~", Toast.LENGTH_LONG).show();
            }
        }
//        myDBDao = new MyDBDao(EditInfoActivity.this);
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", editName.getText().toString());
//        contentValues.put("phone", editPhone.getText().toString());
//        contentValues.put("address", editAddress.getText().toString());
//        contentValues.put("email", editEmail.getText().toString());
//        contentValues.put("avatar", avatarId);
////        boolean existed = myDBDao.isExisted("contact", null, "name=?", new String[]{editName.getText().toString()});
////        if (existed) {
//            Toast.makeText(EditInfoActivity.this, "该联系人已经被添加，请进入主页选择再修改~", Toast.LENGTH_LONG).show();
//            editName.requestFocus(editName.getText().toString().length());
////            return;
////        }
//        boolean flag = myDBDao.insert("userBean", contentValues);
    }
}
