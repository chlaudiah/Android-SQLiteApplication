package com.chlaudiahjulinar.yourfood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.widget.Toast;

public class ViewDetail extends AppCompatActivity {

    DataHelper dbHelper;
    Cursor cursor;
    EditText kode;
    EditText nama;
    EditText pesanan;
    EditText jumlah;
    Button btnDeleteFood;
    int kode_pemesanan;
    String kode_pemesanan_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);


        dbHelper = new DataHelper(this);
        Intent intent = getIntent();
        kode_pemesanan_string = intent.getStringExtra("kode_pemesanan").toString();
        kode_pemesanan = Integer.parseInt(kode_pemesanan_string);

        kode = (EditText) findViewById(R.id.kodeDetailEdit);
        nama = (EditText) findViewById(R.id.namaDetailEdit);
        pesanan = (EditText) findViewById(R.id.pesananDetailEdit);
        jumlah = (EditText) findViewById(R.id.jumlahDetailEdit);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM yourfood WHERE kode_pemesanan ='" + kode_pemesanan + "'", null);
        if (cursor.moveToFirst()){
            do {
                kode.setText(cursor.getString(0));
                nama.setText(cursor.getString(2));
                pesanan.setText(cursor.getString(3));
                jumlah.setText(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        btnDeleteFood = (Button) findViewById(R.id.btnDeleteFood);

        btnDeleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String query = ("DELETE FROM yourfood WHERE kode_pemesanan ='"+kode_pemesanan+"'");
                db.execSQL(query);
                ListFood.da.RefreshList();
                Toast.makeText(getApplicationContext(), "Data berhasil dihapus...",Toast.LENGTH_LONG).show();
                Intent listfood = new Intent(ViewDetail.this, ListFood.class);
                startActivity(listfood);
                finish();
            }
        });
    }
}
