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
    String main_category_name;
    int main_category_id;
    String sub_category_name;
    int sub_category_id;

    protected int[] maker_id = new int[100];
    protected String[] maker_name=new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        Intent intent = getIntent();
        sub_category_name = intent.getStringExtra("sub_category_name");
        sub_category_id = intent.getIntExtra("sub_category_id",0);
        main_category_name = intent.getStringExtra("main_category_name");
        main_category_id = intent.getIntExtra("main_category_id",0);
        Log.d("maker", "メインカテゴリ名："+main_category_name);
        title.setText(sub_category_name+"のメーカー");

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
    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void modoru_onClick(View v) {
        Intent intent=new Intent(getApplication(),kategori.class);
        intent.putExtra("main_category_name",main_category_name);
        intent.putExtra("main_category_id",main_category_id );
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this, SyohinItiran.class);
        intent.putExtra("main_category_name",main_category_name);
        intent.putExtra("main_category_id",main_category_id );
        intent.putExtra("sub_category_name",sub_category_name);
        intent.putExtra("sub_category_id",sub_category_id );
        intent.putExtra("maker_name", maker_name[position]);
        intent.putExtra("maker_id",maker_id[position] );
        startActivity(intent);
    }

    private void getData() {

        final ArrayList<Product> aProductList = new ArrayList<>();
        Log.d("make","メーカー"+ sub_category_name+"カウント="+ sub_category_id);
                                                                 //クエリを投げる
        Call<List<Product>> call = ApiService.items("makers.json?sub_category_id="+sub_category_id);
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
            Log.d("maker","メーカー："+product.getname()+",id："+product.getid());
            //遷移時に投げる用のテキスト取得と格納
            maker_name[count]=(product.getname());
            maker_id[count] = (product.getid());
            adapter.add(product);
            count++;
        }
        //指定のListViewに格納
        topListAdapter.setDatas(aProductList,1);
        topListAdapter.notifyDataSetChanged();
    }

}