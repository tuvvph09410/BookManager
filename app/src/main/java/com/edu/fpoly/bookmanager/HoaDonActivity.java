package com.edu.fpoly.bookmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.edu.fpoly.bookmanager.dao.HoaDonDao;
import com.edu.fpoly.bookmanager.model.HoaDon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoaDonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText edNgayMua, edMaHoaDon;
    HoaDonDao hoaDonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        setTitle("THÊM HOÁ ĐƠN");
        setContentView(R.layout.activity_hoa_don);
        edNgayMua = (EditText) findViewById(R.id.edNgayMua);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(Calendar cal) {
        edNgayMua.setText(sdf.format(cal.getTime()));
    }
    public static class DatePickerFragment extends DialogFragment{
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }
    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");     }
    public void ADDHoaDon(View view) {
        hoaDonDAO = new HoaDonDao(HoaDonActivity.this);
        try {
            if (validation() < 0) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
                HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(),sdf.parse(edNgayMua.getText().toString()));
                if (hoaDonDAO.insertHoaDon(hoaDon) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HoaDonActivity.this,HoaDonChiTietActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MAHOADON", edMaHoaDon.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }
        public int validation(){
        if (edMaHoaDon.getText().toString().isEmpty()||edNgayMua.getText().toString().isEmpty() ){
            return -1;
        }
        return 1;
    }
}
