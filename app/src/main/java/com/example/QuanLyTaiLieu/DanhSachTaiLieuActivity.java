package com.example.QuanLyTaiLieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;



import java.util.ArrayList;
import java.util.List;

public class DanhSachTaiLieuActivity extends AppCompatActivity {

    ActivityTaiLieuBinding binding;
    private TaiLieuAdapter adapter;
    private int REQUEST_ADD = 123;
    private int id_the_loai;
    private int REQUEST_EDIT = 124;
    private List<TaiLieu> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_lieu);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tai_lieu);
        Intent intent = getIntent();
        id_the_loai = intent.getIntExtra("id_the_loai", -1);
        LayDanhSachTaiLieu();
        initClick();
    }

    private void initClick() {
        binding.lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        binding.lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(DanhSachTaiLieuActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btn_edit:
                                Intent intent = new Intent(DanhSachTaiLieuActivity.this, SuaTaiLieuActivity.class);
                                startActivityForResult(intent, REQUEST_EDIT);
                                break;
                            case R.id.btn_delete:
                                XoaTaiLieu(list.get(position));
                                break;

                        }
                        return true;
                    }
                });
                popupMenu.show();
                return true;
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachTaiLieuActivity.this, ThemTaiLieu.class);
                intent.putExtra("id_the_loai", id_the_loai);
                startActivityForResult(intent, REQUEST_ADD);
            }
        });
    }

    private void XoaTaiLieu(TaiLieu taiLieu) {
        DatabaseHelper helper = new DatabaseHelper(getBaseContext());
        helper.XoaTaiLieu(taiLieu);
        LayDanhSachTaiLieu();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ADD && resultCode == RESULT_OK) {
            LayDanhSachTaiLieu();
        }else if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK){

        }
    }

    private void LayDanhSachTaiLieu() {
        if (id_the_loai != -1) {
            DatabaseHelper helper = new DatabaseHelper(getBaseContext());
            list = helper.LayDanhSachTaiLieu(id_the_loai);
            if (list != null) {
                adapter = new TaiLieuAdapter(this, (ArrayList<TaiLieu>) list);
                binding.lvNhanVien.setAdapter(adapter);
            }

        }
    }
}
