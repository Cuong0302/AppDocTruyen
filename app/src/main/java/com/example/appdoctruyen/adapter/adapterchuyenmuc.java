package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.MainActivity;
import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.chuyenmuc;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterchuyenmuc extends BaseAdapter {

    //Truyền vào màn hinh
    private Context context;
    private int layout;
    //Tạo mảng
    private List<chuyenmuc> listchuyenmuc;


    public adapterchuyenmuc(Context context, int layout, List<chuyenmuc> listchuyenmuc) {
        this.context = context;
        this.layout = layout;
        this.listchuyenmuc = listchuyenmuc;
    }

    @Override
    public int getCount() {
        return listchuyenmuc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //chuyển layout thành java
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout,null);


        ImageView img = (ImageView) convertView.findViewById(R.id.imageviewchuyenmmuc);
        TextView txt = (TextView) convertView.findViewById(R.id.textviewchuyenmuc);

        chuyenmuc cm = listchuyenmuc.get(position);

        //Hiển thị lên màn hình
        txt.setText(cm.getTenchuyenmuc());

        Picasso.get().load(cm.getHinhanhchuyenmuc()).placeholder(R.drawable.ic_baseline_cloud_download_24)
                .error(R.drawable.ic_baseline_image_not_supported_24).into(img);

        //img.setImageResource(cm.getHinhanhchuyenmuc());

        return convertView;
    }
}
