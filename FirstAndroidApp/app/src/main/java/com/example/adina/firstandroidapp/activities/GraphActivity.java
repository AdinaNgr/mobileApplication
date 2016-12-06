package com.example.adina.firstandroidapp.activities;

/**
 * Created by Adina on 12/6/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.example.adina.firstandroidapp.R;
import com.example.adina.firstandroidapp.model.Movie;

import java.util.ArrayList;

public class GraphActivity extends Activity {
    Button  barGraph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Intent intent = getIntent();
        ArrayList<String> titles = intent.getStringArrayListExtra("AllTitles");
        ArrayList<String> ratings = intent.getStringArrayListExtra("AllRatings");

        BarGraph bar = new BarGraph(titles, ratings);
        Intent barIntent = bar.getIntent(this);
        startActivity(barIntent);


    }

}
