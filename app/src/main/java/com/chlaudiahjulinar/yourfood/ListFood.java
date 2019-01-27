package com.chlaudiahjulinar.yourfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListFood extends AppCompatActivity {

    public static ListFood da;
    String [] listFood;
    ListView listYourFood;
    Button btnEatAgain;
    DataHelper dbCenter;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_food);

        btnEatAgain = (Button) findViewById(R.id.btnOrder);
        listYourFood = (ListView) findViewById(R.id.listYourFood);

        btnEatAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getfood = new Intent(ListFood.this, GetFood.class);
                startActivity(getfood);
            }
        });
        da = this;
        dbCenter = new DataHelper(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM yourfood", null);
        listFood = new String[cursor.getCount()];
        cursor.moveToFirst();
        for(int cursorcount=0; cursorcount < cursor.getCount(); cursorcount++){
            cursor.moveToPosition(cursorcount);
            listFood[cursorcount] = cursor.getString(0).toString();
        }
        listYourFood.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, listFood));
        listYourFood.setSelected(true);
        listYourFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = listFood[arg2];
                final CharSequence[] dialogItem = {"View Detail"};
                AlertDialog.Builder builder = new AlertDialog.Builder(ListFood.this);
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent viewDetail = new Intent(ListFood.this, ViewDetail.class);
                        viewDetail.putExtra("kode_pemesanan",selection);
                        startActivity(viewDetail);
                        finish();
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) listYourFood.getAdapter()).notifyDataSetInvalidated();
    }
}
