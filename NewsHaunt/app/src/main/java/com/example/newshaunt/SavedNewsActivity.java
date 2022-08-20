package com.example.newshaunt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SavedNewsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;;
    NewsFeedAdapter newsFeedAdapter;
    ArrayList<NewsfeedData> list;
    NewsfeedData  newsfeedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_news);

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference dref=db.getReference().child("savenews").child(MemoryData.getName(SavedNewsActivity.this));
        recyclerView=findViewById(R.id.idRvNews);
        list=new ArrayList<NewsfeedData>();
        newsFeedAdapter=new NewsFeedAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//??????????????
        recyclerView.setAdapter(newsFeedAdapter);
        newsFeedAdapter.notifyDataSetChanged();
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                String str =snapshot.child("title").getValue().toString();
                String str1 =snapshot.child("content").getValue().toString();
                String str2 =snapshot.child("url").getValue().toString();
                String str3 =snapshot.child("urlToImage").getValue().toString();
                String str4 =snapshot.child("description").getValue().toString();
                list.add(new NewsfeedData(str,str1,str2,str3,str4));
                newsFeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
        newsFeedAdapter.notifyDataSetChanged();


    }
}