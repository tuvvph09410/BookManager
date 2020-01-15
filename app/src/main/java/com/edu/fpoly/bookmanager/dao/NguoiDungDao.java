package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.NguoiDung;

public class NguoiDungDao  {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;
    public static final  String TABLE_NAME="nguoidung";
    public static final String SQL_NGUOI_DUNG="CREATE TABLE NguoiDung ( username text primary key, password text, phone text, hoten text)";
    public static final String TAG="NguoiDungDao";
    public NguoiDungDao(Context context){
        databaseHelper=new DatabaseHelper(context);
        db=databaseHelper.getWritableDatabase();
    }
    public long insertNguoiDung(NguoiDung nguoidung){
        ContentValues values=new ContentValues();
        values.put("username",nguoidung.getUserName());
        values.put("password",nguoidung.getPassWord());
        values.put("phone",nguoidung.getPhone());
        values.put("hoten",nguoidung.getHoTen());
        try {
            if (db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    public int checkLogin(String username, String password){
        int result= db.delete(TABLE_NAME,"username=? AND password=?",new String[]{username,password} );
        if (result==0){
            return  -1;
        }
        return 1;
    }
}
