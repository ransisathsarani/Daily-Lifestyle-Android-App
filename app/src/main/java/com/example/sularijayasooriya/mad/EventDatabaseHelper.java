package com.example.sularijayasooriya.mad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DML SANDATHARA on 9/19/2018.
 */

public class EventDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME ="event.db";
    public static final String TABLE_NAME = "event_data";
    public static final String COL1 = "id";
    public static final String COL2 = "title";
    public static final String COL3 = "date";
    public static final String COL4 = "time";
    public static final String COL5 = "location";


    public EventDatabaseHelper(Context context) {
        super(context,DATABASE_NAME , null, 1);




    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(id INTEGER  PRIMARY KEY AUTOINCREMENT, title TEXT, date TEXT, time TEXT, location TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String title,String date,String time,String location){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValue = new ContentValues();
        contentValue.put(COL2,title);
        contentValue.put(COL3,date);
        contentValue.put(COL4,time);
        contentValue.put(COL5,location);

        long result = db.insert(TABLE_NAME,null,contentValue);

        if(result == -1)
            return false;
        else
            return true;

    }

    public Cursor  getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME,null);

        return res;
    }


    public boolean updateData(String id,String title,String date,String time,String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1,id);
        contentValues.put(COL2,title);
        contentValues.put(COL3,date);
        contentValues.put(COL4,time);
        contentValues.put(COL5,location);

        db.update(TABLE_NAME,contentValues,"id=?",new String[] {id});
        return true;




    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"id = ?",new String[] {id});


    }
}
