package com.example.newshaunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    String title,description,content,imageUrl,url;
    private ImageView newsIV;
    private Button FULLNESBTN;
    private TextView titleTV,descriptionTV,contentTV;
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
        descriptionTV=findViewById(R.id.idTVdescription);
        contentTV=findViewById(R.id.idContent);
        newsIV=findViewById(R.id.idIVNews);
        FULLNESBTN=findViewById(R.id.idbutton);
        titleTV.setText(title);
        descriptionTV.setText(description);
        int str=content.length()-12;
        contentTV.setText(content.substring(0,str-1));
        Picasso.get().load(imageUrl).into(newsIV);
        FULLNESBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


    }
}