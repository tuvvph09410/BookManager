package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.edu.fpoly.bookmanager.adapter.NguoiDungAdapter;
import com.edu.fpoly.bookmanager.dao.NguoiDungDao;
import com.edu.fpoly.bookmanager.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class ListNguoiDungActivity extends AppCompatActivity {
    public static List<NguoiDung> nguoiDungList=new ArrayList<>();
    ListView lvNguoiDung;
    NguoiDungAdapter nguoiDungAdapter = null;
    NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("NGƯỜI DÙNG");
        setContentView(R.layout.activity_list_nguoi_dung);
        lvNguoiDung = (ListView) findViewById(R.id.lvNguoiDung);

        nguoiDungDao=new NguoiDungDao(ListNguoiDungActivity.this);
        nguoiDungList=nguoiDungDao.getAllNguoiDung();

        nguoiDungAdapter=new NguoiDungAdapter(this,nguoiDungList);
        lvNguoiDung.setAdapter(nguoiDungAdapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(ListNguoiDungActivity.this,NguoiDungDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("USERNAME",nguoiDungList.get(position).getUserName());
                bundle.putString("PHONE",nguoiDungList.get(position).getPhone());
                bundle.putString("FULLNAME",nguoiDungList.get(position).getHoTen());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        nguoiDungList.clear();
        nguoiDungList=nguoiDungDao.getAllNguoiDung();
        nguoiDungAdapter.changeDataset(nguoiDungDao.getAllNguoiDung());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.add:
                intent = new Intent(ListNguoiDungActivity.this,NguoiDungActivity.class);
                startActivity(intent);
                return(true);
            case R.id.changePass:
               intent = new Intent(ListNguoiDungActivity.this,ChangePasswordActivity.class);
              startActivity(intent);
                return(true);
            case R.id.logOut:
                SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //xoa tinh trang luu tru truoc do
                edit.clear();
                edit.commit();
                intent = new Intent(ListNguoiDungActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
