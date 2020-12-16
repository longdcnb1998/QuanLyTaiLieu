package com.example.QuanLyTaiLieu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaiLieuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TaiLieu> list;

    public TaiLieuAdapter(Context context, ArrayList<TaiLieu> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate((R.layout.item_the_loai), parent, false);
            TextView tvName = convertView.findViewById(R.id.tv_name);
            tvName.setText(list.get(position).getTen());
        }
        return convertView;
    }
}
