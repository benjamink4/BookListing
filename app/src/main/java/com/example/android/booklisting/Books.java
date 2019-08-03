package com.example.android.booklisting;

public class Books {
    private String Title;
    private String Author;
    private String url;
    private String description;
    private String ImageUrl;
    public Books(String Title, String Author,String url,String description,String ImageUrl){
        this.Author=Author;
        this.Title=Title;
        this.url=url;
        this.ImageUrl=ImageUrl;
        this.description=description;
    }


    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
