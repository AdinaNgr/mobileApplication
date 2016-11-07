package com.example.adina.firstandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("Adapter", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MyAdapter(movieList);
        recyclerView.setAdapter(adapter);
        Log.v("main", "onCreate");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });
    }
        @Override
        protected void onStart() {
            super.onStart();
            Log.v("main", "onStart");

            if (movieList.size() == 0) {
                movieList.add(new Movie("http://cms.toptenthailand.net/file/picture/20160106233919529/20160106233919529.jpg", "Dark Knight", "2008", "Christopher Nolan", "9.0"));
                movieList.add(new Movie("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Triton_moon_mosaic_Voyager_2_%28large%29.jpg/320px-Triton_moon_mosaic_Voyager_2_%28large%29.jpg", "Harry Potter and the Goblet of Fire", "2005", "Mike Newell", "7.7"));
                movieList.add(new Movie("http://t0.gstatic.com/images?q=tbn:ANd9GcQhYjUIu2o5v5u3rfJpCq5Cz0Q9WK--XdYxai_N2d0ImohPiIOp", "Titanic", "1997","James Cameron", "7.7"));
                movieList.add(new Movie("http://static.rogerebert.com/uploads/movie/movie_poster/the-salt-of-the-earth-2015/large_76pQSAYjGMYD6ssHkYCG0zDW9bE.jpg", "The salt of the earth", "2014", "Juliano Ribeiro Salgado", "8.4"));
                adapter.notifyItemInserted(movieList.size());
                Log.v("main", "added some movies: " + movieList.size());
            }
        }


}
