package com.example.adina.firstandroidapp.model;
import android.support.annotation.IdRes;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Adina on 11/5/2016.
 */

//public class Movie implements Serializable {
public class Movie extends RealmObject implements Serializable{

    @PrimaryKey
    private long id;
    private String title;
    private String year;
    private String director;
    private String rating;

    private String key;

    public Movie(){
        //Default constructor required for calls to DataSnapshot.getValue(Movie.class)
    }
    public Movie(String title, String year, String director, String rating) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }
    @Exclude
    public String getKey(){
        return key;
    }

    public void setKey(String key){
        this.key = key;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setValues(Movie updatedMovie) {
        this.title = updatedMovie.getTitle();
        this.year = updatedMovie.getYear();
        this.rating = updatedMovie.getRating();
        this.director = updatedMovie.getDirector();
    }
}

