package com.example.newshaunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {
    private ArrayList<CategoryRVModal> categoryRVModals;///?/????????????????
    private Context context;
    private CategorClickInterface categorClickInterface;///?????????????????

    public CategoryRVAdapter(ArrayList<CategoryRVModal> categoryRVModals, Context context, CategorClickInterface categorClickInterface) {
        this.categoryRVModals = categoryRVModals;
        this.context = context;
        this.categorClickInterface = categorClickInterface;
    }

    @NonNull
    @Override
    public CategoryRVAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catagories_rv_items,parent,false);//loading catagories
        return new CategoryRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdapter.ViewHolder holder, int position) {
        CategoryRVModal categoryRVModal =categoryRVModals.get(position);
        holder.categoryTv.setText(categoryRVModal.getCategory());
        Picasso.get().load(categoryRVModal.getCategoryImageUrl()).into(holder.categoryIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categorClickInterface.onCategoryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryRVModals.size();
    }
    //for clciking on categories common click listerenr
    //after clicking categoru changing data inside our recylerview
    //upadating this data main acitiviy
    //use this interace later to update the changing data

    public interface CategorClickInterface{
        void onCategoryClick(int positon);//this position will be used to get the data from the arraylist

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryTv;
        private ImageView categoryIv;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            categoryTv=itemView.findViewById(R.id.idRTVcatagory);
            categoryIv=itemView.findViewById(R.id.idIVCatagory);
        }
    }
}
