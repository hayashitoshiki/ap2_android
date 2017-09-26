package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

                          /*検索結果から緯度経度取得  */
        LatLng tokyo = new LatLng(35.605802, 139.735325);       //検索結果から店舗名取得　store_name
        mMap.addMarker(new MarkerOptions().position(tokyo).title("ヤマダ電機 LABI品川大井町店"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tokyo));

        //現在位置情報
        Intent in = getIntent();
        double latitude1 = in.getDoubleExtra("lat",0);
        double longitude1= in.getDoubleExtra("lon",0);
        //GPS取得　　　　　　　検索結果からGPS情報取得
        LatLng iti = new LatLng(latitude1, longitude1);
        miti = mMap.addMarker(new MarkerOptions().position(iti).title("現在地"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(iti));
        miti.setTag(0);

        //   中心の設定                                                //検索結果よりGPS情報の取得
        CameraUpdate cUpdate = CameraUpdateFactory.newLatLngZoom( new LatLng(latitude1, longitude1), 14);
        mMap.moveCamera(cUpdate);
    }
}
