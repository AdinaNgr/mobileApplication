package com.example.adina.firstandroidapp.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.model.Movie;

import java.util.ArrayList;

/**
 * Created by Adina on 12/1/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ArrayList<Movie> movies;

    public MyAdapter(Context context, ArrayList<Movie> movies){
        this.context = context;
        this.movies = movies;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v("adapter", "onCreateViewHolder");
        View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie itemMovie = movies.get(position);
        holder.bindMovie(itemMovie);
        Log.v("adapter", "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
