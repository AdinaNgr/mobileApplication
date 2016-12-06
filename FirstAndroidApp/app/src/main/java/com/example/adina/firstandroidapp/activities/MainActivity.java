package com.example.adina.firstandroidapp.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.UI.MyAdapter;
import com.example.adina.firstandroidapp.helper.RealmHelper;
import com.example.adina.firstandroidapp.model.Movie;


import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;

public class MainActivity extends Activity {

    public static RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MyAdapter adapter;
    public static Realm realm;
    private EditText movieTitleTxt, movieYearTxt, movieDirectorTxt, movieRatingTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //RECYCLER VIEW SETUP
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //BIND
        adapter = new MyAdapter(this, movieList);
        recyclerView.setAdapter(adapter);

        //REALM SETUP
        realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        //RealmConfiguration config = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(config);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmailActivity.class));
            }
        });
        //RETRIEVE

        //Refresh
        RealmHelper helper = new RealmHelper(realm);
        movieList = helper.retrieve();
        adapter = new MyAdapter(MainActivity.this, movieList);
        recyclerView.setAdapter(adapter);

    }

    private void displayEditDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Edit movie");
        dialog.setContentView(R.layout.input_dialog);

    }
    private void displayInputDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Add movie");
        dialog.setContentView(R.layout.input_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);


        movieTitleTxt = (EditText) dialog.findViewById(R.id.movieTitle);
        movieYearTxt = (EditText) dialog.findViewById(R.id.movieYear);
        movieDirectorTxt = (EditText) dialog.findViewById(R.id.movieDirector);
        final NumberPicker nrPicker = (NumberPicker) dialog.findViewById(R.id.movieRating);
        nrPicker.setMaxValue(10);
        nrPicker.setMinValue(0);
        //nrPicker.setWrapSelectorWheel(false);

        Button saveButton = (Button) dialog.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Get DATA
                String title = movieTitleTxt.getText().toString();
                String director = movieDirectorTxt.getText().toString();
                String year = movieYearTxt.getText().toString();
                String rating =  "" + nrPicker.getValue();

                Movie movie = new Movie(title, year, director, rating);
                RealmHelper helper = new RealmHelper(realm);
                helper.save(movie);
                movieTitleTxt.setText("");

                //Refresh
                movieList = helper.retrieve();
                adapter = new MyAdapter(MainActivity.this, movieList);
                recyclerView.setAdapter(adapter);

                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void AddMovie(View view){
        Log.v("MainActivity", "Add Movie Button Pressed");
        displayInputDialog();
    }

    public void EditMovie(View view){
        Log.v("MainActivity", "Edit Movie Button Pressed");
        displayEditDialog();
    }
}

