package com.example.shariarspc.ariza_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SliderLayout sliderLayout;

    Button gridBtn,listBtn;

    Handler handler = new Handler();

    RecyclerViewAdapter recyclerViewAdapter;
    GridRecyclerViewAdapter gridRecyclerViewAdapter;

    GridView gridView;
    RecyclerView recyclerView_top_sell,recyclerView_hot_sell,recyclerView_latest_products,gridview_latest_product;
    TopsellAdapter topsellAdapter;
    HotsellAdapter hotsellAdapter;
    LatestProductsAdapter latestProductsAdapter;
    LatestProductsModel latestProductsModel;


    int[] catagory_imgs={R.drawable.accessories,R.drawable.beauty,R.drawable.footware,
    R.drawable.selwar_kameez,R.drawable.sharee,R.drawable.western,
    R.drawable.pant,R.drawable.men_care,R.drawable.seemore};


    String[] catagory_names;

    ArrayList<TopsellModel> topList;
    ArrayList<HotsellModel> hotList;
   // ArrayList<LatestProductsModel> latestList;

   // ArrayList<LatestProductsModel> dataList;
   ArrayList<String> dataList = new ArrayList<>();
    LinearLayoutManager layoutManager;
    GridLayoutManager gridlayoutManager;



    private boolean isLoading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridBtn=findViewById(R.id.gridbtnID);
        listBtn=findViewById(R.id.listbtnID);

       // dataList = new ArrayList<>();



        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :

        setSliderViews();

        recyclerView_top_sell=findViewById(R.id.top_sell_recyclerView);
        recyclerView_hot_sell=findViewById(R.id.hot_sell_recyclerView);
        recyclerView_latest_products=findViewById(R.id.latest_products_recyclerView);




        catagory_names=getResources().getStringArray(R.array.Catagory_names);


        gridView=findViewById(R.id.catagorygrid_id);
        CustomAdapter adapter=new CustomAdapter(this, catagory_imgs, catagory_names);
        gridView.setAdapter(adapter);







        toplistInIt();
        hotlistInIt();

        latestProductListShow();





        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gridBtn.setVisibility(View.VISIBLE);
                latestProductListShow();
                listBtn.setVisibility(View.INVISIBLE);
            }
        });
        gridBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBtn.setVisibility(View.VISIBLE);

                latestProductGridshow();
                gridBtn.setVisibility(View.INVISIBLE);
            }
        });




       // latestRecyclerListInIT();

    }

   private void latestProductListShow(){
       loadLIST();
       layoutManager = new LinearLayoutManager(MainActivity.this);
       //layoutManager = new GridLayoutManager(MainActivity.this,2);
       recyclerView_latest_products.setLayoutManager(layoutManager);
       recyclerViewAdapter = new RecyclerViewAdapter(dataList);
       recyclerView_latest_products.setAdapter(recyclerViewAdapter);
       addScrollerListenerLIST();
   }

   private void latestProductGridshow(){
       loadLIST();
       //layoutManager = new LinearLayoutManager(MainActivity.this);
       gridlayoutManager = new GridLayoutManager(MainActivity.this,2);
       recyclerView_latest_products.setLayoutManager(gridlayoutManager);
       recyclerViewAdapter = new RecyclerViewAdapter(dataList);
       recyclerView_latest_products.setAdapter(recyclerViewAdapter);
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
        topList=new ArrayList<>();
        for(int i=0;i<catagory_names.length;i++){
            TopsellModel topsellModel=new TopsellModel(catagory_imgs[i],catagory_names[i]);
            topList.add(topsellModel);
        }

        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_top_sell.setLayoutManager(layoutManager);
        recyclerView_top_sell.setItemAnimator(new DefaultItemAnimator());

        topsellAdapter =new TopsellAdapter(MainActivity.this,topList);
        recyclerView_top_sell.setAdapter(topsellAdapter);
    }

    private void hotlistInIt() {
        hotList=new ArrayList<>();
        for(int i=0;i<catagory_names.length;i++){
            HotsellModel hotsellModel=new HotsellModel(catagory_imgs[i],catagory_names[i]);
            hotList.add(hotsellModel);
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView_hot_sell.setLayoutManager(layoutManager);
        recyclerView_hot_sell.setItemAnimator(new DefaultItemAnimator());

        hotsellAdapter=new HotsellAdapter(MainActivity.this,hotList);
        recyclerView_hot_sell.setAdapter(hotsellAdapter);
    }


    private void addScrollerListenerLIST()
    {
        //attaches scrollListener with RecyclerView
        recyclerView_latest_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                if(!isLoading)
                {
                    //findLastCompletelyVisibleItemPostition() returns position of last fully visible view.
                    //It checks, fully visible view is the last one.
                    if(layoutManager.findLastCompletelyVisibleItemPosition() == dataList.size() - 1)
                    {
                        loadMoreLIST();
                        isLoading = true;
                    }
                }
            }
        });
    }
    private void loadMoreLIST()
    {
        //notify adapter using Handler.post() or RecyclerView.post()
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                dataList.add("load");
                recyclerViewAdapter.notifyItemInserted(dataList.size() - 1);
            }
        });
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //removes "load".
                dataList.remove(dataList.size() - 1);
                int listSize = dataList.size();
                recyclerViewAdapter.notifyItemRemoved(listSize);
                //sets next limit
                int nextLimit = listSize + 10;
                for(int itemCount = listSize; itemCount < nextLimit;  itemCount++)
                {
                    dataList.add("Item No "+ itemCount);
                }
                recyclerViewAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        },2500);
    }


    private void loadLIST()
    {
        for(int i=0; i<10; i++)
        {
            dataList.add("Item No: "+i);
        }
    }




}
