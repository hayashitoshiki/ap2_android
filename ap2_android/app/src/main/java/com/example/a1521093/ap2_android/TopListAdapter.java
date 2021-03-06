package com.example.a1521093.ap2_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

//独自のListViewレイアウト生成クラス
public class TopListAdapter extends BaseAdapter {

    private ArrayList<Product> aProductList;
    public static  Context context;
    private LayoutInflater inflater;
    private int resourcedId;

    private static class ViewHolder {
        Button editButton;
        TextView textViewname;
        TextView textViewaddress;
        TextView textViewdistance;
        ImageView imageView;
    }

    public TopListAdapter(Context context, int resourcedId, ArrayList<Product> list) {
        this.context = context;
        this.aProductList = list;
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

        Log.d("TopListView","resourcedId："+resourcedId);
        if (convertView == null) {
            if(resourcedId==2130968623) {
                 convertView = inflater.inflate(resourcedId, null);
                holder = new ViewHolder();
                holder.editButton = (Button) convertView.findViewById(R.id.tizu);
                holder.textViewname = (TextView)convertView.findViewById(R.id.syouhinmei);
                holder.textViewaddress = (TextView)convertView.findViewById(R.id.zyusyo);
                holder.textViewdistance = (TextView)convertView.findViewById(R.id.kyori);
                holder.imageView = (ImageView)convertView.findViewById(R.id.store_image);
                convertView.setTag(holder);
            }else{
                convertView = inflater.inflate(resourcedId, null);
                holder = new ViewHolder();
                holder.textViewname = (TextView) convertView.findViewById(R.id.product_name);
                holder.imageView = (ImageView) convertView.findViewById(R.id.product_image);
                convertView.setTag(holder);
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(resourcedId==2130968623) {
            Product product = (Product) getItem(position);
            String store_name = product.name;
            String store_address = product.address;
            String store_image = product.image;
            int distance = (int)getDistance(User.user_latitude, User.user_longitude, product.latitude,  product.longitude);

            holder.textViewname.setText(store_name);
            holder.textViewaddress.setText(store_address);
            Picasso.with(context).load(store_image).into(holder.imageView);
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
        }else{
            Product product = (Product) getItem(position);
            String store_image = product.image;
            holder.textViewname.setText(product.name);
            Picasso.with(TopListAdapter.getcontext()).load(store_image).into(holder.imageView);
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
}
