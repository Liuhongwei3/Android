package com.tadm.exp4_z09417216.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(@Nullable Context context) {
        super(context, "contactDb.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table userBean(_id integer primary key autoincrement,"
                +"name text,phone text,address text,eamil text,avatar integer default 0)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
