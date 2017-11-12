package com.example.a1521093.ap2_android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;
import java.net.URL;
import com.example.a1521093.ap2_android.kennsakukekaActivity;
import com.squareup.picasso.Picasso;
//独自のListViewのレイアウト生成クラス

public class TopListView extends LinearLayout {
    private ImageView imageView;
    private TextView textViewShopName;
    private TextView textViewProductName;

    public TopListView(Context context,int i) {
        this(context, null,i);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs,int i) {
        this(context, attrs, 0, i);
    }
            //View生成
    public TopListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int i) {
        super(context, attrs, defStyleAttr);
        if(i==1){
            LayoutInflater.from(context).inflate(R.layout.list_top_adapter, this);
            textViewShopName = (TextView) findViewById(R.id.shopNameText);
        }else if(i==2){
            LayoutInflater.from(context).inflate(R.layout.kategori_sub, this);
            textViewProductName = (TextView) findViewById(R.id.product_name);
            imageView = (ImageView) findViewById(R.id.product_image);
        }
    }

            //Viewに値セット
    public void setProduct(final Product product) {
        textViewShopName.setText(product.getname());
    }

    public void setProduct2(final Product product){
        String store_image = product.getimage();
        textViewProductName.setText(product.getname());
        Picasso.with(TopListAdapter.getcontext()).load(store_image).into(imageView);

    }
}

