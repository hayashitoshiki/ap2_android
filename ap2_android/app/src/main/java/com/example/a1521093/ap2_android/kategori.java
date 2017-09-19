package com.example.a1521093.ap2_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.TextView;
import android.widget.LinearLayout;

public class kategori extends  AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        LinearLayout layout = (LinearLayout) findViewById(R.id.test);
        for(int i=0;i<8;i++) {
            View view = getLayoutInflater().inflate(R.layout.kategori_sub, null);
            layout.addView(view);
            TextView text = (TextView) view.findViewById(R.id.sub);
            text.setText( i+1+"こ目のデータ");


        }

        Button sendButton=(Button)findViewById(R.id.modoru);
        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(getApplication(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
