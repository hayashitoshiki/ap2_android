package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 1521093 on 2017/11/02.
 */

public class Product_Barcode extends AppCompatActivity {

    String product_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_barcode);
        Intent intent = getIntent();
        TextView title = (TextView)findViewById(R.id.product_name);
        product_name = intent.getStringExtra("product_name");
        Log.d("kennsakukekaActivity", "商品名："+product_name);
        title.setText(product_name);

    }

    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void modoru_onClick(View v) {
        Intent intent=new Intent(getApplication(),kennsakukekaActivity.class);
          startActivity(intent);
    }

    }
