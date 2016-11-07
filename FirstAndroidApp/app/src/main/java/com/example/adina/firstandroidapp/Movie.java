package com.example.adina.firstandroidapp;

import java.io.Serializable;

/**
 * Created by Adina on 11/5/2016.
 */

public class Movie implements Serializable {
    private String url;
    private String title;
    private String year;
    private String director;
    private String rating;

    public Movie(String url, String title, String year, String director, String rating) {
        this.url = url;
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
