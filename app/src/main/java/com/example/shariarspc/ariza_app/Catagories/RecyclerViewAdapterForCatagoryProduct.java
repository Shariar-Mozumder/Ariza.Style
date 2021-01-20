package com.example.shariarspc.ariza_app.Catagories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shariarspc.ariza_app.LatestProducts.LatestProductsModel;
import com.example.shariarspc.ariza_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterForCatagoryProduct extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;


    //oldme
   // public List<String> dataList;
    //newme
    private Context context;
    private ArrayList<CatagoryWiseProductsModel> datalist1;
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
    private OnNoteListenerCatagory mOnNoteListenerCatagory;
    //oldme
//    public RecyclerViewAdapter(List<String> dataList)
//    {
//        this.dataList = dataList;
//    }

    //newme


    public RecyclerViewAdapterForCatagoryProduct(Context context, ArrayList<CatagoryWiseProductsModel> datalist1, OnNoteListenerCatagory OnNoteListenerCatagory) {
        this.context = context;
        this.datalist1 = datalist1;
        this.mOnNoteListenerCatagory=OnNoteListenerCatagory;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype)
    {
        if(viewtype == VIEW_TYPE_DATA)
        {//inflates row layout.
            //oldme
            // View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
            //newme
            View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.latest_product_recycler_sample,viewGroup,false);
            return new DataViewHolder(view,mOnNoteListenerCatagory);

        }
        else
        {//inflates progressbar layout.
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading,viewGroup,false);
            return new ProgressViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i)
    {
        if(viewHolder instanceof DataViewHolder)
        {
            //oldme
           // ((DataViewHolder) viewHolder).textView.setText(dataList.get(i));
            //newme
            ((DataViewHolder) viewHolder).textView.setText(datalist1.get(i).getProduct_name());
            ((DataViewHolder) viewHolder).textdescription.setText(datalist1.get(i).getDescription());
            ((DataViewHolder) viewHolder).textPrice.setText(datalist1.get(i).getPrice());
            //((DataViewHolder) viewHolder).imageView.setImageResource(datalist1.get(i).getImage());
            Picasso.get().load(datalist1.get(i).getImage()).into(((DataViewHolder) viewHolder).imageView);
            productID=datalist1.get(i).getProductID();
            images=datalist1.get(i).getImages();
            metadescription=datalist1.get(i).getMetadescription();
            name=datalist1.get(i).getProduct_name();
            price=datalist1.get(i).getPrice();
            stockstatus=datalist1.get(i).getStockstatus();
            quantity=datalist1.get(i).getQuantity();
            description=datalist1.get(i).getDescription();
            weight=datalist1.get(i).getWeight();
            length=datalist1.get(i).getLength();
            width=datalist1.get(i).getWidth();
            height=datalist1.get(i).getHeight();
            image=datalist1.get(i).getImage();

        }
    }
    @Override
    public int getItemViewType(int position)
    {//if data is load, returns Progressbar viewtype.
        //oldme
        return datalist1.get(position).getProduct_name() == "load" ? VIEW_TYPE_PROGRESS : VIEW_TYPE_DATA;
    }
    @Override
    public int getItemCount()
    {
        //newmeDATAlist1
        return datalist1 == null ? 0 : datalist1.size();
    }
    class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //oldme
        TextView textView,textdescription,textPrice;
        //newme
        ImageView imageView;
        OnNoteListenerCatagory onNoteListenerCatagory;
        public DataViewHolder(@NonNull View itemView,OnNoteListenerCatagory onNoteListenerCatagory)
        {
            super(itemView);
            //oldme
            //textView = (TextView)itemView.findViewById(R.id.textview);
            //newme
            textView=itemView.findViewById(R.id.product_name_TV);
            imageView=itemView.findViewById(R.id.model_image);
            textdescription=itemView.findViewById(R.id.product_description_TV);
            textPrice=itemView.findViewById(R.id.product_Price_TV);
            this.onNoteListenerCatagory=onNoteListenerCatagory;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mOnNoteListenerCatagory.onNoteClickCatagory(getAdapterPosition());
        }
    }
    class ProgressViewHolder extends RecyclerView.ViewHolder
    {
        ProgressBar progressBar;
        public ProgressViewHolder(@NonNull View itemView)
        {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
        }
    }
    public interface OnNoteListenerCatagory{
        void onNoteClickCatagory(int position);
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
//                            intent.putExtra("counter","3");
//                            context.startActivity(intent);