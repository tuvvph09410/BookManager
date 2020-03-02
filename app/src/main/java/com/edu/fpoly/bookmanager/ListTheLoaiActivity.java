package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.edu.fpoly.bookmanager.adapter.TheLoaiAdapter;
import com.edu.fpoly.bookmanager.dao.TheLoaiDao;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
    public static List<TheLoai> loaiList=new ArrayList<>();
    ListView lvTheLoai;
    TheLoaiAdapter theLoaiAdapter;
    TheLoaiDao theLoaiDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_list_the_loai);
        lvTheLoai = (ListView) findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDao=new TheLoaiDao(ListTheLoaiActivity.this);
        loaiList=theLoaiDao.getAllTheLoai();

        theLoaiAdapter=new TheLoaiAdapter(this,loaiList);
        lvTheLoai.setAdapter(theLoaiAdapter);


        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("MATHELOAI",loaiList.get(position).getMaTheLoai());
            bundle.putString("TENTHELOAI",loaiList.get(position).getTenTheLoai());
            bundle.putString("MOTA",loaiList.get(position).getMoTa());
            bundle.putString("VITRI",String.valueOf(loaiList.get(position).getViTri()));
            intent.putExtras(bundle);
            startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loaiList.clear();
        loaiList=theLoaiDao.getAllTheLoai();
        theLoaiAdapter.changeDataset(loaiList);

    }
}
