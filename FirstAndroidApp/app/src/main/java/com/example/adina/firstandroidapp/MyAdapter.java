package com.example.adina.firstandroidapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Adina on 11/5/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MovieHolder> {

    private ArrayList<Movie> myMovies;

    public MyAdapter(ArrayList<Movie> myMovies) {
        Log.v("adapter", "MyAdapter");
        this.myMovies = myMovies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("adapter", "onCreateViewHolder b");
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
       // inflatedView.setOnClickListener(new myClickListener());
        Log.v("adapter", "onCreateViewHolder");
        return new MovieHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie itemMovie = myMovies.get(position);
        holder.bindMovie(itemMovie);
        Log.v("adapter", "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        Log.v("adapter", "getItemCount: " + myMovies.size());
        return myMovies.size();
    }



    public static class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String PHOTO_KEY = "PHOTO";

        private ImageView image;
        private TextView title;
        private TextView year;
        private Movie movie;
        private TextView director;
        private TextView rating;


        private ProgressBar spinner;

        public MovieHolder(View view) {
            super(view);
            Log.v("adapter", "PhotoHolder");

            image = (ImageView) view.findViewById(R.id.item_image);
            title = (TextView) view.findViewById(R.id.item_title);
            year = (TextView) view.findViewById(R.id.item_year);
            director = (TextView) view.findViewById(R.id.item_director);
            rating = (TextView) view.findViewById(R.id.item_year);
            view.setOnClickListener(this);

            //spinner = (ProgressBar) view.findViewById(R.id.progressBar);
            //spinner.setVisibility(View.GONE);

        }

        public void bindMovie(Movie movie) {
            Log.v("adapter", "bindPhoto");
            this.movie = movie;
            Log.v("adapter", "load url: " + movie.getUrl());
            Picasso.with(image.getContext()).
                    load(movie.getUrl()).into(image);
            title.setText(movie.getTitle());
            year.setText(movie.getYear());
            director.setText(movie.getDirector());
            rating.setText(movie.getRating());
        }

        @Override
        public void onClick(View v) {
            Log.v("photoHolder", "Clicked!");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Context context = itemView.getContext();
                    Intent detailsIntent = new Intent(context, DetailsActivity.class);
                    detailsIntent.putExtra("movieTitle", movie.getTitle());
                    detailsIntent.putExtra("movieDirector", movie.getDirector());
                    detailsIntent.putExtra("movieRating", movie.getRating());
                    detailsIntent.putExtra("movieYear", movie.getYear());
                    context.startActivity(detailsIntent);
                }
            }, 1000);
        }
    }
}


