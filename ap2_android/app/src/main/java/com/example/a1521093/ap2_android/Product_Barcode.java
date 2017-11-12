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
    public  double user_lat;
    public  double user_lon;
    public int sub_category_id;
    public int product_id;
    public static int count;
    public int bunki;
    public String main_category_name;
    public int main_category_id;
    public String maker_name;
    public int maker_id;
    public String sub_category_name;
    public String product_name;
    public String product_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_barcode);
        Intent intent = getIntent();
        product_name = intent.getStringExtra("product_name");
        product_image = intent.getStringExtra("image");
        main_category_name = intent.getStringExtra("main_category_name");
        main_category_id = intent.getIntExtra("main_category_id",0);
        maker_name = intent.getStringExtra("maker_name");
        maker_id = intent.getIntExtra("maker_id",0);
        sub_category_name = intent.getStringExtra("sub_category_name");
        sub_category_id = intent.getIntExtra("sub_category_id",0);
        product_name = intent.getStringExtra("product_name");
        product_id = intent.getIntExtra("product_id",0);
        bunki = intent.getIntExtra("switch",0);
        user_lon=intent.getDoubleExtra("user_lon",0);
        user_lat = intent.getDoubleExtra("user_lat",0);

        TextView title = (TextView)findViewById(R.id.product_name);
        ImageView image = (ImageView)findViewById(R.id.product_image);

        Picasso.with(this).load(product_image).into(image);
        Log.d("Product_Barcode", "商品名："+product_name+"、画像:"+product_image);
        title.setText(product_name);

    }
    public void code(View v){
        Intent i =  new Intent(getApplication(),MainActivity.class);
        startActivity(i);
    }

    public void homeButton(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void accountButton(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }

    public void modoru_onClick(View v) {
        Intent intent=new Intent(getApplication(),kennsakukekaActivity.class);
        intent.putExtra("main_category_name",main_category_name);
        intent.putExtra("main_category_id",main_category_id );
        intent.putExtra("maker_name",maker_name);
        intent.putExtra("maker_id",maker_id );
        intent.putExtra("sub_category_name",sub_category_name);
        intent.putExtra("sub_category_id",sub_category_id );
        intent.putExtra("product_name",product_name);
        intent.putExtra("product_id",product_id);
        intent.putExtra("product_name",product_name);
        intent.putExtra("image",product_image);
        intent.putExtra("user_lon",user_lon);
        intent.putExtra("user_lat",user_lat);
        intent.putExtra("switch",bunki);
        startActivity(intent);
    }

    }
