package com.edu.fpoly.bookmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edu.fpoly.bookmanager.dao.NguoiDungDao;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="dbbookmanager";
    public static final int VERSION=1;
    public DatabaseHelper (Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("Drop table if exists "+NguoiDungDao.TABLE_NAME);
    }
}