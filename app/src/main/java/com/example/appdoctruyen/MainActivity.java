package com.example.appdoctruyen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import android.widget.ViewFlipper;

import com.example.appdoctruyen.adapter.adapterchuyenmuc;
import com.example.appdoctruyen.adapter.adapterthongtin;
import com.example.appdoctruyen.adapter.adaptertruyen;
import com.example.appdoctruyen.data.DatabaseDocTruyen;
import com.example.appdoctruyen.model.TaiKhoan;
import com.example.appdoctruyen.model.Truyen;
import com.example.appdoctruyen.model.chuyenmuc;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;

    NavigationView navigationView;
    ListView listView,listViewNew,listviewThongtin;
    DrawerLayout drawerLayout;



    ArrayList<chuyenmuc> chuyenmucArrayList;
    ArrayList<TaiKhoan> taiKhoanArrayList;


    ArrayList<Truyen> TruyenArrayList;
    adaptertruyen adaptertruyen;

    adapterthongtin adapterthongtin;

    adapterchuyenmuc adapterchuyenmuc;

    DatabaseDocTruyen databaseDocTruyen;
    String email;
    String tentaikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        databaseDocTruyen = new DatabaseDocTruyen(this);


        Intent intentpq = getIntent();
        int i = intentpq.getIntExtra("phanq",0);
        int idd = intentpq.getIntExtra("idd",0);
        email = intentpq.getStringExtra("email");
        tentaikhoan = intentpq.getStringExtra("tentaikhoan");


        AnhXa();

        ActionBar();
        ActionViewFlipper();


        //listview chuyên mục
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 11){

                        if(i == 2) {
                            Intent intent = new Intent(MainActivity.this, ManAdmin.class);
                            intent.putExtra("Id",idd);
                            startActivity(intent);
                    }
                        else {
                            Toast.makeText(MainActivity.this,"Bạn không có quyền ",Toast.LENGTH_SHORT).show();
                            Log.e("Đăng bài : ","Bạn không có quyền ");
                        }
                    }
                else if(position == 0){
                    Intent intent = new Intent(MainActivity.this,ManThongTinApp.class);
                    startActivity(intent);
                }
                else if(position == 1){
                    finish();
                }

                else if(position == 2){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                    // Tao su kien ket thuc app
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startActivity(startMain);
                    finish();
                }
            }
        });


        //listview Truyen
        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ManNoiDungTruyen.class);
                String tent =   TruyenArrayList.get(position).getTenTruyen();
                String noidungt = TruyenArrayList.get(position).getNoiDung();
                int SoLuotXem = TruyenArrayList.get(position).getSoluotxem();
                SoLuotXem += 1;

                Log.d("fjdhfjsf", "onItemClick: fjshfjhsdf");

                databaseDocTruyen.soLuotXem(SoLuotXem,TruyenArrayList.get(position).getID());

                intent.putExtra("tentruyen",tent);
                intent.putExtra("noidung",noidungt);
                intent.putExtra("soluotxem",SoLuotXem );
                intent.putExtra("vitri",position );
                //Log.e("Tên truyện : ",tent);
                startActivity(intent);


                //Thay đổi màu khi click vào
//                view.setBackgroundColor(Color.parseColor("#FFBB86FC"));
            }
        });

    }


    //Tạo thanh action bar với toolbar
    private void ActionBar() {
        //Hàm hỗ trợ toolBar
        setSupportActionBar(toolbar);
        //set nút của toolbar là true
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Tạo icon cho toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        //Tạo sự kiện click cho toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gọi lại drawerlayout, do toolbar được gọi ra nhờ drawerlayout
                drawerLayout.openDrawer(GravityCompat.START);   //GravityCompat.START làm nó nhảy ra giữa
            }
        });

    }


    //Tạo Flipper quảng cáo
    private void ActionViewFlipper() {
        //Mảng chứa các tấm hình
        ArrayList<String> mangquangcao = new ArrayList<>();
        //Thêm hình
        mangquangcao.add("https://toplist.vn/images/800px/rua-va-tho-230179.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/deo-chuong-cho-meo-230180.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cu-cai-trang-230181.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/de-den-va-de-trang-230182.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/chu-be-chan-cuu-230183.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/cau-be-chan-cuu-va-cay-da-co-thu-230184.jpg");

        //Gán link ảnh vào imageView, rồi gán gán image ra app
        for(int i =0; i <mangquangcao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            //Hàm thư viện của picasso. lấy ảnh từ internet về cho vào imageview
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //phương thức căn chỉnh tấm hình vừa với khung quảng cáo
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //Thêm ảnh từ imageview vào ViewFlipper
            viewFlipper.addView(imageView);
        }
        //Thiết lập tự chạy cho viewFlipper chạy trong 5 giây
        viewFlipper.setFlipInterval(5000);
        //viewFlipper run
        viewFlipper.setAutoStart(true);
        //Gọi animation cho in và out . Animation giúp cho nó biến dổi giữa các giao diện hình ảnh
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        //Gọi animation vào ViewFlippẻ
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void AnhXa(){
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        listViewNew = findViewById(R.id.listviewNew);
        navigationView = findViewById(R.id.navigationview);
        listView = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        listviewThongtin = findViewById(R.id.listviewThongTin);



        //Thong tin
        taiKhoanArrayList = new ArrayList<>();
        taiKhoanArrayList.add(new TaiKhoan(tentaikhoan,email));
        adapterthongtin = new adapterthongtin(this,R.layout.nav_thong_tin,taiKhoanArrayList);
        listviewThongtin.setAdapter(adapterthongtin);

        //chuyên mục
        chuyenmucArrayList = new ArrayList<>();
//        chuyenmucArrayList.add(new chuyenmuc("Đăng bài", R.drawable.ic_post));
        chuyenmucArrayList.add(new chuyenmuc("Thông tin", R.drawable.ic_face));
        chuyenmucArrayList.add(new chuyenmuc("Đăng xuất", R.drawable.ic_login));
//        chuyenmucArrayList.add(new chuyenmuc("Thoát", R.drawable.ic_login));

        adapterchuyenmuc = new adapterchuyenmuc(this,R.layout.chuyen_muc,chuyenmucArrayList);
        listView.setAdapter(adapterchuyenmuc);



        //New Truyện
        TruyenArrayList = new ArrayList<>();
        Cursor cursor1 = databaseDocTruyen.getData1();
        while (cursor1.moveToNext()){
            int id = cursor1.getInt(0);
            String tentruyen = cursor1.getString(1);
            String noidung = cursor1.getString(2);
            String anh = cursor1.getString(3);
            int soluotxem = cursor1.getInt(4);
            int id_tk = cursor1.getInt(5);

            TruyenArrayList.add(new Truyen(id,tentruyen,noidung,anh,id_tk,soluotxem));

            adaptertruyen = new adaptertruyen(getApplicationContext(),TruyenArrayList);
            listViewNew.setAdapter(adaptertruyen);
        }
        cursor1.moveToFirst();
        cursor1.close();

    }

    //Nạp một menu tìm kiếm vào actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    //Bắt sự kiện khi click vào search
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
            case R.id.menu1:
                Intent intent = new Intent(this,ManTimKiem.class);
                startActivity(intent);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}