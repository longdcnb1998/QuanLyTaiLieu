package com.example.QuanLyTaiLieu;

public class TaiLieu {
    private int ma;
    private String ten;
    private String loaiTaiLieu;
    private String link;
    private String kichthuoc;
    private int idLoaiTaiLieu;

    public TaiLieu(String ten, String loaiTaiLieu, String link, String kichthuoc, int idLoaiTaiLieu) {
        this.ten = ten;
        this.loaiTaiLieu = loaiTaiLieu;
        this.link = link;
        this.kichthuoc = kichthuoc;
        this.idLoaiTaiLieu = idLoaiTaiLieu;
    }

    public TaiLieu() {

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

    public String getLoaiTaiLieu() {
        return loaiTaiLieu;
    }

    public void setLoaiTaiLieu(String loaiTaiLieu) {
        this.loaiTaiLieu = loaiTaiLieu;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKichthuoc() {
        return kichthuoc;
    }

    public void setKichthuoc(String kichthuoc) {
        this.kichthuoc = kichthuoc;
    }

    public int getIdLoaiTaiLieu() {
        return idLoaiTaiLieu;
    }

    public void setIdLoaiTaiLieu(int idLoaiTaiLieu) {
        this.idLoaiTaiLieu = idLoaiTaiLieu;
    }
}
