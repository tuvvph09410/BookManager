package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.SachDao;
import com.edu.fpoly.bookmanager.dao.TheLoaiDao;
import com.edu.fpoly.bookmanager.model.Sach;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ThemSachActivity extends AppCompatActivity {
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    SachDao sachDao;
    TheLoaiDao theLoaiDao;
    Spinner spnTheLoai;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sach);
        setTitle("THÊM SÁCH");
        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        getTheLoai();
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edTenSach = (EditText) findViewById(R.id.edTenSach);
        edNXB = (EditText) findViewById(R.id.edNXB);
        edTacGia = (EditText) findViewById(R.id.edTacGia);
        edGiaBia = (EditText) findViewById(R.id.edGiaBia);
        edSoLuong = (EditText)findViewById(R.id.edSoLuong);
        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaSach.setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }
        }

    private int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i <listTheLoai.size(); i++){
            if (strTheLoai.equals(listTheLoai.get(i).getMaTheLoai())){       
            return i;  
        } 
        } 
        return 0;
    }
    public void showSpinner(View view){
        sachDao = new SachDao(ThemSachActivity.this);
        sachDao.getAllSach();
    }
    private void getTheLoai() {
        theLoaiDao = new TheLoaiDao(ThemSachActivity.this);
        listTheLoai = theLoaiDao.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }
    public void addBook(View view){
        SachDao sachDao=new SachDao(ThemSachActivity.this);
        Sach sach = new Sach(edMaSach.getText().toString(),maTheLoai,edTenSach.getText().toString(),
                edTacGia.getText().toString(),edNXB.getText().toString(),
            Double.parseDouble(edGiaBia.getText().toString()),Integer.parseInt(edSoLuong.getText ().toString()));

    try {
        if (sachDao.inserSach(sach) > 0) {
        Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
    } else {
        Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
    }
    } catch (Exception ex) {
        Log.e("Error", ex.toString());
    }
}
    public void showBook(View view){         finish();
}
}
