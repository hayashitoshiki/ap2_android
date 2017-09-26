package com.example.a1521093.ap2_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;

public class kategori extends  AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategori);

        TextView dai = (TextView)findViewById(R.id.kensakugamen);
        Intent intent = getIntent();
        String data = intent.getStringExtra("dai");
        dai.setText(data);

        LinearLayout layout = (LinearLayout) findViewById(R.id.test);
        for(int i=0;i<8;i++) {
            View view = getLayoutInflater().inflate(R.layout.kategori_sub, null);
            layout.addView(view);
            TextView text = (TextView) view.findViewById(R.id.sub);
            text.setText( i+1+"こ目のメーカー名");

            Button btn = (Button)findViewById(R.id.susumu);
            btn.setId(i);

            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(),maker.class);
                    String maker_dai="メーカー名";
                    intent.putExtra("maker",maker_dai);
                    Intent kate_dai = getIntent();
                    String kate = kate_dai.getStringExtra("dai");
                    intent.putExtra("dai",kate);
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
        Button ken=(Button)findViewById(R.id.kensaku);
        ken.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                EditText SendValue = (EditText)findViewById(R.id.kensakutext);
                String syohin = SendValue.getText().toString();
                Intent intent=new Intent(getApplication(),GPS.class);
                intent.putExtra("syohin",syohin);
                startActivity(intent);

            }
        });
    }

}
