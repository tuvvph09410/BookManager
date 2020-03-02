package com.edu.fpoly.bookmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.edu.fpoly.bookmanager.database.DatabaseHelper;
import com.edu.fpoly.bookmanager.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDao {
    private SQLiteDatabase sqLiteDatabase;
    private DatabaseHelper databaseHelper;

    public static final String TABLE_NAME = "Sach";
    public static final String SQL_SACH ="CREATE TABLE Sach (maSach text primary key, maTheLoai text, tensach text," + "tacGia text, NXB text, giaBia double, soLuong number);";
    public static final String TAG = "SachDAO";
    public SachDao(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public int inserSach(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("maSach",sach.getMaSach());
        values.put("maTheLoai",sach.getMaTheLoai());
        values.put("tensach",sach.getTenSach());
        values.put("tacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
       if(checkPrimaryKey(sach.getMaSach())){
            int result=sqLiteDatabase.update(TABLE_NAME,values,"masach=?",new String[]{sach.getMaSach()});
            if (result==0){
                return -1;
            }
        }else {
           try {
               long result1= sqLiteDatabase.insert(TABLE_NAME, null, values);
               if (result1 == -1) {
                   return -1;

               }
           } catch (Exception ex) {
               Log.e(TAG, ex.toString());
           }
       }
        return 1;
    }
    public List<Sach> getAllSach(){
        List<Sach> sachList=new ArrayList<>();
        Cursor c = sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            Sach sach=new Sach();
            sach.setMaSach(c.getString(0));
            sach.setMaTheLoai(c.getString(1));
            sach.setTenSach(c.getString(2));
            sach.setTacGia(c.getString(3));
            sach.setNXB(c.getString(4));
            sach.setGiaBia(c.getDouble(5));
            sach.setSoLuong(c.getInt(6));
            sachList.add(sach);
            c.moveToNext();
        }
        c.close();
        return sachList;
    }
    public int updateSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("maSach",sach.getMaSach());
        values.put("maTheLoai",sach.getMaTheLoai());
        values.put("tensach",sach.getTenSach());
        values.put("tacGia",sach.getTacGia());
        values.put("NXB",sach.getNXB());
        values.put("giaBia",sach.getGiaBia());
        values.put("soLuong",sach.getSoLuong());
        int result=sqLiteDatabase.update(TABLE_NAME,values,"maSach=?",new String[]{sach.getMaSach()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    public int deteleSachByID(String maSach){
        int result=sqLiteDatabase.delete(TABLE_NAME,"maSach=?",new String[]{maSach});
        if (result==0){
            return -1;
        }
        return 1;
    }
    public boolean checkPrimaryKey(String strPrimaryKey) {
        String[] columns = {"masach"};
        String selection = "masach=?";
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = sqLiteDatabase.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Sach checkBook(String strPrimarykey) {
        Sach sach = new Sach();
        String[] columns = {"masach"};
        String selection = "masach=?";
        String[] selectionArgs = {strPrimarykey};
        Cursor c = null;
        try {
            c = sqLiteDatabase.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
            c.moveToFirst();
            while (c.isAfterLast()==false){
            sach.setMaSach(c.getString(0));
            sach.setMaTheLoai(c.getString(1));
            sach.setTenSach(c.getString(2));
            sach.setTacGia(c.getString(3));
            sach.setNXB(c.getString(4));
            sach.setGiaBia(c.getDouble(5));
            sach.setSoLuong(c.getInt(6));
            break;
            }
        c.close();
        return sach;
    }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Sach getSachByID(String maSach){
        Sach sach=null;
        String selection="masach=?";
        String[] selectionArgs={maSach};
        Cursor c = sqLiteDatabase.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
        Log.d("getSachByID","===>"+ c.getCount());
        c.moveToFirst();
        while (c.isAfterLast()==false){
            sach=new Sach();
            sach.setMaSach(c.getString(0));
            sach.setMaTheLoai(c.getString(1));
            sach.setTenSach(c.getString(2));
            sach.setTacGia(c.getString(3));
            sach.setNXB(c.getString(4));
            sach.setGiaBia(c.getDouble(5));
            sach.setSoLuong(c.getInt(6));
            break;
        }
        c.close();
        return sach;
        }
        public List<Sach> getSachTop10(String month){
        List<Sach> sachList=new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month="0"+month;
        }
        String sSQL= "SELECT maSach, SUM(soLuong) as soluong FROM HoaDonChiTiet INNER JOIN HoaDon " + "ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon WHERE strftime('%m',HoaDon.ngayMua) = '"+month+"' " + "GROUP BY maSach ORDER BY soluong DESC LIMIT 10";
        Cursor cursor=sqLiteDatabase.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Sach sach=new Sach();
            sach.setMaSach(cursor.getString(0));
            sach.setSoLuong(cursor.getInt(1));
            sach.setGiaBia(0);
            sach.setMaTheLoai("");
            sach.setTenSach("");
            sach.setTacGia("");
            sach.setNXB("");
            sachList.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return sachList;
    }
}
