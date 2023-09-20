package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sqlitepractice.databinding.ActivityDataAddBinding;

public class DataAddActivity extends AppCompatActivity {
    ActivityDataAddBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}