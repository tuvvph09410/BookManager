package com.edu.fpoly.bookmanager;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.NguoiDungDao;
import com.edu.fpoly.bookmanager.model.NguoiDung;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText edPass,edRePass;
    NguoiDungDao nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ĐỔI MẬT KHẨU");
        setContentView(R.layout.activity_change_password);
        setContentView(R.layout.activity_change_password);
        edPass = (EditText) findViewById(R.id.edPassword);
        edRePass = (EditText) findViewById(R.id.edRePassword);
    }
    public int validateForm(){
        int check = 1;
    if (edPass.getText().length()==0 || edRePass.getText().length() == 0) {
        Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ", Toast.LENGTH_SHORT).show();
        check = -1;
    }else {
        String pass = edPass.getText().toString();
        String rePass = edRePass.getText().toString();
        if (!pass.equals(rePass)) {
            Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
    check = -1;
        }
    }
        return check;
    }

    public void changePassword(View view) {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String strUserName = pref.getString("USERNAME","");
        nguoiDungDAO = new NguoiDungDao(ChangePasswordActivity.this);
        NguoiDung user = new NguoiDung(strUserName, edPass.getText().toString(), "", "");
        try {             if (validateForm()>0){
            if (nguoiDungDAO.changePasswordNguoiDung(user) > 0) {
                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
            }             }
            finish();
    } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
}
