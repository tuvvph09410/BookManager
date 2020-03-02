package com.edu.fpoly.bookmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edu.fpoly.bookmanager.dao.HoaDonChiTietDao;
import com.edu.fpoly.bookmanager.dao.HoaDonDao;
import com.edu.fpoly.bookmanager.dao.NguoiDungDao;
import com.edu.fpoly.bookmanager.dao.SachDao;
import com.edu.fpoly.bookmanager.dao.TheLoaiDao;
import com.edu.fpoly.bookmanager.model.HoaDon;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="dbbookmanager";
    public static final int VERSION=1;
    public DatabaseHelper (Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
    sqLiteDatabase.execSQL(TheLoaiDao.SQL_THE_LOAI);
    sqLiteDatabase.execSQL(SachDao.SQL_SACH);
    sqLiteDatabase.execSQL(HoaDonDao.SQL_HOA_DON);
    sqLiteDatabase.execSQL(HoaDonChiTietDao.SQL_HOA_DON_CHI_TIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("Drop table if exists "+NguoiDungDao.TABLE_NAME);
    sqLiteDatabase.execSQL("Drop table if exists "+TheLoaiDao.TABLE_NAME);
    sqLiteDatabase.execSQL("Drop table if exists "+SachDao.TABLE_NAME);
    sqLiteDatabase.execSQL("Drop table if exists "+HoaDonDao.TABLE_NAME);
        sqLiteDatabase.execSQL("Drop table if exists "+HoaDonChiTietDao.TABLE_NAME);
    }
}
