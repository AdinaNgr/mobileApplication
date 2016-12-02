package com.example.adina.firstandroidapp.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    public void delete(int position){

    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Movie itemMovie = movies.get(position);
        holder.bindMovie(itemMovie);
        Log.v("adapter", "onBindViewHolder");

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                Movie movie = movies.get(position);


                // Remove the item on remove/button click
                movies.remove(position);
                notifyItemRemoved(position);

                /*
                    public final void notifyItemRangeChanged (int positionStart, int itemCount)
                        Notify any registered observers that the itemCount items starting at
                        position positionStart have changed. Equivalent to calling
                        notifyItemRangeChanged(position, itemCount, null);.

                        This is an item change event, not a structural change event. It indicates
                        that any reflection of the data in the given position range is out of date
                        and should be updated. The items in the given range retain the same identity.

                    Parameters
                        positionStart : Position of the first item that has changed
                        itemCount : Number of items that have changed
                */
                notifyItemRangeChanged(position, movies.size());

                // Show the removed item label
                Toast.makeText(context, "Removed : " + movie, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return movies.size();
    }
}
