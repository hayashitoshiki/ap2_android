package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class maker extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ApiService ApiService;
    private TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
    ListView mListView;

    protected int[] maker_id = new int[100];
    protected String[] maker_name=new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        title.setText(Product.sub_category_name+"のメーカー");

        //ArrayAdapterオブジェクト生成
        adapter = new ArrayAdapter<Product>(maker.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext(),R.layout.kategori_sub);
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();
        getData();

        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
        mListView.setOnItemClickListener(this);
    }
    public void home_button(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void account_button(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }

    public void modoru_onClick(View v) {
        Intent intent=new Intent(getApplication(),kategori.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this, SyohinItiran.class);
        Product.maker_id  = maker_id[position];
        Product.maker_name = maker_name[position];
        startActivity(intent);
    }

    private void getData() {

        final ArrayList<Product> aProductList = new ArrayList<>();
        Log.d("make","メーカー"+ Product.sub_category_name+"カウント="+ Product.sub_category_id);
                                                                 //クエリを投げる
        Call<List<Product>> call = ApiService.items("makers.json?sub_category_id="+Product.sub_category_id);
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

        int count=0;
        for (Product product : aProductList) {
            Log.d("maker","メーカー："+product.name+",id："+product.id);
            //遷移時に投げる用のテキスト取得と格納
            maker_name[count]  = product.name;
            maker_id[count] = product.id;
            adapter.add(product);
            count++;
        }
        //指定のListViewに格納
        topListAdapter.setDatas(aProductList,1);
        topListAdapter.notifyDataSetChanged();
    }

}