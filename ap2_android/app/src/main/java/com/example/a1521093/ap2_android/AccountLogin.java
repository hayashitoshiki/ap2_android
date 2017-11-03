package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 1521093 on 2017/11/03.
 */

public class AccountLogin extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void Login(View v){
        Intent intent=new Intent(getApplication(),MainActivity.class);
        startActivity(intent);
    }
}
