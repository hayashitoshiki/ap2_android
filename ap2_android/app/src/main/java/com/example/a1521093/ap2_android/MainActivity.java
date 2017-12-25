package com.example.a1521093.ap2_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= 23){
            checkPermission();
        }
    }

    public void kensaku_button(View v){
        Intent intent = new Intent(getApplication(),KensakuRoot.class);
        EditText  SendValue = (EditText)findViewById(R.id.edittext);
        String syohin = SendValue.getText().toString();
        Product.product_name2 = syohin;
        startActivity(intent);
    }

    public void favorite_Button(View v){
        Intent i = new Intent(this,Favorite.class);
        startActivity(i);
    }

    public void account_Button(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }

    // 位置情報許可の確認
    public void checkPermission() {
        // 既に許可している
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
        }
        // 拒否していた場合
        else{
            requestLocationPermission();
        }
    }

    // 許可を求める
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
        } else {
            Toast toast = Toast.makeText(this, "許可されないとアプリが実行できません", Toast.LENGTH_SHORT);
            toast.show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, REQUEST_PERMISSION);
        }
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}