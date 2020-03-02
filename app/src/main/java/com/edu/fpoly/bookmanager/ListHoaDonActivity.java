package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.edu.fpoly.bookmanager.adapter.HoaDonAdapter;
import com.edu.fpoly.bookmanager.dao.HoaDonDao;
import com.edu.fpoly.bookmanager.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonActivity extends AppCompatActivity {
    public List<HoaDon> dsHoaDon = new ArrayList<>();
    ListView lvHoaDon;
    HoaDonAdapter adapter = null;
    HoaDonDao hoaDonDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don);
        setTitle("HOÁ ĐƠN");
        lvHoaDon = (ListView) findViewById(R.id.lvHoaDon);
        hoaDonDao = new HoaDonDao(ListHoaDonActivity.this);
        try {
            dsHoaDon = hoaDonDao.getAllHoaDon();
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
        adapter = new HoaDonAdapter(this, dsHoaDon);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                HoaDon hoaDon = (HoaDon) adapterView.getItemAtPosition(position);
                Intent intent=new Intent(ListHoaDonActivity.this,ListHoaDonChiTietByIDActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("MAHOADON", hoaDon.getMaHoaDon());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        lvHoaDon.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearch);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return true;     }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
            Intent intent = new Intent(ListHoaDonActivity.this,HoaDonActivity.class);
            startActivity(intent);
    return(true);
        }
    return super.onOptionsItemSelected(item);
    }
}

