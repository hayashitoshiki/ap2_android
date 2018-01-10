package com.example.a1521093.ap2_android;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import java.util.Map;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

import static com.example.a1521093.ap2_android.R.id.listView;
import static com.example.a1521093.ap2_android.R.id.listView2;
import static com.example.a1521093.ap2_android.R.id.list_item;
import static com.example.a1521093.ap2_android.R.id.sakujoButton2;

public class Favorite extends AppCompatActivity {
    String dai;
    ArrayList<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_sub);
        // ListView を取得
        final ListView listView = (ListView) findViewById(listView2);
        // SimpleAdapterに渡すArrayList作成
        createData();

        // リストビューに渡すアダプタを生成
        adapter = new SimpleAdapter(
                this,
                list,
                R.layout.favorite,
        new String[] { "title", "comment","image" },
        new int[] {R.id.title, R.id.comment, R.id.image });

        // アダプタをセット
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (view.getId()) {
                    case R.id.sakujoButton2:
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                }
            }
        });

        ImageButton acounts2 = (ImageButton) findViewById(R.id.account_button3);
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

        ImageButton favorites = (ImageButton) findViewById(R.id.coupon_button3);
        favorites.setOnClickListener(new View.OnClickListener() {
            /** ボタンをクリックした時に呼ばれる */
            @Override
            public void onClick(View v) {
                // ここに処理を記述する
                Intent intent=new Intent(getApplication(),Favorite.class);
                intent.putExtra("dai",dai);
                startActivity(intent);
            }
        });

        ImageButton home2 = (ImageButton) findViewById(R.id.homebutton3);
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
            final Map Item = new HashMap();
            Item.put("title", "title");
            Item.put("comment", "comment");
            Item.put("image", R.mipmap.ic_launcher);
            list.add(Item);
        }
    }

}