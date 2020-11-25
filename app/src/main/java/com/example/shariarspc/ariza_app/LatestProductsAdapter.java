package com.example.shariarspc.ariza_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LatestProductsAdapter extends RecyclerView.Adapter<LatestProductsAdapter.MyViewHolder> {
    Context context;
    ArrayList<LatestProductsModel> latestProductList;


    public LatestProductsAdapter(Context context, ArrayList<LatestProductsModel> latestProductList) {
        this.context = context;
        this.latestProductList = latestProductList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.latest_product_recycler_sample,parent,false);
            return new MyViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.imageView.setImageResource(latestProductList.get(position).getImage());
            holder.textView.setText(latestProductList.get(position).getProduct_name());




    }

    @Override
    public int getItemCount() {
        return latestProductList.size();
    }







    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.model_image);
            textView=itemView.findViewById(R.id.product_name_TV);
        }


    }

}
