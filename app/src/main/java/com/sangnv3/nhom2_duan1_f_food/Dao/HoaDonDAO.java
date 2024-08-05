package com.sangnv3.nhom2_duan1_f_food.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sangnv3.nhom2_duan1_f_food.Database.DB_Helper;
import com.sangnv3.nhom2_duan1_f_food.Model.HoaDon;
import com.sangnv3.nhom2_duan1_f_food.Model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.Collections;

public class HoaDonDAO {
    DB_Helper dbHelper;

    public HoaDonDAO(Context context) {
        dbHelper = new DB_Helper(context);
    }

    public long themHoaDon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maUser", hoaDon.getNguoiDung_id());
        values.put("ngayMua", hoaDon.getThoiGian());
        values.put("tongTien", hoaDon.getTongTien());
        values.put("diaChi", hoaDon.getDiaChi());

        return sqLiteDatabase.insert("HOADON", null, values);
    }


    public ArrayList<HoaDon> getDsHoaDon(){
        ArrayList<HoaDon> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HOADON", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new HoaDon(
                        cursor.getInt(0),    //hoaDon_id
                        cursor.getInt(1),    //nguoiDung_id
                        cursor.getString(2), //ngayMua
                        cursor.getInt(3)     //tongTien
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }

    public HoaDon getHoaDonCuoiCung(int nguoiDung_id){
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HOADON where maUser = ?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(
                        cursor.getInt(0),    //hoaDon_id
                        cursor.getInt(1),    //nguoiDung_id
                        cursor.getString(2), //ngayMua
                        cursor.getInt(3),     //tongTien
                        cursor.getString(4)    //diaChi
                ));
            }while (cursor.moveToNext());
        }
        Collections.reverse(list);
        return list.get(0);
    }

    public ArrayList<HoaDon> getDsHoaDon(int nguoiDung_id){
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HOADON where maUser = ?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(
                        cursor.getInt(0),    //hoaDon_id
                        cursor.getInt(1),    //nguoiDung_id
                        cursor.getString(2), //ngayMua
                        cursor.getInt(3),     //tongTien
                        cursor.getString(4)    //diaChi
                ));
            }while (cursor.moveToNext());
        }
        Collections.reverse(list);
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<HoaDonChiTiet> getDsHoaDonChiTiet(int nguoiDung_id){
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select hdct.hoaDon_id as hoaDon_id, hdct.maSP as maSP, hdct.trangThaiDonHang as trangThaiDonHang, hdct.trangThaiThanhToan as trangThaiThanhToan, hd.maUser as maUser" +
                " from HOADONCHITIET hdct " +
                "inner join HOADON hd on hdct.hoaDon_id = hd.hoaDon_id" +
                " where maUser =?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDonChiTiet(
                        cursor.getInt(cursor.getColumnIndex("hoaDon_id")),    //hoaDon_id
                        cursor.getInt(cursor.getColumnIndex("maSP")),    //id_sp
                        cursor.getInt(cursor.getColumnIndex("trangThaiDonHang")), //ngayMua
                        cursor.getInt(cursor.getColumnIndex("trangThaiThanhToan")),     //tongTien
                        cursor.getInt(cursor.getColumnIndex("nguoiDung_id"))    //diaChi
                ));
            }while (cursor.moveToNext());
        }
        Collections.reverse(list);
        return list;
    }
}
