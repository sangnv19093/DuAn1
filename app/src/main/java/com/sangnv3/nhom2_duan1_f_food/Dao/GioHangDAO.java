package com.sangnv3.nhom2_duan1_f_food.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sangnv3.nhom2_duan1_f_food.Database.DB_Helper;
import com.sangnv3.nhom2_duan1_f_food.Model.GioHang;

import java.util.ArrayList;

public class GioHangDAO  {

    DB_Helper dbHelper;

    public GioHangDAO(Context context) {
        dbHelper = new DB_Helper(context);
    }

    public long themVaoGioHang(GioHang gioHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maUser", gioHang.getNguoiDung_id());
        values.put("maSP", gioHang.getSanPham_id());
        values.put("soLuong", gioHang.getSoLuong());
        values.put("trangThaiMua", gioHang.getTrangThaiMua());

        return sqLiteDatabase.insert("GIOHANG", null, values);
    }

    public long suaSoLuong(GioHang gioHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soLuong", gioHang.getSoLuong());

        return sqLiteDatabase.update("GIOHANG", values, "maSP = ? and maUser = ?", new String[]{String.valueOf(gioHang.getSanPham_id()), String.valueOf(gioHang.getNguoiDung_id())});
    }

    public long suaTrangThaiMua(GioHang gioHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("trangThaiMua", gioHang.getTrangThaiMua());

        return sqLiteDatabase.update("GIOHANG", values, "maSP = ? and maUser = ?", new String[]{String.valueOf(gioHang.getSanPham_id()), String.valueOf(gioHang.getNguoiDung_id())});
    }

    public long xoaSauKhiDatHang(int sanPham_id, int nguoiDung_id, int trangThaiMua){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        return sqLiteDatabase.delete("GIOHANG", "maSP = ? and maUser = ? and trangThaiMua = ?", new String[]{String.valueOf(sanPham_id), String.valueOf(nguoiDung_id), String.valueOf(trangThaiMua)});
    }

    public long xoaKhoiGioHang(int sanPham_id, int nguoiDung_id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        return sqLiteDatabase.delete("GIOHANG", "maSP = ? and maUser = ?", new String[]{String.valueOf(sanPham_id), String.valueOf(nguoiDung_id)});
    }

    @SuppressLint("Range")
    public ArrayList<GioHang> getDsGioHang(int nguoiDung_id){
        ArrayList<GioHang> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select GIOHANG.maUser, GIOHANG.maSP, GIOHANG.soLuong as soLuong, GIOHANG.trangThaiMua as trangThaiMua, SanPham.giaSP as giaSP," +
                " SanPham.tenSP as tenSP, SanPham.anhSP as anhSP from GIOHANG inner join User on GIOHANG.maUser = User.maUser" +
                " inner join SanPham on GIOHANG.maSP = SanPham.maSP where GIOHANG.maUser = ?" , new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new GioHang(
                        cursor.getInt(cursor.getColumnIndex("maUser")),
                        cursor.getInt(cursor.getColumnIndex("maSP")),
                        cursor.getInt(cursor.getColumnIndex("soLuong")),
                        cursor.getInt(cursor.getColumnIndex("trangThaiMua")),
                        cursor.getInt(cursor.getColumnIndex("giaSP")),
                        cursor.getString(cursor.getColumnIndex("tenSP")),
                        cursor.getString(cursor.getColumnIndex("anhSP"))));
            }while (cursor.moveToNext());
        }
        return listResult;
    }

    @SuppressLint("Range")
    public ArrayList<GioHang> getDsMuaHang(int nguoiDung_id, int trangThaiMua){
        ArrayList<GioHang> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select GIOHANG.maUser, GIOHANG.maSP, GIOHANG.soLuong as soLuong, GIOHANG.trangThaiMua as trangThaiMua, SanPham.giaSP as giaSP," +
                " SanPham.tenSP as tenSP, SanPham.anhSP as anhSP from GIOHANG inner join User on GIOHANG.maUser = User.maUser" +
                " inner join SanPham on GIOHANG.maSP = SanPham.maSP where GIOHANG.maUser = ? and GIOHANG.trangThaiMua = ?" , new String[]{String.valueOf(nguoiDung_id), String.valueOf(trangThaiMua)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new GioHang(
                        cursor.getInt(cursor.getColumnIndex("maUser")),
                        cursor.getInt(cursor.getColumnIndex("maSP")),
                        cursor.getInt(cursor.getColumnIndex("soLuong")),
                        cursor.getInt(cursor.getColumnIndex("trangThaiMua")),
                        cursor.getInt(cursor.getColumnIndex("giaSP")),
                        cursor.getString(cursor.getColumnIndex("tenSP")),
                        cursor.getString(cursor.getColumnIndex("anhSP"))));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}


