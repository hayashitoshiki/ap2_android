package com.example.a1521093.ap2_android;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
//独自のListViewのレイアウト生成クラス

public class TopListView extends LinearLayout {
    private ImageView imageView;
    private TextView textViewShopName;
    private TextView textViewProductName;

    public TopListView(Context context) {
        this(context, null);
    }

    public TopListView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
            //View生成
    public TopListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

            LayoutInflater.from(context).inflate(R.layout.kategori_sub, this);
            textViewProductName = (TextView) findViewById(R.id.product_name);
            imageView = (ImageView) findViewById(R.id.product_image);
    }

            //Viewに値セット
    public void setProduct(final Product product) {
        textViewShopName.setText(product.name);
    }

    public void setProduct2(final Product product){
        String store_image = product.image;
        textViewProductName.setText(product.name);
        Picasso.with(TopListAdapter.getcontext()).load(store_image).into(imageView);
    }
}

