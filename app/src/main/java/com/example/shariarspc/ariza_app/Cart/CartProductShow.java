package com.example.shariarspc.ariza_app.Cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shariarspc.ariza_app.CheckOut.CheckOut;
import com.example.shariarspc.ariza_app.CheckOut.CheckOutProductModelFromCart;
import com.example.shariarspc.ariza_app.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartProductShow extends AppCompatActivity {

    RecyclerView recyclerViewCart;
    TextView total,checkOut;
    RecyclerView.LayoutManager layoutManager;
    MyDatabaseHelper myDatabaseHelper;
    Cursor cursor,getPriceCursor,getQuantityCursor;
    private ArrayList<CartProductModel> productList=new ArrayList<>();
    public List<CheckOutProductModelFromCart> chechkOutProductsList=new ArrayList<CheckOutProductModelFromCart>();

    String name,productID,image;
    int quantity1,quantity;
    public float totalCartprice=0,price,totalPrice;

    ImageButton backBtn;

    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_product_show);

        recyclerViewCart=findViewById(R.id.cartproductList);
        total=findViewById(R.id.totalAmountID);
        checkOut=findViewById(R.id.checkoutID);
        backBtn=findViewById(R.id.backbuttonID);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartProductShow.this.finish();
            }
        });



      //  chechkOutProductsList.add(new CheckOutProductModelFromCart())
//        name=getIntent().getStringExtra("name");
//        price=getIntent().getStringExtra("price");
//        productID=getIntent().getStringExtra("id");
//        image=getIntent().getStringExtra("image");
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
               new IntentFilter("quantity-totalprice"));

        recyclerviewInIt();

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartProductShow.this, CheckOut.class);
                intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) chechkOutProductsList);
                intent.putExtra("total",totalCartprice);
                startActivity(intent);
            }
        });





    }

    public BroadcastReceiver mMessageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            quantity=intent.getIntExtra("quantity",1);
            totalPrice=intent.getFloatExtra("totalprice",price);
        }
    };

    public void recyclerviewInIt(){

        handler.post(new Runnable() {
            @Override
            public void run() {


                layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerViewCart.setLayoutManager(layoutManager);
                recyclerViewCart.hasFixedSize();
                myDatabaseHelper=new MyDatabaseHelper(getApplicationContext());

                cursor=myDatabaseHelper.getCartProduct();
                if(cursor.getCount()==0){
                    return;
                }
                while (cursor!=null&&cursor.moveToNext()){

                    CartProductModel cartProductModel=new CartProductModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4));
                    productList.add(cartProductModel);
                    name=cursor.getString(0);
                    productID=cursor.getString(1);
                    //quantity=cursor.getInt(4);



                    CheckOutProductModelFromCart checkOutProductModelFromCart=new CheckOutProductModelFromCart(productID,name,price,totalPrice,quantity);
                    chechkOutProductsList.add(checkOutProductModelFromCart);

                }

                getPriceCursor=myDatabaseHelper.getTotalPrice();
                if (getPriceCursor.getCount()==0){
                    return;
                }
                while(getPriceCursor!=null&&getPriceCursor.moveToNext()){
                    String pr=getPriceCursor.getString(0);
                    // String p=producyDataList.get (position).getPriceCart().substring(producyDataList.get(position).getPriceCart().indexOf(':')+1,producyDataList.get(position).getPriceCart().indexOf("B"));
                    String p=pr.substring(pr.indexOf(':')+1,pr.indexOf("B"));
                    price=Float.parseFloat(p);
                    totalCartprice=totalCartprice+Float.parseFloat(p);

                    Log.d("onValueChange", "Totaaaal: "+totalCartprice);


                }

                CartAdapter cartAdapter=new CartAdapter(productList,total,totalCartprice);
                recyclerViewCart.setAdapter(cartAdapter);
                cartAdapter.notifyDataSetChanged();


                total.setText("Total: "+totalCartprice+" BDT");
            }
        });

    }
}
