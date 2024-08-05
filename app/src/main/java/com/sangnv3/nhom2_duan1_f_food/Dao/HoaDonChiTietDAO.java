package com.sangnv3.nhom2_duan1_f_food.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sangnv3.nhom2_duan1_f_food.Database.DB_Helper;
import com.sangnv3.nhom2_duan1_f_food.Model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.Collections;


public class HoaDonChiTietDAO {
    DB_Helper dbHelper;

    public HoaDonChiTietDAO(Context context) {
        dbHelper = new DB_Helper(context);
    }

    public long themHoaDonChiTiet(HoaDonChiTiet hdct) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoaDon_id", hdct.getHoaDon_id());
        values.put("maSP", hdct.getSanPham_id());
        values.put("soLuong", hdct.getSoLuong());
        values.put("trangThaiDonHang", hdct.getTrangThaiThanhToan());
        values.put("trangThaiThanhToan", hdct.getTrangThaiDonHang());
        return sqLiteDatabase.insert("HOADONCHITIET", null, values);
    }

    //Lấy danh sách đơn hàng thông qua hóa đơn chi tiết
    public ArrayList<HoaDonChiTiet> getDonHangByHDCT(int trangThaiDonHang, int nguoiDung_id) {
        ArrayList<HoaDonChiTiet> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select hdct.hoaDon_id, hdct.maSP, hdct.soLuong, hdct.trangThaiDonHang, hdct.trangThaiThanhToan, " +
                "sp.tenSP, sp.anhSP, sp.giaSP, " +
                "hd.ngayMua, hd.diaChi, (sp.giaSP * hdct.soLuong) as tongTien, hd.maUser " +
                "from HOADONCHITIET hdct " +
                "inner join HOADON hd on hdct.hoaDon_id = hd.hoaDon_id " +
                "inner join SanPham sp on hdct.maSP = sp.maSP " +
                "where hdct.trangThaiDonHang = ? and hd.maUser = ?", new String[]{String.valueOf(trangThaiDonHang), String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new HoaDonChiTiet(
                        cursor.getInt(0),   //hoaDon_id
                        cursor.getInt(1),   //sanPham_id
                        cursor.getInt(2),   //soLuong
                        cursor.getInt(3),   //trangThaiDonHang
                        cursor.getInt(4),   //trangThaiThanhToan
                        cursor.getString(5),//tenSanPham
                        cursor.getString(6),//anhSanPham
                        cursor.getInt(7),   //giaSanPham
                        cursor.getString(8),//ngayMua
                        cursor.getString(9),//diaChi
                        cursor.getInt(10),   //tongTien
                        cursor.getInt(11)    //nguoiDung_id
                ));
            } while (cursor.moveToNext());
        }
        Collections.reverse(listResult);
        return listResult;
    }

    //Lấy danh sách đơn hàng thông qua hóa đơn chi tiết cho admin
    public ArrayList<HoaDonChiTiet> getDonHangByHDCTForAdmin(int trangThaiDonHang) {
        ArrayList<HoaDonChiTiet> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select hdct.hoaDon_id, hdct.maSP, hdct.soLuong, hdct.trangThaiDonHang, hdct.trangThaiThanhToan, " +
                "sp.tenSP, sp.anhSP, sp.giaSP, " +
                "hd.ngayMua, hd.diaChi, (sp.giaSP * hdct.soLuong) as tongTien, hd.maUser " +
                "from HOADONCHITIET hdct " +
                "inner join HOADON hd on hdct.hoaDon_id = hd.hoaDon_id " +
                "inner join SanPham sp on hdct.maSP = sp.maSP " +
                "where hdct.trangThaiDonHang = ? ", new String[]{String.valueOf(trangThaiDonHang)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new HoaDonChiTiet(
                        cursor.getInt(0),   //hoaDon_id
                        cursor.getInt(1),   //sanPham_id
                        cursor.getInt(2),   //soLuong
                        cursor.getInt(3),   //trangThaiDonHang
                        cursor.getInt(4),   //trangThaiThanhToan
                        cursor.getString(5),//tenSanPham
                        cursor.getString(6),//anhSanPham
                        cursor.getInt(7),   //giaSanPham
                        cursor.getString(8),//ngayMua
                        cursor.getString(9),//diaChi
                        cursor.getInt(10),   //tongTien
                        cursor.getInt(11)   //nguoiDung_id
                ));
            } while (cursor.moveToNext());
        }
        Collections.reverse(listResult);
        return listResult;
    }

    public boolean thayDoiTrangThaiDonHang(int newTrangThai, int hoaDon_id, int sanPham_id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangThaiDonHang", newTrangThai);
        long check = sqLiteDatabase.update("HOADONCHITIET", contentValues, "hoaDon_id = ? AND maSP = ?", new String[]{String.valueOf(hoaDon_id), String.valueOf(sanPham_id)});
        return check > 0;
    }

    public boolean xoaDonHang(int hoaDon_id, int sanPham_id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("HOADONCHITIET", "hoaDon_id = ? and maSP = ?", new String[]{String.valueOf(hoaDon_id), String.valueOf(sanPham_id)});
        return check > 0;
    }
    public boolean thayDoiTrangThaiThanhToan(int newTrangThaiThanhToan, int hoaDon_id, int sanPham_id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangThaiThanhToan", newTrangThaiThanhToan);
        long check = sqLiteDatabase.update("HOADONCHITIET",contentValues,"hoaDon_id = ? and maSP = ?", new String[]{String.valueOf(hoaDon_id), String.valueOf(sanPham_id)});
        return check > 0;
    }
}
