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

//adapter cal;ss to set the items and contents in recylerview  fetched from modal calss for both news and catagory;
public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;// articles vitore data thake
    private Context context;

    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);//custom layout adapter e jog korsi
        return new NewsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsRVAdapter.ViewHolder holder, int position) {
        //takuing data from articles arraytlisyt from above and set it into viewholder
        //binding data
        //picasoo for setting the image
        //load method for loading the url of the image
        //inside view news view (where to lad the image )-> done by into function
        //onclick lisetternr ffor item and open new activity
        //for this making new acivity NewsdetailActivity
        //adding onclicklisterner fir item of news
        //here from one pageb to another page we will usde intend

        Articles articles =articlesArrayList.get(position);
        holder.subtitleTv.setText(articles.getDescription());
        holder.titleTv.setText(articles.getTitle());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend=new Intent(context,NewsDetailActivity.class);
                intend.putExtra("title",articles.getTitle());
                intend.putExtra("content",articles.getContent());
                intend.putExtra("description",articles.getDescription());
                intend.putExtra("image",articles.getUrlToImage());
                intend.putExtra("url",articles.getUrl());
                context.startActivity(intend);

            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        //create varaqible for each view ->image view two text vierw
        private TextView titleTv,subtitleTv;
        private ImageView newsIV;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);//top thaka lagbe
            titleTv=itemView.findViewById(R.id.idTVNewsHeading);
            subtitleTv=itemView.findViewById(R.id.ididTVSubtitile);
            newsIV=itemView.findViewById(R.id.idIVNews);


        }
    }
}
