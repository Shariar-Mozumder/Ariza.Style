package com.example.shariarspc.ariza_app.IndividualProductDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shariarspc.ariza_app.R;

import java.util.ArrayList;

//public class ColorListAdapter extends RecyclerView.Adapter<ColorListAdapter.MyViewHolder> {
//    Context context;
//    ArrayList<ColorListModel> colorList;
//
//    public ColorListAdapter(Context context, ArrayList<ColorListModel> colorList) {
//        this.context = context;
//        this.colorList = colorList;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.size_list_sample,parent,false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//
//        holder.textView.setText(colorList.get(position).getColor_name());
//    }
//
//    @Override
//    public int getItemCount() {
//        return colorList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//
//        TextView textView;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textView=itemView.findViewById(R.id.sizeTextView);
//        }
//    }
//}
