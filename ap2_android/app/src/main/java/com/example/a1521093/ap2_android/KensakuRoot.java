package com.example.a1521093.ap2_android;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    ArrayList<Product> list;
    ListView mListView;
    private String title_name;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        TextView title = (TextView)findViewById(R.id.title);
        title_name = Product.product_name2;
        Log.d("検索ルート",title_name);
        title.setText(title_name);
        list= new ArrayList<Product>();
        topListAdapter = new TopListAdapter(getApplicationContext(),R.layout.kategori_sub,list);
        mListView = (ListView) findViewById(R.id.listView);
        ApiService = Provider.provideApiService();
        getData();
        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
        mListView.setOnItemClickListener(this);
    }

    public void home_Button(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void favorite_Button(View v) {
        Intent i = new Intent(this, Favorite.class);
        startActivity(i);
    }

    public void account_Button(View v){
        Intent i = new Intent(this,Account.class);
        startActivity(i);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Product product =(Product)list.get(position);
        Product.product_id = product.id;
        Product.product_name = product.name;
        Product.product_image = product.image;

        if(User.user_latitude==0){
            //ダイアログメッセージ表示
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("位置情報取得中...");
            progressDialog.show();

            for(int i=0;i<10000000;i++){
                if(User.user_latitude>0.0) {
                    Log.d("遷移","OK");
                    Intent intent = new Intent(this, kennsakukekaActivity.class);
                    startActivity(intent);
                }
            }
        }else {
            Intent intent = new Intent(this, kennsakukekaActivity.class);
            startActivity(intent);
        }
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
       int   count=0;

        for (Product product : aProductList) {
            String name = product.name;
            Log.d("比較","比較値１="+ name+",：比較値２="+title_name);
            //遷移時に投げる用のテキスト取得と格納
            if (name.contains(title_name)) {
                Log.d("結果","OK="+product.name);
                list.add(product);
                count++;
            }
        }
        if(count==0){
            TextView a = (TextView)findViewById(R.id.No);
            a.setText("該当する商品は見つかりませんでした。");
        }
        //指定のListViewに格納
        topListAdapter.notifyDataSetChanged();
    }
}
