package com.example.adina.firstandroidapp.helper;

import com.example.adina.firstandroidapp.model.Movie;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Adina on 12/1/2016.
 */

public class RealmHelper {
    Realm realm;
    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    public void save(final Movie movie){
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm){
                Movie m = realm.copyToRealm(movie);
            }
        });
    }

    public ArrayList<Movie> retrieve(){
        ArrayList<Movie> movieTitles = new ArrayList<>();
        RealmResults<Movie> movies = realm.where(Movie.class).findAll();

        for(Movie m: movies){
            movieTitles.add(m);
        }
        return movieTitles;
    }
}
