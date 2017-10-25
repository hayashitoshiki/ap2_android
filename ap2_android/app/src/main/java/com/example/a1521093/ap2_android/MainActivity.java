package com.example.a1521093.ap2_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabWidget;
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
                intent.putExtra("main_category_name",dai);
                int id=2;
                intent.putExtra("main_category_id",id);
                startActivity(intent);
            }
        });

        Button sendButton=(Button)findViewById(R.id.kensakukekka);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),KensakuRoot.class);
                EditText  SendValue = (EditText)findViewById(R.id.edittext);
                String syohin = SendValue.getText().toString();
                intent.putExtra("kensaku",syohin);
                startActivity(intent);
            }
        });

        Button ken=(Button)findViewById(R.id.kaden);
        ken.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                dai="カテゴリ：家電製品";
                intent.putExtra("main_category_name",dai);
                int id=1;
                intent.putExtra("main_category_id",id);
                startActivity(intent);
            }
        });
                 //↓必要なし
       // ImageButton homes = (ImageButton) findViewById(R.id.homebutton);
       // homes.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
        //    @Override
         //   public void onClick(View v) {
          //      // ここに処理を記述する
           //     Intent intent=new Intent(getApplication(),MainActivity.class);
            //    intent.putExtra("dai",dai);
             //   startActivity(intent);
           // }
       // });
                             //今はなし
        //ImageButton points = (ImageButton) findViewById(R.id.coupon_button);
        //points.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
          //  @Override
           // public void onClick(View v) {
                // ここに処理を記述する
           //     Intent intent=new Intent(getApplication(),maker.class);
           //     intent.putExtra("dai",dai);
           //     startActivity(intent);
           // }
       // });

      //  ImageButton acounts = (ImageButton) findViewById(R.id.account_button);
       // acounts.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
         //    @Override
         //   public void onClick(View v) {
                // ここに処理を記述する
         //       Intent intent=new Intent(getApplication(),kategori.class);
         //       intent.putExtra("dai",dai);
         //       startActivity(intent);
         //   }
         // });

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
