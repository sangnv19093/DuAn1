package com.sangnv3.nhom2_duan1_f_food.activity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.io.Serializable;
//import java.util.List;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.GioHangDAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.GioHang;
//
//
//public class GioHangActivity extends AppCompatActivity {
//    RecyclerView recyclerView;
//    GioHangDAO gioHangDAO;
//    GioHangDAO gioHangAdapter;
//    List<GioHang> listGioHang;
//    List<GioHang> listMuaHang;
//    SharedPreferences sharedPreferences;
//    int getNguoiDung_id;
//    public TextView tvTotal, tvThong_bao;
//    ImageView imgBack;
//    public Button btnMua_hang;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gio_hang);
//        recyclerView = findViewById(R.id.recycler_view_gio_hang);
//        tvTotal = findViewById(R.id.tvTotal);
//        tvThong_bao = findViewById(R.id.tvThong_bao);
//        imgBack = findViewById(R.id.imgBack);
//        btnMua_hang = findViewById(R.id.btnMua_hang);
//
//
//        gioHangDAO = new GioHangDAO( GioHangActivity.this);
//        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
//        getNguoiDung_id = sharedPreferences.getInt("maUser", 0);
//
//        getDsGioHang();
//
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//        muaHang(btnMua_hang);
//
//    }
///////////////////////////////////////////////////////////////////////////////////////////
//
//    @SuppressLint("MissingSuperCall")
//    @Override
//    public void onBackPressed() {
//        listGioHang = gioHangDAO.getDsGioHang(getNguoiDung_id);
//
//        for(GioHang gioHang:listGioHang){
//            gioHang.setTrangThaiMua(0);
//            gioHangDAO.suaTrangThaiMua(gioHang);
//        }
//        finish();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        getDsGioHang();
//    }
//
//    public void getDsGioHang(){
//        listGioHang = gioHangDAO.getDsGioHang(getNguoiDung_id);
//        if(listGioHang.size() == 0){
//            tvThong_bao.setVisibility(View.VISIBLE);
//        }
//        tongTien(listGioHang);
//        gioHangAdapter= new GioHangDAO(
//                GioHangActivity.this);
//        recyclerView.setAdapter(gioHangAdapter);
//    }
//
//    public int tongTien(List<GioHang> listMuaHang){
//        int price = 0;
//        for(GioHang gioHang1: listMuaHang){
//            if(gioHang1.getTrangThaiMua()==1){
//                price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
//            }
//        }
//        tvTotal.setText(""+price);
//        return price;
//    }
//
//    public void muaHang(Button btnMua_hang){
//       btnMua_hang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listMuaHang = gioHangDAO.getDsMuaHang(getNguoiDung_id, 1);
//                if(listMuaHang.size() == 0){
//                    return;
//                }
//                Intent intent = new Intent(GioHangActivity.this, DiaChiNhanHangActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("listGioHang", (Serializable) listMuaHang);
//                bundle.putInt("tongTien", tongTien(listMuaHang));
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
//    }
//}
//package md18202.nhom2.duan1application.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
        import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sangnv3.nhom2_duan1_f_food.Adapter.GioHangAdapter;
import com.sangnv3.nhom2_duan1_f_food.Dao.GioHangDAO;
import com.sangnv3.nhom2_duan1_f_food.Model.GioHang;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.io.Serializable;
import java.util.List;


public class GioHangActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GioHangDAO gioHangDAO;
    GioHangAdapter gioHangAdapter;
    List<GioHang> listGioHang;
    List<GioHang> listMuaHang;
    SharedPreferences sharedPreferences;
    int getNguoiDung_id;
    public TextView tvTotal, tvThong_bao;
    ImageView imgBack;
    public Button btnMua_hang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = findViewById(R.id.recycler_view_gio_hang);
        tvTotal = findViewById(R.id.tvTotal);
        tvThong_bao = findViewById(R.id.tvThong_bao);
        imgBack = findViewById(R.id.imgBack);
        btnMua_hang = findViewById(R.id.btnMua_hang);
        gioHangDAO = new GioHangDAO(GioHangActivity.this);
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
       getNguoiDung_id = sharedPreferences.getInt("maUser", 0);

        getDsGioHang();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        muaHang(btnMua_hang);

    }
/////////////////////////////////////////////////////////////////////////////////////////

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        listGioHang = gioHangDAO.getDsGioHang(getNguoiDung_id);
        for (GioHang gioHang : listGioHang) {
            gioHang.setTrangThaiMua(0);
            gioHangDAO.suaTrangThaiMua(gioHang);
        }
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDsGioHang();
    }

    public void getDsGioHang(){
        listGioHang = gioHangDAO.getDsGioHang(getNguoiDung_id);
        if(listGioHang.size() == 0){
            tvThong_bao.setVisibility(View.VISIBLE);
        }
        tongTien(listGioHang);
        gioHangAdapter = new GioHangAdapter(listGioHang, this);
        recyclerView.setAdapter(gioHangAdapter);
    }

    public int tongTien(List<GioHang> listMuaHang){
        int price = 0;
        for(GioHang gioHang1: listMuaHang){
            if(gioHang1.getTrangThaiMua()==1){
                price += (gioHang1.getGiaSanPham()) * (gioHang1.getSoLuong());
            }
        }
        tvTotal.setText(""+price);
        return price;
    }

    public void muaHang(Button btnMua_hang){
        btnMua_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMuaHang = gioHangDAO.getDsMuaHang(getNguoiDung_id, 1);
                if(listMuaHang.size() == 0){
                    return;
                }
                Intent intent = new Intent(GioHangActivity.this, DiaChiNhanHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listGioHang", (Serializable) listMuaHang);
                bundle.putInt("tongTien", tongTien(listMuaHang));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}