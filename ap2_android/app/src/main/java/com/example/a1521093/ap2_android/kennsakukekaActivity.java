package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class kennsakukekaActivity extends AppCompatActivity {
    //データベースlati
    double store_lat=(35.605802);
    //データベースlong
    double store_lon=(139.735325);
    double user_lat;
    double user_lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);

        Intent intent = getIntent();
         user_lon=intent.getDoubleExtra("user_lon",0);
         user_lat = intent.getDoubleExtra("user_lat",0);
        double distance = getDistance(user_lat, user_lon, store_lat, store_lon );
        int kyori_A = (int)distance;
        String kyori_text =(""+kyori_A);
        final String data = intent.getStringExtra("syohin");

        LinearLayout layout = (LinearLayout) findViewById(R.id.loop);
        for(int i=0;i<6;i++) {
            View view = getLayoutInflater().inflate(R.layout.kekka_sub, null);
            layout.addView(view);
            TextView text = (TextView) view.findViewById(R.id.kyori);
            text.setText(kyori_text);
            TextView dai = (TextView)view.findViewById(R.id.syouhinmei);
            dai.setText(data);

            //mapへ移動
            Button map=(Button)findViewById(R.id.susumu);
            map.setId(i);
            map.setOnClickListener(new View.OnClickListener(){
                @Override
                public  void onClick(View v){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                    intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+user_lat+","+user_lon+"&daddr="+store_lat+","+store_lon));
                    startActivity(intent);
                }
            });
        }
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
}

