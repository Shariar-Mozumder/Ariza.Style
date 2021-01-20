package com.example.shariarspc.ariza_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.response.CustomTypeAdapter;
import com.apollographql.apollo.response.CustomTypeValue;
import com.example.BestsellerProductsQuery;
import com.example.CatagoryDetailsQuery;
import com.example.HotsellQuery;
import com.example.LatestProductQuery;
import com.example.shariarspc.ariza_app.Cart.CartProductShow;
import com.example.shariarspc.ariza_app.Catagories.CustomAdapter;
import com.example.shariarspc.ariza_app.HotSell.HotsellAdapter;
import com.example.shariarspc.ariza_app.HotSell.HotsellModel;
import com.example.shariarspc.ariza_app.IndividualProductDetails.ProductDetailsActivity;
import com.example.shariarspc.ariza_app.LatestProducts.GridRecyclerViewAdapter;
import com.example.shariarspc.ariza_app.LatestProducts.LatestProductsModel;
import com.example.shariarspc.ariza_app.LatestProducts.RecyclerViewAdapter;
import com.example.shariarspc.ariza_app.Login_Register.LoginActivity;
import com.example.shariarspc.ariza_app.Login_Register.RegisterActivity;
import com.example.shariarspc.ariza_app.TopSell.TopsellAdapter;
import com.example.shariarspc.ariza_app.TopSell.TopsellModel;
import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity implements TopsellAdapter.OnNoteListener,RecyclerViewAdapter.OnNoteListenerlatest,HotsellAdapter.OnNoteListenerHot, NavigationView.OnNavigationItemSelectedListener {

     ApolloClient apolloClient;

    SliderLayout sliderLayout;

    ImageButton gridBtn, listBtn;

    ProgressBar progressBar;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    Handler handler = new Handler();
    Handler handler1 = new Handler();
    Handler handler2 = new Handler();
    Handler handler3 = new Handler();
    Handler handler4 = new Handler();
    Handler handler5 = new Handler();
    Handler handler6 = new Handler();

    ProgressDialog progressDialog,intialProgress;
    int totallatestProductCount = 10;

    RecyclerViewAdapter recyclerViewAdapter;
    GridRecyclerViewAdapter gridRecyclerViewAdapter;

    GridView gridView;
    RecyclerView recyclerView_top_sell, recyclerView_hot_sell, recyclerView_latest_products, gridview_latest_product;
    TopsellAdapter topsellAdapter;
    HotsellAdapter hotsellAdapter;

    LatestProductsModel latestProductsModel;


    int[] catagory_imgs = {R.drawable.accessories, R.drawable.beauty, R.drawable.footware,
            R.drawable.selwar_kameez, R.drawable.sharee, R.drawable.western,
            R.drawable.pant, R.drawable.men_care, R.drawable.seemore};

    int[] latest_imgs = {R.drawable.accessories, R.drawable.beauty, R.drawable.footware,
            R.drawable.selwar_kameez, R.drawable.sharee, R.drawable.western,
            R.drawable.pant, R.drawable.men_care, R.drawable.seemore,
            R.drawable.accessories, R.drawable.beauty, R.drawable.footware,
            R.drawable.selwar_kameez, R.drawable.sharee, R.drawable.western,
            R.drawable.pant, R.drawable.men_care, R.drawable.seemore,
            R.drawable.accessories, R.drawable.beauty, R.drawable.footware,
            R.drawable.selwar_kameez, R.drawable.sharee, R.drawable.western,
            R.drawable.pant, R.drawable.men_care, R.drawable.seemore,
            R.drawable.accessories, R.drawable.beauty, R.drawable.footware,
            R.drawable.selwar_kameez, R.drawable.sharee, R.drawable.western,
            R.drawable.pant, R.drawable.men_care};


    String[] catagory_names, latest_products_names;
    static String[] catagoryNamesFatches;
    static String[] catagoryImageFatches;
    static String[] catagoryIDFatches;

    static String[] TopImageFatches;
    static String[] TopIDFatches;
    static String[] TopNameFatches;
    static String[] TopPriceFatches;
    static String[][] TopimagesFatches;
    static String[] TopmetadescriptionFatches;
    static String[] TopstockstatusFatches;
    static String[] TopquantityFatches;
    static String[] TopdescriptionFatches;
    static String[] TopweightFatches;
    static String[] ToplengthFatches;
    static String[] TopwidthFatches;
    static String[] TopheightFatches;


    static String[] HotImageFatches;
    static String[] HotNameFatches;
    static String[] HotIDFatches;
    static String[] HotPriceFatches;
    static String[][] HotimagesFatches;
    static String[] HotmetadescriptionFatches;
    static String[] HotstockstatusFatches;
    static String[] HotquantityFatches;
    static String[] HotdescriptionFatches;
    static String[] HotweightFatches;
    static String[] HotlengthFatches;
    static String[] HotwidthFatches;
    static String[] HotheightFatches;

    static String[] latestNameFatches;
    static String[] latestIDFatches;
    static String[] latestimageFatches;
    static String[] latestDescriptionFatches;
    static String[] latestPriceFatches;
    static String[][] latestimagesFatches;
    static String[] latestdefault;
    static String[] latestmetadescriptionFatches;
    static String[] lateststockstatusFatches;
    static String[] latestquantityFatches;
    static String[] latestdescriptionFatches;
    static String[] latestweightFatches;
    static String[] latestlengthFatches;
    static String[] latestwidthFatches;
    static String[] latestheightFatches;



    ArrayList<TopsellModel> topList;
    ArrayList<HotsellModel> hotList;
    //newme
    ArrayList<LatestProductsModel> datalist1 = new ArrayList<>();

    // ArrayList<LatestProductsModel> dataList;
    //oldme
    //ArrayList<String> dataList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    GridLayoutManager gridlayoutManager;
    LinearLayoutManager layoutManagerTop;


    private boolean isLoading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        drawerLayout=findViewById(R.id.drawerID);
        NavigationView navigationView=findViewById(R.id.navigationViewID);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.profileMenuID){
                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                else if (item.getItemId()==R.id.cartMenuID){
                    Intent intent=new Intent(MainActivity.this, CartProductShow.class);
                    startActivity(intent);
                }
                else if(item.getItemId()==R.id.signUpMenuID){
                    Intent intent=new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        toggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        intialProgress=new ProgressDialog(MainActivity.this);
        intialProgress.show();
        intialProgress.setContentView(R.layout.intialprogress);
        intialProgress.setCancelable(false);
        Objects.requireNonNull(Objects.requireNonNull(intialProgress.getWindow())).setBackgroundDrawableResource(android.R.color.transparent);
        //progressdialogshow();
        handler6.postDelayed(new Runnable() {
            @Override
            public void run() {

                intialProgress.dismiss();

            }
        },7000);





        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.US);
        CustomTypeAdapter dateCustomTypeAdapter = new CustomTypeAdapter<Date>() {
            @Override
            public Date decode(CustomTypeValue value) {
                try {
                    return dateFormat.parse(value.value.toString());
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public CustomTypeValue encode(Date value) {
                return new CustomTypeValue.GraphQLString(dateFormat.format(value));
            }
        };
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        apolloClient = ApolloClient.builder()
                .serverUrl("https://ariza.style/index.php?route=api/graphql/usage")
                .okHttpClient(client)
                .build();



// enqueue query






//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                catagoryquery();
//            for(int i=0;i<9;i++) {
//               Log.d("Apollo", "onResponse: " + catagoryNamesFatches[i]);
//           }
//            }
//        });


//        AsyncTaskRunner runner = new AsyncTaskRunner();
//        // String sleepTime = time.getText().toString();
//        runner.execute("5");


        gridBtn = findViewById(R.id.gridbtnID);
        listBtn = findViewById(R.id.listbtnID);

        // dataList = new ArrayList<>();
        progressDialog=new ProgressDialog(MainActivity.this);

//        progressDialog.setTitle("Fetching Top Sells");
//        progressDialog.setCancelable(false);
//        progressDialog.show();


        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :

        setSliderViews();

        recyclerView_top_sell = findViewById(R.id.top_sell_recyclerView);
        recyclerView_hot_sell = findViewById(R.id.hot_sell_recyclerView);
        recyclerView_latest_products = findViewById(R.id.latest_products_recyclerView);


        catagory_names = getResources().getStringArray(R.array.Catagory_names);
        latest_products_names = getResources().getStringArray(R.array.latest_names);


        gridView = findViewById(R.id.catagorygrid_id);



//        for(int i=0;i<9;i++) {
//            Log.d("Apollo", "onResponse: " + catagoryNamesFatches[i]);
//        }




        catagoryquery();
      //  latestProductqueryList1();
        topSellProductQuery();
        hotsellQuery();

        //catagoryquery();
       // hotlistInIt();

       // loadLIST();

       // latestProductListShow();
        latestProductqueryList();
       // intialProgress.dismiss();


        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gridBtn.setVisibility(View.VISIBLE);
                latestProductqueryList();
                listBtn.setVisibility(View.INVISIBLE);
            }
        });
        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBtn.setVisibility(View.VISIBLE);

                latestProductqueryGrid();
                gridBtn.setVisibility(View.INVISIBLE);
            }
        });


        // latestRecyclerListInIT();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.cart_menu_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        if(item.getItemId()==R.id.cartbtn){
            Intent intent=new Intent(MainActivity.this, CartProductShow.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        if(item.getItemId()==R.id.cartMenuID){
            intent=new Intent(MainActivity.this, CartProductShow.class);
            startActivity(intent);
            return  true;
        }
        return false;
    }


    //    private void progressdialogshow() {
//       // progressBar = (ProgressBar)findViewById(R.id.spin_kit);
//        Sprite doubleBounce = new FadingCircle();
//        //doubleBounce.setColor(R.color.cardview_dark_background);
//        progressBar.setIndeterminateDrawable(doubleBounce);
//       // progressBar.setCameraDistance((float) 1.5);
//    }

    public void catagoryquery() {
        apolloClient.query(new CatagoryDetailsQuery())
                .enqueue(new ApolloCall.Callback<CatagoryDetailsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<CatagoryDetailsQuery.Data> response) {
                        //Log.e("Apollo", "Launch site: " + response.getData().launch.site);

                        handler1.postDelayed(new Runnable() {
                            @Override
                            public void run() {


                               // Log.d("Apollo", "onResponse: " + response.data().categories().size());
                                catagoryNamesFatches = new String[response.data().categories().size()];
                                catagoryImageFatches=new String[response.data().categories().size()];
                                catagoryIDFatches=new String[response.data().categories().size()];
                                for (int i = 0; i < response.data().categories().size(); i++) {
                                    String cats = (Objects.requireNonNull(response.data().categories()).get(i).name());
                                    String catImages= Objects.requireNonNull(response.data().categories()).get(i).image();
                                    String catID= Objects.requireNonNull(response.data().categories()).get(i).category_id();

                                    catagoryNamesFatches[i] = cats;
                                    catagoryImageFatches[i]="https://ariza.style/image/"+catImages;
                                    catagoryIDFatches[i]=catID;

                                   // getBitmapFromURL(catagoryImageFatches[i]);

//                                    TopsellModel topsellModel = new TopsellModel(catagory_imgs[i], catagoryNamesFatches[i]);
//                                    topList.add(topsellModel);



                                   // Log.d("Apollo", "onResponse: " + catagoryNamesFatches[i]);
                                  //  Toast.makeText(MainActivity.this, catagoryNamesFatches[i], Toast.LENGTH_SHORT).show();

                                }


                                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), catagoryImageFatches, catagoryNamesFatches,catagoryIDFatches);
                                gridView.setAdapter(adapter);


                            }

                        },3000);



                        //                     for(int i=0;i<9;i++) {
//                            //String cats= Objects.requireNonNull(response.data().categories().get(i).name());
//                            String catImages=response.data().categories().get(i).image();
//                            //  Log.d("Apollo", "onResponse: "+cats);
//                            Log.d("Apollo", "onResponse: Images:"+catImages);
                        //                       }




                       // progressDialog.dismiss();

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e("Apollo", "Error", e);
                    }
                });

    }

    public void hotsellQuery(){
        apolloClient.query(new HotsellQuery())
                .enqueue(new ApolloCall.Callback<HotsellQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<HotsellQuery.Data> response) {
                        handler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                assert response.data() != null;
                                HotNameFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotImageFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotPriceFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotIDFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotimagesFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()][10];
                                HotmetadescriptionFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotstockstatusFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotquantityFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotdescriptionFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotweightFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotlengthFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotwidthFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];
                                HotheightFatches=new String[Objects.requireNonNull(response.data().popularProducts()).size()];

                                for(int i = 0; i< Objects.requireNonNull(response.data().popularProducts()).size(); i++){
                                    String hotNames= Objects.requireNonNull(response.data().popularProducts()).get(i).name();
                                    String hotImages= Objects.requireNonNull(response.data().popularProducts()).get(i).image();
                                    String hotPrice= Objects.requireNonNull(response.data().popularProducts()).get(i).price();
                                    String hotID= Objects.requireNonNull(response.data().popularProducts()).get(i).product_id();
                                    int size= Objects.requireNonNull(Objects.requireNonNull(response.data().popularProducts()).get(i).images()).size();
                                    String[] hotimages = new String[size];
                                    for (int i1=0;i1<size;i1++){

                                        hotimages[i1]= String.valueOf(Objects.requireNonNull(response.data().popularProducts()).get(i).images());
                                    }

                                    String hotmetadescription= Objects.requireNonNull(response.data().popularProducts()).get(i).meta_description();
                                    String hotstock= Objects.requireNonNull(response.data().popularProducts()).get(i).stock_status();
                                    String hotquantity= Objects.requireNonNull(response.data().popularProducts()).get(i).quantity();
                                    String hotdescription= Objects.requireNonNull(response.data().popularProducts()).get(i).description();
                                    String hotweight= Objects.requireNonNull(response.data().popularProducts()).get(i).weight();
                                    String hotlength= Objects.requireNonNull(response.data().popularProducts()).get(i).length().toString();
                                    String hotwidth= Objects.requireNonNull(response.data().popularProducts()).get(i).width().toString();
                                    String hotheight= Objects.requireNonNull(response.data().popularProducts()).get(i).height().toString();

                                    HotNameFatches[i]=hotNames;
                                    HotImageFatches[i]="https://ariza.style/image/"+hotImages;
                                    HotPriceFatches[i]=hotPrice+" BDT";
                                    HotIDFatches[i]=hotID;
                                    HotimagesFatches[i]=hotimages;
                                    HotmetadescriptionFatches[i]=hotmetadescription;
                                    HotstockstatusFatches[i]=hotstock;
                                    HotquantityFatches[i]=hotquantity;
                                    HotdescriptionFatches[i]=hotdescription;
                                    HotweightFatches[i]=hotweight;
                                    HotlengthFatches[i]=hotlength;
                                    HotwidthFatches[i]=hotwidth;
                                    HotheightFatches[i]=hotheight;
                                }
                                hotlistInIt();
                            }
                        },3000);

                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
    }

    public void topSellProductQuery(){
        apolloClient.query(new BestsellerProductsQuery())
                .enqueue(new ApolloCall.Callback<BestsellerProductsQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<BestsellerProductsQuery.Data> response) {
                        handler2.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                assert response.data() != null;
                                TopNameFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopImageFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopPriceFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopIDFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopimagesFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()][10];
                                TopmetadescriptionFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopstockstatusFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopquantityFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopdescriptionFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopweightFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                ToplengthFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopwidthFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];
                                TopheightFatches=new String[Objects.requireNonNull(response.data().bestsellerProducts()).size()];

                                for(int i = 0; i< Objects.requireNonNull(response.data().bestsellerProducts()).size(); i++){
                                    String topNames= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).name();
                                    String topImages= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).image();
                                    String topprice= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).price();
                                    String topID= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).product_id();
                                    //String[] topimages= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).images();
                                    int size= Objects.requireNonNull(Objects.requireNonNull(response.data().bestsellerProducts()).get(i).images()).size();
                                    String[] topimages = new String[size];
                                    for (int i1=0;i1<size;i1++){

                                        topimages[i1]= String.valueOf(Objects.requireNonNull(Objects.requireNonNull(response.data().bestsellerProducts()).get(i).images()).get(i1).image());
                                    }
                                    String toptmetadescription= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).meta_description();
                                    String topstock= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).stock_status();
                                    String topquantity= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).quantity();
                                    String topdescription= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).description();
                                    String topweight= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).weight();
                                    String toplength= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).length().toString();
                                    String topwidth= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).width().toString();
                                    String topheight= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).height().toString();

                                    TopNameFatches[i]=topNames;
                                    TopImageFatches[i]="https://ariza.style/image/"+topImages;
                                    TopPriceFatches[i]=topprice+" BDT";
                                    TopIDFatches[i]=topID;
                                    TopimagesFatches[i]=topimages;
                                    TopmetadescriptionFatches[i]=toptmetadescription;
                                    TopstockstatusFatches[i]=topstock;
                                    TopquantityFatches[i]=topquantity;
                                    TopdescriptionFatches[i]=topdescription;
                                    TopweightFatches[i]=topweight;
                                    ToplengthFatches[i]=toplength;
                                    TopwidthFatches[i]=topwidth;
                                    TopheightFatches[i]=topheight;
                                }
                                toplistInIt();

                            }
                        },3000);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
    }

    public void latestProductqueryList(){
        apolloClient.query(new LatestProductQuery())
                .enqueue(new ApolloCall.Callback<LatestProductQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<LatestProductQuery.Data> response) {
                        handler4.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                assert response.data() != null;
                                latestNameFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestimageFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestPriceFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestDescriptionFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestIDFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestimagesFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()][10];
                                latestmetadescriptionFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                lateststockstatusFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestquantityFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestdescriptionFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestweightFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestlengthFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestwidthFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestheightFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                              //  TopPriceFatches=new String[response.data().bestsellerProducts().size()];

                                for(int i = 0; i< Objects.requireNonNull(response.data().latestProducts()).size(); i++){
                                    String latestNames= Objects.requireNonNull(response.data().latestProducts()).get(i).name();
                                    String latestImages= Objects.requireNonNull(response.data().latestProducts()).get(i).image();
                                    String latestPrice= Objects.requireNonNull(response.data().latestProducts()).get(i).price();
                                    String latestdescription= Objects.requireNonNull(response.data().latestProducts()).get(i).meta_description();
                                    String latestID= Objects.requireNonNull(response.data().latestProducts()).get(i).meta_description();
                                    //String[] latestimages= Objects.requireNonNull(response.data().latestProducts()).get(i).images();
                                    int size= Objects.requireNonNull(response.data().latestProducts().get(i).images()).size();
                                    String[] latestimages = new String[size];
                                    for (int i1=0;i1<size;i1++){

                                        latestimages[i1]= String.valueOf(Objects.requireNonNull(Objects.requireNonNull(response.data().latestProducts()).get(i).images()).get(i1).image());
                                    }
                                    String latesttmetadescription= Objects.requireNonNull(response.data().latestProducts()).get(i).meta_description();
                                    String lateststock= Objects.requireNonNull(response.data().latestProducts()).get(i).stock_status();
                                    String latestquantity= Objects.requireNonNull(response.data().latestProducts()).get(i).quantity();
                                    String latestweight= Objects.requireNonNull(response.data().latestProducts()).get(i).weight();
                                    String latestlength= Objects.requireNonNull(response.data().latestProducts()).get(i).length().toString();
                                    String latestwidth= Objects.requireNonNull(response.data().latestProducts()).get(i).width().toString();
                                    String latestheight= Objects.requireNonNull(response.data().latestProducts()).get(i).height().toString();
                                   // String topprice= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).price();

                                    latestNameFatches[i]=latestNames;
                                    latestimageFatches[i]="https://ariza.style/image/"+latestImages;
                                    latestPriceFatches[i]=latestPrice+" BDT";
                                    latestDescriptionFatches[i]=latestdescription;
                                    latestIDFatches[i]=latestID;
                                    latestimagesFatches[i]=latestimages;
                                    latestmetadescriptionFatches[i]=latesttmetadescription;
                                    lateststockstatusFatches[i]=lateststock;
                                    latestquantityFatches[i]=latestquantity;
                                    latestweightFatches[i]=latestweight;
                                    latestlengthFatches[i]=latestlength;
                                    latestwidthFatches[i]=latestwidth;
                                    latestheightFatches[i]=latestheight;
                                   // TopPriceFatches[i]=topprice+" BDT";
                                }
                                loadLIST();
                                latestProductListShow();



                            }
                        },3000);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
    }


//    public void latestProductqueryList1(){
//        apolloClient.query(new LatestProductQuery())
//                .enqueue(new ApolloCall.Callback<LatestProductQuery.Data>() {
//                    @Override
//                    public void onResponse(@NotNull Response<LatestProductQuery.Data> response) {
//                        handler6.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                assert response.data() != null;
//                                latestNameFatches=new String[response.data().latestProducts().size()];
//                                latestimageFatches=new String[response.data().latestProducts().size()];
//                                latestPriceFatches=new String[response.data().latestProducts().size()];
//                                latestDescriptionFatches=new String[response.data().latestProducts().size()];
//                                //  TopPriceFatches=new String[response.data().bestsellerProducts().size()];
//
//                                for(int i = 0; i< 10; i++){
//                                    String latestNames= Objects.requireNonNull(response.data().latestProducts()).get(i).name();
//                                    String latestImages= Objects.requireNonNull(response.data().latestProducts()).get(i).image();
//                                    String latestPrice= Objects.requireNonNull(response.data().latestProducts()).get(i).price();
//                                    String latestdescription= Objects.requireNonNull(response.data().latestProducts()).get(i).meta_description();
//                                    // String topprice= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).price();
//
//                                    latestNameFatches[i]=latestNames;
//                                    latestimageFatches[i]="https://ariza.style/image/"+latestImages;
//                                    latestPriceFatches[i]=latestPrice+" BDT";
//                                    latestDescriptionFatches[i]=latestdescription;
//                                    // TopPriceFatches[i]=topprice+" BDT";
//                                }
//                                loadLIST();
//                               // latestProductListShow();
//
//
//                            }
//                        },5000);
//                    }
//
//                    @Override
//                    public void onFailure(@NotNull ApolloException e) {
//
//                    }
//                });
    //}

    public void latestProductqueryGrid(){
        apolloClient.query(new LatestProductQuery())
                .enqueue(new ApolloCall.Callback<LatestProductQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<LatestProductQuery.Data> response) {
                        handler5.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                assert response.data() != null;
                                latestNameFatches=new String[response.data().latestProducts().size()];
                                latestimageFatches=new String[response.data().latestProducts().size()];
                                latestPriceFatches=new String[response.data().latestProducts().size()];
                                latestDescriptionFatches=new String[response.data().latestProducts().size()];
                                latestimagesFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()][10];
                                latestmetadescriptionFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                lateststockstatusFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestquantityFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestdescriptionFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestweightFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestlengthFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestwidthFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                latestheightFatches=new String[Objects.requireNonNull(response.data().latestProducts()).size()];
                                //  TopPriceFatches=new String[response.data().bestsellerProducts().size()];

                                for(int i = 0; i< Objects.requireNonNull(response.data().latestProducts()).size(); i++){
                                    String latestNames= Objects.requireNonNull(response.data().latestProducts()).get(i).name();
                                    String latestImages= Objects.requireNonNull(response.data().latestProducts()).get(i).image();
                                    String latestPrice= Objects.requireNonNull(response.data().latestProducts()).get(i).price();
                                    String latestdescription= Objects.requireNonNull(response.data().latestProducts()).get(i).meta_description();
                                  //  String[] latestimages= Objects.requireNonNull(response.data().latestProducts()).get(i).images();

                                    int size=response.data().latestProducts().get(i).images().size();
                                    String[] latestimages = new String[size];
                                    for (int i1=0;i1<size;i1++){

                                        latestimages[i1]= String.valueOf(Objects.requireNonNull(Objects.requireNonNull(response.data().latestProducts()).get(i).images()).get(i1).image());
                                    }
                                    String latesttmetadescription= Objects.requireNonNull(response.data().latestProducts()).get(i).meta_description();
                                    String lateststock= Objects.requireNonNull(response.data().latestProducts()).get(i).stock_status();
                                    String latestquantity= Objects.requireNonNull(response.data().latestProducts()).get(i).quantity();
                                    String latestweight= Objects.requireNonNull(response.data().latestProducts()).get(i).weight();
                                    String latestlength= Objects.requireNonNull(response.data().latestProducts()).get(i).length().toString();
                                    String latestwidth= Objects.requireNonNull(response.data().latestProducts()).get(i).width().toString();
                                    String latestheight= Objects.requireNonNull(response.data().latestProducts()).get(i).height().toString();
                                    // String topprice= Objects.requireNonNull(response.data().bestsellerProducts()).get(i).price();

                                    latestNameFatches[i]=latestNames;
                                    latestimageFatches[i]="https://ariza.style/image/"+latestImages;
                                    latestPriceFatches[i]=latestPrice+" BDT";
                                    latestDescriptionFatches[i]=latestdescription;
                                    latestimagesFatches[i]=latestimages;
                                    latestmetadescriptionFatches[i]=latesttmetadescription;
                                    lateststockstatusFatches[i]=lateststock;
                                    latestquantityFatches[i]=latestquantity;
                                    latestweightFatches[i]=latestweight;
                                    latestlengthFatches[i]=latestlength;
                                    latestwidthFatches[i]=latestwidth;
                                    latestheightFatches[i]=latestheight;
                                    // TopPriceFatches[i]=topprice+" BDT";
                                }
                               // loadLIST();
                                latestProductGridshow();

                            }
                        },3000);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
    }

    private void latestProductListShow() {


        layoutManager = new LinearLayoutManager(MainActivity.this);
        //layoutManager = new GridLayoutManager(MainActivity.this,2);
        recyclerView_latest_products.setLayoutManager(layoutManager);
        //newme
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, datalist1,this);
        //oldme
        //recyclerViewAdapter = new RecyclerViewAdapter(dataList);
        recyclerView_latest_products.setAdapter(recyclerViewAdapter);
        addScrollerListenerLIST();
    }

    private void latestProductGridshow() {

        //loadLIST();
        //layoutManager = new LinearLayoutManager(MainActivity.this);
        gridlayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView_latest_products.setLayoutManager(gridlayoutManager);
        //oldme
        //recyclerViewAdapter = new RecyclerViewAdapter(dataList);
        //newme
        gridRecyclerViewAdapter = new GridRecyclerViewAdapter(MainActivity.this, datalist1);
        recyclerView_latest_products.setAdapter(gridRecyclerViewAdapter);
        addScrollerListenerLIST();
    }


    private void setSliderViews() {

        for (int i = 0; i <= 2; i++) {

            SliderView sliderView = new SliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.ariza);
                    sliderView.setDescription("Join Ariza");
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.ariza3);
                    sliderView.setDescription("Explore Ariza");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.ariza2);
                    sliderView.setDescription("Share Ariza");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            // sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }


    private void toplistInIt() {


        topList = new ArrayList<>();

//        for (int i = 0; i < catagory_names.length; i++) {
//            TopsellModel topsellModel = new TopsellModel(catagory_imgs[i], catagory_names[i]);
//            topList.add(topsellModel);
//        }



        for(int i=0;i<TopNameFatches.length;i++){
            TopsellModel topsellModel = new TopsellModel(TopImageFatches[i], TopNameFatches[i],TopPriceFatches[i],TopIDFatches[i],TopimagesFatches[i],TopmetadescriptionFatches[i],TopstockstatusFatches[i],TopquantityFatches[i],TopdescriptionFatches[i],TopweightFatches[i],ToplengthFatches[i],TopwidthFatches[i],TopheightFatches[i]);
            topList.add(topsellModel);
             // Toast.makeText(MainActivity.this, catagoryNamesFatches[i] +" Hello", Toast.LENGTH_SHORT).show();
        }

        layoutManagerTop= new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_top_sell.setLayoutManager(layoutManagerTop);
        recyclerView_top_sell.setItemAnimator(new DefaultItemAnimator());

        topsellAdapter = new TopsellAdapter(MainActivity.this, topList,this);
        recyclerView_top_sell.setAdapter(topsellAdapter);



    }

    private void hotlistInIt() {
        hotList = new ArrayList<>();
        for (int i = 0; i < HotNameFatches.length; i++) {
            HotsellModel hotsellModel = new HotsellModel(HotImageFatches[i], HotNameFatches[i],HotPriceFatches[i],HotIDFatches[i],HotimagesFatches[i],HotmetadescriptionFatches[i],HotstockstatusFatches[i],HotquantityFatches[i],HotdescriptionFatches[i],HotweightFatches[i],HotlengthFatches[i],HotwidthFatches[i],HotheightFatches[i]);
            hotList.add(hotsellModel);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_hot_sell.setLayoutManager(layoutManager);
        recyclerView_hot_sell.setItemAnimator(new DefaultItemAnimator());

        hotsellAdapter = new HotsellAdapter(MainActivity.this, hotList,this);
        recyclerView_hot_sell.setAdapter(hotsellAdapter);
    }


    private void addScrollerListenerLIST() {
        //attaches scrollListener with RecyclerView
        recyclerView_latest_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoading) {
                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                    //It checks, fully visible view is the last one.
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == datalist1.size() - 1) {

                        loadMoreLIST();

                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMoreLIST() {


        //notify adapter using Handler.post() or RecyclerView.post()
        handler.post(new Runnable() {
            @Override
            public void run() {

                LatestProductsModel latestProductsModel = new LatestProductsModel("Loading", "load","default","default","default",latestdefault,"default","default","default","default","default","default","default");
                datalist1.add(latestProductsModel);
                recyclerViewAdapter.notifyItemInserted(datalist1.size() - 1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //removes "load".
                datalist1.remove(datalist1.size() - 1);
                int listSize = datalist1.size();
                recyclerViewAdapter.notifyItemRemoved(listSize);
                //sets next limit

                int nextLimit = listSize + 10;

                for (int itemCount = listSize; itemCount < nextLimit; itemCount++) {
                    //oldme
                    //dataList.add("Item No "+ itemCount);
                    //newme
                    try {
                        latestProductsModel = new LatestProductsModel(latestimageFatches[itemCount], latestNameFatches[itemCount],latestPriceFatches[itemCount],latestDescriptionFatches[itemCount],latestIDFatches[itemCount],latestimagesFatches[itemCount],latestmetadescriptionFatches[itemCount],lateststockstatusFatches[itemCount],latestquantityFatches[itemCount],latestweightFatches[itemCount],latestlengthFatches[itemCount],latestwidthFatches[itemCount],latestheightFatches[itemCount]);
                        datalist1.add(latestProductsModel);
                        totallatestProductCount++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                        // Toast.makeText(MainActivity.this, "Data out of Bound", Toast.LENGTH_SHORT).show();
                    }


                }


                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;

            }
        }, 3000);


    }


    private void loadLIST() {
        for (int i = 0; i < 10; i++) {
            //oldme
            // dataList.add("Item No: "+i);
            //newme
            latestProductsModel = new LatestProductsModel(latestimageFatches[i], latestNameFatches[i],latestPriceFatches[i],latestDescriptionFatches[i],latestIDFatches[i],latestimagesFatches[i],latestmetadescriptionFatches[i],lateststockstatusFatches[i],latestquantityFatches[i],latestweightFatches[i],latestlengthFatches[i],latestwidthFatches[i],latestheightFatches[i]);
            datalist1.add(latestProductsModel);
        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent=new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("productID",TopIDFatches[position]);
        intent.putExtra("images",TopimagesFatches[position]);
        intent.putExtra("metadescription",TopmetadescriptionFatches[position]);
        intent.putExtra("price",TopPriceFatches[position]);
        intent.putExtra("name",TopNameFatches[position]);
        intent.putExtra("stockstatus",TopstockstatusFatches[position]);
        intent.putExtra("quantity",TopquantityFatches[position]);
        intent.putExtra("description",TopdescriptionFatches[position]);
        intent.putExtra("weight",TopweightFatches[position]);
        intent.putExtra("length",ToplengthFatches[position]);
        intent.putExtra("width",TopwidthFatches[position]);
        intent.putExtra("height",TopheightFatches[position]);
        intent.putExtra("image",TopImageFatches[position]);
        intent.putExtra("counter","1");
        this.startActivity(intent);
    }

    @Override
    public void onNoteClicklatest(int position) {
            Intent intent=new Intent(this,ProductDetailsActivity.class);
                    intent.putExtra("productID",latestIDFatches[position]);
                            intent.putExtra("images",latestimagesFatches[position]);
                            intent.putExtra("metadescription",latestmetadescriptionFatches[position]);
                            intent.putExtra("price",latestPriceFatches[position]);
                            intent.putExtra("name",latestNameFatches[position]);
                            intent.putExtra("stockstatus",lateststockstatusFatches[position]);
                            intent.putExtra("quantity",latestquantityFatches[position]);
                            intent.putExtra("description",latestdescriptionFatches[position]);
                            intent.putExtra("weight",latestweightFatches[position]);
                            intent.putExtra("length",latestlengthFatches[position]);
                            intent.putExtra("width",latestwidthFatches[position]);
                            intent.putExtra("height",latestheightFatches[position]);
                            intent.putExtra("image",latestimageFatches[position]);
                            intent.putExtra("counter","3");
                            this.startActivity(intent);
    }

    @Override
    public void onNoteClickhot(int position) {
            Intent intent=new Intent(this,ProductDetailsActivity.class);
                    intent.putExtra("productID",HotIDFatches[position]);
                            intent.putExtra("images",HotimagesFatches[position]);
                            intent.putExtra("metadescription",HotmetadescriptionFatches[position]);
                            intent.putExtra("price",HotPriceFatches[position]);
                            intent.putExtra("name",HotNameFatches[position]);
                            intent.putExtra("stockstatus",HotstockstatusFatches[position]);
                            intent.putExtra("quantity",HotquantityFatches[position]);
                            intent.putExtra("description",HotdescriptionFatches[position]);
                            intent.putExtra("weight",HotweightFatches[position]);
                            intent.putExtra("length",HotlengthFatches[position]);
                            intent.putExtra("width",HotwidthFatches[position]);
                            intent.putExtra("height",HotheightFatches[position]);
                            intent.putExtra("image",HotImageFatches[position]);
                            intent.putExtra("counter","2");
                            this.startActivity(intent);
    }




//    public static Bitmap getBitmapFromURL(String src) {
//        try {
//            Log.e("src",src);
//            URL url = new URL(src);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.connect();
//            InputStream input = connection.getInputStream();
//            Bitmap myBitmap = BitmapFactory.decodeStream(input);
//            Log.e("Bitmap","returned");
//            return myBitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.e("Exception",e.getMessage());
//            return null;
//        }
//    }

}






//private class AsyncTaskRunner extends AsyncTask<String, String, String> {
//
//    private String resp;
//    ProgressDialog progressDialog;
//
//    @Override
//    protected String doInBackground(String... params) {
//        publishProgress("Sleeping..."); // Calls onProgressUpdate()
//        try {
//            int time = Integer.parseInt(params[0])*1000;
//
//            Thread.sleep(time);
//            resp = "Slept for " + params[0] + " seconds";
//            catagoryquery();
//
//            for(int i=0;i<9;i++) {
//                Log.d("Apollo", "onResponse: " + catagoryNamesFatches[i]);
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            resp = e.getMessage();
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp = e.getMessage();
//        }
//        return resp;
//    }
//
//
//    @Override
//    protected void onPostExecute(String result) {
//        // execution of result of Long time consuming operation
//        progressDialog.dismiss();
//        // finalResult.setText(result);
//    }
//
//
//    @Override
//    protected void onPreExecute() {
//        //progressDialog = ProgressDialog.show(MainActivity.this,
//        //"ProgressDialog",
//        //  "Wait for "+time.getText().toString()+ " seconds");
//    }
//
//
//    @Override
//    protected void onProgressUpdate(String... text) {
//        // finalResult.setText(text[0]);
//
//    }
//}
//}
//https://androidride.com/android-recyclerview-load-more-on-scroll-example/