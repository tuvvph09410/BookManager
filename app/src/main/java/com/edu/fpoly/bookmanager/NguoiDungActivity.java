package com.edu.fpoly.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.NguoiDungDao;
import com.edu.fpoly.bookmanager.model.NguoiDung;


public class NguoiDungActivity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDao nguoiDungDao;
    EditText edUser, edPass,edRePass, edPhone, edFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("THÊM NGƯỜI DÙNG");
        btnThemNguoiDung = (Button) findViewById(R.id.btnAddUser);
        edUser = (EditText) findViewById(R.id.edUserName);
        edPass = (EditText) findViewById(R.id.edPassword);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edRePass = (EditText) findViewById(R.id.edRePassword);
    }

    public void showUsers(View view) {
        finish();
    }

    public void addUser(View view){
    nguoiDungDao=new NguoiDungDao(NguoiDungActivity.this);
        NguoiDung nguoiDung=new NguoiDung(edUser.getText().toString(),edPass.getText().toString(),edPhone.getText().toString(),edFullName.getText().toString());
        try{
            if (validateForm()>0){
                if (nguoiDungDao.insertNguoiDung(nguoiDung)>0){
                    Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Thêm Thất Bại",Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception ex){

        }
    }
    public int validateForm(){
        int check=1;
        if (edUser.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0 || edFullName.getText().length() == 0 || edPhone.getText().length() == 0 ){
            Toast.makeText(getApplicationContext(),"Bạn phải nhập đầy đủ thông tin ",Toast.LENGTH_LONG).show();
            check=-1;
        }
        else {
            String pass=edPass.getText().toString();
            String rePass=edRePass.getText().toString();
            if (!pass.equals(rePass)){
                Toast.makeText(getApplicationContext(),"Mật Khẩu không trùng khớp",Toast.LENGTH_LONG).show();
                check=-1;
            }
        }
        return check;
    }
}

