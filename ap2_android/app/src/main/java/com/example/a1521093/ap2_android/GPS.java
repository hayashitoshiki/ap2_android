package com.example.a1521093.ap2_android;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GPS extends Activity implements LocationListener {

    private LocationManager locationManager;
    private TextView textView;
    private String text = "start\n";
    private Button buttonStart, buttonStop;

    private static final int MinTime = 1000;
    private static final float MinDistance = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // LocationManager インスタンス生成
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
 // GPS測位開始
        startGPS();

    }

    protected void startGPS() {

        Log.d("LocationActivity", "gpsEnabled");
        final boolean gpsEnabled
                = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            // GPSを設定するように促す
            enableLocationSettings();
        }

        if (locationManager != null) {
            Log.d("LocationActivity", "locationManager.requestLocationUpdates");
            // バックグラウンドから戻ってしまうと例外が発生する場合がある
            try {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(this,
                                Manifest.permission.ACCESS_COARSE_LOCATION)!=
                                PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MinTime, MinDistance, this);
            } catch (Exception e) {
                e.printStackTrace();

                Toast toast = Toast.makeText(this,
                        "例外が発生、位置情報のPermissionを許可していますか？", Toast.LENGTH_SHORT);
                toast.show();

                //MainActivityに戻す
                finish();
            }
        } else {
        }

        super.onResume();
    }

    @Override
    protected void onPause() {

        if (locationManager != null) {
            Log.d("LocationActivity", "locationManager.removeUpdates");
            // update を止める
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(this);
        } else {

        }

        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        double user_latitude = location.getLatitude();
        double user_longitude =location.getLongitude();
        Intent intent=new Intent(getApplication(),kennsakukekaActivity.class);
        Intent get = getIntent();

        String main_category_name = get.getStringExtra("main_category_name");
        int main_category_id = get.getIntExtra("main_category_id",0);
        String maker_name = get.getStringExtra("maker_name");
        int maker_id = get.getIntExtra("maker_id",0);
        String sub_category_name = get.getStringExtra("sub_category_name");
        int sub_category_id = get.getIntExtra("sub_category_id",0);
        String product_name = get.getStringExtra("product_name");
        int product_id = get.getIntExtra("product_id",0);
        int bunki = get.getIntExtra("switch",0);
        Log.d("GPS", "main"+main_category_name+":"+main_category_id+"sub"+sub_category_name+":"+sub_category_id+"maker"+maker_name+":"+maker_id);

        intent.putExtra("main_category_name",main_category_name);
        intent.putExtra("main_category_id",main_category_id );
        intent.putExtra("maker_name",maker_name);
        intent.putExtra("maker_id",maker_id );
        intent.putExtra("sub_category_name",sub_category_name);
        intent.putExtra("sub_category_id",sub_category_id );
        intent.putExtra("product_name",product_name);
        intent.putExtra("product_id",product_id);
        intent.putExtra("switch",bunki);
        intent.putExtra("user_lat",user_latitude);
        intent.putExtra("user_lon",user_longitude);

        startActivity(intent);
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                break;
            case LocationProvider.OUT_OF_SERVICE:
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                break;
        }
    }

    private void enableLocationSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

    private void stopGPS(){
        if (locationManager != null) {
            Log.d("LocationActivity", "onStop()");
            // update を止める
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(this);
        } else {

        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}
