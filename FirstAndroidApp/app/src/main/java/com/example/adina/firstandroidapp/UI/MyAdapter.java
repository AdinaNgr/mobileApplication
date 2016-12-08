package com.example.adina.firstandroidapp.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.activities.ChartActivity;
import com.example.adina.firstandroidapp.activities.GraphActivity;
import com.example.adina.firstandroidapp.activities.MainActivity;
import com.example.adina.firstandroidapp.helper.RealmHelper;
import com.example.adina.firstandroidapp.model.Movie;

import java.lang.reflect.Array;
import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by Adina on 12/1/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Movie itemMovie = movies.get(position);
        holder.bindMovie(itemMovie);
        Log.v("adapter", "onBindViewHolder");

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                Movie movie = movies.get(position);
                String movieTitle = movie.getTitle();
                // Remove the item on remove/button click
                movies.remove(position);
                notifyItemRemoved(position);
                Realm realm = MainActivity.realm;
                RealmHelper helper = new RealmHelper(realm);
                helper.delete(position);

                notifyItemRangeChanged(position, movies.size());
                // Show the removed item label
                Toast.makeText(context, "Removed : " + movieTitle, Toast.LENGTH_SHORT).show();
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.v("movieHolder", "Clicked Edit Button!");
                final Dialog dialog = new Dialog(view.getContext());
                dialog.setTitle("Edit movie");
                dialog.setContentView(R.layout.input_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

                final EditText movieTitleTxt = (EditText) dialog.findViewById(R.id.movieTitle);
                final EditText movieYearTxt = (EditText) dialog.findViewById(R.id.movieYear);
                final EditText movieDirectorTxt = (EditText) dialog.findViewById(R.id.movieDirector);
                final NumberPicker movieRating = (NumberPicker) dialog.findViewById(R.id.movieRating);


                final Movie movie = movies.get(position);
                Log.v("title:", movie.getTitle());
                Log.v("rating:", movie.getRating());
                movieTitleTxt.setText(movie.getTitle());
                movieDirectorTxt.setText(movie.getDirector());
                movieYearTxt.setText(movie.getYear());
                movieRating.setMinValue(Integer.parseInt(movie.getRating()));
                movieRating.setMaxValue(10);
//                movieRating.setFormatter(new NumberPicker.Formatter() {
//                    @Override
//                    public String format(int i) {
//                        return movie.getRating();
//                    }
//                });
                movieRating.setValue(Integer.parseInt(movie.getRating()));

                dialog.show();

                Button saveButton = (Button) dialog.findViewById(R.id.saveButton);

                saveButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //Get DATA
                        String title = movieTitleTxt.getText().toString();
                        String director = movieDirectorTxt.getText().toString();
                        String year = movieYearTxt.getText().toString();
                        String rating =  "" + movieRating.getValue();

                        //Movie movie = new Movie(title, year, director, rating);

                        Realm realm = MainActivity.realm;
                        RealmHelper helper = new RealmHelper(realm);
                        long id = movie.getId();
                        helper.update(id, title, director, year, rating);
                        movieTitleTxt.setText("");

                        dialog.dismiss();
                        //Refresh
                        movies = helper.retrieve();
                        MyAdapter adapter = new MyAdapter(v.getContext(), movies);
                        MainActivity.recyclerView.setAdapter(adapter);
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView title, year, director, rating;
        private Movie movie;
        public Button removeButton, editButton;

        public MyViewHolder(View view){
            super(view);
            Log.v("ViewHolder", "MyViewHolder");
            title = (TextView) view.findViewById(R.id.item_title);
            year = (TextView) view.findViewById(R.id.item_year);
            director = (TextView) view.findViewById(R.id.item_director);
            rating = (TextView) view.findViewById(R.id.item_rating);
            removeButton = (Button) view.findViewById(R.id.removeMovie);
            removeButton.setOnClickListener(this);
            editButton = (Button) view.findViewById(R.id.editMovie);
            editButton.setOnClickListener(this);
            view.setOnClickListener(this);
        }

        public void bindMovie(Movie movie) {
            this.movie = movie;
            title.setText(movie.getTitle());
            year.setText(movie.getYear());
            director.setText(movie.getDirector());
            rating.setText(movie.getRating());
        }


        @Override
        public void onClick(View v) {
            Realm realm = MainActivity.realm;
            RealmHelper helper = new RealmHelper(realm);
            ArrayList<Movie> movies = helper.retrieve();
            final ArrayList<String> allTitles = new ArrayList<>();
            final ArrayList<String> allRatings = new ArrayList<>();
            for(int i=0;i<movies.size();i++){
                allTitles.add(movies.get(i).getTitle());
                allRatings.add(movies.get(i).getRating());
            }
            Log.v("holder", "Clicked!");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Context context = itemView.getContext();
//                Intent detailsIntent = new Intent(context, DetailsActivity.class);
//                detailsIntent.putExtra("movieTitle", movie.getTitle());
//                detailsIntent.putExtra("movieDirector", movie.getDirector());
//                detailsIntent.putExtra("movieRating", movie.getRating());
//               detailsIntent.putExtra("movieYear", movie.getYear());
//                context.startActivity(detailsIntent);
//                    Intent chartIntent = new Intent(context, ChartActivity.class);
//                    chartIntent.putExtra("movieTitle", movie.getTitle());
//                    chartIntent.putExtra("movieRating", movie.getRating());
                    Intent graphIntent = new Intent(context, GraphActivity.class);
                    graphIntent.putExtra("AllTitles", allTitles);
                    graphIntent.putExtra("AllRatings", allRatings);
                    context.startActivity(graphIntent);

                }
            }, 1000);
        }

    }
}
