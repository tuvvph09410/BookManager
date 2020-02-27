package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

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
    public List<NguoiDung> getAllNguoiDung(){
        List<NguoiDung> nguoiDungList=new ArrayList<>();
        Cursor cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() ==  false){
            NguoiDung nguoiDung=new NguoiDung();
            nguoiDung.setUserName(cursor.getString(0));
            nguoiDung.setPassWord(cursor.getString(1));
            nguoiDung.setPhone(cursor.getString(2));
            nguoiDung.setHoTen(cursor.getString(3));
            nguoiDungList.add(nguoiDung);
            cursor.moveToNext();
        }
        cursor.close();
        return nguoiDungList;
    }
    public int updateNguoiDung(NguoiDung nguoiDung){
        ContentValues values=new ContentValues();
        values.put("username",nguoiDung.getUserName());
        values.put("password",nguoiDung.getPassWord());
        values.put("phone",nguoiDung.getPhone());
        values.put("hoten",nguoiDung.getHoTen());
        int result=db.update(TABLE_NAME,values,"username=?",new String[]{nguoiDung.getUserName()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int changePasswordNguoiDung(NguoiDung nguoiDung){
        ContentValues values=new ContentValues();
        values.put("username",nguoiDung.getUserName());
        values.put("password",nguoiDung.getPassWord());
        int result=db.update(TABLE_NAME,values,"username=?",new String[]{nguoiDung.getUserName()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int updateInfoNguoiDung(String username,String phone,String name){
        ContentValues values=new ContentValues();
        values.put("phone",phone);
        values.put("name",name);
        int result=db.update(TABLE_NAME,values,"username=?",new String[]{username});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int deleteNguoiDungID(String username){
        int result= db.delete(TABLE_NAME,"username=?",new String[]{username});
        if (result == 0){
            return -1;
        }
        return 1;
    }
}
