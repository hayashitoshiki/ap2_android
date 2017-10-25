package com.example.a1521093.ap2_android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import com.example.a1521093.ap2_android.kennsakukekaActivity;
//独自のListViewのレイアウト生成クラス

public class TopListView extends LinearLayout {
    private ImageView imageView;
    private TextView textViewShopName;
    private TextView textViewStoreName;
    private TextView textViewaddress;
    private TextView textViewdistance;
    private TextView textViewstock;

    private kennsakukekaActivity kekka;
    URL url;
    InputStream istream;
    private int count ;

    public TopListView(Context context,int i) {
        this(context, null,i);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs,int i) {
        this(context, attrs, 0, i);
    }
            //View生成
    public TopListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int i) {
        super(context, attrs, defStyleAttr);
        if(i==1){
            LayoutInflater.from(context).inflate(R.layout.list_top_adapter, this);
        }else{
            LayoutInflater.from(context).inflate(R.layout.kekka_sub, this);

        }
        textViewShopName = (TextView) findViewById(R.id.shopNameText);
         textViewStoreName = (TextView) findViewById(R.id.syouhinmei);
        textViewaddress = (TextView) findViewById(R.id.zyusyo);
        textViewdistance = (TextView) findViewById(R.id.kyori);
        textViewstock = (TextView) findViewById(R.id.kosuu);
      //  imageView = (ImageView) findViewById(R.id.imageViewShop);
        count=0;
    }

            //Viewに値セット
    public void setProduct(final Product product) {
        textViewShopName.setText(product.getname());
        // textViewCompanyName.setText("aa");
         //textViewCategory.setText(product.getAddress());
        // textViewUpdate_at.setText(qiitaItem.getUpdated_at());



    }

    public void setProduct2(final Product product) {
        textViewStoreName.setText(product.getname());
         textViewaddress.setText(product.getAddress());



        kennsakukekaActivity keka= new kennsakukekaActivity();
        double user_lon  = keka.getuser_lon();
        double user_lati = keka.getuser_lati();
        double store_lati = product.getlatitude();
        double store_lon = product.getlongitude();
        double distance = getDistance(user_lati, user_lon, store_lati, store_lon);
        int kyori_A = (int)distance;
        if(kyori_A>10000) {
            kyori_A = kyori_A / 1000;
            textViewdistance.setText(kyori_A + "km");
        }else {
            textViewdistance.setText(kyori_A + "m");
        }
        int stock = keka.getstock();
        textViewstock.setText(stock+"個");
        count++;
    }
    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +  Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        double miles = dist * 60 * 1.1515;
        return (miles * 1.609344*1000);
    }

    private double rad2deg(double radian) {
        return radian * (180f / Math.PI);
    }

    public double deg2rad(double degrees) {
        return degrees * (Math.PI / 180f);
    }
}

