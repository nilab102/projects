package com.example.newshaunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategorClickInterface {
    private RecyclerView newsRV,CategoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private NewsRVAdapter newsRVAdapter;
    private CategoryRVAdapter categoryRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//6e6994d04ca94060b5da7d01cf2a1744
        //initilizing values
        newsRV=findViewById(R.id.idRvNews);
        CategoryRV=findViewById(R.id.idRVcatagories);
        loadingPB=findViewById(R.id.idPBLoading);
        articlesArrayList=new ArrayList<>();
        categoryRVModalArrayList =new ArrayList<>();
        newsRVAdapter=new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter =new CategoryRVAdapter(categoryRVModalArrayList , this,this::onCategoryClick);
        //set this addapeter to recylerview
        newsRV.setLayoutManager(new LinearLayoutManager(this));//??????????????
        newsRV.setAdapter(newsRVAdapter);
        //alreay set layout manager inside acivity main.xml dont need not do 32setlayout manager again 53:07
        CategoryRV.setAdapter(categoryRVAdapter);
        //to get the catagoris call categories
        getCategories();;
        getNews("ALL");
        newsRVAdapter.notifyDataSetChanged();

    }
    //creting the method for geting data
    //->for category
    private void getCategories(){
        //adding the data categeryModel araylist
        categoryRVModalArrayList.add(new CategoryRVModal("ALL","https://images.unsplash.com/photo-1508612761958-e931d843bdd5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=658&q=80"));
       // categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1508612761958-e931d843bdd5?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=658&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1535223289827-42f1e9919769?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=634&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science","https://images.unsplash.com/photo-1507413245164-6160d8298b31?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://images.unsplash.com/photo-1512719994953-eabf50895df7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=670&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1457369804613-52c61a468e7d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Business","https://images.unsplash.com/photo-1591696205602-2f950c417cb9?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://images.unsplash.com/photo-1616469829941-c7200edec809?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1506126613408-eca07ce68773?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=731&q=80"));
        //Data has changer so notify adapter
        categoryRVAdapter.notifyDataSetChanged();
    }
    //get news from Api new method
    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        //claer our arraylist previous data wil be cleared
        articlesArrayList.clear();
        String categoryURL = " https://newsapi.org/v2/top-headlines?language=en&category=" + category + "&apiKey=6e6994d04ca94060b5da7d01cf2a1744";//debug
     //    https://newsapi.org/v2/top-headlines?language=en&category=Science&apiKey=6e6994d04ca94060b5da7d01cf2a1744
        String url = "https://newsapi.org/v2/top-headlines?excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=6e6994d04ca94060b5da7d01cf2a1744";
        String BASE_URL="http://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitApi retrofitApi =retrofit.create(RetrofitApi.class);
        Call<NewsModal> call;
        if(category.equals("ALL")){
            call=retrofitApi.getALLNews(url);
        } else{
            call=retrofitApi.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal =response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles=newsModal.getArticles();
                for(int i=0;i<articles.size();i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to get News", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int positon) {
//we getting the posituin when the category is clicked
        //getting the category from the arraylist
    String category= categoryRVModalArrayList.get(positon).getCategory();
    getNews(category);
    }
}