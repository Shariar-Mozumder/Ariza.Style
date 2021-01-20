package com.example.shariarspc.ariza_app.Catagories;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shariarspc.ariza_app.R;
import com.squareup.picasso.Picasso;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private String[] catagory_images;
    private String[] catagory_names;
    private String[] catagory_ID;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, String[] catagory_images, String[] catagory_names,String[] catagory_ID) {
        this.context = context;
        this.catagory_images = catagory_images;
        this.catagory_names = catagory_names;
        this.catagory_ID=catagory_ID;
    }



    @Override
    public int getCount() {
        return catagory_names.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.catagory_sample_view,viewGroup,false);

        }

        ImageView imageView=view.findViewById(R.id.catagory_sample_image);
        TextView textView=view.findViewById(R.id.catagory_sample_name);

       // imageView.setImageResource(catagory_images[i]);
       // Picasso.get().load(topList.get(position).getImage()).into(holder.imageView);
        Picasso.get().load(catagory_images[i]).into(imageView);
        textView.setText(catagory_names[i]);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(catagory_ID[i].equals("97")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","97");
                    intent.putExtra("catagoryNAME","Brand");
                    context.startActivity(intent);

                   // context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","97"));
                }
                else if (catagory_ID[i].equals("114")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","114");
                    intent.putExtra("catagoryNAME","Comming Soon");
                    context.startActivity(intent);

                   // context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","114"));
                }
                else if (catagory_ID[i].equals("95")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","95");
                    intent.putExtra("catagoryNAME","Footwear");
                    context.startActivity(intent);

                   // context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","95"));
                }
                else if (catagory_ID[i].equals("373")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","373");
                    intent.putExtra("catagoryNAME","Home &amp; Living");
                    context.startActivity(intent);

                  //  context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","373"));
                }
                else if (catagory_ID[i].equals("452")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","452");
                    intent.putExtra("catagoryNAME","Jamdani");
                    context.startActivity(intent);

                   // context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","452"));
                }
                else if (catagory_ID[i].equals("320")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","320");
                    intent.putExtra("catagoryNAME","Junior");
                    context.startActivity(intent);

                  //  context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","320"));
                }
                else if (catagory_ID[i].equals("84")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","84");
                    intent.putExtra("catagoryNAME","Latest Catalogs");
                    context.startActivity(intent);

                   // context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","84"));
                }
                else if (catagory_ID[i].equals("79")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","79");
                    intent.putExtra("catagoryNAME","Mens");
                    context.startActivity(intent);

                  //  context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","79"));
                }
                else if (catagory_ID[i].equals("96")){
                    Intent intent=new Intent(context,Catagory_Product_List.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("catagoryID","96");
                    intent.putExtra("catagoryNAME","Women");
                    context.startActivity(intent);

                   // context.startActivity(new Intent(context,Catagory_Product_List.class).putExtra("catagoryID","96"));
                }
            }
        });

        return view;
    }
}
