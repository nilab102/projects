package com.example.newshaunt;

public class NewsfeedData {
    String header,subtitile,link,image,description;

    public NewsfeedData(String header, String subtitile, String link, String image, String description) {
        this.header = header;
        this.subtitile = subtitile;
        this.link = link;
        this.image = image;
        this.description = description;
    }

    public NewsfeedData() {

    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSubtitile() {
        return subtitile;
    }

    public void setSubtitile(String subtitile) {
        this.subtitile = subtitile;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
