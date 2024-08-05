//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.activity;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Adapter.SanPhamThanhToanAdapter;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.GioHangDAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.HoaDonChiTietDAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.HoaDonDAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao.SanPham_DAO;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.GioHang;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.HoaDonChiTiet;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.R;
//import md18202.nhom2.duan1application.Models.HoaDon;
//
//public class XacNhanThanhToanActivity extends AppCompatActivity {
//    ImageView imgBack, imgHinh_thuc_thanh_toan;
//    TextView tvNguoi_nhan_hang, tvSdt_nguoi_nhan, tvGia_tam_tinh, tvThanh_tien, tvDia_chi_nhan_hang, tvHinh_thuc_thanh_toan;
//    Button btnXac_nhan_dat_hang;
//    HoaDonDAO hoaDonDAO;
//    HoaDon hoaDon;
//    SharedPreferences sharedPreferences;
//    int tongTien, nguoiDung_id, hinhThucThanhToan;
//    String diaChi, tenNguoiNhan, sdt;
//    List<GioHang> listGioHang;
//    RecyclerView recycleView_thanh_toan;
//    HoaDonChiTietDAO hoaDonChiTietDAO;
//    SanPhamThanhToanAdapter sanPhamThanhToanAdapter;
//    SanPham_DAO sanPhamDAO;
//    GioHang gioHang;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xac_nhan_thanh_toan);
//        imgBack = findViewById(R.id.imgBack);
//        imgHinh_thuc_thanh_toan = findViewById(R.id.imgHinh_thuc_thanh_toan);
//        tvNguoi_nhan_hang = findViewById(R.id.tvNguoi_nhan_hang);
//        tvSdt_nguoi_nhan = findViewById(R.id.tvSdt_nguoi_nhan);
//        tvGia_tam_tinh = findViewById(R.id.tvGia_tam_tinh);
//        tvThanh_tien = findViewById(R.id.tvThanh_tien);
//        tvHinh_thuc_thanh_toan = findViewById(R.id.tvHinh_thuc_thanh_toan);
//        recycleView_thanh_toan = findViewById(R.id.recycler_view_don_hang_thanh_toan);
//        tvDia_chi_nhan_hang = findViewById(R.id.tvDia_chi_nhan_hang);
//        btnXac_nhan_dat_hang = findViewById(R.id.btnXac_nhan_dat_hang);
//        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
//        nguoiDung_id = sharedPreferences.getInt("maUser", -1);
//
//        hoaDonDAO = new HoaDonDAO(this);
//        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
//        sanPhamDAO = new SanPham_DAO(this);
//
//        Intent intent = getIntent();
//        tongTien = intent.getIntExtra("tongTien", 0);
//        hinhThucThanhToan = intent.getIntExtra("hinhThucThanhToan", 0);
//        diaChi = intent.getStringExtra("diaChi");
//        tenNguoiNhan = intent.getStringExtra("nguoiNhan");
//        sdt = intent.getStringExtra("sdt");
//        listGioHang = (List<GioHang>) intent.getSerializableExtra("listGioHang");
//
//        sanPhamThanhToanAdapter = new SanPhamThanhToanAdapter(listGioHang, XacNhanThanhToanActivity.this);
//        recycleView_thanh_toan.setAdapter(sanPhamThanhToanAdapter);
//
//
//        tvNguoi_nhan_hang.setText(tenNguoiNhan);
//        tvSdt_nguoi_nhan.setText(sdt);
//        tvThanh_tien.setText("" + tongTien);
//        tvGia_tam_tinh.setText("" + tongTien);
//        tvDia_chi_nhan_hang.setText(diaChi);
//        if (hinhThucThanhToan == 0) {
//            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_account_cash);
//            tvHinh_thuc_thanh_toan.setText("Thanh toán tiền mặt");
//        } else if (hinhThucThanhToan == 1) {
//            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_atm_card);
//            tvHinh_thuc_thanh_toan.setText("Thẻ ATM");
//        } else if (hinhThucThanhToan == 2) {
//            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_atm_card_person);
//            tvHinh_thuc_thanh_toan.setText("Thêm thẻ tín dụng/Ghi nợ");
//        } else if (hinhThucThanhToan == 3) {
//            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_wallet);
//            tvHinh_thuc_thanh_toan.setText("Ví điện tử");
//        }
//
//
//        imgBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//
//        btnXac_nhan_dat_hang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xacNhanDatHang();
//            }
//        });
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
//
//    public void xacNhanDatHang() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Xác nhận")
//                .setPositiveButton("Đặt hàng", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Calendar calendar = Calendar.getInstance();
//                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                        String currentTime = format.format(calendar.getTime());
//
//                        hoaDon = new HoaDon();
//                        hoaDon.setNguoiDung_id(nguoiDung_id);
//                        hoaDon.setThoiGian(currentTime);
//                        hoaDon.setTongTien(tongTien);
//                        hoaDon.setDiaChi(diaChi);
//                        if (hoaDonDAO.themHoaDon(hoaDon) > 0) {
//                            GioHangDAO gioHangDAO = new GioHangDAO(XacNhanThanhToanActivity.this);
//                            for (GioHang gioHang : listGioHang) {
////                                if (gioHang.getTrangThaiMua() == 1) {
//                                    gioHangDAO.xoaSauKhiDatHang(gioHang.getSanPham_id(), nguoiDung_id, 1);
//                                    HoaDonChiTiet hdct = new HoaDonChiTiet();
//                                    HoaDon hoaDon1 = hoaDonDAO.getHoaDonCuoiCung(nguoiDung_id);
//                                    hdct.setHoaDon_id(hoaDon1.getHoaDon_id());
//                                    hdct.setSanPham_id(gioHang.getSanPham_id());
//                                    hdct.setSoLuong(gioHang.getSoLuong());
//                                    hdct.setTrangThaiThanhToan(0);
//                                    hdct.setTrangThaiDonHang(0);
//
////                                    if (hoaDonChiTietDAO.themHoaDonChiTiet(hdct) > 0) {
////                                        SanPham sanPham = sanPhamDAO.getSanPham(gioHang.getSanPham_id());
////                                        sanPham.setSoLuongConLai(Integer.parseInt(String.valueOf(((sanPham.getSoLuongConLai()) - (gioHang.getSoLuong())))));
////                                        sanPhamDAO.soLuongConLai(sanPham);
////                                    }
////                                }
//                                startActivity(new Intent(XacNhanThanhToanActivity.this, MainActivity.class));
//                                finish();
//                                Log.d("XacNhanThanhToan", "Hóa đơn đã được thêm vào cơ sở dữ liệu: " + hdct.toString());
//                            }
//                            Toast.makeText(XacNhanThanhToanActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
//
//                        }
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).setCancelable(false);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
//}
package com.sangnv3.nhom2_duan1_f_food.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sangnv3.nhom2_duan1_f_food.Adapter.SanPhamThanhToanAdapter;
import com.sangnv3.nhom2_duan1_f_food.Dao.GioHangDAO;
import com.sangnv3.nhom2_duan1_f_food.Dao.HoaDonChiTietDAO;
import com.sangnv3.nhom2_duan1_f_food.Dao.HoaDonDAO;
import com.sangnv3.nhom2_duan1_f_food.Dao.SanPham_DAO;
import com.sangnv3.nhom2_duan1_f_food.Model.GioHang;
import com.sangnv3.nhom2_duan1_f_food.Model.HoaDon;
import com.sangnv3.nhom2_duan1_f_food.Model.HoaDonChiTiet;
import com.sangnv3.nhom2_duan1_f_food.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class XacNhanThanhToanActivity extends AppCompatActivity {
    ImageView imgBack, imgHinh_thuc_thanh_toan;
    TextView tvNguoi_nhan_hang, tvSdt_nguoi_nhan, tvGia_tam_tinh, tvThanh_tien, tvDia_chi_nhan_hang, tvHinh_thuc_thanh_toan;
    Button btnXac_nhan_dat_hang;
    HoaDonDAO hoaDonDAO;
    HoaDon hoaDon;
    SharedPreferences sharedPreferences;
    int tongTien, nguoiDung_id, hinhThucThanhToan;
    String diaChi, tenNguoiNhan, sdt;
    List<GioHang> listGioHang;
    RecyclerView recycleView_thanh_toan;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SanPhamThanhToanAdapter sanPhamThanhToanAdapter;
    SanPham_DAO sanPhamDAO;
    GioHang gioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_thanh_toan);
        imgBack = findViewById(R.id.imgBack);
        imgHinh_thuc_thanh_toan = findViewById(R.id.imgHinh_thuc_thanh_toan);
        tvNguoi_nhan_hang = findViewById(R.id.tvNguoi_nhan_hang);
        tvSdt_nguoi_nhan = findViewById(R.id.tvSdt_nguoi_nhan);
        tvGia_tam_tinh = findViewById(R.id.tvGia_tam_tinh);
        tvThanh_tien = findViewById(R.id.tvThanh_tien);
        tvHinh_thuc_thanh_toan = findViewById(R.id.tvHinh_thuc_thanh_toan);
        recycleView_thanh_toan = findViewById(R.id.recycler_view_don_hang_thanh_toan);
        tvDia_chi_nhan_hang = findViewById(R.id.tvDia_chi_nhan_hang);
        btnXac_nhan_dat_hang = findViewById(R.id.btnXac_nhan_dat_hang);
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        nguoiDung_id = sharedPreferences.getInt("maUser", -1);

        hoaDonDAO = new HoaDonDAO(this);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        sanPhamDAO = new SanPham_DAO(this);

        Intent intent = getIntent();
        tongTien = intent.getIntExtra("tongTien", 0);
        hinhThucThanhToan = intent.getIntExtra("hinhThucThanhToan", 0);
        diaChi = intent.getStringExtra("diaChi");
        tenNguoiNhan = intent.getStringExtra("nguoiNhan");
        sdt = intent.getStringExtra("sdt");
        listGioHang = (List<GioHang>) intent.getSerializableExtra("listGioHang");

        sanPhamThanhToanAdapter = new SanPhamThanhToanAdapter(listGioHang, XacNhanThanhToanActivity.this);
        recycleView_thanh_toan.setAdapter(sanPhamThanhToanAdapter);


        tvNguoi_nhan_hang.setText(tenNguoiNhan);
        tvSdt_nguoi_nhan.setText(sdt);
        tvThanh_tien.setText("" + tongTien);
        tvGia_tam_tinh.setText("" + tongTien);
        tvDia_chi_nhan_hang.setText(diaChi);
        if (hinhThucThanhToan == 0) {
            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_account_cash);
            tvHinh_thuc_thanh_toan.setText("Thanh toán tiền mặt");
        } else if (hinhThucThanhToan == 1) {
            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_atm_card);
            tvHinh_thuc_thanh_toan.setText("Thẻ ATM");
        } else if (hinhThucThanhToan == 2) {
            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_atm_card_person);
            tvHinh_thuc_thanh_toan.setText("Thêm thẻ tín dụng/Ghi nợ");
        } else if (hinhThucThanhToan == 3) {
            imgHinh_thuc_thanh_toan.setImageResource(R.drawable.thanhtoan_wallet);
            tvHinh_thuc_thanh_toan.setText("Ví điện tử");
        }


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnXac_nhan_dat_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xacNhanDatHang();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

//    public void xacNhanDatHang() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Xác nhận")
//                .setPositiveButton("Đặt hàng", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Calendar calendar = Calendar.getInstance();
//                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//                        String currentTime = format.format(calendar.getTime());
//
//                        hoaDon = new HoaDon();
//                        hoaDon.setNguoiDung_id(nguoiDung_id);
//                        hoaDon.setThoiGian(currentTime);
//                        hoaDon.setTongTien(tongTien);
//                        hoaDon.setDiaChi(diaChi);
//                        if (hoaDonDAO.themHoaDon(hoaDon) > 0) {
//                            GioHangDAO gioHangDAO = new GioHangDAO(XacNhanThanhToanActivity.this);
//                            for (GioHang gioHang : listGioHang) {
////                                if (gioHang.getTrangThaiMua() == 1) {
//                                gioHangDAO.xoaSauKhiDatHang(gioHang.getSanPham_id(), nguoiDung_id, 1);
//                                HoaDonChiTiet hdct = new HoaDonChiTiet();
//                                HoaDon hoaDon1 = hoaDonDAO.getHoaDonCuoiCung(nguoiDung_id);
//                                hdct.setHoaDon_id(hoaDon1.getHoaDon_id());
//                                hdct.setSanPham_id(gioHang.getSanPham_id());
//                                hdct.setSoLuong(gioHang.getSoLuong());
//                                hdct.setTrangThaiThanhToan(0);
//                                hdct.setTrangThaiDonHang(0);
//                                hoaDonChiTietDAO.themHoaDonChiTiet(hdct);
//                                if(hoaDonChiTietDAO.themHoaDonChiTiet(hdct)>0){
//                                    Toast.makeText(XacNhanThanhToanActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
//                                }else{
//                                    Toast.makeText(XacNhanThanhToanActivity.this, "Đặt hàng không thành công", Toast.LENGTH_SHORT).show();
//                                }
//
//
//                                // Ghi log thông tin hóa đơn đã được thêm vào cơ sở dữ liệu
//                                Log.d("XacNhanThanhToan", "Hóa đơn đã được thêm vào cơ sở dữ liệu: " + hdct.toString());
//
////                                    if (hoaDonChiTietDAO.themHoaDonChiTiet(hdct) > 0) {
////                                        SanPham sanPham = sanPhamDAO.getSanPham(gioHang.getSanPham_id());
////                                        sanPham.setSoLuongConLai(Integer.parseInt(String.valueOf(((sanPham.getSoLuongConLai()) - (gioHang.getSoLuong())))));
////                                        sanPhamDAO.soLuongConLai(sanPham);
////                                    }
////                                }
//                                startActivity(new Intent(XacNhanThanhToanActivity.this, MainActivity.class));
//                                finish();
//                            }
//
//
//                        }
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                }).setCancelable(false);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }
public void xacNhanDatHang() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Xác nhận")
            .setPositiveButton("Đặt hàng", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String currentTime = format.format(calendar.getTime());

                    hoaDon = new HoaDon();
                    hoaDon.setNguoiDung_id(nguoiDung_id);
                    hoaDon.setThoiGian(currentTime);
                    hoaDon.setTongTien(tongTien);
                    hoaDon.setDiaChi(diaChi);
                    long hoaDonID = hoaDonDAO.themHoaDon(hoaDon);
                    if (hoaDonID > 0) {
                        GioHangDAO gioHangDAO = new GioHangDAO(XacNhanThanhToanActivity.this);
                        for (GioHang gioHang : listGioHang) {
                            gioHangDAO.xoaSauKhiDatHang(gioHang.getSanPham_id(), nguoiDung_id, 1);
                            HoaDonChiTiet hdct = new HoaDonChiTiet();
                            hdct.setHoaDon_id((int) hoaDonID);
                            hdct.setSanPham_id(gioHang.getSanPham_id());
                            hdct.setSoLuong(gioHang.getSoLuong());
                            hdct.setTrangThaiThanhToan(0);
                            hdct.setTrangThaiDonHang(0);
                            hoaDonChiTietDAO.themHoaDonChiTiet(hdct);
                            // Ghi log thông tin hóa đơn đã được thêm vào cơ sở dữ liệu
                            Log.d("XacNhanThanhToan", "Hóa đơn đã được thêm vào cơ sở dữ liệu: " + hdct.toString());
                        }
                        Toast.makeText(XacNhanThanhToanActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(XacNhanThanhToanActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(XacNhanThanhToanActivity.this, "Đặt hàng không thành công", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            })
            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setCancelable(false);
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
}

}
