package com.example.newshaunt;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//retrro interface data fetch korebe modal class pita hold korbe
public interface RetrofitApi {
    @GET
    //api req
    //url mainy activity dtheked dea hvbe
    Call<NewsModal> getALLNews(@Url String url);
        @GET
    Call<NewsModal>getNewsByCategory(@Url String url);
}
