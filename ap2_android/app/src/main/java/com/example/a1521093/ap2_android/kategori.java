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
import com.example.a1521093.ap2_android.Product;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class kategori extends  AppCompatActivity implements AdapterView.OnItemClickListener{
    private ApiService ApiService;
    private TopListAdapter topListAdapter;
    ArrayAdapter<Product> adapter;
     Product product;
    ListView mListView;
    String kategori_name;
    int main_category_id;


    protected int[] sub_category_id = new int[100];
    protected String[] scenes=new String[100];

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        //ArrayAdapterオブジェクト生成
        adapter=new ArrayAdapter<Product>(kategori.this, android.R.layout.simple_list_item_1);
        topListAdapter = new TopListAdapter(getApplicationContext());
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();

        TextView title = (TextView)findViewById(R.id.kensakugamen);
        Intent intent = getIntent();
        kategori_name = intent.getStringExtra("main_category_name");
        main_category_id = intent.getIntExtra("main_category_id",0);
        title.setText(kategori_name);

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
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

        Intent intent = new Intent(this, maker.class);
        // clickされたpositionのtextとphotoのID
        String Item = scenes[position];
        int ID = sub_category_id[position];
        // インテントにセット
        intent.putExtra("main_category_name",kategori_name);
        intent.putExtra("sub_category_name", Item);
        intent.putExtra("sub_category_id",ID );
        intent.putExtra("main_category_id",main_category_id );
        // Activity をスイッチする
        startActivity(intent);
    }

    private void getData() {
            //仮でint型で１と置く。月曜marge時に変更。

        final ArrayList<Product> aProductList = new ArrayList<>();
                                                //クエリを投げる
        Call<List<Product>> call = ApiService.items("sub_categories.json?main_category_id="+main_category_id);
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override
                                            //取得成功
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
            Log.d("サブカテゴリ", product.getname()+"カウント="+ product.getid());
                    //遷移時に投げる用のテキスト取得と格納
            scenes[count]=(product.getname());
            sub_category_id[count]=(product.getid());
            adapter.add(product);
            count++;
        }
                            //指定のListViewに格納
        topListAdapter.setDatas(aProductList,1);
        topListAdapter.notifyDataSetChanged();
    }
}

