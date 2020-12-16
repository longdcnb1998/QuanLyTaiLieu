package com.example.QuanLyTaiLieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.QuanLyTaiLieu.databinding.ActivityThemTaiLieuBinding;


public class ThemTaiLieu extends AppCompatActivity {

    ActivityThemTaiLieuBinding binding;
    private int id_the_loai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tai_lieu);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_them_tai_lieu);

        Intent intent = getIntent();
        id_the_loai = intent.getIntExtra("id_the_loai", -1);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id_the_loai != -1) {
                    TaiLieu taiLieu = new TaiLieu(binding.edtName.getText().toString().trim(), binding.edtQuequan.getText().toString().trim(),
                            binding.edtNamsinh.getText().toString().trim(), binding.edtType.getText().toString().trim(), id_the_loai);
                    DatabaseHelper helper = new DatabaseHelper(getBaseContext());
                    helper.ThemTaiLieu(taiLieu);
                    setResult(RESULT_OK, null);
                    finish();
                }
            }
        });

    }
}
