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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        getData();
    }

    public void Logout_Button(View v){
        Intent intent=new Intent(getApplication(),AccountLogin.class);
        startActivity(intent);
    }

    public void Home_Button(View v){
        Intent intent = new Intent(getApplication(),MainActivity.class);
        startActivity(intent);
    }

    public void Favorite_Button(View v){
        Intent i =new Intent(this,Favorite.class);
        startActivity(i);
    }

    private void getData() {
        ApiService = Provider.provideApiService();
        final ArrayList<User> UserList = new ArrayList<>();
        //クエリを投げる
        Call<List<User>> call = ApiService.account("users.json?email="+AccountLogin.email+"&password="+AccountLogin.password);
        Log.d("Account","email："+AccountLogin.email+"、password："+AccountLogin.password);

        try {
            call.enqueue(new Callback<List<User>>() {
                @Override                           //取得成功
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.d("Account", "call onResponse");
                    UserList.addAll(response.body());
                    updateContainer(UserList);
                }
                @Override                           //取得失敗
                public void onFailure(Call<List<User>> call, Throwable t) {
                    Log.d("Account", "call onFailure");
                    updateContainer(UserList);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateContainer(ArrayList<User> UserList) {
        for (User user : UserList) {
            TextView nameView = (TextView)findViewById(R.id.account_name);
            TextView emailView = (TextView)findViewById(R.id.account_email);
            TextView passwordView = (TextView)findViewById(R.id.account_password);
            nameView.setText(user.name);
            emailView.setText(user.email);
            passwordView.setText(user.password);
        }
    }
}
