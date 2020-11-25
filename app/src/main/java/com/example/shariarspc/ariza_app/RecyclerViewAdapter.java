package com.example.shariarspc.ariza_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static final int VIEW_TYPE_DATA = 0;
    public static final int VIEW_TYPE_PROGRESS = 1;
    public List<String> dataList;
    public RecyclerViewAdapter(List<String> dataList)
    {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewtype)
    {
        if(viewtype == VIEW_TYPE_DATA)
        {//inflates row layout.
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row,viewGroup,false);
            return new DataViewHolder(view);
            //hey
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
            ((DataViewHolder) viewHolder).textView.setText(dataList.get(i));
        }
    }
    @Override
    public int getItemViewType(int position)
    {//if data is load, returns Progressbar viewtype.
        return dataList.get(position) == "load" ? VIEW_TYPE_PROGRESS : VIEW_TYPE_DATA;
    }
    @Override
    public int getItemCount()
    {
        return dataList == null ? 0 : dataList.size();
    }
    class DataViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        public DataViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.textview);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Toast.makeText(view.getContext(), dataList.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();
                }
            });
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
}
