package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class NguoiDungDetailActivity extends AppCompatActivity {
    EditText edFullName, edPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT NGƯỜI DÙNG");
        setContentView(R.layout.activity_nguoi_dung_detail);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);

    }

    public void Huy(View view){
        finish();
    }
}
