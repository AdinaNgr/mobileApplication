package com.example.adina.firstandroidapp.UI;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.helper.RealmHelper;
import com.example.adina.firstandroidapp.model.Movie;

/**
 * Created by Adina on 12/1/2016.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView title, year, director, rating;
    private Movie movie;
    public Button removeButton;

    public MyViewHolder(View view){
        super(view);
        Log.v("ViewHolder", "MyViewHolder");
        title = (TextView) view.findViewById(R.id.item_title);
        year = (TextView) view.findViewById(R.id.item_year);
        director = (TextView) view.findViewById(R.id.item_director);
        rating = (TextView) view.findViewById(R.id.item_rating);
        removeButton = (Button) view.findViewById(R.id.removeMovie);
        removeButton.setOnClickListener(this);
    }

    public void bindMovie(Movie movie) {
        this.movie = movie;
        title.setText(movie.getTitle());
        year.setText(movie.getYear());
        director.setText(movie.getDirector());
        rating.setText(movie.getRating());
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        /*movies.remove(position);
        notifyItemRemoved(position);]
        delete(position);*/
        String stringPosition = "" + position;
        Log.v("position", stringPosition);
    }

}


