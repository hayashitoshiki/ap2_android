package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Search_Button(View v){
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