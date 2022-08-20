package com.example.newshaunt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class History extends AppCompatActivity implements HistoryAdapter.onclickR {
    RecyclerView recyclerView;
    ArrayList<HistoryModal> list;
    String url,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView =findViewById(R.id.idRvNews);

        list=new ArrayList<HistoryModal>();
        HistoryAdapter adapter = new HistoryAdapter(list,History.this,this::onItemClick);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//??????????????
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference dref=db.getReference().child("user").child(MemoryData.getName(History.this));
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                 date =snapshot.child("date").getValue().toString().substring(0,20);
                 url =snapshot.child("url").getValue().toString();
                list.add(new HistoryModal(url,date));
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        adapter.notifyDataSetChanged();

    }

    public void onItemClick(int position) {

        Intent intent =new Intent(History.this,webviewActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}