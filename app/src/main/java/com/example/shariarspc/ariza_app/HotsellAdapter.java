package com.example.shariarspc.ariza_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class HotsellAdapter extends RecyclerView.Adapter<HotsellAdapter.MyViewHolder> {
    Context context;
    ArrayList<HotsellModel> hotList;

    public HotsellAdapter(Context context, ArrayList<HotsellModel> hotList) {
        this.context = context;
        this.hotList = hotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.top_sell_sample,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.imageView.setImageResource(hotList.get(position).getImage());
        holder.textView.setText(hotList.get(position).getProduct_name());
    }

    @Override
    public int getItemCount() {
        return hotList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.topsell_sample_image);
            textView=itemView.findViewById(R.id.topsell_sample_name);
        }
    }
}
