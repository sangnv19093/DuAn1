package com.sangnv3.nhom2_duan1_f_food.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sangnv3.nhom2_duan1_f_food.Database.DB_Helper;
import com.sangnv3.nhom2_duan1_f_food.Model.BinhLuan;

import java.util.ArrayList;


public class BinhLuanDAO {
    DB_Helper dbHelper;

    public BinhLuanDAO(Context context) {
        dbHelper = new DB_Helper(context);
    }

    @SuppressLint("Range")
    public ArrayList<BinhLuan> getDsBinhLuan(int sanPham_id) {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select BINHLUAN.binhLuan_id, BINHLUAN.maUser, BINHLUAN.maSP, BINHLUAN.noiDung, BINHLUAN.thoiGian, User.hoTen as tenNguoiDung from BINHLUAN inner join User on BINHLUAN.maUser = User.maUser where BINHLUAN.maSP = ?", new String[]{String.valueOf(sanPham_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(cursor.getColumnIndex("binhLuan_id")),    //binhLuan_id
                        cursor.getInt(cursor.getColumnIndex("maUser")),    //nguoiDung_id
                        cursor.getInt(cursor.getColumnIndex("maSP")),    //sanPham_id
                        cursor.getString(cursor.getColumnIndex("noiDung")), //noiDung
                        cursor.getString(cursor.getColumnIndex("thoiGian")), //thoiGian
                        cursor.getString(cursor.getColumnIndex("tenNguoiDung")) //ten nguoi dung
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public long themBinhLuan(BinhLuan binhLuan) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("maUser", binhLuan.getNguoiDung_id());
        values.put("maSP", binhLuan.getSanPham_id());
        values.put("noiDung", binhLuan.getNoiDung());
        values.put("thoiGian", binhLuan.getThoiGian());

        return sqLiteDatabase.insert("BINHLUAN", null, values);
    }

    public long upDateBinhLuan(BinhLuan binhLuan) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("noiDung", binhLuan.getNoiDung());
        values.put("thoiGian", binhLuan.getThoiGian());

        return sqLiteDatabase.update("BINHLUAN", values, "binhLuan_id = ?", new String[]{String.valueOf(binhLuan.getBinhLuan_id())});
    }

    public long deleteBinhLuan(String binhLuan_id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        return sqLiteDatabase.delete("BINHLUAN", "binhLuan_id = ?", new String[]{binhLuan_id});
    }

    //Lấy toàn bộ danh sách bình luận theo binhLuan_id
    public ArrayList<BinhLuan> getDSBinhLuanCach1() {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select bl.binhLuan_id, nd.maUser, sp.maSP, bl.noiDung, bl.thoiGian, sp.anhSP, sp.tenSP, nd.hoTen " +
                "from BINHLUAN bl " +
                "inner join User nd on bl.maUser = nd.maUser " +
                "inner join SanPham sp on bl.maSP = sp.maSP", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    //Lấy tổng số bình luận theo sanPham_id
    public ArrayList<BinhLuan> getDSBinhLuanCach2() {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select bl.binhLuan_id, nd.maUser, sp.maSP, sp.anhSP, sp.tenSP, count(sp.maSP) as tongBinhLuan " +
                "from BINHLUAN bl " +
                "inner join User nd on bl.maUser = nd.maUser " +
                "inner join SanPham sp on bl.maSP = sp.maSP " +
                "group by sp.maSP", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    //Lấy danh sách bình luận
    public ArrayList<BinhLuan> getDsBinhLuanTheoSanPham_id(int sanPham_id) {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select bl.binhLuan_id, nd.nguoiDung_id, sp.sanPham_id, bl.noiDung, bl.thoiGian, sp.anhSanPham, sp.tenSanPham, nd.hoTen " +
                "from BINHLUAN bl " +
                "inner join User nd on bl.maUser = nd.maUser " +
                "inner join SanPham sp on bl.maSP = sp.maSP where sp.maSP = ?", new String[]{String.valueOf(sanPham_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    //Xoa binh luan theo binhLuan_id
    public boolean xoaBinhLuanTheoBinhLuan_id(int binhLuan_id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("BINHLUAN", "binhLuan_id = ?", new String[]{String.valueOf(binhLuan_id)});
        return check > 0;
    }




}
