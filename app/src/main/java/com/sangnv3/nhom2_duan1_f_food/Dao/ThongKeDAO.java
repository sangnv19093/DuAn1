package com.sangnv3.nhom2_duan1_f_food.Dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sangnv3.nhom2_duan1_f_food.Database.DB_Helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThongKeDAO {

    private final DB_Helper dbHelper;

    public ThongKeDAO(Context context) {
        dbHelper = new DB_Helper(context);
    }
    @SuppressLint("Range")
    public int getTongTienTheoThanga(Date tuNgay, Date denNgay) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int tongTien = 0;

        // Định dạng ngày thành chuỗi để sử dụng trong câu truy vấn
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        String tuNgayString = dateFormat.format(tuNgay);
        String denNgayString = dateFormat.format(denNgay);

        // Thực hiện truy vấn để tính tổng tiền theo khoảng ngày
        Cursor cursor = db.rawQuery("SELECT SUM(HOADONCHITIET.soLuong * SanPham.giaSP) AS tongTien " +
                        "FROM HOADONCHITIET " +
                        "INNER JOIN SanPham ON HOADONCHITIET.maSP = SanPham.maSP " +
                        "INNER JOIN HOADON ON HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +
                        "WHERE HOADON.ngayMua BETWEEN ? AND ? AND HOADONCHITIET.trangThaiDonHang = 3 AND HOADONCHITIET.trangThaiThanhToan = 1",
                new String[]{tuNgayString, denNgayString});

        if (cursor.moveToFirst()) {
            tongTien = cursor.getInt(cursor.getColumnIndex("tongTien"));
        }

        cursor.close();
        db.close();

        return tongTien;
    }


    @SuppressLint("Range")
    public int getTongTienTheoThang(Date tuNgay, Date denNgay, int nguoiDungId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int tongTien = 0;

        // Định dạng ngày thành chuỗi để sử dụng trong câu truy vấn
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        String tuNgayString = dateFormat.format(tuNgay);
        String denNgayString = dateFormat.format(denNgay);

        // Thực hiện truy vấn để tính tổng tiền theo khoảng ngày và ID của người dùng
        Cursor cursor = db.rawQuery("SELECT SUM(HOADONCHITIET.soLuong * SanPham.giaSP) AS tongTien " +
                        "FROM HOADONCHITIET " +
                        "INNER JOIN SanPham ON HOADONCHITIET.maSP = SanPham.maSP " +
                        "INNER JOIN HOADON ON HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +
                        "WHERE HOADON.ngayMua BETWEEN ? AND ? AND HOADON.maUser = ? AND HOADONCHITIET.trangThaiDonHang = 3 AND HOADONCHITIET.trangThaiThanhToan = 1",
                new String[]{tuNgayString, denNgayString, String.valueOf(nguoiDungId)});

        if (cursor.moveToFirst()) {
            tongTien = cursor.getInt(cursor.getColumnIndex("tongTien"));
        }

        cursor.close();
        db.close();

        return tongTien;
    }
}
