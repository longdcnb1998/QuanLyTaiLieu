package com.example.QuanLyTaiLieu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.QuanLyTaiLieu.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_EDIT = 15;
    private static final int ADD_REQUEST = 123;
    private ActivityMainBinding binding;
    private TheLoaiAdapter adapter;
    private ArrayList<TheLoai> listTheLoai;
    private List<TheLoai> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        SharedPreferences preferences = getSharedPreferences("dataRoom", MODE_PRIVATE);
        int first = preferences.getInt("LoginFirst", -1);
        if (first == -1) {
            ThemLoaiTaiLieu();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("LoginFirst", 1);
            editor.apply();
        }
        LayDanhSachTheLoai();
        intiClick();
    }

    private void intiClick() {
        binding.lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DanhSachTaiLieuActivity.class);
                intent.putExtra("id_the_loai", list.get(position).getMa());
                startActivity(intent);
            }
        });

        binding.lvTheLoai.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btn_edit:
                                Intent intent = new Intent(MainActivity.this, SuaTheLoaiActivity.class);
                                intent.putExtra("Id", list.get(position).getMa());
                                intent.putExtra("Name", list.get(position).getTen());
                                intent.putExtra("Description", list.get(position).getMota());
                                startActivityForResult(intent, REQUEST_EDIT);
                                break;
                            case R.id.btn_delete:
                                XoaTheLoai(list.get(position));
                                break;

                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });
        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemTheLoaiActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
            }
        });
    }

    private void ThemLoaiTaiLieu() {
        listTheLoai = new ArrayList<>();
        listTheLoai.add(new TheLoai("Kinh doanh", "Chua tai lieu phong kinh doanh"));
        listTheLoai.add(new TheLoai("Tổ chức", "Chua tai lieu phong to chuc"));
        listTheLoai.add(new TheLoai("Phần mềm", "Chua tai lieu phong kinh doanh"));
        listTheLoai.add(new TheLoai("Kiểm định", "Chua tai lieu phong kinh doanh"));
        listTheLoai.add(new TheLoai("Tổng hợp", "Chua tai lieu phong kinh doanh"));

        DatabaseHelper helper = new DatabaseHelper(getBaseContext());
        for (TheLoai theLoai : listTheLoai) {
            helper.ThemTheLoai(theLoai);
        }
        Toast.makeText(this, "Thêm thể loại thành công", Toast.LENGTH_SHORT).show();
    }

    private void LayDanhSachTheLoai() {
        DatabaseHelper helper = new DatabaseHelper(getBaseContext());
        list = helper.LayTatCaTheLoai();
        if (list != null) {
            adapter = new TheLoaiAdapter(this, (ArrayList<TheLoai>) list);
            binding.lvTheLoai.setAdapter(adapter);
        }
    }

    private void XoaTheLoai(TheLoai theLoai) {
        DatabaseHelper helper = new DatabaseHelper(getBaseContext());
        helper.XoaTheLoai(theLoai);
        LayDanhSachTheLoai();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra("Name");
            String des = data.getStringExtra("Description");

            TheLoai theLoai = new TheLoai(name, des);
            DatabaseHelper helper = new DatabaseHelper(getBaseContext());
            helper.ThemTheLoai(theLoai);
            LayDanhSachTheLoai();
            Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
            int id = data.getIntExtra("Id", -1);
            if ((id == -1)) {
                Toast.makeText(this, "Không thể cập nhập", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra("Name");
            String des = data.getStringExtra("Description");

            TheLoai theLoai = new TheLoai(name, des);
            theLoai.setMa(id);
            DatabaseHelper helper = new DatabaseHelper(getBaseContext());
            helper.CapNhatTheLoai(theLoai);
            LayDanhSachTheLoai();
            Toast.makeText(this, "Đã cập nhập", Toast.LENGTH_SHORT).show();
        }

    }
}
