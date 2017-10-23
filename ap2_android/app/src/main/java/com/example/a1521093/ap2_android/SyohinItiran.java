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

    private ApiService ApiService;
    private TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
    private Product product;
    ListView mListView;
    String sub_category_name;
    int sub_category_id;
    int maker_id;
    String main_category_name;

    protected int[] product_id = new int[100];
    protected String[] scenes=new String[100];


    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syohinnitiran);

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        Intent intent = getIntent();
        main_category_name = intent.getStringExtra("dai");
        Log.d("MainActivity", "カテゴリ名==="+main_category_name);
        sub_category_name = intent.getStringExtra("sub_category_name");
        String maker_name = intent.getStringExtra("maker_name");
        sub_category_id = intent.getIntExtra("Sub_category_id",0);
        maker_id = intent.getIntExtra("Maker_id",0);
        title.setText(maker_name+"の"+sub_category_name);

        //ArrayAdapterオブジェクト生成
        adapter=new ArrayAdapter<Product>(SyohinItiran.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext());
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();
        getData();

        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
        mListView.setOnItemClickListener(this);





    }
    public void modoru_onClick(View v) {
        Intent intent=new Intent(getApplication(),maker.class);
        Intent kate_dai = getIntent();
        String sub_category_name = kate_dai.getStringExtra("sub_category_name");
        intent.putExtra("dai",main_category_name);
        intent.putExtra("sub_category_name",sub_category_name);
        intent.putExtra("sub_category_id",sub_category_id );
        startActivity(intent);
    }

    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        Intent intent = new Intent(this, GPS.class);

        // clickされたpositionのtextとID
        String Item = scenes[position];
        int ID = product_id[position];
        // インテントにセット
        intent.putExtra("dai",main_category_name);
        intent.putExtra("sub_category_name",sub_category_name);
        intent.putExtra("sub_category_id",sub_category_id );
        intent.putExtra("maker_name", Item);
        intent.putExtra("maker_id",ID );
        // Activity をスイッチする
        startActivity(intent);
    }

    private void getData() {

        final ArrayList<Product> aProductList = new ArrayList<>();
        //クエリを投げる

        Log.d("MainActivity", sub_category_name+"メーカーID="+ maker_id+"サブカテゴリID="+sub_category_id);
        Call<List<Product>> call = ApiService.items("products.json?sub_category_id="+sub_category_id+"&maker_id="+maker_id);
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override
                //取得成功
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
//                    Log.d("MainActivity", response.body().toString());
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
        try {
            updateContainer(aProductList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //ListViewに値入れるクラス
    private void updateContainer(ArrayList<Product> aProductList) {

        int count=0;
        for (Product product : aProductList) {
            Log.d("メーカー", product.getname()+",id"+product.getid());
            //遷移時に投げる用のテキスト取得と格納
            scenes[count]=(product.getname());
            product_id[count] = (product.getid());
            adapter.add(product);
            count++;
        }
        //指定のListViewに格納
        topListAdapter.setDatas(aProductList);
        topListAdapter.notifyDataSetChanged();
    }

}