package com.example.newshaunt;

import java.util.ArrayList;

public class NewsModal {
    //field staatus totalresult
//    ??arrylist crearing obj for each article for getting title url url tool for eacch item save in calass
    ///creating varaible sstatus and total result in newasModal checking from json  file
    private String totalResults;
    private String  status;
    private ArrayList<Articles> articles;//name match with json

    public NewsModal(String totalResults, String status, ArrayList<Articles> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
