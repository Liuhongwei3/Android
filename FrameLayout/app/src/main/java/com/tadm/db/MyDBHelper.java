package com.tadm.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(@Nullable Context context) {
        //  update version to execute onUpgrade
        super(context, "test.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(_id integer primary key autoincrement,"
                + "name text,pwd text,sex text,dept text,sdept text,avatar integer default 0)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String sql = "alter table user add column avatar integer default 0";
//        String sql = "alter table user rename to user3";
//        db.execSQL(sql);
//        sql = "create table user(_id integer primary key autoincrement,"
//                + "name text,pwd text,sex text,dept text,sdept text,avatar integer)";
//        db.execSQL(sql);
//        sql = "insert into user select * from user3";
//        db.execSQL(sql);
    }
}
