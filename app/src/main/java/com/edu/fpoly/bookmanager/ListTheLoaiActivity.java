package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {

    ListView lvTheLoai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_list_the_loai);
        lvTheLoai = (ListView) findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);


        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn thông tin");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_ctx_edit:
                Intent intent1 = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent1);
                return(true);
            case R.id.menu_ctx_del:
                Intent intent2 = new Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
                startActivity(intent2);
                return(true);
        }
        return super.onContextItemSelected(item);

    }
}
