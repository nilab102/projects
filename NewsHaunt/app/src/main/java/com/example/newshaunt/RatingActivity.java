package com.example.newshaunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import org.jetbrains.annotations.NotNull;

public class RatingActivity extends AppCompatActivity {
    private ReviewInfo reviewInfo;
    private ReviewManager manager;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        activeReViewInfo();
        btn=findViewById(R.id.button2);
        btn.setOnClickListener((view)->{
            startReviewFlow();
        });

    }
    void activeReViewInfo(){
        manager= ReviewManagerFactory.create(RatingActivity.this);
       Task<ReviewInfo> request=manager.requestReviewFlow();
        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<ReviewInfo> task) {
                if(task.isSuccessful()){
                    reviewInfo=task.getResult();
                    Task<Void> flow=manager.launchReviewFlow(RatingActivity.this,reviewInfo);
                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });
                }else {
                    Toast.makeText(RatingActivity.this, "Eror ", Toast.LENGTH_SHORT).show();
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