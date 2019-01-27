package com.chlaudiahjulinar.yourfood;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "food.db";
    public static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table yourfood (kode_pemesanan integer primary key, nomorhp text not null, nama text not null, pesanan text not null, jumlah_pesanan text not null, alamat text not null);";
        Log.d("Data", "OnCreate" + query);
        db.execSQL(query);
        query = "INSERT INTO yourfood (kode_pemesanan, nomorhp, nama, pesanan, jumlah_pesanan, alamat) VALUES (001, '082198941048', 'Chlaudiah Julinar', 'Nasi Goreng Sapi', '2', 'Kos Amoris');";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
