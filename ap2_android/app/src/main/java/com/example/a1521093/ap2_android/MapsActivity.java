package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Marker;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker miti;
    double store_latitude ;
    double store_longitude;
    double user_latitude;
    double user_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button sendButton=(Button)findViewById(R.id.modoru);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kennsakukekaActivity.class);
                Intent modosi = getIntent();
                double latitude1 = modosi.getDoubleExtra("user_lat",0);
                intent.putExtra("user_lat",latitude1);
                double longitude1= modosi.getDoubleExtra("user_lon",0);
                intent.putExtra("user_lon",longitude1);
                String syohinmei = modosi.getStringExtra("syohin");
                intent.putExtra("syohin",syohinmei);
                startActivity(intent);

            }
        });

        Button map=(Button)findViewById(R.id.go_map);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
                intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+user_latitude+","+user_longitude+"&daddr="+store_latitude+","+store_longitude));
                startActivity(intent);


            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //位置情報
        Intent in = getIntent();
        store_latitude = in.getDoubleExtra("store_lat",0);
        store_longitude= in.getDoubleExtra("store_lon",0);
         user_latitude = in.getDoubleExtra("user_lat",0);
         user_longitude= in.getDoubleExtra("user_lon",0);

        LatLng store_iti = new LatLng(store_latitude, store_longitude);       //検索結果から店舗名取得　store_name
        mMap.addMarker(new MarkerOptions().position(store_iti).title("ヤマダ電機 LABI品川大井町店"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(store_iti));

        LatLng user_iti = new LatLng(user_latitude, user_longitude);
        miti = mMap.addMarker(new MarkerOptions().position(user_iti).title("現在地"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(user_iti));
        miti.setTag(0);

        //   中心の設定
        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom( new LatLng(user_latitude, user_longitude), 14);
        mMap.moveCamera(cUpdate);

       /* Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName("com.google.android.apps.maps","com.google.android.maps.MapsActivity");
        intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+user_latitude+","+user_longitude+"&daddr="+store_latitude+","+store_longitude));
        startActivity(intent);
*/
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
