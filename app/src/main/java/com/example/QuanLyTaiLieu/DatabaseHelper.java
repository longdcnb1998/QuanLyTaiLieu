package com.example.QuanLyTaiLieu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String database_name = "ql_tai_lieu";
    public static int database_version = 1;
    public static String table_name = "tb_the_loai";
    private String table_tai_lieu = "tb_tai_lieu";
    public static String ma_the_loai = "ma";
    public static String ma_tai_lieu = "ma";
    public static String id_the_loai = "id_the_loai";
    public static String ten = "ten";
    public static String mota = "mota";
    public static String loai_tai_lieu = "loai_tai_lieu";
    public static String link = "link";
    public static String size = "size";

    private Context context;

    private String createTBTheLoai = "CREATE TABLE " + table_name + " (" + ma_the_loai + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ten + " TEXT, " + mota + " TEXT)";

    private String createTBTaiLieu = "CREATE TABLE " + table_tai_lieu + "(" + ma_tai_lieu + " INTEGER PRIMARY KEY AUTOINCREMENT, " + id_the_loai + " INTEGER ," + ten + " TEXT, " + loai_tai_lieu + " TEXT, " + link + " TEXT, " + size + " TEXT )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTBTheLoai);
        db.execSQL(createTBTaiLieu);
        Toast.makeText(context, "Create Database success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean ThemTheLoai(TheLoai theLoai) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ten, theLoai.getTen());
        values.put(mota, theLoai.getMota());
        long result = database.insert(table_name, null, values);
        if (result > 0) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }


    public List<TheLoai> LayTatCaTheLoai() {
        List<TheLoai> list = new ArrayList<>();

        String query = "SELECT * FROM " + table_name;

        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                TheLoai theLoai = new TheLoai();
                theLoai.setMa(cursor.getInt(0));
                theLoai.setTen(cursor.getString(1));
                theLoai.setMota(cursor.getString(2));
                list.add(theLoai);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }

    public boolean ThemTaiLieu(TaiLieu taiLieu) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id_the_loai, taiLieu.getIdLoaiTaiLieu());
        values.put(ten, taiLieu.getTen());
        values.put(loai_tai_lieu, taiLieu.getLoaiTaiLieu());
        values.put(link, taiLieu.getLink());
        values.put(size, taiLieu.getKichthuoc());

        long result = database.insert(table_tai_lieu, null, values);
        if (result > 0) {
            database.close();
            return true;
        }
        database.close();
        return false;
    }

    public void XoaTaiLieu(TaiLieu taiLieu) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + table_tai_lieu + " WHERE " + ma_tai_lieu + "='" + taiLieu.getMa() + "'";
        database.execSQL(deleteQuery);
    }

    public List<TaiLieu> LayDanhSachTaiLieu(int id) {
        List<TaiLieu> list = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM " + table_tai_lieu + " WHERE " + id_the_loai + "='" + id + "'";

        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                TaiLieu taiLieu = new TaiLieu();
                taiLieu.setMa(cursor.getInt(0));
                taiLieu.setIdLoaiTaiLieu(cursor.getInt(1));
                taiLieu.setTen(cursor.getString(2));
                taiLieu.setLoaiTaiLieu(cursor.getString(3));
                taiLieu.setLink(cursor.getString(4));
                taiLieu.setKichthuoc(cursor.getString(5));
                list.add(taiLieu);
            }
            while (cursor.moveToNext());
        }
        return list;
    }

    public void XoaTheLoai(TheLoai theLoai) {
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + table_name + " WHERE " + ma_the_loai + "='" + theLoai.getMa() + "'";
        database.execSQL(deleteQuery);
    }

    public void CapNhatTheLoai(TheLoai theLoai) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ten, theLoai.getTen());
        cv.put(mota, theLoai.getMota());
        database.update(table_name, cv, ma_the_loai + " = " + theLoai.getMa(), null);
    }
}
