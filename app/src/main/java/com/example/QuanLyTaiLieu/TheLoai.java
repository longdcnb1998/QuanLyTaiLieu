package com.example.QuanLyTaiLieu;

public class TheLoai {

    private int ma;
    private String ten;
    private String mota;

    public TheLoai(String ten, String mota) {
        this.ten = ten;
        this.mota = mota;
    }

    public TheLoai() {

    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
