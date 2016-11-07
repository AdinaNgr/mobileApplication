package com.example.adina.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String mTitle = extras.getString("movieTitle");
            EditText title = (EditText) findViewById(R.id.et_title);
            title.setText(mTitle);
            String mDirector = extras.getString("movieDirector");
            EditText director = (EditText) findViewById(R.id.et_director);
            director.setText(mDirector);
            String mYear = extras.getString("movieYear");
            EditText year = (EditText) findViewById(R.id.et_year);
            year.setText(mYear);
            String mRating = extras.getString("movieRating");
            EditText rating = (EditText) findViewById(R.id.et_rating);
            rating.setText(mRating);
            //The key argument here must match that used in the other activity
        }

    }
}
