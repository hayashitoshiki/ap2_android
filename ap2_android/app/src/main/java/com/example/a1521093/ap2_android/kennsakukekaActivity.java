package com.example.a1521093.ap2_android;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class kennsakukekaActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);
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

