package com.example.a1521093.ap2_android;


import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);

        Intent intent = getIntent();
        double user_lon=intent.getDoubleExtra("user_lon",0);
        double user_lat = intent.getDoubleExtra("user_lat",0);
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
            Button btn = (Button) findViewById(R.id.susumu);
            btn.setId(i);
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(getApplication(), MapsActivity.class);
                    Intent in = getIntent();
                    double user_latitude = in.getDoubleExtra("user_lat", 0);
                    i.putExtra("user_lat", user_latitude);
                    double user_longitude = in.getDoubleExtra("user_lon", 0);
                    i.putExtra("user_lon", user_longitude);
                    i.putExtra("store_lat", store_lat);
                    i.putExtra("store_lon", store_lon);
                    i.putExtra("syohin", data);
                    startActivity(i);
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

    public void susumu_onClick(View v) {
        Intent i = new Intent(this, MapsActivity.class);
        Intent in = getIntent();
        double user_latitude = in.getDoubleExtra("user_lat",0);
        i.putExtra("user_lat",user_latitude);
        double user_longitude=in.getDoubleExtra("user_lon",0);
        i.putExtra("user_lon",user_longitude);
        i.putExtra("store_lat",store_lat);
        i.putExtra("store_lon",store_lon);
        String syohin = in.getStringExtra("syohin");
        i.putExtra("syohin",syohin);
        startActivity(i);
    }

}

