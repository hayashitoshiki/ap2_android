package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import java.util.Map;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

public class Favorite extends AppCompatActivity {
    String dai;
    ArrayList<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_sub);

        // ListView を取得
        ListView listView = (ListView) findViewById(R.id.listView2);
        // SimpleAdapterに渡すArrayList作成
        createData();

        // リストビューに渡すアダプタを生成
        adapter = new SimpleAdapter(
                this,
                list,
                R.layout.favorite,
        new String[] { "title", "comment","img" },
        new int[] {R.id.title, R.id.comment, R.id.image });
        // アダプタをセット
        listView.setAdapter(adapter);

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
    private void createData() {
        for (int n = 0; n < 3; n++) {
            Map data = new HashMap();
            data.put("title", "title" + n);
            data.put("comment", "comment" + n);
            data.put("image", R.mipmap.ic_launcher);
            list.add(data);
        }
    }

}