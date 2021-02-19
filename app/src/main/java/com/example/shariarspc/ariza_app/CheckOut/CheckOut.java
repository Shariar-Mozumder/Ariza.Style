package com.example.shariarspc.ariza_app.CheckOut;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.exception.ApolloException;
import com.example.AddToCartItemsMutation;
import com.example.AddorderMutation;
import com.example.CartQuery;
import com.example.shariarspc.ariza_app.Cart.CartProductShow;
import com.example.shariarspc.ariza_app.Cart.MyDatabaseHelper;
import com.example.shariarspc.ariza_app.MainActivity;
import com.example.shariarspc.ariza_app.R;
import com.example.type.CartItemInput;
import com.example.type.CartItemOptionInput;
import com.example.type.OrderInput;
import com.example.type.OrderProductInput;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.type.OrderInput.builder;

public class CheckOut extends AppCompatActivity {

    ApolloClient apolloClient;
    @Nullable ArrayList<OrderProductInput> productdetailsList=new ArrayList<>();
    @Nullable ArrayList<OrderProductInput> productdetailsList1;
    @Nullable Double Totaaaal;

    Handler handler=new Handler();
    Handler handler1=new Handler();
    CheckOutProductModelFromCart checkOutProductModelFromCart;

    MyDatabaseHelper myDatabaseHelper;
    Cursor cursor;

    int quantity;
    Input<CartItemOptionInput> optionslist;
    String pid;
    float totalPrice;
    Button confirmOrderBtn;

    ImageButton backbtn;
    EditText fullname,deliveryaddress,phnumber;

    CheckBox checkBoxagree;
     String[] cid ;
     String cname = "";
     Double cp ;
     int cq;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        confirmOrderBtn=findViewById(R.id.confirmBTn);
        backbtn=findViewById(R.id.backbuttonID);

        checkBoxagree=findViewById(R.id.checkTerms);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOut.this.finish();
            }
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        apolloClient = ApolloClient.builder()
                .serverUrl("https://ariza.style/index.php?route=api/graphql/usage")
                .okHttpClient(client)
                .build();

        int r=0;
        double tax=0.0;
        //productdetailsList= (List<OrderProductInput>) getIntent().getSerializableExtra("list");
        productdetailsList1=getIntent().getParcelableExtra("list");


//        for(int i=0;i<new CartProductShow().chechkOutProductsList.size();i++){
//            String pname=new CartProductShow().chechkOutProductsList.get(i).checkoutProductName;
//            String pid=new CartProductShow().chechkOutProductsList.get(i).checkoutProductID;
//            float pprice=new CartProductShow().chechkOutProductsList.get(i).priceOfProduct;
//            float ptotal=new CartProductShow().chechkOutProductsList.get(i).totalPriceSingleProduct;
//            int pquantity=new CartProductShow().chechkOutProductsList.get(i).CheckOutproductquantity;
//
//            OrderProductInput orderProductInput=OrderProductInput.builder().product_id(pid).name(pname).price(pprice).model("Hello").quantity(pquantity).total(ptotal).reward(r).tax(tax).build();
//            assert productdetailsList != null;
//            productdetailsList.add(orderProductInput);
//
//
//            Toast.makeText(this, "Details:"+pid+pname+pprice+ptotal+pquantity, Toast.LENGTH_SHORT).show();
//            Log.d("Details_product", "onCreate: "+pid+pname+pprice+ptotal+pquantity);
//        }




//            List productDetails1=new ArrayList();
//                productDetails1.add("product_id");
//                productDetails1.add("product_name");
//                productDetails1.add(5.6);
//                productDetails1.add("product_model");
//                productDetails1.add(1);
//                productDetails1.add(3.5);
//                productDetails1.add(0.0);
//                productDetails1.add(0);


       // assert productdetailsList1 != null;
       // Log.d("list1234", "onCreate: "+productdetailsList1.get(0)+productdetailsList1.get(1)+productdetailsList1.get(2)+productdetailsList1.get(3));



        myDatabaseHelper=new MyDatabaseHelper(getApplicationContext());
        cursor=myDatabaseHelper.getCartProduct();
        if(cursor.getCount()==0){
            return;
        }
        while (cursor!=null&&cursor.moveToNext()){



            pid=cursor.getString(0);
            String pname=cursor.getString(1);
            String pprice=cursor.getString(2);



            String p=pprice.substring(pprice.indexOf(':')+1,pprice.indexOf("B"));
            float price=Float.parseFloat(p);



            Log.d("list1234", "onCreate: "+" ID: "+pid+" Name: "+pname+" Price: "+price+ " quantity: "+1+" total: "+price);



            OrderProductInput orderProductInput=OrderProductInput.builder().product_id(pid).name(pname).price((price)).model("Hello").quantity(1).reward(r).tax(tax).build();
            assert productdetailsList != null;
            productdetailsList.add(orderProductInput);

        }

        Log.d("list1234", "size: "+productdetailsList.size() );

        assert productdetailsList != null;
        for (int i = 0; i<productdetailsList.size(); i++){
        Log.d("list1234", "onCreate: "+productdetailsList.get(0).name()+" "+productdetailsList.get(0).product_id()+" "+productdetailsList.get(0).price());

        }




        Totaaaal= Double.valueOf(String.valueOf(getIntent().getFloatExtra("total",0)));
        Toast.makeText(this, "Total"+Totaaaal, Toast.LENGTH_SHORT).show();

        String name = "Shariar";
    String address="feni";
    String phoneNumber="01853297010";
    String emailAdd="shmozumder2@gmail.com";



    fullname=findViewById(R.id.checkFullNameET);
    deliveryaddress=findViewById(R.id.checkDeliveryddressET);
    phnumber=findViewById(R.id.checkmobileNumberET);

    handler1.postDelayed(new Runnable() {
        @Override
        public void run() {
            CartItemOptionInput cartItemOptionInput=CartItemOptionInput.builder().option_id("6772").value("12345").build();
            CartItemOptionInput cartItemOptionInput1=CartItemOptionInput.builder().option_id("9148").value("16604").build();
            List<CartItemOptionInput> oplist=new ArrayList<>();
            oplist.add(cartItemOptionInput);
            oplist.add(cartItemOptionInput1);




            CartItemInput cartItemInput= CartItemInput.builder().product_id("2046")
                    .quantity(1).options(oplist).recurring_id("").build();



            apolloClient.mutate(new AddToCartItemsMutation(cartItemInput)).enqueue(new ApolloCall.Callback<AddToCartItemsMutation.Data>() {
                @Override
                public void onResponse(@NotNull Response<AddToCartItemsMutation.Data> response) {
                    assert response.data() != null;
                    Log.d("cartQuerydekhi_Mutation", "Total: "+response.data().addItemToCart().total());

//                    int size=response.data().addItemToCart().items().size();
////                    cid=new String[size];
////
////                    for (int i=0;i<size;i++){
////                        cid[i] = Objects.requireNonNull(Objects.requireNonNull(response.data().addItemToCart()).items()).get(i).product_id();
////                        Log.d("cidtag", "onResponse: "+cid[i]);
////                    }

                    // cname = Objects.requireNonNull(Objects.requireNonNull(response.data().addItemToCart()).items()).get(0).name();
                    //cq[0] =response.data().addItemToCart().items().get(0);
                    // cp = Objects.requireNonNull(response.data().addItemToCart()).total();
                    // cp[0] =response.data().addItemToCart().items().get(0).;
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {

                }
            });
        }
    },2000);




        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    if( TextUtils.isEmpty(fullname.getText())){
//                        /**
//                         *   You can Toast a message here that the Username is Empty
//                         **/
//
//                        fullname.setError( "Full name is required!" );
//
//                    }
//                    if(TextUtils.isEmpty(deliveryaddress.getText())){
//                        deliveryaddress.setError( "Delivery Address is required!" );
//                    }
//                    if (TextUtils.isEmpty(phnumber.getText())){
//                        phnumber.setError("Phone Number is required!");
//                    }
//                    if (!checkBoxagree.isChecked()){
//                        checkBoxagree.setError("Selection is Required!");
//                    }
//                    else{
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//
//
//
//
//                                OrderInput orderInput = OrderInput.builder()
//                                        .customer_id("123")
//                                        .firstname(name)
//                                        .lastname(name)
//                                        .email(emailAdd)
//                                        .telephone(phoneNumber)
//                                        .fax(name)
//                                        .payment_firstname(name)
//                                        .payment_lastname(name)
//                                        .payment_company(name)
//                                        .payment_address_1("Feni")
//                                        .payment_address_2("Dhaka")
//                                        .payment_city("Dhaka")
//                                        .payment_postcode("Dhaka")
//                                        .payment_country("Dhaka")
//                                        .payment_country_id("12")
//                                        .payment_zone("Dhaka")
//                                        .payment_zone_id("12")
//                                        .payment_address_format("Dhaka")
//                                        .payment_custom_field("Dhaka")
//                                        .payment_method("Dhaka")
//                                        .payment_code("Dhaka")
//                                        .shipping_firstname("Dhaka")
//                                        .shipping_lastname("Dhaka")
//                                        .shipping_company("Dhaka")
//                                        .shipping_address_1("Dhaka")
//                                        .shipping_address_2("Dhaka")
//                                        .shipping_city("Dhaka")
//                                        .shipping_postcode("Dhaka")
//                                        .shipping_country("Dhaka")
//                                        .shipping_country_id("12")
//                                        .shipping_zone("Dhaka")
//                                        .shipping_zone_id("12")
//                                        .shipping_address_format("Dhaka")
//                                        .shipping_custom_field("Dhaka")
//                                        .shipping_method("Dhaka")
//                                        .shipping_code("Dhaka")
//                                        .comment("Dhaka")
//                                        .products(productdetailsList)
//                                        .total(6.7)
//                                        .build();
//
//
//                                try {
//                                    apolloClient.mutate(new AddorderMutation(orderInput)).enqueue(new ApolloCall.Callback<AddorderMutation.Data>() {
//                                        @Override
//                                        public void onResponse(@NotNull Response<AddorderMutation.Data> response) {
//                                            // Toast.makeText(CheckOut.this, "Response"+response, Toast.LENGTH_SHORT).show();
//                                            Log.d("IDBACK", "onResponse: " + response.data().addOrder());
//                                        }
//
//                                        @Override
//                                        public void onFailure(@NotNull ApolloException e) {
//                                            Toast.makeText(CheckOut.this, "Failed" + e, Toast.LENGTH_SHORT).show();
//                                            Log.d("failed", "onFailure: " + e);
//                                        }
//                                    });
//                                } catch (NullPointerException e) {
//                                    Log.d("Shit", "run: " + e);
//                                }
//                            }
//                        },3000);
//                        Intent intent = new Intent(CheckOut.this, MainActivity.class);
//                        startActivity(intent);
//                    }

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {




                    apolloClient.query(new CartQuery()).enqueue(new ApolloCall.Callback<CartQuery.Data>() {
                        @Override
                        public void onResponse(@NotNull Response<CartQuery.Data> response) {
                            Log.d("QueryofCarItems", "Query: "+response.data());

                        }

                        @Override
                        public void onFailure(@NotNull ApolloException e) {
                            Log.d("cartQuerydekhi", "onResponse: "+e);
                        }
                    });

                        }
                    },3000);


                }
            });




    }

    public BroadcastReceiver mMessageReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            quantity=intent.getIntExtra("quantity",1);
           // totalPrice=intent.getFloatExtra("totalprice",price);
        }
    };
}

//    private String formatDataAsJSON(){
//        final JSONObject root=new JSONObject();
//
//        try {
//            root.put("customer_id","");
//            root.put("firstname","");
//            root.put("lastname","");
//            root.put("email","shmozumder2@gmail.com");
//            root.put("telephone","");
//            root.put("fax","");
//            root.put("payment_firstname","");
//            root.put("payment_lastname","");
//            root.put("payment_company","");
//            root.put("payment_address_1","");
//            root.put("payment_address_2","");
//            root.put("payment_city","");
//            root.put("payment_postcode","");
//            root.put("payment_country","");
//            root.put("payment_country_id","");
//            root.put("payment_zone","");
//            root.put("payment_zone_id","");
//            root.put("payment_address_format","");
//            root.put("payment_custom_field","");
//            root.put("payment_method","");
//            root.put("payment_code","");
//            root.put("shipping_firstname","");
//            root.put("shipping_lastname","");
//            root.put("shipping_company","");
//            root.put("shipping_address_1","");
//            root.put("shipping_address_2","");
//            root.put("shipping_city","");
//            root.put("shipping_postcode","");
//            root.put("shipping_country","");
//            root.put("shipping_country_id","");
//            root.put("shipping_zone","");
//            root.put("shipping_zone_id","");
//            root.put("shipping_address_format","");
//            root.put("shipping_custom_field","");
//            root.put("shipping_method","");
//            root.put("shipping_code","");
//            root.put("comment","");
//            root.put("total",5.7);
//
//
//            JSONObject productdata=new JSONObject();
//            productdata.put("product_id","");
//            productdata.put("name","");
//            productdata.put("price",5.6);
//            productdata.put("model","");
//            productdata.put("quantity",1);
//            productdata.put("total",3.5);
//            productdata.put("tax",0.0);
//            productdata.put("reward",0);
//
//            root.put("products",productdata);
//
//            JSONObject inputForOrder=new JSONObject();
//            inputForOrder.put("input",root);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
// return root.toString();
//    }

//    String name = "Shariar";
//    String address="feni";
//    String phoneNumber="01853297010";
//    String emailAdd="shmozumder2@gmail.com";
//    List productDetails1=new ArrayList();
//                productDetails1.add("product_id");
//                productDetails1.add("product_name");
//                productDetails1.add(5.6);
//                productDetails1.add("product_model");
//                productDetails1.add(1);
//                productDetails1.add(3.5);
//                productDetails1.add(0.0);
//                productDetails1.add(0);
//
//    List productDetails2=new ArrayList();
//                productDetails2.add("product_id");
//                productDetails2.add("product_name");
//                productDetails2.add(5.6);
//                productDetails2.add("product_model");
//                productDetails2.add(1);
//                productDetails2.add(3.5);
//                productDetails2.add(0.0);
//                productDetails2.add(0);
//
//    List allProducts=new ArrayList();
//                allProducts.add(productDetails1);
//                allProducts.add(productDetails2);
//
//    int id=12;
//
//
//
//    OrderInput orderInput =  OrderInput.builder().email(emailAdd).
//            firstname(name).lastname(name)
//            .fax(name).payment_firstname(name)
//            .payment_lastname(name)
//            .payment_company(name)
//            .payment_address_1("Dhaka")
//            .payment_address_2("Dhaka")
//            .payment_city("Dhaka")
//            .payment_postcode("Dhaka")
//            .payment_country("Dhaka")
//            .payment_zone("Dhaka")
//            .payment_address_format("Dhaka")
//            .payment_custom_field("Dhaka")
//            .payment_method("Dhaka")
//            .payment_code("Dhaka")
//            .shipping_firstname("Dhaka")
//            .shipping_lastname("Dhaka")
//            .shipping_company("Dhaka")
//            .shipping_address_1("Dhaka")
//            .shipping_address_2("Dhaka")
//            .shipping_city("Dhaka")
//            .shipping_postcode("Dhaka")
//            .shipping_country("Dhaka")
//            .shipping_zone("Dhaka")
//            .shipping_address_format("Dhaka")
//            .shipping_custom_field("Dhaka")
//            .shipping_method("Dhaka")
//            .shipping_code("Dhaka")
//            .comment("Dhaka")
//            .payment_country_id(12)
//            .customer_id(12)
//            .payment_zone_id(12)
//            .shipping_country_id(12)
//            .shipping_zone_id(12)
//            .total(Totaaaal)
//            .telephone(phoneNumber).products(productdetailsList).build();
//
//    // AddorderMutation addorderMutation=new AddorderMutation(orderInput);
//
//                apolloClient.mutate(AddorderMutation.builder().input(orderInput).build()).enqueue(new ApolloCall.Callback<AddorderMutation.Data>() {
//        @Override
//        public void onResponse(@NotNull Response<AddorderMutation.Data> response) {
//
//            assert response.data() != null;
//            Toast.makeText(CheckOut.this, response.data().addOrder(), Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onFailure(@NotNull ApolloException e) {
//
//        }
//    });

