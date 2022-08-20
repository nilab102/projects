package com.example.newshaunt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;

public class NewsDetailActivity extends AppCompatActivity {
    String title,description,content,imageUrl,url;
    private ImageView newsIV;
    private Button FULLNESBTN;
    private TextView titleTV,descriptionTV,contentTV,sourceTV;
    private ImageButton imageButton;
    int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=getIntent().getStringExtra("title");
        description=getIntent().getStringExtra("description");
        content=getIntent().getStringExtra("content");
        imageUrl=getIntent().getStringExtra("image");
        url=getIntent().getStringExtra("url");
        titleTV=findViewById(R.id.idTVTitle);
        sourceTV=findViewById(R.id.source);
        descriptionTV=findViewById(R.id.idTVdescription);
        contentTV=findViewById(R.id.idContent);
        newsIV=findViewById(R.id.idIVNews);
        FULLNESBTN=findViewById(R.id.idbutton);
        titleTV.setText(title);
        descriptionTV.setText(description);
        sourceTV.setText("Source : "+url.substring(url.indexOf(".")+1,url.lastIndexOf(".com")).toUpperCase());
        int str=content.length()-12;
        contentTV.setText(content.substring(0,str-1));
        Picasso.get().load(imageUrl).into(newsIV);

        imageButton=findViewById(R.id.savebutton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            Articles ac =new Articles(title,description,imageUrl,url,content);
            @Override
            public void onClick(View v) {
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference dref=db.getReference().child("savenews").child(MemoryData.getName(NewsDetailActivity.this));
                dref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

                        String str =snapshot.child("url").getValue().toString();
                        if(str.equals(url)){
                            flag=0;
                        }
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

                if(flag==1){
                    dref.push().setValue(ac);
                    new AlertDialog.Builder(NewsDetailActivity.this)
                            .setTitle("Notification ")
                            .setMessage("NEWS SAVED").setPositiveButton(android.R.string.ok, null).show();

                }else{
                    new AlertDialog.Builder(NewsDetailActivity.this)
                            .setTitle("Notification ")
                            .setMessage("Already SAVED").setPositiveButton(android.R.string.ok, null).show();
                }





            }
        });

        Date currentTime = Calendar.getInstance().getTime();
        String   time = currentTime.toString() ;


        FULLNESBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference dref=db.getReference().child("user").child(MemoryData.getName(NewsDetailActivity.this));
                dref.push().setValue(new HistoryModal(url,time));



                Intent intent =new Intent(NewsDetailActivity.this,webviewActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });




    }
}