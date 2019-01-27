package com.chlaudiahjulinar.yourfood;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class GetFood extends AppCompatActivity {

    private EditText nomorhp, nama, pesanan, jumlah_pesanan, alamat;
    private Button btnGetFood;
    public DataHelper dbHelper;
    String edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_food);

        nomorhp = (EditText) findViewById(R.id.nomorGetFoodEdit);
        nama = (EditText) findViewById(R.id.namaGetFoodEdit);
        pesanan = (EditText) findViewById(R.id.pesananGetFoodEdit);
        jumlah_pesanan = (EditText) findViewById(R.id.jumlahGetFoodEdit);
        alamat = (EditText) findViewById(R.id.alamatGetFoodEdit);

        btnGetFood = (Button) findViewById(R.id.btnGetFood);
        dbHelper = new DataHelper(this);

        btnGetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                edit = nomorhp.getText().toString();
                edit = nama.getText().toString();
                edit = pesanan.getText().toString();
                edit = jumlah_pesanan.getText().toString();
                edit = alamat.getText().toString();

                if(edit.isEmpty()){

                } else{
                    String query = "INSERT INTO yourfood (nomorhp, nama, pesanan, jumlah_pesanan, alamat) VALUES ('" +
                            nomorhp.getText().toString() + "','" +
                            nama.getText().toString() + "','" +
                            pesanan.getText().toString() + "','" +
                            jumlah_pesanan.getText().toString() + "','" +
                            alamat.getText().toString() + "')";
                    db.execSQL(query);
                    Toast.makeText(getApplicationContext(), "Data berhasil disimpan...",Toast.LENGTH_LONG).show();
                }
                ListFood.da.RefreshList();

                Intent listfood = new Intent(GetFood.this, ListFood.class);
                startActivity(listfood);
            }
        });
    }
}
