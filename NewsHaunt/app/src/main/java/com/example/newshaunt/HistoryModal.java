package com.example.newshaunt;

public class HistoryModal {
    String Url;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDate() {
        return Date;
    }

    public HistoryModal(String url, String date) {
        Url = url;
        Date = date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String Date;
}
