package com.example.a1521093.ap2_android;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountLogin extends AppCompatActivity {
    private ApiService ApiService;
    private EditText emailEdit;
    private EditText passwordEdit;

    public static String email;
    public static String password;


    private final int REQUEST_PERMISSION = 1000;

    // Serviceとのインターフェースクラス
    static public ServiceConnection mConnection = new ServiceConnection() {
        GPS mBindService;
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Serviceとの接続確立時に呼び出される。

            // service引数には、Onbind()で返却したBinderが渡される
            mBindService = ((GPS.LocalBinder)service).getService();
            //必要であればmBoundServiceを使ってバインドしたServiceへの制御を行う
        }
        public void onServiceDisconnected(ComponentName className) {
            // Serviceとの切断時に呼び出される。
            mBindService = null;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailEdit = (EditText)findViewById(R.id.email);
        passwordEdit = (EditText)findViewById(R.id.password);
        ApiService = Provider.provideApiService();

        if(Build.VERSION.SDK_INT >= 23){
            checkPermission();
        }
        Intent intent = new Intent(getApplication(), GPS.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
    public void Login(View v){
        getData();
    }

    public void NewAccount(View v){
        Intent intent = new Intent(getApplication(),newacount.class);
        startActivity(intent);
    }

    private void getData() {
        final ArrayList<User> UserList = new ArrayList<>();
        email = emailEdit.getText().toString();
        password = passwordEdit.getText().toString();

        //クエリを投げる
        Call<List<User>> call = ApiService.account("users.json?email="+email+"&password="+password);
        Log.d("AccountLogin","email："+email+"password："+password);
        try {
            call.enqueue(new Callback<List<User>>() {
                @Override                           //取得成功
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    Log.d("AccountLogin", "call onResponse");
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

        int count=0;
        for (User user : UserList) {
            String account_name=(user.name);
            Log.d("AccountLogin", "アカウント名："+account_name);
            Intent intent=new Intent(getApplication(),MainActivity.class);
            startActivity(intent);

            count+=1;
        }
        if(count==0){
            emailEdit.getEditableText().clear();
            passwordEdit.getEditableText().clear();
        }
    }

    // 位置情報許可の確認
    public void checkPermission() {
        // 既に許可している
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
        }
        // 拒否していた場合
        else{
            requestLocationPermission();
        }
    }

    // 許可を求める
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(AccountLogin.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);
        } else {
            Toast toast = Toast.makeText(this, "許可されないとアプリが実行できません", Toast.LENGTH_SHORT);
            toast.show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, REQUEST_PERMISSION);
        }
    }

    // 結果の受け取り
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                return;
            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "これ以上なにもできません", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
