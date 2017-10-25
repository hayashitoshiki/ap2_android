package com.example.a1521093.ap2_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

public class KensakuRoot extends  AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kensakuroot);

        TextView title = (TextView)findViewById(R.id.titlebar);
        Intent intent = getIntent();
        final String title_name = intent.getStringExtra("syohin");
        title.setText(title_name);

        LinearLayout layout = (LinearLayout) findViewById(R.id.loop);
        for(int i=0;i<8;i++) {
            View view = getLayoutInflater().inflate(R.layout.kategori_sub, null);
            layout.addView(view);
            TextView text = (TextView) view.findViewById(R.id.loop_name);
            final String product_name =("商品"+(i+1));
            text.setText(product_name);

            Button btn = (Button)findViewById(R.id.susumu);
            btn.setId(i);

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),GPS.class);
                    intent.putExtra("syohin",product_name);
                    intent.putExtra("kensaku",title_name);
                    intent.putExtra("bunki",2);
                    startActivity(intent);
                }
            });
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
    public void home_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void kensaku_onClick(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
