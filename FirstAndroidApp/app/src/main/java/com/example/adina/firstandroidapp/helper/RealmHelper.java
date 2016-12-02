package com.example.adina.firstandroidapp.helper;

import android.util.Log;

import com.example.adina.firstandroidapp.model.Movie;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Adina on 12/1/2016.
 */

public class RealmHelper {
    Realm realm;
    ArrayList<Movie> movieTitles;
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
        movieTitles = new ArrayList<>();
        RealmResults<Movie> movies = realm.where(Movie.class).findAll();

        for(Movie m: movies){
            movieTitles.add(m);
        }
        return movieTitles;
    }

    public void delete(final int position){
        Log.v("Helper", "delete on position: " + position);

        realm.executeTransaction(new Realm.Transaction() {

            @Override
            public void execute(Realm realm) {
                // remove a single object
                RealmResults<Movie> movies = realm.where(Movie.class).findAll();
                Movie movie = movies.get(position);
                Log.v("Helper", "movie: " +movie.toString() );

                movie.deleteFromRealm();



                // Delete all matches
                //results.deleteAllFromRealm();
            }

        });
    }
}
