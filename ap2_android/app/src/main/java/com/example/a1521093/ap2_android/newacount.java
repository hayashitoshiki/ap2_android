package com.example.a1521093.ap2_android;
/**
 * Created by 1521077 on 2017/11/04.
 */
import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class newacount extends AppCompatActivity {

    String dai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newacount);

        Button kesyo=(Button)findViewById(R.id.modoru);
        kesyo.setOnClickListener(new View.OnClickListener(){
            @Override

            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),MainActivity.class);
                intent.putExtra("dai",dai);
                startActivity(intent);
            }
        });

        

    }
}
