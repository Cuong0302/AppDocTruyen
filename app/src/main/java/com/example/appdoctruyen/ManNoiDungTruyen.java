package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.appdoctruyen.data.DatabaseDocTruyen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ManNoiDungTruyen extends AppCompatActivity {

    TextView txtTenTruyen,txtNoidung, textView2;
    FloatingActionButton openDialog;
    View manNoiDung;
    DatabaseDocTruyen databaseDocTruyen;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_noi_dung_truyen);

        anhXa();
        hienThiND();
        databaseDocTruyen = new DatabaseDocTruyen(this);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                settings();
            }
        });

//        Cursor cursor = databaseDocTruyen.getData4();
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cursor.moveToNext();
//                String tentruyen = cursor.getString(1);
//                String noidung = cursor.getString(2);
//                int soluotxem = cursor.getInt(4);
//
//                txtNoidung.setText(noidung);
//                txtTenTruyen.setText(tentruyen);
//                textView2.setText("Số lượt xem: "+soluotxem);
//
//                if(cursor.isLast()){
//                    cursor.moveToFirst();
//                }
//            }
//        });
    }

    private void settings() {
        final Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.layout_dialog);

        final SeekBar seekBar = dialog.findViewById(R.id.seekBar);
        final Switch cheDoDoc = dialog.findViewById(R.id.cheDoDoc);
        Button btnSave = dialog.findViewById(R.id.btnSave);

        seekBar.setMax(100);

        //Chế độ đọc
        cheDoDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((Switch)view).isChecked();
                if(checked){
                    manNoiDung.setBackgroundColor(Color.parseColor("#DCE39C"));
                    cheDoDoc.setChecked(true);
                }
                else {
                    manNoiDung.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
        //Thay đổi cỡ chữ
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    txtNoidung.setTextSize(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                editor.putInt("font_size", this.seekBarBrightness.getProgress());
//                editor.putInt("Check", checked);
//
//                // Checked RadioButton ID.
//                int checkedRadioButtonId = radioGroupDiffLevel.getCheckedRadioButtonId();
//
//                editor.putInt("checkedRadioButtonId", checkedRadioButtonId);
//
//                // Save.
//                editor.apply();
//
//                Toast.makeText(this,"Game Setting saved!",Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void hienThiND() {
        Intent intent = getIntent();
        String tenTruyen = intent.getStringExtra("tentruyen");
        String noidung = intent.getStringExtra("noidung");
        int SoLuotXem = intent.getIntExtra("soluotxem",0);


        txtTenTruyen.setText(tenTruyen);
        txtNoidung.setText(noidung);
        textView2.setText("Số lượt xem: " + SoLuotXem);

        //Cuộn textview
        txtNoidung.setMovementMethod(new ScrollingMovementMethod());

    }

    private void anhXa() {
        textView2 = findViewById(R.id.textView2);
        txtNoidung = findViewById(R.id.NoiDung);
        txtTenTruyen = findViewById(R.id.TenTruyen);
        openDialog =findViewById(R.id.openDialog);
        manNoiDung = findViewById(R.id.manNoiDung);
    }
}