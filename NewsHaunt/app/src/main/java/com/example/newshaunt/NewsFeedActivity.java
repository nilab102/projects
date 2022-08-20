package com.example.newshaunt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class  NewsFeedActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;;
    NewsFeedAdapter newsFeedAdapter;
    ArrayList<NewsfeedData> list;
    NewsfeedData  newsfeedData;
    private ProgressBar loadingPB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        loadingPB=findViewById(R.id.idPBLoading);
        loadingPB.setVisibility(View.VISIBLE);
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference dref=db.getReference().child("news");
        recyclerView=findViewById(R.id.idRvNews);
        list=new ArrayList<NewsfeedData>();
        newsFeedAdapter=new NewsFeedAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//??????????????
        recyclerView.setAdapter(newsFeedAdapter);
        newsFeedAdapter.notifyDataSetChanged();
        dref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
               String str =snapshot.child("header").getValue().toString();
               String str1 =snapshot.child("subtitile").getValue().toString();
               String str2 =snapshot.child("link").getValue().toString();
               String str3 =snapshot.child("image").getValue().toString();
               String str4 =snapshot.child("description").getValue().toString();
                //titile,content,url,url to imag,ddes
               list.add(new NewsfeedData(str,str4,str2,str3,str1));
                newsFeedAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
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