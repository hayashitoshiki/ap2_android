package com.example.a1521093.ap2_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account extends AppCompatActivity {
    private ApiService ApiService;
    AccountLogin accountlogin;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        ApiService = Provider.provideApiService();
        getData();
    }

    public void Logout(View v){
        Intent intent=new Intent(getApplication(),AccountLogin.class);
        startActivity(intent);
    }

    public void homeButton(View v){
        Intent intent = new Intent(getApplication(),MainActivity.class);
        startActivity(intent);
    }

    private void getData() {
        final ArrayList<Product> aProductList = new ArrayList<>();
        //クエリを投げる
        Call<List<Product>> call = ApiService.items("users.json?email="+accountlogin.getemail()+"&password="+accountlogin.getpassword());
        try {
            call.enqueue(new Callback<List<Product>>() {
                @Override                           //取得成功
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    Log.d("Account", "call onResponse");
                    aProductList.addAll(response.body());
                    Log.d("Account", aProductList.toString());
                    updateContainer(aProductList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.d("Account", "call onFailure");
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

    private void updateContainer(ArrayList<Product> aProductList) {

        for (Product product : aProductList) {
            String account_name=(product.getname());
            int point = (product.getpoint());

            TextView nameView = (TextView)findViewById(R.id.account_name);
            TextView emailView = (TextView)findViewById(R.id.account_email);
            TextView passwordView = (TextView)findViewById(R.id.account_password);
            TextView pointView = (TextView)findViewById(R.id.account_point);


            Log.d("Account", "アカウント名："+account_name);
            nameView.setText(account_name);
            emailView.setText(accountlogin.getemail());
            passwordView.setText(accountlogin.getpassword());
            pointView.setText(point+"p");
        }

    }
}
