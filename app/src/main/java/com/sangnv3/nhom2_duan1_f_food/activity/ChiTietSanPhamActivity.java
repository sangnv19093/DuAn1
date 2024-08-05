//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.activity;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Adapter.BinhLuanAdapter;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.BinhLuanDAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.BinhLuan;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.SanPham;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.R;
//
//public class ChiTietSanPhamActivity extends AppCompatActivity {
//    ImageView imgAnh_sanpham_chitiet, imgBack;
//    TextView tvTen_sanpham_chitiet, tvGia_sanpham_chitiet;
//    RecyclerView recyclerView_binh_luan;
//    Button btnChon_mua;
//    SanPham sanPham;
//    ImageView imgYeuThich_frameSPChiTiet2;
//    BinhLuanDAO binhLuanDAO;
//    List<BinhLuan> listBinhLuan;
//    BinhLuanAdapter binhLuanAdapter;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_chi_tiet_san_pham);
//        imgAnh_sanpham_chitiet = findViewById(R.id.imgAnh_sanpham_chitiet);
//        imgBack = findViewById(R.id.imgBack);
//        imgYeuThich_frameSPChiTiet2 = findViewById(R.id.imgYeuThich_frameSPChiTiet2);
//        tvTen_sanpham_chitiet = findViewById(R.id.tvTen_sanpham_chitiet);
//        tvGia_sanpham_chitiet = findViewById(R.id.tvGia_sanpham_chitiet);
//        btnChon_mua = findViewById(R.id.btnChon_mua);
//        recyclerView_binh_luan = findViewById(R.id.recycler_view_binh_luan);
//        binhLuanDAO = new BinhLuanDAO(this);
//
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        sanPham = (SanPham) bundle.getSerializable("sanPham");
//
//        String srcImg = sanPham.getAnhSP();
//        int resourceId = getResources().getIdentifier(srcImg, "drawable", getPackageName());
//        Picasso.get().load(resourceId).into(imgAnh_sanpham_chitiet);
//        tvTen_sanpham_chitiet.setText(sanPham.getTenSP());
//        tvGia_sanpham_chitiet.setText(""+sanPham.getGiaSP() + " VND");
//        getDsBinhLuan(sanPham.getMaSP());
//
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//        // Chức năng yêu thích
//        sanPhamYeuThich(imgYeuThich_frameSPChiTiet2);
//    }
//
//    public void sanPhamYeuThich(ImageView imageView){
//        imgYeuThich_frameSPChiTiet2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //code here
//            }
//        });
//
//    }
//
//
//
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
//
//
//    public void getDsBinhLuan(int sanPhamId){
//        listBinhLuan = binhLuanDAO.getDsBinhLuan(sanPhamId);
//        binhLuanAdapter = new BinhLuanAdapter(listBinhLuan, ChiTietSanPhamActivity.this);
//        recyclerView_binh_luan.setAdapter(binhLuanAdapter);
//    }
//}
package com.sangnv3.nhom2_duan1_f_food.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sangnv3.nhom2_duan1_f_food.Adapter.BinhLuanAdapter;
import com.sangnv3.nhom2_duan1_f_food.Dao.BinhLuanDAO;
import com.sangnv3.nhom2_duan1_f_food.Dao.GioHangDAO;
import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Model.BinhLuan;
import com.sangnv3.nhom2_duan1_f_food.Model.GioHang;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;
import com.sangnv3.nhom2_duan1_f_food.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class ChiTietSanPhamActivity extends AppCompatActivity {
    ImageView imgAnh_sanpham_chitiet, imgBack, imgGio_hang, imgYeu_thich, imgHome, imgThong_bao;
    TextView tvTen_sanpham_chitiet, tvGia_sanpham_chitiet, tvSo_luong;
    RecyclerView recyclerView_binh_luan;
    Button btnChon_mua, btnThem_vao_gio_hang;
    EditText edDialog_binh_luan;
    SanPham sanPham;
    ImageView imgYeuThich_frameSPChiTiet2;
    BinhLuanDAO binhLuanDAO;
    ArrayList<BinhLuan> listBinhLuan;
    BinhLuanAdapter binhLuanAdapter;
    SharedPreferences sharedPreferences;
    int getNguoiDung_id;
    int loaiTaiKhoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        imgAnh_sanpham_chitiet = findViewById(R.id.imgAnh_sanpham_chitiet);
        imgBack = findViewById(R.id.imgBack);
        edDialog_binh_luan = findViewById(R.id.edDialog_binh_luan);
        tvTen_sanpham_chitiet = findViewById(R.id.tvTen_sanpham_chitiet);
        tvGia_sanpham_chitiet = findViewById(R.id.tvGia_sanpham_chitiet);
//        tvSo_luong = findViewById(R.id.tvSo_luong);
        btnChon_mua = findViewById(R.id.btnChon_mua);
        btnThem_vao_gio_hang = findViewById(R.id.btnThem_vao_gio_hang);
        recyclerView_binh_luan = findViewById(R.id.recycler_view_binh_luan);
//        binhLuanDAO = new BinhLuanDAO(this);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        getNguoiDung_id = sharedPreferences.getInt("maUser", 0);
        loaiTaiKhoan = sharedPreferences.getInt("loaiTaiKhoan", -1);


        //lay san pham tu ben adapter san pham 2, khi click vao san pham se lay san pham do va truyen qua chi tiet san pham
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sanPham = (SanPham) bundle.getSerializable("sanPham");

        //set anh cho san pham
        String srcImg = sanPham.getAnhSP();
        int resourceId = getResources().getIdentifier(srcImg, "drawable", getPackageName());
        Picasso.get().load(srcImg).into(imgAnh_sanpham_chitiet);
        tvTen_sanpham_chitiet.setText(sanPham.getTenSP());
        tvGia_sanpham_chitiet.setText("" + sanPham.getGiaSP() + " VND");
//        tvSo_luong.setText("" + sanPham.get());

//        getDsBinhLuan(sanPham.getMaSP());

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Chức năng bình luận
        binhLuanDialog(edDialog_binh_luan);

        // Chức năng yêu thích
        int sanPham_id = sanPham.getMaSP();

//        if (validate(sanPham_id) < 0) {
//            imgYeuThich_frameSPChiTiet2.setImageResource(R.drawable.frame4_trai_tim);
//        }
//        imgYeuThich_frameSPChiTiet2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                sanPhamYeuThich(sanPham_id, getNguoiDung_id, imgYeuThich_frameSPChiTiet2);
//            }
//        });




//        imgYeu_thich.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent1 = new Intent(ChiTietSanPhamActivity.this, YeuThichAtivivty.class);
////                Bundle bundle1 = new Bundle();
////                bundle1.putSerializable("sanPham", sanPham);
////                intent1.putExtras(bundle1);
////                startActivity(intent1);
////                finish();
//            }
//        });

//        imgThong_bao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ChiTietSanPhamActivity.this, "Chưa tồn tại màn hình thông báo", Toast.LENGTH_SHORT).show();
//            }
//        });

        if(loaiTaiKhoan != 1){
            btnThem_vao_gio_hang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chonMua(getNguoiDung_id, sanPham_id);
                }
            });

//            if(sanPham.getSoLuongConLai()>0){
                btnChon_mua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogMuaNgay(sanPham);
                    }
                });
            }
        }




//    public void sanPhamYeuThich(int sanPham_id, int nguoiDung_id, ImageView imageView) {
//        SanPhamYeuThich spyt = new SanPhamYeuThich();
//        SanPhamYeuThichDAO sanPhamYeuThichDAO = new SanPhamYeuThichDAO(getApplicationContext());
//        spyt.setSanPham_id(sanPham_id);
//        spyt.setNguoiDung_id(nguoiDung_id);
//        if (validate(sanPham_id) < 0) {
//            if (sanPhamYeuThichDAO.boYeuThichSanPham(sanPham_id, nguoiDung_id) > 0) {
//                imageView.setImageResource(R.drawable.frame4_trai_tim2);
//            }
//        } else {
//            if (sanPhamYeuThichDAO.yeuThichSanPham(spyt) > 0) {
//                imageView.setImageResource(R.drawable.frame4_trai_tim);
//            }
//        }
//
//
////        SanPhamDAO sanPhamDAO = new SanPhamDAO(getApplicationContext());
////        int isYeuThich = sanPhamDAO.getTrangThaiYeuThichBySanPhamId(sanPham_id);
////        if (isYeuThich == 1) {
////            imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
////        } else {
////            imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
////        }
////        imgYeuThich_frameSPChiTiet2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                SanPhamDAO sanPhamDAO = new SanPhamDAO(getApplicationContext());
////                if (isYeuThich == 1) {
////                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim2);
////                    sanPhamDAO.changeIsYeuThich(sanPham_id, 0);
////                } else {
////                    imgIsYeuThich.setImageResource(R.drawable.frame4_trai_tim);
////                    sanPhamDAO.changeIsYeuThich(sanPham_id, 1);
////                }
////            }
////        });
//
//    }

    public void binhLuan() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_binh_luan);
        EditText edBinh_luan = dialog.findViewById(R.id.edBinh_luan);
        ImageView imgBinh_luan = dialog.findViewById(R.id.imgBinh_luan);

        //day binh luan cua nguoi dung vao database
        imgBinh_luan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nguoiDung_id lay tu home

                //sanPham_id lay tu sanPham
                int sanPham_id = sanPham.getMaSP();
                //noiDung lay tu edBinh_luan
                if (edBinh_luan.getText().length() == 0) {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Binh luan trong tron a", Toast.LENGTH_SHORT).show();
                    return;
                }
                String binh_luan = edBinh_luan.getText().toString();
                //thoiGian lay tu thoi gian thuc new Date(), new Time()
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm - dd-MM-yyyy");
                String currentTime = format.format(calendar.getTime());

                BinhLuan binhLuan = new BinhLuan();
                binhLuan.setNguoiDung_id(getNguoiDung_id);
                binhLuan.setSanPham_id(sanPham_id);
                binhLuan.setNoiDung(binh_luan);
                binhLuan.setThoiGian(currentTime);
                if (binhLuanDAO.themBinhLuan(binhLuan) > 0) {
                    getDsBinhLuan(sanPham_id);
                    dialog.cancel();
                    Toast.makeText(ChiTietSanPhamActivity.this, "Da binh luan", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Them binh luan khong thanh cong", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.show();
    }

    public void binhLuanDialog(EditText edDialog_binh_luan) {
        edDialog_binh_luan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binhLuan();
            }
        });
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        finish();
    }


    public void getDsBinhLuan(int sanPhamId) {
        binhLuanDAO = new BinhLuanDAO(this);
        listBinhLuan = binhLuanDAO.getDsBinhLuan(sanPhamId);
        Collections.reverse(listBinhLuan);
        binhLuanAdapter = new BinhLuanAdapter(listBinhLuan, ChiTietSanPhamActivity.this);
        recyclerView_binh_luan.setAdapter(binhLuanAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDsBinhLuan(sanPham.getMaSP());
    }

    public void chonMua(int nguoiDung_id, int sanPham_id) {
        SanPham_DAO sanPhamDAO = new SanPham_DAO(ChiTietSanPhamActivity.this);
        SanPham sanPham = sanPhamDAO.getSanPham(sanPham_id);
//        if(sanPham.getSoLuongConLai() == 0){
//            Toast.makeText(ChiTietSanPhamActivity.this, "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
//            return;
//        }
        GioHangDAO gioHangDAO = new GioHangDAO(ChiTietSanPhamActivity.this);
        GioHang gioHang = new GioHang();
        gioHang.setNguoiDung_id(nguoiDung_id);
        gioHang.setSanPham_id(sanPham_id);
        gioHang.setSoLuong(1);
        gioHang.setTrangThaiMua(0);
        if (gioHangDAO.themVaoGioHang(gioHang) > 0) {
            Toast.makeText(this, "Da them vao gio hang", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Mặt hàng này đã tồn tại trong giỏ hàng", Toast.LENGTH_SHORT).show();
        }
    }

//    public int validate(int sanPham_id) {
//        int check = 1;
//        SanPhamYeuThichDAO spytd = new SanPhamYeuThichDAO(getApplicationContext());
//        ArrayList<SanPham> list1 = spytd.getSanPhamYeuThich(getNguoiDung_id);
//        for (SanPham sp : list1) {
//            if (sp.getSanPham_id() == sanPham_id) {
//                check = -1;
//            }
//        }
//        return check;
//    }

    public void dialogMuaNgay(SanPham sanPham){
        Dialog dialog = new Dialog(ChiTietSanPhamActivity.this);
        dialog.setContentView(R.layout.dialog_mua_ngay);
        TextView tvGia = dialog.findViewById(R.id.tvGia);
        ImageView imgAnh = dialog.findViewById(R.id.imgAnh);
        TextView tvSoluongmua = dialog.findViewById(R.id.tvSo_luong_mua);
        Button btnMua = dialog.findViewById(R.id.btnMua);
        ImageView imgMinus = dialog.findViewById(R.id.imgMinus);
        ImageView imgPlus = dialog.findViewById(R.id.imgPlus);

        String srcImg = sanPham.getAnhSP();
//        int resourceId = getResources().getIdentifier(srcImg, "drawable", getPackageName());
//        if (resourceId != 0) {
            Picasso.get().load(sanPham.getAnhSP()).into(imgAnh);
//        } else {
//            // Sử dụng hình ảnh mặc định hoặc không có gì
//            imgAnh.setImageResource(R.drawable.food); // Thay 'default_image' bằng ID của hình ảnh mặc định của bạn
//        }

        tvGia.setText(""+sanPham.getGiaSP()+" vnđ");
//        tvKho.setText("Kho: "+sanPham.getSoLuongConLai());

        GioHangDAO gioHangDAO = new GioHangDAO(ChiTietSanPhamActivity.this);
        GioHang gioHang = new GioHang();

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvSoluongmua.getText().toString().matches("1")){
                    return;
                }
                tvSoluongmua.setText(""+(Integer.parseInt(tvSoluongmua.getText().toString())-1));
                gioHang.setSoLuong(Integer.parseInt(tvSoluongmua.getText().toString()));

                int price = ((Integer.parseInt(tvSoluongmua.getText().toString())) * sanPham.getGiaSP());
                tvGia.setText(""+price+" vnđ");
            }
        });

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(Integer.parseInt(tvSoluongmua.getText().toString()) == sanPham.getSoLuongConLai()
//                        || sanPham.getSoLuongConLai() == 0){
//                    return;
//                }
                tvSoluongmua.setText(""+(Integer.parseInt(tvSoluongmua.getText().toString())+1));
                gioHang.setSoLuong(Integer.parseInt(tvSoluongmua.getText().toString()));

                int price = ((Integer.parseInt(tvSoluongmua.getText().toString())) * sanPham.getGiaSP());
                tvGia.setText(""+price+" vnđ");
            }
        });

        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gioHang.setSanPham_id(sanPham.getMaSP());
                gioHang.setNguoiDung_id(getNguoiDung_id);
                gioHang.setSoLuong(Integer.parseInt(tvSoluongmua.getText().toString()));
                gioHang.setGiaSanPham(sanPham.getGiaSP());
                gioHang.setAnhSanPham(sanPham.getAnhSP());
                gioHang.setTenSanPham(sanPham.getTenSP());
//                gioHangDAO.themVaoGioHang(gioHang);

                int tongTien = (sanPham.getGiaSP() * Integer.parseInt(tvSoluongmua.getText().toString()));
//                List<GioHang> listMuaHang = gioHangDAO.getDsMuaHang(getNguoiDung_id, 1);
                List<GioHang> listMuaHang = new ArrayList<>();
                listMuaHang.add(gioHang);

                Intent intent = new Intent(ChiTietSanPhamActivity.this, DiaChiNhanHangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listGioHang", (Serializable) listMuaHang);
                bundle.putInt("tongTien", tongTien);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        dialog.show();
    }
}