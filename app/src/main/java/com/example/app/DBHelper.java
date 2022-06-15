package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Practice2.db";
    private static final String TABLE_NAME = "practice_library";


    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_PUSHUP = "pushup";
    private static final String COLUMN_CRUNCH = "crunch";
    private static final String COLUMN_SQUART = "squart";




    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE if not EXISTS " + TABLE_NAME + "(date TEXT PRIMARY KEY, pushup TEXT, crunch TEXT, squart TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE if exists " + TABLE_NAME;

        db.execSQL(sql);
        onCreate(db);
    }

    public void insertData2(String date, String a, String b, String c) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "insert or replace into practice_library (date, pushup, crunch, squart) values ('" + date + "', '" + a + "', '" + b + "', '" + c + "');";

        db.execSQL(sql);
    }

    public Cursor getAllData(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where date = '" + date + "';", null);

        return res;
    }



}










//    public boolean insertData(String date, String a, String b, String c){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_DATE, date);
//        cv.put(COLUMN_PUSHUP, a);
//        cv.put(COLUMN_CRUNCH, b);
//        cv.put(COLUMN_SQUART, c);
//
//        long result = db.insert(TABLE_NAME, null, cv);
//
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }
//
//
//    public boolean updateData(String date, String a, String b, String c){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//        cv.put(COLUMN_DATE, date);
//        cv.put(COLUMN_PUSHUP, a);
//        cv.put(COLUMN_CRUNCH, b);
//        cv.put(COLUMN_SQUART, c);
//
//        db.update(TABLE_NAME, cv, "date = ?", new String[] {date});
//
//        return true;
//    }
