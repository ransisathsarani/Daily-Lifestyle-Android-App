package com.example.sularijayasooriya.mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null,  1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(id text primary key ,email text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }
    //inserting in database
    public boolean insert(String email, String password){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user","null",contentValues);
        if(ins==1) return false;
        else return true;
    }

    //checking if email exists;
    public Boolean chkemail (String email){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if(cursor.getCount()>0) return false;
        else return true;

    }
    //checking the email and password;
    public Boolean emailpassword(String email , String password){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{email,password});
    if(cursor.getCount()>0)return true;
    else return false;
    }

    //retrive userdetails
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from user",null);
        return res;
    }
    //update userdetails
    public boolean updateData(String id, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id,id);
        contentValues.put(email,email);
        contentValues.put(password,password);
        db.update("user", contentValues, "ID = ?",new String[] { id });
        return true;
    }

    //delete user
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user", "ID = ? ",new String[] {id});
    }



}