package com.example.a1521093.ap2_android;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;


public class GPS extends Service implements LocationListener {
    private ProgressDialog progressDialog;

    private static final int MinTime = 1000;
    private static final float MinDistance = 50;
        //ダイアログメッセージ表示
       // progressDialog = new ProgressDialog(this);
        //progressDialog.setIndeterminate(true);
        //progressDialog.setMessage("Loading...");
        //progressDialog.show();
    LocationManager locationManager;
    private Timer timer = null;
    private int count = 0;
    double user_latitude;
    double user_longitude;
    // Serviceに接続するためのBinderクラスを実装する
    public class LocalBinder extends Binder {
        //Serviceの取得
        GPS getService() {
            return GPS.this;
        }
    }

    // Binderの生成
    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // Service接続時に呼び出される
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("debug", "location manager Enabled");
        } else {
            // GPSを設定するように促す
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
            Log.d("debug", "not gpsEnable, startActivity");
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            Log.d("debug", "checkSelfPermission false");
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                500, 50, this);
        Log.d("location","位置情報取得開始");

        // 戻り値として、Serviceクラスとのbinderを返す。
        Log.d("TestService", "onBind" + ": " + intent);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("TestService", "count = " + count);
                Log.d("Latitude", "緯度" + user_latitude);
                Log.d("Longitude", "経度" + user_longitude);
                count++;
            }
        }, 0, 1000);
        return mBinder;
    }

    @Override
    public void onRebind(Intent intent){
        // Unbind後に再接続する場合に呼ばれる
        Log.d("TestService", "onRebind" + ": " + intent);
    }

    @Override
    public boolean onUnbind(Intent intent){
        // Service切断時に呼び出される
        //onUnbindをreturn trueでoverrideすると次回バインド時にonRebildが呼ばれる
        Log.d("TestService", "onUnbind" + ": " + intent);
        if( timer != null ){
            timer.cancel();
            timer = null;
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
        user_latitude = location.getLatitude();
       user_longitude =location.getLongitude();
        User.user_latitude = user_latitude;
        User.user_longitude = user_longitude;
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}
