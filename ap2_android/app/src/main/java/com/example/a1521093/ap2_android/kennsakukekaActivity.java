package com.example.a1521093.ap2_android;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class kennsakukekaActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);
        TextView kyori = (TextView)findViewById(R.id.kyori);
        Intent intent = getIntent();
        String lon = intent.getStringExtra("lon");
        kyori.setText(lon);

        TextView dai = (TextView)findViewById(R.id.syouhinmei);
        Intent syohin = getIntent();
        String data = syohin.getStringExtra("syohin");
        dai.setText(data);
    }
    public void modoru_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void kensaku_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void susumu_onClick(View v) {
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }

}

