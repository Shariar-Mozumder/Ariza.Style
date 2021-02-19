package com.example.shariarspc.ariza_app.Cart;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.shariarspc.ariza_app.CheckOut.CheckOutProductModelFromCart;
import com.example.shariarspc.ariza_app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

     float Total = 0;
    float finalynew=1,finalyold=0,totally;
    static float subTotal=0;
    String productID="";
    TextView tvTotal;
    Context context;
    int quantity;
    float daam=0;
    CartProductShow cartProductShow=new CartProductShow();


    private ArrayList<CartProductModel> producyDataList;
    private ArrayList<CheckOutProductModelFromCart> checkOutProductModelFromCartArrayList;
    private static String name,id,image;
    float totalprice=1;

    int old=0,ne=1;

    public CartAdapter(ArrayList<CartProductModel> producyDataList, TextView total, float totalCartprice) {

        this.producyDataList = producyDataList;
        tvTotal=total;
        totally=totalCartprice;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.customcartdesign,parent,false);
        context=parent.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {

        Picasso.get().load(producyDataList.get(position).getImageCart()).into(holder.productimage);

        //Log.d("quantity", "onBindViewHolder: "+producyDataList.get(position).getImageCart());

        holder.nameTV.setText(producyDataList.get(position).getNameCart());
        holder.priceTV.setText(producyDataList.get(position).getPriceCart());

        productID=producyDataList.get(position).getIdCart();

        Total=totally;

        String p1=producyDataList.get (position).getPriceCart().substring(producyDataList.get(position).getPriceCart().indexOf(':')+1,producyDataList.get(position).getPriceCart().indexOf("B"));

        holder.metaTotalTV.setText(p1+" X "+producyDataList.get(position).getQuantityy());



        holder.elegantNumberButton.setNumber(String.valueOf(producyDataList.get(position).getQuantityy()));



        daam=daam+(Float.parseFloat(p1)*(producyDataList.get(position).getQuantityy()));


        tvTotal.setText("Total: "+daam+" BDT");



        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {

            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                String p=producyDataList.get (position).getPriceCart().substring(producyDataList.get(position).getPriceCart().indexOf(':')+1,producyDataList.get(position).getPriceCart().indexOf("B"));
                //quantity=newValue;
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(context);
                quantity=myDatabaseHelper.insertQuantity(productID,newValue);

                Log.d("Quantity123", "onValueChange: " +
                        " ID-"+productID);



                old=oldValue;
                ne=newValue;

                Log.d("new", "onValueChange: "+ newValue);
                Log.d("old", "onValueChange: "+ oldValue);
                Log.d("subtotal", "onValueChange: "+ subTotal);

                float subnew=(Float.parseFloat(p)*(newValue));
                float subold=(Float.parseFloat(p)*(oldValue));
               // if(newValue)
                finalynew=subnew;
                finalyold=subold;
                if(newValue>oldValue){

                    int ac=newValue-oldValue;
                    daam=daam+(ac*Float.parseFloat(p));

                    Total=Total+Float.parseFloat(p);
                    holder.metaTotalTV.setText(p+" X "+newValue);

                    tvTotal.setText("Total: "+daam+" BDT");
                }
                else if(oldValue>newValue){
                    int ac=oldValue-newValue;
                    daam=daam-(ac*Float.parseFloat(p));

                    Total=Total-Float.parseFloat(p);
                    holder.metaTotalTV.setText(p+" X "+newValue);

                    tvTotal.setText("Total: "+daam+" BDT");
                }




               // finaly=(totally+subTotal);
               // Total=Total+finaly;

                float price=Float.parseFloat(p1)*quantity;
                Intent intent=new Intent("quantity-totalprice");
                intent.putExtra("quantity",quantity);
                intent.putExtra("totalprice",price);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


            }
        });




        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(context);

                try {
                    myDatabaseHelper.delete(producyDataList.get(position).getIdCart());
                }catch (Exception e){

                }

                producyDataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,producyDataList.size());
                notifyDataSetChanged();

               // Total=Total-finaly;
               // tvTotal.setText("Total: "+Total+" BDT");

                try {
                    String p=producyDataList.get (position).getPriceCart().substring(producyDataList.get(position).getPriceCart().indexOf(':')+1,producyDataList.get(position).getPriceCart().indexOf("B"));
                    String number=holder.elegantNumberButton.getNumber();
                    float del=Float.parseFloat(p)*Float.parseFloat(number);
                    Log.d("numberrrr", "onClick: "+number);

                    Total=Total-del;



                    tvTotal.setText("Total: "+Total+" BDT");
                }catch (Exception e){

                }

                return;













            }
        });


//Total=Total+subTotal;

        Log.d("subtotal", "onValueChange: "+ Total);





       // int quantity=ne;


    }

    @Override
    public int getItemCount() {
        return producyDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView productimage,deleteImage;
        TextView nameTV,priceTV,metaTotalTV;
        ElegantNumberButton elegantNumberButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productimage=itemView.findViewById(R.id.model_image_cart);
            deleteImage=itemView.findViewById(R.id.deleteimG);
            nameTV=itemView.findViewById(R.id.product_name_TV_cart);
            priceTV=itemView.findViewById(R.id.product_Price_TV_cart);
            metaTotalTV=itemView.findViewById(R.id.metaTotal);
            elegantNumberButton=itemView.findViewById(R.id.number_btn_cart);




        }
    }




}


