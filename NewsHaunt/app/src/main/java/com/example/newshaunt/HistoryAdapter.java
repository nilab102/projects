package com.example.newshaunt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewholder>{
    ArrayList<HistoryModal> list;
    Context context;
    private onclickR onclickInterface;

    public HistoryAdapter(ArrayList<HistoryModal> list, Context context, onclickR onclickInterface) {
        this.list = list;
        this.context = context;
        this.onclickInterface = onclickInterface;
    }


    @NonNull
    @NotNull
    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_sample,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HistoryAdapter.viewholder holder, int position) {
        HistoryModal model =list.get(position);
        holder.textView1.setText(model.getUrl());
        holder.textView2.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public interface onclickR{
        void onItemClick(int position);

    }
    public class viewholder extends RecyclerView.ViewHolder {
    TextView textView1;
    TextView textView2;

    public viewholder(@NonNull View itemView) {
        super(itemView);
        textView1 = itemView.findViewById(R.id.textView1);
        textView2 = itemView.findViewById(R.id.textView2);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onclickInterface.onItemClick(getAdapterPosition());
            }
        });
    }
}
}



