package com.example.shariarspc.ariza_app.Catagories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.BrandCatagoryQuery;
import com.example.ComingSoonCatagoryQuery;
import com.example.FootwareCatagoryQuery;
import com.example.HomeLivingQuery;
import com.example.JamdaniQuery;
import com.example.JuniorQuery;
import com.example.LatestCatalogQuery;
import com.example.MensQuery;
import com.example.WomensQuery;
import com.example.shariarspc.ariza_app.IndividualProductDetails.ProductDetailsActivity;
import com.example.shariarspc.ariza_app.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class Catagory_Product_List extends AppCompatActivity implements RecyclerViewAdapterForCatagoryProduct.OnNoteListenerCatagory {

    ApolloClient apolloClient;
    Handler handler7=new Handler();
    Handler handler8=new Handler();
    ProgressDialog progressDialog;
    TextView catagoryname,noProduct;

    String catagoryID,catagoryName;

    RecyclerView catagoryProductRecyclerView;
    CatagoryWiseProductsModel catagoryWiseProductsModel;
    RecyclerViewAdapterForCatagoryProduct recyclerViewAdapterForCatagoryProduct;
    GridRecyclerViewAdapterforCatagoryProduct gridRecyclerViewAdapterforCatagoryProduct;


    @Nullable
    static String[] catagoryProductsNameFatches;
    @Nullable static String[] catagoryProductsIDFatches;
    @Nullable static String[] catagoryProductsimageFatches;
    @Nullable static String[] catagoryProductsDescriptionFatches;
    @Nullable static String[] catagoryProductsPriceFatches;
    @Nullable static String[][] catagoryProductsimagesFatches;
    @Nullable static String[] catagoryProductsdefault;
    @Nullable static String[] catagoryProductsmetadescriptionFatches;
    @Nullable static String[] catagoryProductsstockstatusFatches;
    @Nullable static String[] catagoryProductsquantityFatches;
    @Nullable static String[] catagoryProductsdescriptionFatches;
    @Nullable static String[] catagoryProductsweightFatches;
    @Nullable static String[] catagoryProductslengthFatches;
    @Nullable static String[] catagoryProductswidthFatches;
    @Nullable static String[] catagoryProductsheightFatches;

    ArrayList<CatagoryWiseProductsModel> cat_datalist=new ArrayList<>();

    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;

    private boolean isLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory__product__list);
        catagoryname=findViewById(R.id.catagory_name);
       // noProduct=findViewById(R.id.noProductFound);

        apolloClient = ApolloClient.builder()
                .serverUrl("https://ariza.style/index.php?route=api/graphql/usage")
                .build();

        progressDialog=new ProgressDialog(Catagory_Product_List.this);

        catagoryProductRecyclerView=findViewById(R.id.catagoryproductListID);

        catagoryID=getIntent().getStringExtra("catagoryID");
        catagoryName=getIntent().getStringExtra("catagoryNAME");
       // Toast.makeText(this, catagoryID, Toast.LENGTH_SHORT).show();
        catagoryname.setText(catagoryName);

        listshows();

    }

    private void listshows() {
        if ("97".equals(catagoryID)) {
            apolloClient.query(new BrandCatagoryQuery()).enqueue(new ApolloCall.Callback<BrandCatagoryQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<BrandCatagoryQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 1500);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                   // noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("114".equals(catagoryID)) {
            apolloClient.query(new ComingSoonCatagoryQuery()).enqueue(new ApolloCall.Callback<ComingSoonCatagoryQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<ComingSoonCatagoryQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 1500);

                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                   // noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("95".equals(catagoryID)) {
            apolloClient.query(new FootwareCatagoryQuery()).enqueue(new ApolloCall.Callback<FootwareCatagoryQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<FootwareCatagoryQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 1500);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                   // noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("373".equals(catagoryID)) {
            apolloClient.query(new HomeLivingQuery()).enqueue(new ApolloCall.Callback<HomeLivingQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<HomeLivingQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 1500);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                    //noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("452".equals(catagoryID)) {
            apolloClient.query(new JamdaniQuery()).enqueue(new ApolloCall.Callback<JamdaniQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<JamdaniQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 1500);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                    //noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("320".equals(catagoryID)) {
            apolloClient.query(new JuniorQuery()).enqueue(new ApolloCall.Callback<JuniorQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<JuniorQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                             //   Toast.makeText(Catagory_Product_List.this, "baaaal: "+i+" "+catagoryProductsNameFatches[i], Toast.LENGTH_SHORT).show();
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 1500);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                   // noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("84".equals(catagoryID)) {
            apolloClient.query(new LatestCatalogQuery()).enqueue(new ApolloCall.Callback<LatestCatalogQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<LatestCatalogQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           // assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 1500);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                   // noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("79".equals(catagoryID)) {
            apolloClient.query(new MensQuery()).enqueue(new ApolloCall.Callback<MensQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<MensQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //assert response.data() != null;
                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 10000);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                   // noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else if ("96".equals(catagoryID)) {
            apolloClient.query(new WomensQuery()).enqueue(new ApolloCall.Callback<WomensQuery.Data>() {
                @Override
                public void onResponse(@NotNull Response<WomensQuery.Data> response) {
                    handler7.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            catagoryProductsNameFatches = new String[response.data().category().products().size()];
                            catagoryProductsimageFatches = new String[response.data().category().products().size()];
                            catagoryProductsPriceFatches = new String[response.data().category().products().size()];
                            catagoryProductsDescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsIDFatches = new String[response.data().category().products().size()];
                            catagoryProductsimagesFatches = new String[response.data().category().products().size()][10];
                            catagoryProductsmetadescriptionFatches = new String[response.data().category().products().size()];
                            catagoryProductsstockstatusFatches = new String[response.data().category().products().size()];
                            catagoryProductsquantityFatches = new String[response.data().category().products().size()];
                            // catagoryProductsDescriptionFatches=new String[Objects.requireNonNull(response.data().category()).products().size()];
                            catagoryProductsweightFatches = new String[response.data().category().products().size()];
                            catagoryProductslengthFatches = new String[response.data().category().products().size()];
                            catagoryProductswidthFatches = new String[response.data().category().products().size()];
                            catagoryProductsheightFatches = new String[response.data().category().products().size()];

                            for (int i = 0; i < response.data().category().products().size(); i++) {
                                String name = response.data().category().products().get(i).name();
                                String image = response.data().category().products().get(i).image();
                                String price = response.data().category().products().get(i).price();
                                String description = response.data().category().products().get(i).description();
                                String id = response.data().category().products().get(i).product_id();
                                int size = response.data().category().products().get(i).images().size();
                                String[] images = new String[size];
                                for (int i1 = 0; i1 < size; i1++) {
                                    images[i1] = response.data().category().products().get(i).images().get(i1).image();
                                }
                                String metadescription = response.data().category().products().get(i).meta_description();
                                String stock = response.data().category().products().get(i).stock_status();
                                String quantity = response.data().category().products().get(i).quantity();
                                String weight = response.data().category().products().get(i).weight();
                                String length = response.data().category().products().get(i).length().toString();
                                String width = response.data().category().products().get(i).width().toString();
                                String height = response.data().category().products().get(i).height().toString();


                                catagoryProductsNameFatches[i] = name;
                                catagoryProductsimageFatches[i] = "https://ariza.style/image/"+image;
                                catagoryProductsPriceFatches[i] = price;
                                catagoryProductsDescriptionFatches[i] = description;
                                catagoryProductsIDFatches[i] = id;
                                catagoryProductsimagesFatches[i] = images;
                                catagoryProductsmetadescriptionFatches[i] = metadescription;
                                catagoryProductsstockstatusFatches[i] = stock;
                                catagoryProductsquantityFatches[i] = quantity;
                                catagoryProductsweightFatches[i] = weight;
                                catagoryProductslengthFatches[i] = length;
                                catagoryProductswidthFatches[i] = width;
                                catagoryProductsheightFatches[i] = height;
                            }

                            loadlist();
                            productlistshow();
                        }
                    }, 10000);
                }

                @Override
                public void onFailure(@NotNull ApolloException e) {
                    //noProduct.setVisibility(View.VISIBLE);
                }
            });
        } else {
            throw new IllegalStateException("Unexpected value: " + catagoryID);
        }


    }

    private void productlistshow() {
        linearLayoutManager=new LinearLayoutManager(Catagory_Product_List.this);
        catagoryProductRecyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapterForCatagoryProduct=new RecyclerViewAdapterForCatagoryProduct(Catagory_Product_List.this,cat_datalist,this);
        catagoryProductRecyclerView.setAdapter(recyclerViewAdapterForCatagoryProduct);
        addscrollListenerList();
    }

    private void addscrollListenerList() {
        catagoryProductRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!isLoading){
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition()==cat_datalist.size()-1){
                        loadmoreList();
                        isLoading=true;
                    }
                }
            }
        });
    }

    private void loadmoreList() {
        handler8.post(new Runnable() {
            @Override
            public void run() {
               CatagoryWiseProductsModel catagoryWiseProductsModel=new CatagoryWiseProductsModel("default","load","default","default","default",catagoryProductsdefault,"default","default","default","default","default","default","default");
               cat_datalist.add(catagoryWiseProductsModel);
               recyclerViewAdapterForCatagoryProduct.notifyItemInserted(cat_datalist.size()-1);
            }
        });
        handler8.postDelayed(new Runnable() {
            @Override
            public void run() {
                cat_datalist.remove(cat_datalist.size()-1);
                int listsize=cat_datalist.size();
                recyclerViewAdapterForCatagoryProduct.notifyItemRemoved(listsize);

                int nextLimit=listsize+10;
                for(int itemCount=listsize;itemCount<nextLimit;itemCount++){
                    try{
                        catagoryWiseProductsModel=new CatagoryWiseProductsModel(catagoryProductsimageFatches != null ? catagoryProductsimageFatches[itemCount] : "", catagoryProductsNameFatches != null ? catagoryProductsNameFatches[itemCount] : "", catagoryProductsPriceFatches != null ? catagoryProductsPriceFatches[itemCount] : "", catagoryProductsdescriptionFatches != null ? catagoryProductsdescriptionFatches[itemCount] : "", catagoryProductsIDFatches != null ? catagoryProductsIDFatches[itemCount] : "", catagoryProductsimagesFatches != null ? catagoryProductsimagesFatches[itemCount] : new String[0], catagoryProductsmetadescriptionFatches != null ? catagoryProductsmetadescriptionFatches[itemCount] : "", catagoryProductsstockstatusFatches != null ? catagoryProductsstockstatusFatches[itemCount] : "", catagoryProductsquantityFatches != null ? catagoryProductsquantityFatches[itemCount] : "", catagoryProductsweightFatches != null ? catagoryProductsweightFatches[itemCount] : "", catagoryProductslengthFatches != null ? catagoryProductslengthFatches[itemCount] : "", catagoryProductswidthFatches != null ? catagoryProductswidthFatches[itemCount] : "", catagoryProductsheightFatches != null ? catagoryProductsheightFatches[itemCount] : "");
                        cat_datalist.add(catagoryWiseProductsModel);
                    }catch (ArrayIndexOutOfBoundsException e){
                       // noProduct.setVisibility(View.VISIBLE);
                    }


                }

                recyclerViewAdapterForCatagoryProduct.notifyDataSetChanged();
                isLoading=false;
            }
        },5000);
    }

    @Nullable
    private void loadlist() {

        for (int i=0;i<10;i++){
//            Toast.makeText(this,"Image:"+catagoryProductsimageFatches[i] , Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsNameFatches[i], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsPriceFatches[i], Toast.LENGTH_SHORT).show();
////      Toast.makeText(this, catagoryProductsdescriptionFatches[0], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsIDFatches[i], Toast.LENGTH_SHORT).show();
////            Toast.makeText(this, i+catagoryProductsimagesFatches[i][0], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsmetadescriptionFatches[i], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsstockstatusFatches[i], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsquantityFatches[i], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsweightFatches[i], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductslengthFatches[i], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductswidthFatches[i], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, i+catagoryProductsheightFatches[i], Toast.LENGTH_SHORT).show();
           // if(catagoryProductsimageFatches[itemCount]!=null&&catagoryProductsNameFatches[itemCount]!=null&&catagoryProductsPriceFatches[itemCount]!=null&&catagoryProductsdescriptionFatches[itemCount]!=null&&catagoryProductsIDFatches[itemCount]!=null&&catagoryProductsimagesFatches[itemCount]!=null&&catagoryProductsmetadescriptionFatches[itemCount]!=null&&catagoryProductsstockstatusFatches[itemCount]!=null&&catagoryProductsquantityFatches[itemCount]!=null&&catagoryProductsweightFatches[itemCount]!=null&&catagoryProductslengthFatches[itemCount]!=null&&catagoryProductswidthFatches[itemCount]!=null&&catagoryProductsheightFatches[itemCount]!=null){

            try {
                catagoryWiseProductsModel=new CatagoryWiseProductsModel( catagoryProductsimageFatches[i],catagoryProductsNameFatches[i],catagoryProductsPriceFatches[i], catagoryProductsdescriptionFatches != null ? catagoryProductsdescriptionFatches[i] : "",catagoryProductsIDFatches[i], catagoryProductsimagesFatches != null ? catagoryProductsimagesFatches[i] : new String[0] ,catagoryProductsmetadescriptionFatches[i],catagoryProductsstockstatusFatches[i],catagoryProductsquantityFatches[i],catagoryProductsweightFatches[i],catagoryProductslengthFatches[i],catagoryProductswidthFatches[i],catagoryProductsheightFatches[i]);
                cat_datalist.add(catagoryWiseProductsModel);
            }catch (ArrayIndexOutOfBoundsException e){
               // noProduct.setVisibility(View.VISIBLE);
            }


           // }

        }


    }

    @Override
    public void onNoteClickCatagory(int position) {
        Intent intent=new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("productID",catagoryProductsIDFatches[position]);
        intent.putExtra("images",catagoryProductsimagesFatches[position]);
        intent.putExtra("metadescription",catagoryProductsmetadescriptionFatches[position]);
        intent.putExtra("price",catagoryProductsPriceFatches[position]);
        intent.putExtra("name",catagoryProductsNameFatches[position]);
        intent.putExtra("stockstatus",catagoryProductsstockstatusFatches[position]);
        intent.putExtra("quantity",catagoryProductsquantityFatches[position]);
        intent.putExtra("description",catagoryProductsDescriptionFatches[position]);
        intent.putExtra("weight",catagoryProductsweightFatches[position]);
        intent.putExtra("length",catagoryProductslengthFatches[position]);
        intent.putExtra("width",catagoryProductswidthFatches[position]);
        intent.putExtra("height",catagoryProductsheightFatches[position]);
        intent.putExtra("image",catagoryProductsimageFatches[position]);
        intent.putExtra("counter","4");
        this.startActivity(intent);
    }
}
