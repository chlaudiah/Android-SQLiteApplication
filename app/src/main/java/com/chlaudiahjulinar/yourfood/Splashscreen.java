package com.chlaudiahjulinar.yourfood;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splashscreen extends AppCompatActivity {
    private int waktu = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent dashboard = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(dashboard);
                finish();
            }
        }, waktu);
    }
}
