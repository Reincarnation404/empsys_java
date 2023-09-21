package com.example.sqlitepractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.sqlitepractice.adapter.DataAdapter;
import com.example.sqlitepractice.databinding.ActivityMainBinding;
import com.example.sqlitepractice.model.Emp;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    SQLiteHelper dbHelper;

    DataAdapter adapter;

    private String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new SQLiteHelper(this);

        dbHelper.getAllData();

        binding.rvEmpData.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DataAdapter(dbHelper.getAllData(),this);

        binding.rvEmpData.setAdapter(adapter);


        binding.fabDataAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DataAddActivity.class);
                startActivity(intent);
            }
        });

        binding.spinnerDept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedItem = adapterView.getItemAtPosition(i).toString();
                    setChange(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void setChange(String selectedCategory) {
        ArrayList<Emp> newList = new ArrayList<>();

        if (!selectedCategory.matches("全部")) {
            for (Emp info : dbHelper.getAllData()) {
                String dept = Emp.getDept();
                if (dept.contains(selectedCategory)) {
                    newList.add(info);
                }
            }
            adapter.setFilter(newList);
        }else {
            for (Emp info : dbHelper.getAllData()) {
                newList.add(info);
            }

            adapter.setFilter(newList);
        }
    }
}