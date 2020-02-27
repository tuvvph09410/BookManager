package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDao {
    private SQLiteDatabase database;
    private DatabaseHelper helper;
    public static final String TABLE_NAME="TheLoai";
    public static final String SQL_THE_LOAI="CREATE TABLE TheLoai ( matheloai text primary key, tentheloai text, mota text , vitri int)";
    public static final String TAG="TheLoaiDao";
    public TheLoaiDao(Context context){
        helper=new DatabaseHelper(context);
        database=helper.getWritableDatabase();
    }
    // insert
    public int insertTheLoai(TheLoai theLoai){
        ContentValues values=new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        try {
            if (database.insert(TABLE_NAME,null,values) == -1){
            return -1;
            }
        }
        catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    // get danh sách ra
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> loaiList=new ArrayList<>();
        Cursor cursor=database.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            TheLoai theLoai=new TheLoai();
            theLoai.setMaTheLoai(cursor.getString(0));
            theLoai.setTenTheLoai(cursor.getString(1));
            theLoai.setMoTa(cursor.getString(2));
            theLoai.setViTri(cursor.getInt(3));
            loaiList.add(theLoai);
            cursor.moveToNext();
        }
        cursor.close();
        return loaiList;
    }
    // update danh sách ra
    public int updateTheLoai(TheLoai theLoai){
        ContentValues values=new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        int result=database.update(TABLE_NAME,values,"matheloai=?",new String[]{theLoai.getMaTheLoai()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    // xóa danh sách
    public int deleteTheLoaiByID(String matheloai){
        int result=database.delete(TABLE_NAME,"matheloai=?",new String[]{matheloai});
        if (result == 0){
            return -1;
        }
        return 1;
    }
}
