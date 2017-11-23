package com.example.a1521093.ap2_android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KensakuRoot extends  AppCompatActivity implements AdapterView.OnItemClickListener{
    ApiService ApiService;
    TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
    ListView mListView;
    private String title_name;
    public static int count;

    private int[] product_id = new int[100];
    private static String[] product_name = new String[100];
    public static String[] product_image = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        title_name = Product.product_name2;
        Log.d("検索ルート",title_name);
        title.setText(title_name);

        adapter=new ArrayAdapter<Product>(KensakuRoot.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext(),R.layout.kategori_sub);
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();
        getData();
        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
        mListView.setOnItemClickListener(this);

        Button sendButton=(Button)findViewById(R.id.modoru);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this, GPS.class);
        Product.product_id  = product_id[position];
        Product.product_name = product_name[position];
        Product.product_image = product_image[position];
        intent.putExtra("switch",2);
        startActivity(intent);
    }

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();
        Log.d("検索ルート", "持ってきたやつ="+title_name);
        //クエリを投げる
        Call<List<Product>> call = ApiService.items("products.json");
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override                           //取得成功
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    Log.d("MainActivity", "call onResponse");
                    aProductList.addAll(response.body());
                    updateContainer(aProductList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.d("MainActivity", "call onFailure");
                    updateContainer(aProductList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ListViewに値入れるクラス
    private void updateContainer(ArrayList<Product> aProductList) {
         count=0;
        for (Product product : aProductList) {
            String name = product.name;
            Log.d("比較","比較値１="+ name+",：比較値２="+title_name);
            //遷移時に投げる用のテキスト取得と格納
            if (name.contains(title_name)) {
                Log.d("結果","OK="+product.name);
                product_name[count] = (product.name);
                product_id[count] = (product.id);
                product_image[count] = (product.image);
                count++;
            }
        }
        //指定のListViewに格納
        topListAdapter.setData(aProductList,title_name,2);
        topListAdapter.notifyDataSetChanged();
    }
}
