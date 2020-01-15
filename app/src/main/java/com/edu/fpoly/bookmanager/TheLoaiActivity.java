package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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





}
