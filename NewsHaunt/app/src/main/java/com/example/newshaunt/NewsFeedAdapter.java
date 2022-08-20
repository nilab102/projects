package com.example.newshaunt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.MyviewHolder> {

    Context context;
    ArrayList<NewsfeedData> list;

    public NewsFeedAdapter(Context context, ArrayList<NewsfeedData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NewsFeedAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.news_rv_item,parent,false);
       return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsFeedAdapter.MyviewHolder holder, int position) {
        NewsfeedData newsfeedData =list.get(position);
        holder.header.setText(newsfeedData.getHeader());
        holder.subtitile.setText(newsfeedData.getSubtitile());
        Picasso.get().load(newsfeedData.getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend=new Intent(context,NewsDetailActivity.class);
                intend.putExtra("title",newsfeedData.getHeader());
                intend.putExtra("content",newsfeedData.getSubtitile());
                intend.putExtra("description",newsfeedData.getDescription());
                intend.putExtra("image",newsfeedData.getImage());
                intend.putExtra("url",newsfeedData.getLink());
                context.startActivity(intend);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView header;
        TextView subtitile;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.idIVNews);
            header=itemView.findViewById(R.id.idTVNewsHeading);
            subtitile=itemView.findViewById(R.id.ididTVSubtitile);
        }
    }
}
