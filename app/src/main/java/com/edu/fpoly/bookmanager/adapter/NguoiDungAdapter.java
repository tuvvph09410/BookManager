package com.edu.fpoly.bookmanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.fpoly.bookmanager.R;
import com.edu.fpoly.bookmanager.dao.NguoiDungDao;
import com.edu.fpoly.bookmanager.model.NguoiDung;

import java.util.List;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> nguoiDungList;
    public Activity activity;
    public LayoutInflater inflater;
    private NguoiDungDao nguoiDungDao;
    public NguoiDungAdapter (Activity activity,List<NguoiDung> nguoiDungList){
        super();
        this.activity=activity;
        this.nguoiDungList=nguoiDungList;
        this.inflater=(LayoutInflater)activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao=new NguoiDungDao(activity);
    }
    @Override
    public int getCount() {
        return nguoiDungList.size();
    }

    @Override
    public Object getItem(int position) {
        return nguoiDungList.get(position);
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
            convertView=inflater.inflate(R.layout.item_nguoi_dung,null);
            viewHolder.img=(ImageView)convertView.findViewById(R.id.ivIcon);
            viewHolder.txtName=(TextView)convertView.findViewById(R.id.tvName);
            viewHolder.txtPhone=(TextView)convertView.findViewById(R.id.tvPhone);
            viewHolder.imgDetele=(ImageView)convertView.findViewById(R.id.ivDelete);
            viewHolder.imgDetele.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nguoiDungDao.deleteNguoiDungID(nguoiDungList.get(position).getUserName());
                    nguoiDungList.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        NguoiDung nguoiDung=(NguoiDung)nguoiDungList.get(position);
        if (position % 3 == 0){
            viewHolder.img.setImageResource(R.drawable.emone);
        }else if (position % 3 == 1){
            viewHolder.img.setImageResource(R.drawable.emtwo);
        }else {
            viewHolder.img.setImageResource(R.drawable.emthree);
        }
        viewHolder.txtName.setText(nguoiDung.getHoTen());
        viewHolder.txtPhone.setText(nguoiDung.getPhone());
        return convertView;
    }
    public static class ViewHolder{
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDetele;
    }
    public void  notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<NguoiDung> nguoiDungList){
        this.nguoiDungList=nguoiDungList;
        notifyDataSetChanged();
    }
}
