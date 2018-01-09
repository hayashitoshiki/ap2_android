package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class Favorite extends AppCompatActivity {
    String dai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite);

        ImageButton acounts2 = (ImageButton) findViewById(R.id.account_button2);
        acounts2.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
            @Override
            public void onClick(View v) {
                // ここに処理を記述する
                Intent intent=new Intent(getApplication(),Account.class);
                intent.putExtra("dai",dai);
                startActivity(intent);
            }
        });

        ImageButton home2 = (ImageButton) findViewById(R.id.homebutton2);
        home2.setOnClickListener(new View.OnClickListener() {
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