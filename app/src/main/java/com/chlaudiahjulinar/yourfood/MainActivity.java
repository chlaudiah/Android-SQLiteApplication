package com.chlaudiahjulinar.yourfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnGetFood, btnListFood, btnEditData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listfood(View view) {
        Intent listFood = new Intent(MainActivity.this, ListFood.class);
        startActivity(listFood);
        finish();
    }
}
