package com.sangnv3.nhom2_duan1_f_food.Dao;//package chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Dao;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.util.ArrayList;
//
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Database.DB_Helper;
//import chienncph29246.fpoly.example.nhom2_duan1_happyfoo.Model.User;
//
//public class User_Dao {
//    private DB_Helper dbHelper;
//    private SQLiteDatabase db;
//    public User_Dao(Context context){
//        dbHelper = new DB_Helper(context);
//        db = dbHelper.getWritableDatabase();
//    }
//    public boolean themUser(User user){
//        ContentValues values = new ContentValues();
//        values.put("email", user.getEmail());
//        values.put("name", user.getName());
//        values.put("address", user.getAddress());
//        values.put("pass", user.getPass());
//        values.put("role", user.getRole());
//        long check = db.insert("User", null, values);
//        if (check>0)
//            return true;
//        return false;
//    }
//
//    public boolean suaUser(User user){
//        ContentValues values = new ContentValues();
//        values.put("email", user.getEmail());
//        values.put("name", user.getName());
//        values.put("address", user.getAddress());
//        values.put("pass", user.getPass());
//        values.put("role", user.getRole());
//        String[] dk = {String.valueOf(user.getMaUser())};
//        long check = db.update("User", values, "maUser=?", dk);
//        if (check>0)
//            return true;
//        return false;
//    }
//
//    public boolean xoaUser(User user){
//        String[] dk = {String.valueOf(user.getMaUser())};
//        long check = db.delete("User", "maUser=?", dk);
//        if (check>0)
//            return true;
//        return false;
//    }
//    public ArrayList<User> getUser(){
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//        ArrayList<User> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT * FROM User", null);
//        if (cursor.moveToFirst()){
//            do {
//                int maUser = cursor.getInt(0);
//                String email = cursor.getString(1);
//                String name = cursor.getString(2);
//                String address = cursor.getString(3);
//                String pass = cursor.getString(4);
//                String role = cursor.getString(5);
//                User user = new User(maUser, email, name, address, pass, role);
//                list.add(user);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return list;
//    }
//
//}
