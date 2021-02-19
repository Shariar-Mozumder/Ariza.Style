package com.example.shariarspc.ariza_app.LatestProducts;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shariarspc.ariza_app.IndividualProductDetails.ProductDetailsActivity;
import com.example.shariarspc.ariza_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//public class GridRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
//{
//    public static final int VIEW_TYPE_DATA = 0;
//    public static final int VIEW_TYPE_PROGRESS = 1;
//
//
//    //oldme
//   // public List<String> dataList;
//    //newme
//    private Context context;
//    private ArrayList<LatestProductsModel> datalist1;
//    //oldme
////    public RecyclerViewAdapter(List<String> dataList)
////    {
////        this.dataList = dataList;
////    }
//
//    //newme
//    private static String productID;
//    private static String[] images;
//    private static String metadescription;
//    private static String price;
//    private static String name;
//    private static String stockstatus;
//    private static String quantity;
//    private static String description;
//    private static String weight;
//    private static String length;
//    private static String width;
//    private static String height;
//    private static String image;
//
//
//    public GridRecyclerViewAdapter(Context context, ArrayList<LatestProductsModel> datalist1) {
//        this.context = context;
//        this.datalist1 = datalist1;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype)
//    {
//        if(viewtype == VIEW_TYPE_DATA)
//        {//inflates row layout.
//            //oldme
//            // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
//            //newme
//            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.latestproductgridviewsample,viewGroup,false);
//            return new DataViewHolder(view);
//
//        }
//        else
//        {//inflates progressbar layout.
//            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading,viewGroup,false);
//            return new ProgressViewHolder(view);
//        }
//    }
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i)
//    {
//        if(viewHolder instanceof DataViewHolder)
//        {
//            //oldme
//           // ((DataViewHolder) viewHolder).textView.setText(dataList.get(i));
//            //newme
//            ((DataViewHolder) viewHolder).textView.setText(datalist1.get(i).getProduct_namelatest());
//            ((DataViewHolder) viewHolder).textdescription.setText(datalist1.get(i).getDescriptionlatest());
//            ((DataViewHolder) viewHolder).textPrice.setText(datalist1.get(i).getPricelatest());
//           // ((DataViewHolder) viewHolder).imageView.setImageResource(datalist1.get(i).getImage());
//
//            Picasso.get().load(datalist1.get(i).getImagelatest()).into(((DataViewHolder) viewHolder).imageView);
//
//            productID=datalist1.get(i).getProductIDlatest();
//            images=datalist1.get(i).getImageslatest();
//            metadescription=datalist1.get(i).getMetadescriptionlatest();
//            price=datalist1.get(i).getPricelatest();
//            name=datalist1.get(i).getProduct_namelatest();
//            stockstatus=datalist1.get(i).getStockstatuslatest();
//            quantity=datalist1.get(i).getQuantitylatest();
//            description=datalist1.get(i).getDescriptionlatest();
//            weight=datalist1.get(i).getWeightlatest();
//            length=datalist1.get(i).getLengthlatest();
//            width=datalist1.get(i).getWidthlatest();
//            height=datalist1.get(i).getHeightlatest();
//            image=datalist1.get(i).getImagelatest();
//        }
//    }
//    @Override
//    public int getItemViewType(int position)
//    {//if data is load, returns Progressbar viewtype.
//        //oldme
//        return datalist1.get(position).getProduct_namelatest() == "load" ? VIEW_TYPE_PROGRESS : VIEW_TYPE_DATA;
//    }
//    @Override
//    public int getItemCount()
//    {
//        //newmeDATAlist1
//        return datalist1 == null ? 0 : datalist1.size();
//    }
//    class DataViewHolder extends RecyclerView.ViewHolder
//    {
//        //oldme
//        TextView textView,textdescription,textPrice;
//        //newme
//        ImageView imageView;
//        public DataViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            //oldme
//            //textView = (TextView)itemView.findViewById(R.id.textview);
//            //newme
//            textView=itemView.findViewById(R.id.product_name_TV_grid);
//            imageView=itemView.findViewById(R.id.model_image_grid);
//            textdescription=itemView.findViewById(R.id.product_description_TV_grid);
//            textPrice=itemView.findViewById(R.id.product_price_TV_grid);
//            itemView.setOnClickListener(new View.OnClickListener()
//            {
//                @Override
//                public void onClick(View view)
//                {
//                    //oldme
//                    //Toast.makeText(view.getContext(), datalist1.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
//                    //newme
//                   // context.startActivity(new Intent(context, ProductDetailsActivity.class));
//                    Intent intent=new Intent(context,ProductDetailsActivity.class);
//                    intent.putExtra("productID",productID);
//                    intent.putExtra("images",images);
//                    intent.putExtra("metadescription",metadescription);
//                    intent.putExtra("price",price);
//                    intent.putExtra("name",name);
//                    intent.putExtra("stockstatus",stockstatus);
//                    intent.putExtra("quantity",quantity);
//                    intent.putExtra("description",description);
//                    intent.putExtra("weight",weight);
//                    intent.putExtra("length",length);
//                    intent.putExtra("width",width);
//                    intent.putExtra("height",height);
//                    intent.putExtra("image",image);
//                    intent.putExtra("counter","3");
//                    context.startActivity(intent);
//                }
//            });
//        }
//    }
//    class ProgressViewHolder extends RecyclerView.ViewHolder
//    {
//        ProgressBar progressBar;
//        public ProgressViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
//        }
//    }
//}


/////////////////////////

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<LatestProductsModel> datalist1;
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
    private OnNoteListenerlatest mOnNoteListenerlatest;

    public GridRecyclerViewAdapter(Context context, ArrayList<LatestProductsModel> datalist1, OnNoteListenerlatest mOnNoteListenerlatest) {

        this.context = context;
        this.datalist1 = datalist1;
        this.mOnNoteListenerlatest=mOnNoteListenerlatest;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.latestproductgridviewsample,parent,false);
        return new MyViewHolder(view,mOnNoteListenerlatest);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        Picasso.get().load(datalist1.get(position).getImage()).into(holder.imageView);

        // holder.imageView.setImageResource(topList.get(position).getImage());
        // holder.imageView.setImageBitmap(MainActivity.getBitmapFromURL(topList.get(position).getImage()));

        // name=datalist1.get(position).getProduct_namelatest();
        Log.d("baaalsaal", "onBindViewHolder: "+name);
        holder.textView.setText(datalist1.get(position).getProduct_namelatest());
        holder.textdescription.setText(datalist1.get(position).getDescriptionlatest());
        holder.textPrice.setText(datalist1.get(position).getPricelatest());
        //((DataViewHolder) viewHolder).imageView.setImageResource(datalist1.get(i).getImage());
        Picasso.get().load(datalist1.get(position).getImagelatest()).into(holder.imageView);
        // productID=datalist1.get(position).getProductIDlatest();
        // images=datalist1.get(position).getImageslatest();
        // metadescription=datalist1.get(position).getMetadescriptionlatest();

        // price=datalist1.get(position).getPricelatest();
        // stockstatus=datalist1.get(position).getStockstatuslatest();
        //  quantity=datalist1.get(position).getQuantitylatest();
        //  description=datalist1.get(position).getDescriptionlatest();
        // weight=datalist1.get(position).getWeightlatest();
        // length=datalist1.get(position).getLengthlatest();
        //width=datalist1.get(position).getWidthlatest();
        // height=datalist1.get(position).getHeightlatest();
        // image=datalist1.get(position).getImagelatest();
    }

    @Override
    public int getItemCount() {
        return datalist1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView,textdescription,textPrice;
        //newme
        ImageView imageView;
        OnNoteListenerlatest onNoteListenerlatest;
        public MyViewHolder(@NonNull View itemView,OnNoteListenerlatest onNoteListenerlatest) {
            super(itemView);
            textView=itemView.findViewById(R.id.product_name_TV_grid);
            imageView=itemView.findViewById(R.id.model_image_grid);
            textdescription=itemView.findViewById(R.id.product_description_TV_grid);
            textPrice=itemView.findViewById(R.id.product_price_TV_grid);
            this.onNoteListenerlatest=onNoteListenerlatest;
            itemView.setOnClickListener(this);

            // itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            mOnNoteListenerlatest.onNoteClicklatest(getAdapterPosition());
        }
    }


    public interface OnNoteListenerlatest{
        void onNoteClicklatest(int position);
    }









}

