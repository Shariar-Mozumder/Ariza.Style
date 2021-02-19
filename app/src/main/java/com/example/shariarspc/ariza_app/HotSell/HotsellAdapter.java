package com.example.shariarspc.ariza_app.HotSell;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shariarspc.ariza_app.IndividualProductDetails.ProductDetailsActivity;
import com.example.shariarspc.ariza_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class HotsellAdapter extends RecyclerView.Adapter<HotsellAdapter.MyViewHolder> {
    private Context context;
    HashMap optionMap;
    private ArrayList<HotsellModel> hotList;
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
    private OnNoteListenerHot monNoteListenerHot;

    public HotsellAdapter(Context context, ArrayList<HotsellModel> hotList,OnNoteListenerHot onNoteListenerHot) {
        this.context = context;
        this.hotList = hotList;
        this.monNoteListenerHot=onNoteListenerHot;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.top_sell_sample,parent,false);
        return new MyViewHolder(view,monNoteListenerHot);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(hotList.get(position).getImagehot()).into(holder.imageView);
        //holder.imageView.setImageResource(hotList.get(position).getImage());
        holder.textView.setText(hotList.get(position).getProduct_namehot());
        holder.priceText.setText(hotList.get(position).getPricehot());
        productID=hotList.get(position).getProductIDhot();
        images=hotList.get(position).getImageshot();
        metadescription=hotList.get(position).getMetadescriptionhot();
        price=hotList.get(position).getPricehot();
        name=hotList.get(position).getProduct_namehot();
        stockstatus=hotList.get(position).getStockstatushot();
        quantity=hotList.get(position).getQuantityhot();
        description=hotList.get(position).getDescriptionhot();
        weight=hotList.get(position).getWeighthot();
        length=hotList.get(position).getLengthhot();
        width=hotList.get(position).getWidthhot();
        height=hotList.get(position).getHeighthot();
        image=hotList.get(position).getImagehot();
        optionMap=hotList.get(position).getOptionMap();


    }

    @Override
    public int getItemCount() {
        return hotList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;
        TextView priceText;
        OnNoteListenerHot onNoteListenerHot;

        public MyViewHolder(@NonNull View itemView,OnNoteListenerHot onNoteListenerHot) {
            super(itemView);
            imageView=itemView.findViewById(R.id.topsell_sample_image);
            textView=itemView.findViewById(R.id.topsell_sample_name);
            priceText=itemView.findViewById(R.id.topsell_sample_price);
            this.onNoteListenerHot=onNoteListenerHot;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListenerHot.onNoteClickhot(getAdapterPosition());
        }
    }

    public interface OnNoteListenerHot{
        void onNoteClickhot(int position);
    }
}

//    Intent intent=new Intent(context,ProductDetailsActivity.class);
//                    intent.putExtra("productID",productID);
//                            intent.putExtra("images",images);
//                            intent.putExtra("metadescription",metadescription);
//                            intent.putExtra("price",price);
//                            intent.putExtra("name",name);
//                            intent.putExtra("stockstatus",stockstatus);
//                            intent.putExtra("quantity",quantity);
//                            intent.putExtra("description",description);
//                            intent.putExtra("weight",weight);
//                            intent.putExtra("length",length);
//                            intent.putExtra("width",width);
//                            intent.putExtra("height",height);
//                            intent.putExtra("image",image);
//                            intent.putExtra("counter","2");
//                            context.startActivity(intent);
