package com.example.a1521093.ap2_android;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void kensaku_button(View v){
        Intent intent = new Intent(getApplication(),KensakuRoot.class);
        EditText  SendValue = (EditText)findViewById(R.id.edittext);
        String syohin = SendValue.getText().toString();
        Product.product_name2 = syohin;
        startActivity(intent);
    }

    public void favorite_Button(View v){
        Intent i = new Intent(this,Favorite.class);
        startActivity(i);
    }

    public void account_Button(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }


}