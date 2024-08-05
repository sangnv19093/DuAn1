package com.sangnv3.nhom2_duan1_f_food.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int maSP;
    private String anhSP;
    private String tenSP;
    private String phanloaiSP;
    private int trongluongSP;
    private int giaSP;
    private String mota;

    public SanPham(int maSP, String anhSP, String tenSP, String phanloaiSP, int trongluongSP, int giaSP, String mota) {
        this.maSP = maSP;
        this.anhSP = anhSP;
        this.tenSP = tenSP;
        this.phanloaiSP = phanloaiSP;
        this.trongluongSP = trongluongSP;
        this.giaSP = giaSP;
        this.mota = mota;
    }
    public SanPham(int maSP, String tenSP) {
        this.maSP = maSP;
        this.tenSP = tenSP;
    }

    public SanPham() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getAnhSP() {
        return anhSP;
    }

    public void setAnhSP(String anhSP) {
        this.anhSP = anhSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getPhanloaiSP() {
        return phanloaiSP;
    }

    public void setPhanloaiSP(String phanloaiSP) {
        this.phanloaiSP = phanloaiSP;
    }

    public int getTrongluongSP() {
        return trongluongSP;
    }

    public void setTrongluongSP(int trongluongSP) {
        this.trongluongSP = trongluongSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
