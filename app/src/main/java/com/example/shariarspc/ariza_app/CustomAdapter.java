package com.example.shariarspc.ariza_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context context;
    int[] catagory_images;
    String[] catagory_names;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int[] catagory_images, String[] catagory_names) {
        this.context = context;
        this.catagory_images = catagory_images;
        this.catagory_names = catagory_names;
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

        imageView.setImageResource(catagory_images[i]);
        textView.setText(catagory_names[i]);

        return view;
    }
}
