package com.example.QuanLyTaiLieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.QuanLyTaiLieu.databinding.ActivitySuaTheLoaiBinding;

public class SuaTheLoaiActivity extends AppCompatActivity {

    ActivitySuaTheLoaiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_the_loai);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sua_the_loai);
        Intent intent = getIntent();


        int id = intent.getIntExtra("Id", -1);
        if (id != -1) {
            setTitle("Sua The Loai");
            String name = intent.getStringExtra("Name");
            String des = intent.getStringExtra("Description");
            binding.edtTen.setText(name);
            binding.edtMota.setText(des);
        }

        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LuuTheLoai();
            }
        });
    }


    private void LuuTheLoai() {
        String name = binding.edtTen.getText().toString().trim();
        String des = binding.edtMota.getText().toString().trim();

        if (name.isEmpty() || des.isEmpty()) {
            Toast.makeText(this, "Làm ơn nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra("Name", name);
        data.putExtra("Description", des);

        int id = getIntent().getIntExtra("Id", -1);
        if (id != -1) {
            data.putExtra("Id", id);

        }
        setResult(RESULT_OK, data);
        finish();
    }
}
