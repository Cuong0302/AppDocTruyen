package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.Truyen;
import com.squareup.picasso.Picasso;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class adaptertruyen extends BaseAdapter  {


    //Truyền vào màn hinh
    private Context context;;
    //Tạo mảng
    private ArrayList<Truyen> listtruyen;
//    private List<String> movieListAll;
//    private List<Truyen> movieList;

    public adaptertruyen(Context context, ArrayList<Truyen> listtruyen) {
        this.context = context;
        this.listtruyen = listtruyen;
    }

    @Override
    public int getCount() {
        return listtruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listtruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class ViewHolder{
        TextView txtTentruyen;
        ImageView imgtruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
         //Gọi viewHolder
         viewHolder = new ViewHolder();

         //Tạo đối tượng layoutInflater giúp get layout ra
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          convertView = inflater.inflate(R.layout.new_truyen,null);


          viewHolder.txtTentruyen = convertView.findViewById(R.id.textviewTenTruyen);
          viewHolder.imgtruyen = convertView.findViewById(R.id.imgNewTruyen);
          convertView.setTag(viewHolder);

        //Lấy dữ liệu
        Truyen truyen = (Truyen) getItem(position);
        viewHolder.txtTentruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getAnh()).placeholder(R.drawable.ic_baseline_cloud_download_24).error(R.drawable.ic_baseline_image_not_supported_24).into(viewHolder.imgtruyen);

        return convertView;
    }


    //filter
    public void filterList(ArrayList<Truyen> filteredList){
        listtruyen = filteredList;
        notifyDataSetChanged();
    }

}
