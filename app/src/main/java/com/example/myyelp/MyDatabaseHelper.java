package com.example.myyelp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public  class MyDatabaseHelper extends SQLiteOpenHelper {
    private final String tableName= "Favorites";
    private final String DataBaseName= "sqLiteDatabase";

    public MyDatabaseHelper(Context context) {
        super(context, "sqLiteDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Favorites ( NAME TEXT PRIMARY KEY, RATING REAL," +
                " PRICE TEXT, CATEGORY TEXT, PHONE TEXT, ADDRESS TEXT, CITY TEXT, STATE TEXT, IMAGE TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Favorites");

    }
    public Boolean insertuserdata(String name, double rating, String price, String category, String phone, String address, String city, String state, String image_url ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("RATING", rating);
        contentValues.put("PRICE", price);
        contentValues.put("CATEGORY", category);
        contentValues.put("PHONE", phone);
        contentValues.put("ADDRESS", address);
        contentValues.put("CITY", city);
        contentValues.put("STATE", state);
        contentValues.put("IMAGE", image_url);
        long result = DB.insert(tableName, null, contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }
    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Favorites", null);
        return cursor;
    }
}
