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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button SendEditTextValue;
    private final int REQUEST_PERMISSION = 1000;
    String dai;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= 23){
            checkPermission();
        }
        SendEditTextValue = (Button)findViewById(R.id.kensaku);

        Button kesyo=(Button)findViewById(R.id.kesyouhin);
        kesyo.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                dai="カテゴリ：化粧品";
                intent.putExtra("dai",dai);
                intent.putExtra("kategori",dai);
                startActivity(intent);

            }
        });

        Button kensakuke =(Button)findViewById(R.id.kensakukekka);
        kensakuke.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),GPS.class);
                EditText  SendValue = (EditText)findViewById(R.id.edittext);
                String syohin = SendValue.getText().toString();
                intent.putExtra("syohin",syohin);
                startActivity(intent);

            }
        });

        Button ken=(Button)findViewById(R.id.kaden);
        ken.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                dai="カテゴリ：家電製品";
                intent.putExtra("dai",dai);
                intent.putExtra("kategori",dai);
                startActivity(intent);

            }
        });

        Button kenf=(Button)findViewById(R.id.kesyohinmaker);
        kenf.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),maker.class);
                dai="カテゴリ：化粧品メイカー";
                intent.putExtra("dai",dai);
                intent.putExtra("maker",dai);
                startActivity(intent);

            }
        });

        Button kadenmaker=(Button)findViewById(R.id.kadenmaker);
        kadenmaker.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),maker.class);
                dai="カテゴリ：家電メイカー";
                intent.putExtra("dai",dai);
                intent.putExtra("maker",dai);
                startActivity(intent);

            }
        });

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
   /* public void kensaku_onClick(View v) {
        intent = new Intent(getApplicationContext(), GPS.class);
        intent.putExtra("EdiTtEXTvALUE", SendValue.getText().toString());
        startActivity(intent);

    }

    public void keshouhin_onClick(View v) {
        Intent i = new Intent(this, kategori.class);
        startActivity(i);
    }

    public void kaden_onClick(View v) {
        Intent i = new Intent(this, kategori.class);
        startActivity(i);
    }
*/


}
