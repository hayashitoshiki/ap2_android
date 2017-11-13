package com.example.a1521093.ap2_android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyohinItiran extends  AppCompatActivity implements AdapterView.OnItemClickListener{

    ApiService ApiService;
    TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
    ListView mListView;
    private  String sub_category_name;
    private int sub_category_id;
    private String maker_name;
    private int maker_id;

    private int[] product_id = new int[100];
    private String[] product_name = new String[100];
    public static String[] product_image = new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        sub_category_name = Product.sub_category_name;
        sub_category_id = Product.sub_category_id;
        maker_name = Product.maker_name;
        maker_id = Product.maker_id;
        title.setText(maker_name+"の"+sub_category_name);
        //ArrayAdapterオブジェクト生成
        adapter=new ArrayAdapter<Product>(SyohinItiran.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext(),R.layout.kategori_sub);
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();
        getData();
        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
        mListView.setOnItemClickListener(this);
    }

    public void modoru_onClick(View v) {
        Intent intent=new Intent(getApplication(),maker.class);
        intent.putExtra("product_id",product_id);
        startActivity(intent);
    }

    public void homeButton(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void accountButton(View v){
        Intent i = new Intent(this, Account.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this, GPS.class);
        Product.product_id  = product_id[position];
        Product.product_name = product_name[position];
        Product.product_image = product_image[position];
        intent.putExtra("switch",1);
        startActivity(intent);
    }

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();
        Log.d("MainActivity", sub_category_name+"メーカーID="+ maker_id+"サブカテゴリID="+sub_category_id);
                                                                        //クエリを投げる
        Call<List<Product>> call = ApiService.items("products.json?sub_category_id="+sub_category_id+"&maker_id="+maker_id);
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
            Log.d("メーカー", product.name+",id"+product.id);
            //遷移時に投げる用のテキスト取得と格納
            product_name[count]=(product.name);
            product_id[count] = (product.id);
            product_image[count] = (product.image);
            adapter.add(product);
            count++;
        }
        //指定のListViewに格納
        topListAdapter.setDatas(aProductList,2);
        topListAdapter.notifyDataSetChanged();
    }
}