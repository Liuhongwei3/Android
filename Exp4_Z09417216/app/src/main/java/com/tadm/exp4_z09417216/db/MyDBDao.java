package com.tadm.exp4_z09417216.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDBDao {
    private SQLiteDatabase sqLiteDatabase;
    private MyDBHelper myDBHelper;
    private Context context;

    public MyDBDao(Context context){
        myDBHelper = new MyDBHelper(context);
        sqLiteDatabase = myDBHelper.getReadableDatabase();
    }

    public boolean insert(String tableName, ContentValues contentValues){
        long flag = sqLiteDatabase.insert(tableName,null,contentValues);
        return flag != -1;
    }

    public boolean isExisted(String tableName,String[] columns,String selection,String[] args){
        Cursor cursor = sqLiteDatabase.query(tableName, columns, selection, args, null, null, null);
        return cursor != null && cursor.getCount() > 0;
    }

    public boolean update(String tableName, ContentValues contentValues) {
        int i = sqLiteDatabase.update(tableName, contentValues, null, null);
        return i > 0;
    }

    public void deleteOne(String tableName, int id) {
        String sql = "delete from " + tableName + " where _id=" + id;
        sqLiteDatabase.execSQL(sql);
    }

    public boolean deleteAll(String tableName) {
        int i = sqLiteDatabase.delete(tableName, null, null);
        return i > 0;
    }

    public void close(){
        sqLiteDatabase.close();
    }
}
