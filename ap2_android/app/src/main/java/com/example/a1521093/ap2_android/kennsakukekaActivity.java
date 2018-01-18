package com.example.a1521093.ap2_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.a1521093.ap2_android.R.id.listView;

public class kennsakukekaActivity extends AppCompatActivity {
    ListView mListView;
    ApiService ApiService;
    TopListAdapter topListAdapter;
    ArrayList<Product> List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakukeka);

        TextView title = (TextView)findViewById(R.id.procutd_name);
        title.setText(Product.product_name);

        Log.d("kennsakukekaActivity", "メーカーID："+Product.product_id);
        //ArrayAdapterオブジェクト生成
       List = new ArrayList<>();
        topListAdapter = new TopListAdapter(getApplicationContext(), R.layout.kekka_sub,List);
        mListView = (ListView) findViewById(listView);
        ApiService = Provider.provideApiService();
        //サンプルのListViewに独自で造ったListViewの適用
        mListView.setAdapter(topListAdapter);
        getData();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                switch (view.getId()) {
                    case R.id.tizu:
                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(kennsakukekaActivity.this);
                        alertDlg.setTitle("”この商品”をお気に入り登録しますか？");
                        alertDlg.setMessage("”この店舗”でのこの商品がお気に入りに登録されます");
                        final Product product =(Product)List.get(position);
                        alertDlg.setPositiveButton("はい", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+User.user_latitude+","+User.user_longitude+"&daddr="+product.latitude+","+product.longitude));
                                startActivity(intent);
                            }
                        });
                        alertDlg.setNegativeButton("いいえ",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.setData(Uri.parse("http://maps.google.com/maps?saddr="+User.user_latitude+","+User.user_longitude+"&daddr="+product.latitude+","+product.longitude));
                                        startActivity(intent);
                                    }
                                });
                        alertDlg.create().show();
                }
            }
        });
  }


    public void Home_Button(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void Favorite_Button(View v){
        Intent i = new Intent(this, Favorite.class);
        startActivity(i);
    }

    public void Account_Button(View v){
        Intent i = new Intent(this, Account.class);
        startActivity(i);
    }

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();

        //クエリを投げる
        Call<List<Product>> call = ApiService.items("prices.json?product_id="+Product.product_id);
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override                             //取得成功
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
        for (Product product : aProductList) {

            //遷移時に投げる用のテキスト取得と格納
            final ArrayList<Product> aList = new ArrayList<>();
            //クエリを投げる
            Call<List<Product>> call = ApiService.items("shops.json?id="+product.shop_id);
            Log.d("kennsakukekaActivity", "店舗ID：" + product.shop_id);
            try {
                call.enqueue(new Callback<List<Product>>() {
                    @Override
                    //取得成功
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        aList.addAll(response.body());
                       for (Product product : aList) {
                            //指定のListViewに格納
                           List.add(product);
                        }
                        topListAdapter.notifyDataSetChanged();
                    }
                    @Override                           //取得失敗
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

