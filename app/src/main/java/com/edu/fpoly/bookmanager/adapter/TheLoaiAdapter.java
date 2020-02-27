package com.edu.fpoly.bookmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fpoly.bookmanager.R;
import com.edu.fpoly.bookmanager.dao.TheLoaiDao;
import com.edu.fpoly.bookmanager.model.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    private List<TheLoai> theLoaiList;
    public Activity activity;
    public LayoutInflater inflater;
    private TheLoaiDao theLoaiDao;

    public TheLoaiAdapter(Activity activity,List<TheLoai> theLoaiList){
        super();
        this.activity=activity;
        this.theLoaiList=theLoaiList;
        this.inflater=(LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        theLoaiDao=new TheLoaiDao(activity);
    }
    @Override
    public int getCount() {
        return theLoaiList.size();
    }

    @Override
    public Object getItem(int position) {
        return theLoaiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_theloai,null);
            viewHolder.img=(ImageView)convertView.findViewById(R.id.ivIcon);
            viewHolder.txtMaTheLoai=(TextView)convertView.findViewById(R.id.tvMaTheLoai);
            viewHolder.txtTenTheLoai=(TextView)convertView.findViewById(R.id.tvTenTheLoai);
            viewHolder.imgDelete=(ImageView)convertView.findViewById(R.id.ivDelete);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    theLoaiDao.deleteTheLoaiByID(theLoaiList.get(position).getMaTheLoai());
                    theLoaiList.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
            TheLoai theLoai=theLoaiList.get(position);
            viewHolder.img.setImageResource(R.drawable.cateicon);
            viewHolder.txtMaTheLoai.setText(theLoai.getMaTheLoai());
            viewHolder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
        }
        return convertView;
    }
    public static class ViewHolder{
        ImageView img;
        TextView txtMaTheLoai;
        TextView txtTenTheLoai;
        ImageView imgDelete;
    }
    public void  notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<TheLoai> theLoaiList){
        this.theLoaiList=theLoaiList;
        notifyDataSetChanged();
    }
}
