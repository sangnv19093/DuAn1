package com.sangnv3.nhom2_duan1_f_food.Model;

public class HoaDon {
    private int hoaDon_id;
    private int nguoiDung_id;
    private String thoiGian;
    private int tongTien;
    private String month;
    private String diaChi;

    public HoaDon(int hoaDon_id, int nguoiDung_id, String thoiGian, int tongTien) {
        this.hoaDon_id = hoaDon_id;
        this.nguoiDung_id = nguoiDung_id;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
    }

    public String getMonth() {
        return month;
    }

    public HoaDon(String month) {
        this.month = month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public HoaDon(int hoaDon_id, int nguoiDung_id, String thoiGian, int tongTien, String diaChi) {
        this.hoaDon_id = hoaDon_id;
        this.nguoiDung_id = nguoiDung_id;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
        this.diaChi = diaChi;
    }

    public HoaDon(int nguoiDung_id, String thoiGian, int tongTien, String diaChi) {
        this.nguoiDung_id = nguoiDung_id;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
        this.diaChi = diaChi;
    }

    public HoaDon() {
    }

    public int getHoaDon_id() {
        return hoaDon_id;
    }

    public void setHoaDon_id(int hoaDon_id) {
        this.hoaDon_id = hoaDon_id;
    }

    public int getNguoiDung_id() {
        return nguoiDung_id;
    }

    public void setNguoiDung_id(int nguoiDung_id) {
        this.nguoiDung_id = nguoiDung_id;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
