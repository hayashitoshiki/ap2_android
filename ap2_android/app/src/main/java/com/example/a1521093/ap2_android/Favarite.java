package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Favarite extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favarite);
    }


    public void homebutton2(View v){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void account_button2(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }
}
