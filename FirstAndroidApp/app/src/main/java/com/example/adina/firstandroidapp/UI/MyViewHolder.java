package com.example.adina.firstandroidapp.UI;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.model.Movie;

/**
 * Created by Adina on 12/1/2016.
 */

public class MyViewHolder extends RecyclerView.ViewHolder{

    private ImageView image;
    private TextView title;
    private TextView year;
    private Movie movie;
    private TextView director;
    private TextView rating;

    public MyViewHolder(View view){
        super(view);
        Log.v("adapter", "MyViewHolder");
        title = (TextView) view.findViewById(R.id.item_title);
        year = (TextView) view.findViewById(R.id.item_year);
        director = (TextView) view.findViewById(R.id.item_director);
        rating = (TextView) view.findViewById(R.id.item_year);
    }

    public void bindMovie(Movie movie) {
        Log.v("adapter", "bindPhoto");
        this.movie = movie;
        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        director.setText(movie.getDirector());
        rating.setText(movie.getRating());
    }
}


