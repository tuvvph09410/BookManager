package com.edu.fpoly.bookmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.adapter.SachAdapter;
import com.edu.fpoly.bookmanager.dao.SachDao;
import com.edu.fpoly.bookmanager.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDao sachDAO;
    EditText edThang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TOP 10 SÁCH BÁN CHẠY");
        setContentView(R.layout.activity_luot_sach_ban_chay);
        lvBook = (ListView) findViewById(R.id.lvBookTop);
        edThang = (EditText) findViewById(R.id.edThang);
    }
    public void VIEW_SACH_TOP_10(View view){
        if (Integer.parseInt(edThang.getText().toString())>13 || Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getApplicationContext(),"Không đúng định dạng tháng (112)",Toast.LENGTH_SHORT).show();
        }else {
            sachDAO = new SachDao(LuotSachBanChayActivity.this);
    dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new SachAdapter(this, dsSach);
            lvBook.setAdapter(adapter);
        }
    }
    }
