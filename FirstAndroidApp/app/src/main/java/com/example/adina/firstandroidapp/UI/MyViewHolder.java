package com.example.adina.firstandroidapp.UI;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.example.adina.firstandroidapp.activities.*;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.helper.RealmHelper;
import com.example.adina.firstandroidapp.model.Movie;

/**
 * Created by Adina on 12/1/2016.
 */
//
//public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
//{
//    private TextView title, year, director, rating;
//    private Movie movie;
//    public Button removeButton, editButton;
//
//    public MyViewHolder(View view){
//        super(view);
//        Log.v("ViewHolder", "MyViewHolder");
//        title = (TextView) view.findViewById(R.id.item_title);
//        year = (TextView) view.findViewById(R.id.item_year);
//        director = (TextView) view.findViewById(R.id.item_director);
//        rating = (TextView) view.findViewById(R.id.item_rating);
//        removeButton = (Button) view.findViewById(R.id.removeMovie);
//        removeButton.setOnClickListener(this);
//        editButton = (Button) view.findViewById(R.id.editMovie);
//        editButton.setOnClickListener(this);
//    }
//
//    public void bindMovie(Movie movie) {
//        this.movie = movie;
//        title.setText(movie.getTitle());
//        year.setText(movie.getYear());
//        director.setText(movie.getDirector());
//        rating.setText(movie.getRating());
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        Log.v("holder", "Clicked!");
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Context context = itemView.getContext();
////                Intent detailsIntent = new Intent(context, DetailsActivity.class);
////                detailsIntent.putExtra("movieTitle", movie.getTitle());
////                detailsIntent.putExtra("movieDirector", movie.getDirector());
////                detailsIntent.putExtra("movieRating", movie.getRating());
////               detailsIntent.putExtra("movieYear", movie.getYear());
////                context.startActivity(detailsIntent);
//                Intent chartIntent = new Intent(context, ChartActivity.class);
//                chartIntent.putExtra("movieTitle", movie.getTitle());
//                chartIntent.putExtra("movieRating", movie.getRating());
//                context.startActivity(chartIntent);
//
//            }
//        }, 1000);
//    }
//
//}


