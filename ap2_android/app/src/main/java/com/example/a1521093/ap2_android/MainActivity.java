package com.example.a1521093.ap2_android;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

        EditText editText1 = (EditText) findViewById(R.id.edittext);
        editText1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.d("onEditorAction", "actionId = " + actionId + " event = " + (event == null ? "null" : event));

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.d("onEditorAction", "check");
                    Intent intent = new Intent(getApplication(),KensakuRoot.class);
                    EditText  SendValue = (EditText)findViewById(R.id.edittext);
                    String syohin = SendValue.getText().toString();
                   Product.product_name2 = syohin;
                    startActivity(intent);
                }
                return true;
            }
        });

        ImageButton kesyo=(ImageButton)findViewById(R.id.kesyouhin);
        kesyo.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                dai="カテゴリ：化粧品";
                int id=2;
                Product.main_category_id  = id;
                Product.main_category_name = dai;
                startActivity(intent);
            }
        });

        ImageButton ken=(ImageButton)findViewById(R.id.kaden);
        ken.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),kategori.class);
                dai="カテゴリ：家電製品";
                int id=1;
                Product.main_category_id  = id;
                Product.main_category_name = dai;
                startActivity(intent);
            }
        });

        ImageButton acounts = (ImageButton) findViewById(R.id.account_button);
        acounts.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
            @Override
            public void onClick(View v) {
                // ここに処理を記述する
                Intent intent=new Intent(getApplication(),Account.class);
                intent.putExtra("dai",dai);
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
}