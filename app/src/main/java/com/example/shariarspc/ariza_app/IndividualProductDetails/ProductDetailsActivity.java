package com.example.shariarspc.ariza_app.IndividualProductDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestFutureTarget;
import com.example.AddToCartItemsMutation;
import com.example.ProductDetailsQuery;
import com.example.shariarspc.ariza_app.Cart.CartProductShow;
import com.example.shariarspc.ariza_app.Cart.MyDatabaseHelper;
import com.example.shariarspc.ariza_app.CheckOut.CheckOut;
import com.example.shariarspc.ariza_app.MainActivity;
import com.example.shariarspc.ariza_app.R;
import com.example.type.CartItemInput;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jsibbold.zoomage.ZoomageView;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

//import static com.example.shariarspc.ariza_app.R.id.imageSlider2;

public class ProductDetailsActivity extends AppCompatActivity {

//    RecyclerView sizeRecyclerView,colorRecyclerView;
//    String sizeNames[],colorNames[];
//    ArrayList<SizeListModel> sizelist;
//    ArrayList<ColorListModel> colorlist;
//    SizeListAdapter sizeListAdapter;
//    ColorListAdapter colorListAdapter;
    int quantity;
    float totalPrice;

    ViewFlipper viewFlipper;
    ImageButton prev,next;
    ZoomageView img1,img2,img3,img4;
    ImageView imageView;
    FloatingActionButton backBtn;

    Spinner sizespinner;
    LinearLayout linearLayout;

    Dialog dialog;
    RelativeLayout relativefullImage;

   ApolloClient apolloClient;

    Handler handler7=new Handler();
    Handler handler8=new Handler();
    String[] imagesTop=new String[10];
    String[] imagesHot=new String[10];
    String[] imageslatest=new String[10];
    String[] imagescatagorywise=new String[10];
    String[] sizeTop=new String[10];
    String[] sizeHot=new String[10];
    String[] sizelatest=new String[10];
    String[] sizecatagorywise=new String[10];
    HashMap optionHot=new HashMap();
    String counter="",productID="",priceTop="",nameTop="",image="",metadescriptionTop="",stockstatusTop="",quantityTop="",descriptionTop="",weightTop="",lengthTop="",widthTop="",heightTop="",modelTop="";
    String productIDHot="",priceHot="",nameHot="",imageHot="",metadescriptionHot="",stockstatusHot="",quantityHot="",descriptionHot="",weightHot="",lengthHot="",widthHot="",heightHot="",modelHot="";
    String productIDlatest="",pricelatest="",namelatest="",imagelatest="",metadescriptionlatest="",stockstatuslatest="",quantitylatest="",descriptionlatest="",weightlatest="",lengthlatest="",widthlatest="",heightlatest="",modellatest="";
    String productIDcatagorywise="",pricecatagorywise="",namecatagorywise="",imagecatagorywise="",metadescriptioncatagorywise="",stockstatuscatagorywise="",quantitycatagorywise="",descriptioncatagorywise="",weightcatagorywise="",lengthcatagorywise="",widthcatagorywise="",heightcatagorywise="",modelcatagorywise="";


    TextView nametext,descriptionText,priceText,stockstatustext,quantityText,weighttext,heighttext,lengthText,widthText,modeltext;



    Button buyNow,addToCart;

    MyDatabaseHelper myDatabaseHelper;


    SliderLayout sliderLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

//        apolloClient = ApolloClient.builder()
//                .serverUrl("https://ariza.style/index.php?route=api/graphql/usage")
//                .build();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        apolloClient = ApolloClient.builder()
                .serverUrl("https://ariza.style/index.php?route=api/graphql/usage")
                .okHttpClient(client)
                .build();

        nametext=findViewById(R.id.product_name);
        descriptionText=findViewById(R.id.descriptionDetails);
        priceText=findViewById(R.id.pricetext);
        stockstatustext=findViewById(R.id.stocktext);
        quantityText=findViewById(R.id.quantitytext);
        modeltext=findViewById(R.id.modeltext);
        sizespinner=findViewById(R.id.sizespinner);
        linearLayout=findViewById(R.id.linear1);
//        weighttext=findViewById(R.id.weighttext);
//        heighttext=findViewById(R.id.heighttext);
//        lengthText=findViewById(R.id.lengthttext);
//        widthText=findViewById(R.id.widhttext);
        backBtn=findViewById(R.id.fab);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetailsActivity.this.finish();
            }
        });



        imageView=findViewById(R.id.imagview);

        View view =getLayoutInflater().inflate(R.layout.full_scree_image,null);
        dialog=new Dialog(this,R.style.Theme_AppCompat_DayNight_NoActionBar);
        dialog.setContentView(view);

        viewFlipper=view.findViewById(R.id.viewflipper);
        prev=view.findViewById(R.id.pervious);
        next=view.findViewById(R.id.next);
        img1=view.findViewById(R.id.img1);
        img2=view.findViewById(R.id.img2);
        img3=view.findViewById(R.id.img3);
        img4=view.findViewById(R.id.img4);

        relativefullImage=view.findViewById(R.id.rlfull);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter.equals("1")){
                    setSliderViews1();
                }
                else if(counter.equals("2")){
                    setSliderViews2();
                }
                else if(counter.equals("3")){
                    setSliderViews3();
                }
                else if(counter.equals("4")){
                    setSliderViews4();
                }
               // Glide.with(ProductDetailsActivity.this).load(image).into(img1);
                dialog.show();
            }
        });
        relativefullImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });




//        prev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewFlipper.showPrevious();
//            }
//        });
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewFlipper.showNext();
//            }
//        });

        addToCart=findViewById(R.id.addToCartID);
        buyNow=findViewById(R.id.buynowID);

        myDatabaseHelper=new MyDatabaseHelper(ProductDetailsActivity.this);
        SQLiteDatabase sqLiteDatabase=myDatabaseHelper.getWritableDatabase();



        //sizeNames=getResources().getStringArray(R.array.size_array);
       // sizeRecyclerView=findViewById(R.id.size_recyclerView);

        //colorNames=getResources().getStringArray(R.array.colour_array);
        //colorRecyclerView=findViewById(R.id.color_recyclerView);

     //  sizeListRecyclerViewInIT();
       //colorRecyclerViewInIt();

       // sliderLayout = findViewById(imageSlider2);
       // sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
       // sliderLayout.setScrollTimeInSec(10); //set scroll delay in seconds :



        counter=getIntent().getStringExtra("counter");


        productID=getIntent().getStringExtra("productID");

        imagescatagorywise=getIntent().getStringArrayExtra("images");
        pricecatagorywise=getIntent().getStringExtra("price");
        namecatagorywise=getIntent().getStringExtra("name");
        metadescriptioncatagorywise=getIntent().getStringExtra("metadescription");
        stockstatuscatagorywise=getIntent().getStringExtra("stockstatus");
        quantitycatagorywise=getIntent().getStringExtra("quantity");
        descriptioncatagorywise=getIntent().getStringExtra("description");
        weightcatagorywise=getIntent().getStringExtra("weight");
        lengthcatagorywise=getIntent().getStringExtra("length");
        widthcatagorywise=getIntent().getStringExtra("width");
        heightcatagorywise=getIntent().getStringExtra("height");
        image=getIntent().getStringExtra("image");
        modelcatagorywise=getIntent().getStringExtra("model");
        sizecatagorywise=getIntent().getStringArrayExtra("size");


       // productID=getIntent().getStringExtra("productID");
        imageslatest=getIntent().getStringArrayExtra("images");
        pricelatest=getIntent().getStringExtra("price");
        namelatest=getIntent().getStringExtra("name");
        metadescriptionlatest=getIntent().getStringExtra("metadescription");
        stockstatuslatest=getIntent().getStringExtra("stockstatus");
        quantitylatest=getIntent().getStringExtra("quantity");
        descriptionlatest=getIntent().getStringExtra("description");
        weightlatest=getIntent().getStringExtra("weight");
        lengthlatest=getIntent().getStringExtra("length");
        widthlatest=getIntent().getStringExtra("width");
        heightlatest=getIntent().getStringExtra("height");
        image=getIntent().getStringExtra("image");
        modellatest=getIntent().getStringExtra("model");
        sizelatest=getIntent().getStringArrayExtra("size");



        //productID=getIntent().getStringExtra("productID");
        imagesTop=getIntent().getStringArrayExtra("images");
        priceTop=getIntent().getStringExtra("price");
        nameTop=getIntent().getStringExtra("name");
        metadescriptionTop=getIntent().getStringExtra("metadescription");
        stockstatusTop=getIntent().getStringExtra("stockstatus");
        quantityTop=getIntent().getStringExtra("quantity");
        descriptionTop=getIntent().getStringExtra("description");
        weightTop=getIntent().getStringExtra("weight");
        lengthTop=getIntent().getStringExtra("length");
        widthTop=getIntent().getStringExtra("width");
        heightTop=getIntent().getStringExtra("height");
        image=getIntent().getStringExtra("image");
        modelTop=getIntent().getStringExtra("model");
        sizeTop=getIntent().getStringArrayExtra("size");


       // productID=getIntent().getStringExtra("productID");
        imagesHot=getIntent().getStringArrayExtra("images");
        priceHot=getIntent().getStringExtra("price");
        nameHot=getIntent().getStringExtra("name");
        metadescriptionHot=getIntent().getStringExtra("metadescription");
        stockstatusHot=getIntent().getStringExtra("stockstatus");
        quantityHot=getIntent().getStringExtra("quantity");
        descriptionHot=getIntent().getStringExtra("description");
        weightHot=getIntent().getStringExtra("weight");
        lengthHot=getIntent().getStringExtra("length");
        widthHot=getIntent().getStringExtra("width");
        heightHot=getIntent().getStringExtra("height");
        image=getIntent().getStringExtra("image");
        modelHot=getIntent().getStringExtra("model");
        sizeHot=getIntent().getStringArrayExtra("size");
        Bundle bundle=this.getIntent().getExtras();
        if(bundle!=null){
            optionHot= (HashMap) bundle.getSerializable("options");
        }


        Toast.makeText(this, "ID:"+productID+sizelatest+modellatest, Toast.LENGTH_SHORT).show();


        //productDetailsQuery();

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=  nametext.getText().toString();
                String price=  priceText.getText().toString();
                //  String image1="https://ariza.style/image/"+image;

                boolean inserted;

                if (!name.isEmpty()&& !price.isEmpty() && !productID.isEmpty()){
                    inserted=new MyDatabaseHelper(ProductDetailsActivity.this).insertProduct(name,productID,price,image,quantity);
                    Log.d("TAG", "onClick: "+image);


                    if (inserted){
                        sendToAPI();
                        Toast.makeText(ProductDetailsActivity.this, "Product Added to the Cart", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ProductDetailsActivity.this, CartProductShow.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(ProductDetailsActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }






            }
        });

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=  nametext.getText().toString();
                String price=  priceText.getText().toString();
                //  String image1="https://ariza.style/image/"+image;

                boolean inserted;

                if (!name.isEmpty()&& !price.isEmpty() && !productID.isEmpty()){
                    inserted=new MyDatabaseHelper(ProductDetailsActivity.this).insertProduct(name,productID,price,image,quantity);
                    Log.d("TAG", "onClick: "+image);


                    if (inserted){
                        sendToAPI();
                        Toast.makeText(ProductDetailsActivity.this, "Product Added to the Cart", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ProductDetailsActivity.this, CheckOut.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(ProductDetailsActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        settingTexts();


    }



    private void sendToAPI(){
        handler8.post(new Runnable() {
            @Override
            public void run() {
                CartItemInput cartItemInput=CartItemInput.builder().quantity(1).product_id(productID).build();
                apolloClient.mutate(new AddToCartItemsMutation(cartItemInput)).enqueue(new ApolloCall.Callback<AddToCartItemsMutation.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<AddToCartItemsMutation.Data> response) {
                        assert response.data() != null;
                        Log.d("carttt", "onResponse: ===HELLO");

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
            }
        });
    }

    private void settingTexts() {
        if (counter.equals("1")){
            Picasso.get().load(image).into(imageView);
            nametext.setText(nameTop);
            Toast.makeText(this, nameTop, Toast.LENGTH_SHORT).show();
            descriptionText.setText("Description: "+metadescriptionTop);
            priceText.setText("Price: "+priceTop);
            stockstatustext.setText("Stock Status: "+stockstatusTop);
            quantityText.setText("Quantity Available: "+quantityTop);
            modeltext.setText("Model: "+modelTop);
            try {
                if (sizeTop.length==0){
                    linearLayout.setVisibility(View.GONE);
                }
                else{
                    ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(ProductDetailsActivity.this,R.layout.support_simple_spinner_dropdown_item,sizeTop);
                    sizespinner.setAdapter(spinnerAdapter);
                }
            }catch (Exception e){

            }






//            weighttext.setText("Weight: "+weightTop);
//            heighttext.setText("Height: "+heightTop);
//            lengthText.setText("Length: "+lengthTop);
//            widthText.setText("Width: "+widthTop);
        }
        else if (counter.equals("2")){

            Picasso.get().load(image).into(imageView);
            nametext.setText(nameHot);
            Toast.makeText(this, nameHot, Toast.LENGTH_SHORT).show();
            descriptionText.setText("Description: "+metadescriptionHot);
            priceText.setText("Price: "+priceHot);
            stockstatustext.setText("Stock Status: "+stockstatusHot);
            quantityText.setText("Quantity Available: "+quantityHot);
            modeltext.setText("Model: "+modelHot);
            try {
                if (sizeHot.length==0){
                    linearLayout.setVisibility(View.GONE);
                }
                else{
                    // ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(ProductDetailsActivity.this,R.layout.support_simple_spinner_dropdown_item,sizeHot);
                    //  sizespinner.setAdapter(spinnerAdapter);
                }
            }catch (Exception e){

            }

            String[] keyset;


            ArrayList<String>[] values = (ArrayList<String>[])new ArrayList[optionHot.keySet().size()];

           // for (int i=0;i<optionHot.size();i++){
                keyset= (String[]) optionHot.keySet().toArray(new String[optionHot.keySet().size()]);
                Log.d("optionTaggggg", "settingTexts: "+ Objects.requireNonNull(Arrays.toString(keyset)));


            //    for (int j=0;j<keyset.length;j++){

                   // values[i]= (ArrayList<String>) optionHot.get(keyset[j]);
            for(int k=0;k<keyset.length;k++){
                Log.d("optionTaggggg", "settingTexts: "+ Objects.requireNonNull((keyset[k])));
                List<Object> list=new ArrayList<>();
                list.add(optionHot.get(Objects.requireNonNull(keyset[k])));
                        //=  Arrays.asList(optionHot.get(Objects.requireNonNull(keyset[k])));
                Log.d("optionTaggggg", "settingTexts: "+  Arrays.toString((Object[]) optionHot.get(Objects.requireNonNull(keyset[k]))) );

                ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(ProductDetailsActivity.this,R.layout.support_simple_spinner_dropdown_item, (String[]) optionHot.get(Objects.requireNonNull(keyset[0])));
                sizespinner.setAdapter(spinnerAdapter);



                   for(int k1=0;k1< list.size();k1++){
                   // Log.d("optionTaggggg", "settingTexts: "+  Arrays.toString((Object[]) optionHot.get(Objects.requireNonNull(keyset[k]))) );
                   // Log.d("optionTaggggg", "settingTexts: "+  Arrays.asList( optionHot.get(Objects.requireNonNull(keyset[k]))) );
                     //  Log.d("optionTaggggg", "settingTexts: "+list.get(k1));

                }





            }

                   // Log.d("optionTaggggg", "settingTexts: "+ Objects.requireNonNull((String.valueOf(optionHot.get("Shirt size")))));

              //  }

          //  }


//            weighttext.setText("Weight: "+weightHot);
//            heighttext.setText("Height: "+heightHot);
//            lengthText.setText("Length: "+lengthHot);
//            widthText.setText("Width: "+widthHot);
        }
        else if (counter.equals("3")){
            setSliderViews3();
            Picasso.get().load(image).into(imageView);
            nametext.setText(namelatest);
            Toast.makeText(this, namelatest+sizelatest+modellatest, Toast.LENGTH_SHORT).show();
//            Log.d("testingbaal", "settingTexts: "+namelatest+" "+sizelatest[0]+" "+modellatest);
            descriptionText.setText("Description: "+metadescriptionlatest);
            priceText.setText("Price: "+pricelatest);
            stockstatustext.setText("Stock Status: "+stockstatuslatest);
            quantityText.setText("Quantity Available: "+quantitylatest);
            modeltext.setText("Model: "+modellatest);
           try {
               if (sizelatest.length==0){
                   linearLayout.setVisibility(View.GONE);
               }
               else{
                   ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(ProductDetailsActivity.this,R.layout.support_simple_spinner_dropdown_item,sizelatest);
                   sizespinner.setAdapter(spinnerAdapter);
               }
           }catch (Exception e){

           }
//            weighttext.setText("Weight: "+weightlatest);
//            heighttext.setText("Height: "+heightlatest);
//            lengthText.setText("Length: "+lengthlatest);
//            widthText.setText("Width: "+widthlatest);
        }
        else if (counter.equals("4")){

            Picasso.get().load(image).into(imageView);
            nametext.setText(namecatagorywise);
            Toast.makeText(this, namecatagorywise, Toast.LENGTH_SHORT).show();
            descriptionText.setText("Description: "+metadescriptioncatagorywise);
            priceText.setText("Price: "+pricecatagorywise);
            stockstatustext.setText("Stock Status: "+stockstatuscatagorywise);
            quantityText.setText("Quantity Available: "+quantitycatagorywise);
            modeltext.setText("Model: "+modelcatagorywise);
           try {
               if (sizecatagorywise.length==0){
                   linearLayout.setVisibility(View.GONE);
               }
               else{
                   ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(ProductDetailsActivity.this,R.layout.support_simple_spinner_dropdown_item,sizecatagorywise);
                   sizespinner.setAdapter(spinnerAdapter);
               }
           }
           catch (Exception e){

           }
//            weighttext.setText("Weight: "+weightcatagorywise);
//            heighttext.setText("Height: "+heightcatagorywise);
//            lengthText.setText("Length: "+lengthcatagorywise);
//            widthText.setText("Width: "+widthcatagorywise);
        }



    }

    private void setSliderViews1() {


        handler7.post(new Runnable() {
            @Override
            public void run() {

               // imageView.setImageURI(Uri.parse(image));
                int size=imagesTop.length;
                if(size<=0){
//                    SliderView sliderView = new SliderView(getApplicationContext());
//                     // Picasso.get().load(imageTop).into();
//
//                    sliderView.setImageUrl(image);
//                    sliderLayout.addSliderView(sliderView);

                    Glide.with(ProductDetailsActivity.this).load(image).into(img1);


                   // Glide.with(ProductDetailsActivity.this).load(image).into(img1);
                }
                else{

                    for (int i = 0; i < size; i++) {

                        //SliderView sliderView = new SliderView(getApplicationContext());
                        switch (i) {
                            case 0:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[0]);
////                                sliderView.setDescription("Join Ariza");
                                Glide.with(ProductDetailsActivity.this).load(image).into(img1);
                                break;
                            case 1:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[1]);
//                                sliderView.setDescription("Explore Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagesTop[0]).into(img2);
                                break;
                            case 2:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[2]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagesTop[1]).into(img3);
                                break;

                            case 3:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[3]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagesTop[2]).into(img4);
                                break;

//                            case 4:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[4]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 5:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[5]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 6:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[6]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 7:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[7]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 8:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[8]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 9:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesTop[9]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
                        }

//                        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//                        sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
//                            @Override
//                            public void onSliderClick(SliderView sliderView) {
//                               // Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        sliderLayout.addSliderView(sliderView);
                    }

                }
            }
        });




    }

    private void setSliderViews2() {


        handler7.post(new Runnable() {
            @Override
            public void run() {
               // imageView.setImageURI(Uri.parse(image));

                int size=imagesHot.length;
                if(size<=0){
//                    SliderView sliderView = new SliderView(getApplicationContext());
//                    //  Picasso.get().load(image).into((Target) sliderView);
//                    sliderView.setImageUrl(image);
//                    sliderLayout.addSliderView(sliderView);
                    Glide.with(ProductDetailsActivity.this).load(image).into(img1);
                }
                else{
                    for (int i = 0; i < size; i++) {

                       // SliderView sliderView = new SliderView(getApplicationContext());


                        switch (i) {
                            case 0:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[0]);
//                                sliderView.setDescription("Join Ariza");
                                Glide.with(ProductDetailsActivity.this).load(image).into(img1);
                                break;
                            case 1:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[1]);
//                                sliderView.setDescription("Explore Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagesHot[0]).into(img2);

                                break;
                            case 2:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[2]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagesHot[1]).into(img3);

                                break;

                            case 3:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[3]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagesHot[2]).into(img4);

                                break;

//                            case 4:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[4]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 5:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[5]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 6:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[6]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 7:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[7]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 8:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[8]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 9:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagesHot[9]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
                        }

//                        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//                        sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
//                            @Override
//                            public void onSliderClick(SliderView sliderView) {
//                                // Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        sliderLayout.addSliderView(sliderView);
                    }
                }
            }
        });




    }

    private void setSliderViews3() {


        handler7.post(new Runnable() {
            @Override
            public void run() {
               // imageView.setImageURI(Uri.parse(image));
                int size=imageslatest.length;
                if(size<=0){
//                    SliderView sliderView = new SliderView(getApplicationContext());
////                    //  Picasso.get().load(image).into((Target) sliderView);
////                    sliderView.setImageUrl(image);
////                    sliderLayout.addSliderView(sliderView);
                    Glide.with(ProductDetailsActivity.this).load(image).into(img1);

                }
                else{
                    for (int i = 0; i < size; i++) {

                       // SliderView sliderView = new SliderView(getApplicationContext());


                        switch (i) {
                            case 0:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[0]);
//                                sliderView.setDescription("Join Ariza");
                                Glide.with(ProductDetailsActivity.this).load(image).into(img1);

                                break;
                            case 1:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[1]);
//                                sliderView.setDescription("Explore Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imageslatest[0]).into(img2);

                                break;
                            case 2:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[2]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imageslatest[1]).into(img3);
                                break;

                            case 3:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[3]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imageslatest[2]).into(img4);

                                break;

//                            case 4:
////                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[4]);
////                                sliderView.setDescription("Share Ariza");
//                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imageslatest[3]).into(img4);
//
//                                break;
//
//                            case 5:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[5]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 6:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[6]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 7:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[7]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 8:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[8]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 9:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imageslatest[9]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
                        }

//                        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//                        sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
//                            @Override
//                            public void onSliderClick(SliderView sliderView) {
//                                // Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        sliderLayout.addSliderView(sliderView);
                    }
                }
            }
        });




    }

    private void setSliderViews4() {


        handler7.post(new Runnable() {
            @Override
            public void run() {
                //imageView.setImageURI(Uri.parse(image));
                int size=imagescatagorywise.length;
                if(size<=0){
//                    SliderView sliderView = new SliderView(getApplicationContext());
//                    //  Picasso.get().load(image).into((Target) sliderView);
//                    sliderView.setImageUrl(image);
//                    sliderLayout.addSliderView(sliderView);
                    Glide.with(ProductDetailsActivity.this).load(image).into(img1);

                }
                else{
                    for (int i = 0; i < size; i++) {

                       // SliderView sliderView = new SliderView(getApplicationContext());


                        switch (i) {
                            case 0:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[0]);
//                                sliderView.setDescription("Join Ariza");
                                Glide.with(ProductDetailsActivity.this).load(image).into(img1);
                                break;
                            case 1:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[1]);
//                                sliderView.setDescription("Explore Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagescatagorywise[0]).into(img2);

                                break;
                            case 2:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[2]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagescatagorywise[1]).into(img3);

                                break;

                            case 3:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[3]);
//                                sliderView.setDescription("Share Ariza");
                                Glide.with(ProductDetailsActivity.this).load("https://ariza.style/image/"+imagescatagorywise[2]).into(img4);

                                break;

//                            case 4:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[4]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 5:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[5]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 6:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[6]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 7:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[7]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 8:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[8]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
//
//                            case 9:
//                                sliderView.setImageUrl("https://ariza.style/image/"+imagescatagorywise[9]);
//                                sliderView.setDescription("Share Ariza");
//                                break;
                        }

//                        sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//                        sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
//                            @Override
//                            public void onSliderClick(SliderView sliderView) {
//                                // Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        sliderLayout.addSliderView(sliderView);
                    }
                }
            }
        });




    }

//    public void productDetailsQuery(){
//        apolloClient.query(new ProductDetailsQuery()).enqueue(new ApolloCall.Callback<ProductDetailsQuery.Data>() {
//            @Override
//            public void onResponse(@NotNull Response<ProductDetailsQuery.Data> response) {
//                handler7.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        assert response.data() != null;
//                        int size= Objects.requireNonNull(response.data().products()).size();
//                        int sizeofImages = 0;
//
//                        int i=0;
//
//                        do {
//                            sizeofImages =response.data().products().get(i).images().size();
//                            imges=new String[sizeofImages];
//                            for (int i1=0;i1<sizeofImages;i1++){
//                                String img=response.data().products().get(i).images().get(i1).image();
//                                imges[i1]="https://ariza.style/image/"+img;
//                            }
//
//                            String productImage=response.data().products().get(i).image();
//                            String productName=response.data().products().get(i).name();
//                            String productdesc=response.data().products().get(i).meta_description();
//                            String productprice=response.data().products().get(i).price();
//                            String productstock=response.data().products().get(i).stock_status();
//                            String productquantity=response.data().products().get(i).quantity();
//                            String productweight=response.data().products().get(i).weight();
//                            String productheight=response.data().products().get(i).height().toString();
//                            String productLength=response.data().products().get(i).length().toString();
//                            String productWidth=response.data().products().get(i).width().toString();
//
//                            nametext.setText(productName);
////                            descriptionText.setText(productdesc);
////                            priceText.setText(productprice);
////                            stockstatustext.setText(productstock);
////                            quantityText.setText(productquantity);
////                            weighttext.setText(productweight);
////                            heighttext.setText(productheight);
////                            lengthText.setText(productLength);
////                            widthText.setText(productWidth);
////                            image="https://ariza.style/image/"+productImage;
//                        }while (response.data().products().get(i).product_id().equals(productID));
//
//                        for (int i=0;i<size;i++){
//                            String productIDFetched=response.data().products().get(i).product_id();
//
//                         //  if(productIDFetched==productID){
//
//                          //  }
//
//
//
//                        }
//
//                        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//                        sliderLayout.setScrollTimeInSec(10); //set scroll delay in seconds :
//                        setSliderViews();
//                    }
//                },3000);
//            }
//
//            @Override
//            public void onFailure(@NotNull ApolloException e) {
//
//            }
//        });
//    }

//    private void colorRecyclerViewInIt() {
//        colorlist=new ArrayList<>();
//        for(int i=0;i<colorNames.length;i++){
//            ColorListModel colorListModel=new ColorListModel(colorNames[i],1);
//            colorlist.add(colorListModel);
//        }
//        LinearLayoutManager layoutManager=new LinearLayoutManager(ProductDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false);
//        colorRecyclerView.setLayoutManager(layoutManager);
//        colorRecyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        colorListAdapter=new ColorListAdapter(ProductDetailsActivity.this,colorlist);
//
//        colorRecyclerView.setAdapter(colorListAdapter);
//    }

//    private void sizeListRecyclerViewInIT() {
//        sizelist=new ArrayList<>();
//        for(int i=0;i<sizeNames.length;i++){
//            SizeListModel sizeListModel=new SizeListModel(sizeNames[i]);
//            sizelist.add(sizeListModel);
//        }
//        LinearLayoutManager layoutManager=new LinearLayoutManager(ProductDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false);
//        sizeRecyclerView.setLayoutManager(layoutManager);
//        sizeRecyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        sizeListAdapter=new SizeListAdapter(ProductDetailsActivity.this,sizelist);
//
//        sizeRecyclerView.setAdapter(sizeListAdapter);
//    }
}
