package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Favorite extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);
    }

    public void Logout(View v){
        Intent intent=new Intent(getApplication(),AccountLogin.class);
        startActivity(intent);
    }

    public void home_button(View v){
        Intent intent = new Intent(getApplication(),MainActivity.class);
        startActivity(intent);
    }

    public void account(View v){
        Intent intent = new Intent(getApplication(),Account.class);
        startActivity(intent);
    }
}
