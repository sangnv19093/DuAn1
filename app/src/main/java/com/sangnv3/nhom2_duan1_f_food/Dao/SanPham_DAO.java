//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.ArrayList;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Database.DB_Helper;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.SanPham;
//
//public class SanPham_DAO {
//    private DB_Helper dbHelper;
//    private SQLiteDatabase db;
//    public SanPham_DAO(Context context) {
//        dbHelper = new DB_Helper(context);
//        db = dbHelper.getWritableDatabase();
//    }
//    public boolean themSP(SanPham sanPham){
//        ContentValues values = new ContentValues();
//        values.put("anhSP", sanPham.getAnhSP());
//        values.put("tenSP", sanPham.getTenSP());
//        values.put("phanloaiSP", sanPham.getPhanloaiSP());
//        values.put("trongluongSP", sanPham.getTrongluongSP());
//        values.put("giaSP", sanPham.getGiaSP());
//        values.put("mota", sanPham.getMota());
//        long check = db.insert("SanPham", null, values);
//        if (check > 0)
//            return true;
//        return false;
//    }
//    public boolean suaSP(SanPham sanPham){
//        ContentValues values = new ContentValues();
//        values.put("anhSP", sanPham.getAnhSP());
//        values.put("tenSP", sanPham.getTenSP());
//        values.put("phanloaiSP", sanPham.getPhanloaiSP());
//        values.put("trongluongSP", sanPham.getTrongluongSP());
//        values.put("giaSP", sanPham.getGiaSP());
//        values.put("mota", sanPham.getMota());
//        String[] dk = {String.valueOf(sanPham.getMaSP())};
//        long check = db.update("SanPham", values, "maSP=?", dk);
//        if (check > 0)
//            return true;
//        return false;
//    }
//    public boolean xoaSP(SanPham sanPham){
//        String[] dk = {String.valueOf(sanPham.getMaSP())};
//        long check = db.delete("SanPham", "maSP=?", dk);
//        if (check > 0)
//            return true;
//        return false;
//    }
//
//    public ArrayList<SanPham> getSP(){
//        SQLiteDatabase database= dbHelper.getReadableDatabase();
//        ArrayList<SanPham> list_sp = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
//        if (cursor.moveToFirst()){
//            do {
//                int maSP = cursor.getInt(0);
//                String anhSP = cursor.getString(1);
//                String tenSP = cursor.getString(2);
//                String phanloai = cursor.getString(3);
//                int trongluong = cursor.getInt(4);
//                int giaSP = cursor.getInt(5);
//                String mota = cursor.getString(6);
//
//                SanPham sanPham = new SanPham(maSP, anhSP, tenSP, phanloai, trongluong, giaSP, mota);
//                list_sp.add(sanPham);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return list_sp;
//    }
//    public SanPham getSanPham(int sanPham_id) {
//        SanPham sanPham = new SanPham();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM where isXoaMem=0 and maSP= ?", new String[]{String.valueOf(sanPham_id)});
//        if (cursor.getCount() != 0) {
//            cursor.moveToFirst();
//            do {
//                sanPham = new SanPham(
//                        cursor.getInt(0), //sanPham_id
//                        cursor.getString(1), //loaiSanPham_id
//                        cursor.getString(2), //tenSanPham
//                        cursor.getString(3), //anhSanPham
//                        cursor.getInt(4),//trong luong
//                        cursor.getInt(5) ,//giaSanPham
//                        cursor.getString(6) //moTa
//                           //soLuongConLai
//                );
//            } while (cursor.moveToNext());
//        }
//        return sanPham;
//    }
//
//}
package com.sangnv3.nhom2_duan1_f_food.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sangnv3.nhom2_duan1_f_food.Database.DB_Helper;
import com.sangnv3.nhom2_duan1_f_food.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPham_DAO {
    private DB_Helper dbHelper;
    private SQLiteDatabase db;

    public SanPham_DAO(Context context) {
        dbHelper = new DB_Helper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean themSP(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("anhSP", sanPham.getAnhSP());
        values.put("tenSP", sanPham.getTenSP());
        values.put("phanloaiSP", sanPham.getPhanloaiSP());
        values.put("trongluongSP", sanPham.getTrongluongSP());
        values.put("giaSP", sanPham.getGiaSP());
        values.put("mota", sanPham.getMota());
        long check = db.insert("SanPham", null, values);
        return check > 0;
    }
    @SuppressLint("Range")
    public List<SanPham> getTop10SanPhamBanNhieuNhat() {
        List<SanPham> top10List = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Truy vấn SQL để lấy top 10 sản phẩm bán nhiều nhất
        String query = "SELECT SanPham.*, COUNT(HOADONCHITIET.maSP) AS soLuongBan " +
                "FROM SanPham " +
                "INNER JOIN HOADONCHITIET ON SanPham.maSP = HOADONCHITIET.maSP " +
                "GROUP BY SanPham.maSP " +
                "ORDER BY soLuongBan DESC " +
                "LIMIT 10";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                // Đọc dữ liệu từ Cursor và tạo đối tượng SanPham tương ứng
                int maSP = cursor.getInt(cursor.getColumnIndex("maSP"));
                String tenSP = cursor.getString(cursor.getColumnIndex("tenSP"));
                // Đọc thêm các thông tin khác về sản phẩm nếu cần

                // Thêm sản phẩm vào danh sách top 10
                SanPham sanPham = new SanPham(maSP, tenSP); // Tạo đối tượng SanPham từ dữ liệu Cursor
                top10List.add(sanPham);
            } while (cursor.moveToNext());
        }

        // Đóng Cursor và database sau khi sử dụng xong
        cursor.close();
        db.close();

        return top10List;
    }

    public boolean suaSP(SanPham sanPham) {
        ContentValues values = new ContentValues();
        values.put("anhSP", sanPham.getAnhSP());
        values.put("tenSP", sanPham.getTenSP());
        values.put("phanloaiSP", sanPham.getPhanloaiSP());
        values.put("trongluongSP", sanPham.getTrongluongSP());
        values.put("giaSP", sanPham.getGiaSP());
        values.put("mota", sanPham.getMota());
        String[] dk = {String.valueOf(sanPham.getMaSP())};
        long check = db.update("SanPham", values, "maSP=?", dk);
        return check > 0;
    }

    public boolean xoaSP(SanPham sanPham) {
        String[] dk = {String.valueOf(sanPham.getMaSP())};
        long check = db.delete("SanPham", "maSP=?", dk);
        return check > 0;
    }

    public ArrayList<SanPham> getSP() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ArrayList<SanPham> list_sp = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
        if (cursor.moveToFirst()) {
            do {
                int maSP = cursor.getInt(0);
                String anhSP = cursor.getString(1);
                String tenSP = cursor.getString(2);
                String phanloai = cursor.getString(3);
                int trongluong = cursor.getInt(4);
                int giaSP = cursor.getInt(5);
                String mota = cursor.getString(6);

                SanPham sanPham = new SanPham(maSP, anhSP, tenSP, phanloai, trongluong, giaSP, mota);
                list_sp.add(sanPham);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list_sp;
    }

    public SanPham getSanPham(int sanPham_id) {
        SanPham sanPham = null;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM where isXoaMem=0 and maSP= ?", new String[]{String.valueOf(sanPham_id)});
        if (cursor.moveToFirst()) {
            do {
                sanPham = new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getString(1), //loaiSanPham_id
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4), //trong luong
                        cursor.getInt(5), //giaSanPham
                        cursor.getString(6) //moTa
                        //soLuongConLai
                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        return sanPham;
    }

    public ArrayList<SanPham> timKiemSanPham(String tuKhoa) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ArrayList<SanPham> list_sp = new ArrayList<>();
        String query = "SELECT * FROM SanPham WHERE tenSP LIKE '%" + tuKhoa + "%'";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int maSP = cursor.getInt(0);
                String anhSP = cursor.getString(1);
                String tenSP = cursor.getString(2);
                String phanloai = cursor.getString(3);
                int trongluong = cursor.getInt(4);
                int giaSP = cursor.getInt(5);
                String mota = cursor.getString(6);

                SanPham sanPham = new SanPham(maSP, anhSP, tenSP, phanloai, trongluong, giaSP, mota);
                list_sp.add(sanPham);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list_sp;
    }
}

