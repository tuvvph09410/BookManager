package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.TheLoaiDao;
import com.edu.fpoly.bookmanager.model.TheLoai;


public class TheLoaiActivity extends AppCompatActivity {
    EditText edMaTheLoai, edTenTheLoai, edMoTa, edViTri;
    Button btnAdd, btnCancel, btnShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_the_loai);
        edMaTheLoai = (EditText) findViewById(R.id.edMaTheLoai);
        edTenTheLoai = (EditText) findViewById(R.id.edTenTheLoai);
        edMoTa = (EditText) findViewById(R.id.edMoTa);
        edViTri = (EditText) findViewById(R.id.edViTri);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edMaTheLoai.setText(b.getString("MATHELOAI"));
            edTenTheLoai.setText(b.getString("TENTHELOAI"));
            edMoTa.setText(b.getString("MOTA"));
            edViTri.setText(b.getString("VITRI"));
        }
    }

    public void showTheLoai(View view) {
        finish();
    }


    public void addTheLoai(View view) {
        TheLoaiDao theLoaiDao=new TheLoaiDao(TheLoaiActivity.this);
        TheLoai theLoai=new TheLoai(edMaTheLoai.getText().toString(),edTenTheLoai.getText().toString(),edMoTa.getText().toString(),Integer.parseInt(edViTri.getText().toString()));
        if (validateForm() > 0){
            if (theLoaiDao.insertTheLoai(theLoai) >0){
                Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Thêm Thất Bại",Toast.LENGTH_LONG).show();
            }
        }

    }
    public int validateForm(){
        int check=1;
        if (edMaTheLoai.getText().length() == 0 || edTenTheLoai.getText().length() == 0 || edMoTa.getText().length() == 0 || edViTri.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"Bạn phải nhập đầy đủ thông tin ",Toast.LENGTH_LONG).show();
            check=-1;
        }
        return check;
    }
}
