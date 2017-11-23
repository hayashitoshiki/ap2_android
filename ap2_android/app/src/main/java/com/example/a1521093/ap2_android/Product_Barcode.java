package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class Product_Barcode extends AppCompatActivity {
    public int bunki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_barcode);
        Intent intent = getIntent();
        bunki = intent.getIntExtra("switch",0);

        TextView title = (TextView)findViewById(R.id.product_name);
        ImageView image = (ImageView)findViewById(R.id.product_image);

        Picasso.with(this).load(Product.product_image).into(image);
        title.setText(Product.product_name);
    }

    public void code(View v){
        Intent i =  new Intent(getApplication(),MainActivity.class);
        startActivity(i);
    }

    public void home_button(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void account_button(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }

    public void modoru_onClick(View v) {
        Intent intent=new Intent(getApplication(),kennsakukekaActivity.class);
        intent.putExtra("switch",bunki);
        startActivity(intent);
    }
}
