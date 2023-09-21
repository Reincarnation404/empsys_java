package com.example.sqlitepractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sqlitepractice.databinding.ActivityDataAddBinding;

import java.util.Objects;
import java.util.regex.Pattern;

public class DataAddActivity extends AppCompatActivity {
    ActivityDataAddBinding binding;

    SQLiteDatabase db;
    SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // dbHelper = new SQLiteHelper(this);

        binding.tilDataAddDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDept();
            }
        });

        binding.tilDataAddJobTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showJobTitle();
            }
        });

        binding.btDataAddCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.btDataAddSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO: 2023/9/21 職位跟部門選完旁邊還是有error 
                if (!inputValid()){
                    Toast.makeText(DataAddActivity.this, "請確認資料", Toast.LENGTH_SHORT).show();
                }else {
                    addData();
                    Toast.makeText(DataAddActivity.this, "資料新增成功", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DataAddActivity.this,MainActivity.class);
                    startActivity(i);
                }


            }
        });



    }


    private AlertDialog alertDialog;
    private void showDept(){
        final String[] choice={"財務部","技術部","人力資源部"};
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("選擇部門").setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String dept = choice[i];
                binding.tietDataAddDept.setText(dept);
            }
        });
        ab.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        ab.setCancelable(true);
        alertDialog = ab.create();
        alertDialog.show();
    }

    private void showJobTitle(){
        final String[] choice={"部長","經理","員工"};
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("選擇職位").setSingleChoiceItems(choice, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String jobTitle = choice[i];
                binding.tietDataAddJobTitle.setText(jobTitle);
            }
        });
        ab.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        ab.setCancelable(true);
        alertDialog = ab.create();
        alertDialog.show();
    }

    private boolean inputValid(){
        boolean valid = true;
        String name = Objects.requireNonNull(binding.tietDataAddName.getText()).toString().trim();
        String exnum = Objects.requireNonNull(binding.tietDataAddExNumber.getText()).toString().trim();
        String dept = binding.tietDataAddDept.getText().toString();
        String jobTitle = binding.tietDataAddJobTitle.getText().toString();
        String regex = "^\\d{4}$";

        if (name.isEmpty()){
            binding.tietDataAddName.setError("請輸入姓名");
            valid = false;
        }

        if (exnum.isEmpty()){
            binding.tietDataAddExNumber.setError("請輸入分機號碼");
            valid = false;
        }else if (!Pattern.matches(regex,exnum)){
            binding.tietDataAddExNumber.setError("分機號碼為4碼");
            valid = false;
        }

        if(dept.isEmpty()){
            binding.tietDataAddDept.setError("請選擇部門");
            valid = false;
        }

        if (jobTitle.isEmpty()){
            binding.tietDataAddJobTitle.setError("請選擇職位");
            valid = false;
        }

        return valid;
    }

    private void addData(){
        dbHelper = new SQLiteHelper(this);
        String name = Objects.requireNonNull(binding.tietDataAddName.getText()).toString().trim();
        String exnum = Objects.requireNonNull(binding.tietDataAddExNumber.getText()).toString().trim();
        String dept = binding.tietDataAddDept.getText().toString().trim();
        String jobTitle = binding.tietDataAddJobTitle.getText().toString().trim();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("exnum", exnum);
        cv.put("dept", dept);
        cv.put("jobTitle", jobTitle);
        long id = dbHelper.getWritableDatabase().insert("empList",null,cv);
        System.out.println("新增資料id="+id);
    }
}