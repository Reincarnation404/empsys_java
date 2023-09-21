package com.example.sqlitepractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlitepractice.model.Emp;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    // 資料庫名稱
    public static final String DATABASE_NAME = "emp";
    // 資料庫版本，資料結構改變的時候要更改這個數字，通常是加一
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "empList";
    //欄位
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EXNUM = "exnum";
    public static final String DEPT = "dept";
    public static final String JOBTITLE = "jobTitle";

    public static final String create_table=
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT NOT NULL, " +
                    EXNUM + " TEXT NOT NULL, " +
                    DEPT + " TEXT NOT NULL, " +
                    JOBTITLE + " TEXT NOT NULL);";
    public static final String drop_table= "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(drop_table);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Emp> getAllData(){
        ArrayList<Emp> emplist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String sql="SELECT * FROM "+TABLE_NAME+";";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Emp emp = new Emp();
            emp.setId(cursor.getInt(0));
            emp.setName(cursor.getString(1));
            emp.setExNum(cursor.getString(2));
            emp.setDept(cursor.getString(3));
            emp.setJobTitle(cursor.getString(4));
            emplist.add(emp);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return emplist;
    }

//    public void insertData(Emp e){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(NAME, e.getName());
//        cv.put(EXNUM, e.getExNum());
//        cv.put(DEPT, e.getDept());
//        cv.put(JOBTITLE, e.getJobTitle());
//        db.insert(TABLE_NAME, null, cv);
//        db.close();
//    }

    public void deleteByIdEZ(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id = " + id,null);
    }

}
