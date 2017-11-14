package com.example.a1521093.ap2_android;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kategori extends  AppCompatActivity implements AdapterView.OnItemClickListener{
    ApiService ApiService;
    TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
    ListView mListView;

    private int[] sub_category_id = new int[100];
    private  String[] sub_category_name = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        //ArrayAdapterオブジェクト生成
        adapter=new ArrayAdapter<Product>(kategori.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext(),R.layout.kategori_sub);
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        title.setText(Product.main_category_name);

        getData();

        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
        mListView.setOnItemClickListener(this);
        }

    public void homeButton(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void modoru_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void accountButton(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        Product.sub_category_id  = sub_category_id[position];
        Product.sub_category_name = sub_category_name[position];
        Intent intent = new Intent(this, maker.class);
        startActivity(intent);
    }

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();
                                                //クエリを投げる
        Call<List<Product>> call = ApiService.items("sub_categories.json?main_category_id="+Product.main_category_id);
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override                           //取得成功
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    Log.d("MainActivity", "call onResponse");
                    aProductList.addAll(response.body());
                    Log.d("MainActivity", aProductList.toString());
                    updateContainer(aProductList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.d("MainActivity", "call onFailure");
                    Log.d("MainActivity", t.getMessage());
                    Log.d("MainActivity", aProductList.toString());
                    updateContainer(aProductList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
                            //ListViewに値入れるクラス
    private void updateContainer(ArrayList<Product> aProductList) {

        int count=0;
        for (Product product : aProductList) {
            Log.d("kategori","サブカテゴリ"+ product.name+"カウント="+ product.id);
                    //遷移時に投げる用のテキスト取得と格納
            sub_category_name[count] = product.name;
            sub_category_id[count] = product.id;
            adapter.add(product);
            count++;
        }
                            //指定のListViewに格納
        topListAdapter.setDatas(aProductList,1);
        topListAdapter.notifyDataSetChanged();
    }
}

