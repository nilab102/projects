package com.example.newshaunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import org.jetbrains.annotations.NotNull;

import pl.droidsonroids.gif.GifImageView;

public class HomeBar extends AppCompatActivity {
    private ReviewInfo reviewInfo;
    private ReviewManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_bar);
        LinearLayout linearLayout1 = findViewById(R.id.lineralayout1);
        LinearLayout linearLayout2 = findViewById(R.id.lineralayout2);
        LinearLayout linearLayout3 = findViewById(R.id.lineralayout3);
        LinearLayout linearLayout4 = findViewById(R.id.lineralayout4);
        GifImageView gif1= findViewById(R.id.gifImageView);
        GifImageView gif2= findViewById(R.id.gifImageView2);
        GifImageView gif3= findViewById(R.id.gifImageView3);
        GifImageView gif4= findViewById(R.id.gifImageView4);
        linearLayout1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,MainActivity.class));
            }
        });
        linearLayout4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,NewsFeedActivity.class));
            }
        });
        linearLayout3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,SavedNewsActivity.class));
            }
        });  linearLayout2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,History.class));
            }
        });
        gif1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,MainActivity.class));
            }
        });
        gif2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,SavedNewsActivity.class));
            }
        });
        gif3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,History.class));
            }
        });
        gif4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeBar.this,NewsFeedActivity.class));
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    void activeReViewInfo(){
        manager= ReviewManagerFactory.create(HomeBar.this);
        Task<ReviewInfo> request=manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<ReviewInfo> task) {
                if(task.isSuccessful()){
                    reviewInfo=task.getResult();
                    Task<Void> flow=manager.launchReviewFlow(HomeBar.this,reviewInfo);
                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });
                }else {
                    Toast.makeText(HomeBar.this, "Error ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void startReviewFlow(){
        if(reviewInfo!=null){
            Task<Void> flow =manager.launchReviewFlow(this,reviewInfo);
            flow.addOnCompleteListener(task -> {
                Toast.makeText(this, "Reiview Is Complicated", Toast.LENGTH_SHORT).show();
            });
        }
    }
}