package com.edu.fpoly.bookmanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.NguoiDungDao;


public class LoginActivity extends AppCompatActivity {
    EditText edUserName, edPassword;
    Button btnLogin, btnCancel;
    CheckBox chkRememberPass;
    NguoiDungDao nguoiDungDao;
    String strUser,strPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUserName=(EditText)findViewById(R.id.edUserName);
        edPassword=(EditText)findViewById(R.id.edPassword);
        chkRememberPass=(CheckBox)findViewById(R.id.chkRememberPass);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        nguoiDungDao=new NguoiDungDao(LoginActivity.this);
    }
    public void checkLogin(View view){
        strUser=edUserName.getText().toString();
        strPass=edPassword.getText().toString();
        if (strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên Đăng Nhập và Mật khẩu không được bỏ trống",Toast.LENGTH_LONG).show();
        }else {
            if (nguoiDungDao.checkLogin(strUser,strPass) >0){
                Toast.makeText(getApplicationContext(),"Login Thành Công",Toast.LENGTH_LONG).show();
                finish();
            }
            if (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")){
            rememberUser(strUser,strPass,chkRememberPass.isChecked());
            finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên Đăng Nhập Hoặc Mật Khẩu Không Đúng",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences sharedPreferences=getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if (!status){
            // Xóa Tình Trạng lữu Trự Trước Đó
            editor.clear();
        }else {
            //Lưu Dữ Liệu
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }



}
