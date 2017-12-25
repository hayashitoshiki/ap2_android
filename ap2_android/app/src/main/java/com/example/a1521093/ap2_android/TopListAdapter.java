package com.example.a1521093.ap2_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


//独自のListViewレイアウト生成クラス

public class TopListAdapter extends BaseAdapter {

    private ArrayList<TopListView> aProductList;
    public static  Context context;
    private LayoutInflater inflater;
    private int resourcedId;

    private static class ViewHolder {
        public Button editButton;

        public TextView textViewname;
        public TextView textViewaddress;
        public TextView textViewdistance;
        public ImageView imageViewstore;
    }
    public TopListAdapter(Context context, int resourcedId) {
        this.context = context;
        this.aProductList = new ArrayList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resourcedId = resourcedId;
    }

    @Override
    public int getCount() {
        return aProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return aProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static Context getcontext(){return context;}

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            if(resourcedId==2130968622) {
                 convertView = inflater.inflate(resourcedId, null);
                holder = new ViewHolder();
                holder.editButton = (Button) convertView.findViewById(R.id.tizu);
                holder.textViewname = (TextView)convertView.findViewById(R.id.syouhinmei);
                holder.textViewaddress = (TextView)convertView.findViewById(R.id.zyusyo);
                holder.textViewdistance = (TextView)convertView.findViewById(R.id.kyori);
                holder.imageViewstore = (ImageView)convertView.findViewById(R.id.store_image);
                convertView.setTag(holder);
            }else{
                  return aProductList.get(position);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(resourcedId==2130968622) {
            kennsakukekaActivity kekka= new kennsakukekaActivity();
            int stock = kekka.stock[position];
            String store_name = kekka.store_name[position];
            String store_address = kekka.store_address[position];
            String store_image = kekka.store_image[position];
            int distance = (int)getDistance(User.user_latitude, User.user_longitude, kekka.store_lati[position],  kekka.store_lon[position]);

            holder.textViewname.setText(store_name);
            holder.textViewaddress.setText(store_address);

            Picasso.with(context).load(store_image).into(holder.imageViewstore);
            if(distance>10000) {
                distance = distance / 1000;
                holder.textViewdistance.setText(distance + "km");
            }else {
                holder.textViewdistance.setText(distance + "m");
            }

            holder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ListView) parent).performItemClick(view, position,R.id.tizu);
                }
            });
        }
        return convertView;
    }

    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +  Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        double miles = dist * 60 * 1.1515;
        return (miles * 1.609344*1000);
    }

    private double rad2deg(double radian) {
        return radian * (180f / Math.PI);
    }

    public double deg2rad(double degrees) {
        return degrees * (Math.PI / 180f);
    }

    //データ取得
    public void setDatas(ArrayList<Product> aProductList,int i) {
        for (Product product : aProductList) {
            TopListView view = new TopListView(context);
            if(i==1){
            //TopListViewに値を渡してレイアウトセット
                view.setProduct(product);
            }else if(i==2){
                view.setProduct2(product);
            }
            this.aProductList.add(view);
        }
    }

    public void setData(ArrayList<Product> aProductList,String name) {
        for (Product product : aProductList) {
            if (product.name.contains(name)) {
                Log.d("ループ処理","商品名＝"+product.name+"：比較値＝"+name);
                TopListView view = new TopListView(context);
                view.setProduct2(product);
                this.aProductList.add(view);
            }
        }
    }

}
