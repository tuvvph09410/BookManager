package com.edu.fpoly.bookmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.edu.fpoly.bookmanager.adapter.SachAdapter;
import com.edu.fpoly.bookmanager.dao.SachDao;
import com.edu.fpoly.bookmanager.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class ListThemSachActivity extends AppCompatActivity {
    public static List<Sach> dsSach=new ArrayList<>();
    ListView lvBook;
    SachAdapter adapter = null;
    SachDao sachDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Sách");
        setContentView(R.layout.activity_list_them_sach);
        lvBook = (ListView) findViewById(R.id.lvBook);
        registerForContextMenu(lvBook);
        sachDAO = new SachDao(ListThemSachActivity.this);
        dsSach = sachDAO.getAllSach();

        adapter = new SachAdapter(this, dsSach);
        lvBook.setAdapter(adapter);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Sach sach = (Sach) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(ListThemSachActivity.this,ThemSachActivity.class);
                Bundle b = new Bundle();
                b.putString("MASACH", sach.getMaSach());
                b.putString("MATHELOAI", sach.getMaTheLoai());
                b.putString("TENSACH", sach.getTenSach());
                b.putString("TACGIA", sach.getTacGia());
                b.putString("NXB", sach.getNXB());
                b.putString("GIABIA", String.valueOf(sach.getGiaBia()));
                b.putString("SOLUONG", String.valueOf(sach.getSoLuong()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvBook.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearchBook);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                System.out.println("Text [" + charSequence + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    adapter.resetData();
                }
                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_book, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(ListThemSachActivity.this,ThemSachActivity.class);
                startActivity(intent);
                return(true);
    }
        return super.onOptionsItemSelected(item);
    }
    protected void onResume() {
        super.onResume();
        dsSach.clear();
        dsSach=sachDAO.getAllSach();
        adapter.changeDataset(dsSach);

    }
}
