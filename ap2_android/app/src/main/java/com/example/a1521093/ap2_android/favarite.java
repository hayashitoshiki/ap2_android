package com.example.a1521093.ap2_android;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import static com.example.a1521093.ap2_android.R.id.listView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
/**
 * Created by 1521077 on 2018/01/05.
 */

public class favarite  extends AppCompatActivity {
    ArrayAdapter<Product> adapter;
    ListView mListView;
    ApiService ApiService;
    TopListAdapter topListAdapter;
    String dai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favarite);

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

        ImageButton home = (ImageButton) findViewById(R.id.homebutton);
        home.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
            @Override
            public void onClick(View v) {
                // ここに処理を記述する
                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("dai",dai);
                startActivity(intent);
            }
        });


    }

}
