package com.example.shariarspc.ariza_app.TopSell;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shariarspc.ariza_app.IndividualProductDetails.ProductDetailsActivity;
import com.example.shariarspc.ariza_app.MainActivity;
import com.example.shariarspc.ariza_app.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TopsellAdapter extends RecyclerView.Adapter<TopsellAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<TopsellModel> topList;
    private static String productID;
    private static String[] images;
    private static String metadescription;
    private static String price;
    private static String name;
    private static String stockstatus;
    private static String quantity;
    private static String description;
    private static String weight;
    private static String length;
    private static String width;
    private static String height;
    private static String image;
    private OnNoteListener mOnNoteListener;

    public TopsellAdapter(Context context, ArrayList<TopsellModel> topList,OnNoteListener onNoteListener) {

        this.context = context;
        this.topList = topList;
        this.mOnNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.top_sell_sample,parent,false);
        return new MyViewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(topList.get(position).getImagetop()).into(holder.imageView);

       // holder.imageView.setImageResource(topList.get(position).getImage());
       // holder.imageView.setImageBitmap(MainActivity.getBitmapFromURL(topList.get(position).getImage()));

        holder.textView.setText(topList.get(position).getProduct_nametop());
        holder.priceText.setText(topList.get(position).getPricetop());
        productID=topList.get(position).productIDtop;
        images=topList.get(position).getImagestop();
        metadescription=topList.get(position).getMetadescriptiontop();
        price=topList.get(position).getPricetop();
        name=topList.get(position).getProduct_nametop();
        stockstatus=topList.get(position).getStockstatustop();
        quantity=topList.get(position).getQuantitytop();
        description=topList.get(position).getDescriptiontop();
        weight=topList.get(position).getWeighttop();
        length=topList.get(position).getLengthtop();
        width=topList.get(position).getWidthtop();
        height=topList.get(position).getHeighttop();
        image=topList.get(position).getImagetop();
    }

    @Override
    public int getItemCount() {
        return topList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        TextView priceText;
        OnNoteListener onNoteListener;
        public MyViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.topsell_sample_image);
            textView=itemView.findViewById(R.id.topsell_sample_name);
            priceText=itemView.findViewById(R.id.topsell_sample_price);
            this.onNoteListener=onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }


    public interface OnNoteListener{
        void onNoteClick(int position);
    }









}
